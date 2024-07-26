package br.com.lojavitual.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.lojavitual.DTO.CepDTO;
import br.com.lojavitual.enums.TipoPessoa;
import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.Endereco;
import br.com.lojavitual.model.PessoaFisica;
import br.com.lojavitual.model.PessoaJuridica;
import br.com.lojavitual.model.Usuario;
import br.com.lojavitual.repository.EnderecoRepository;
import br.com.lojavitual.repository.PessoaFisicaRepository;
import br.com.lojavitual.repository.PessoaJuridicaRepository;
import br.com.lojavitual.repository.UsuarioRepository;
import br.com.lojavitual.util.Utilitaria;
import br.com.lojavitual.util.ValidaCPF;

@Service
public class PessoaFisicaService {

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;

	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	ServiceSendEmail serviceSendEmail;

	@Autowired
	Utilitaria utilitaria;

	@Autowired
	EnderecoRepository enderecoRepository;

	public List<PessoaFisica> buscarTodasPessoaFisica() {
		List<PessoaFisica> lista = (List<PessoaFisica>) pessoaFisicaRepository.findAll();
		return lista;
	}

	public List<PessoaFisica> buscarPessoaFisicaPorCpf(String cpf) {
		List<PessoaFisica> lista = (List<PessoaFisica>) pessoaFisicaRepository.listaCpfPessoaFisica(cpf);
		return lista;
	}

	public List<PessoaFisica> buscarPessoaFisicaPorNome(String nome) {
		List<PessoaFisica> lista = (List<PessoaFisica>) pessoaFisicaRepository.listaNomePessoaFisica(nome);
		return lista;
	}

	public PessoaFisica buscarPessoaFisicaPorId(Long id) {
		return pessoaFisicaRepository.findById(id).get();

	}

	public PessoaFisica salvarPessoaFisica(PessoaFisica pessoaFisica) throws ExceptionMentoriaJava {
		if (pessoaFisica == null) {
			throw new ExceptionMentoriaJava("Não foi passado nenhum registro");
		}
		if (pessoaFisica.getId() == null && existeCpfPessoaFisica(pessoaFisica) != null) {
			throw new ExceptionMentoriaJava("Já existe pessoa cadastrada com esses cpf :" + pessoaFisica.getCpf());
		}
		if (pessoaFisica.getId() == null ) {
			pessoaFisica.setTipoPessoa(TipoPessoa.PESSOAFISICA.name());
		}
		if (!ValidaCPF.isCPF(pessoaFisica.getCpf())) {
			throw new ExceptionMentoriaJava("CPF invlido");
		}
		PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.existeCnpj("4546544464565471");
		if (pessoaFisica.getId() == null || pessoaFisica.getId() <= 0) {
			for (int i = 0; i < pessoaFisica.getEnderecos().size(); i++) {
				CepDTO cepDTO = utilitaria.consultaCep(pessoaFisica.getEnderecos().get(i).getCep());
				pessoaFisica.getEnderecos().get(i).setBairro(cepDTO.getBairro());
				pessoaFisica.getEnderecos().get(i).setCep(cepDTO.getCep());
				pessoaFisica.getEnderecos().get(i).setCidade(cepDTO.getLogradouro());
				pessoaFisica.getEnderecos().get(i).setComplemento(cepDTO.getComplemento());
				pessoaFisica.getEnderecos().get(i).setRuaLogra(cepDTO.getLogradouro());
				pessoaFisica.getEnderecos().get(i).setUf(cepDTO.getUf());
				pessoaFisica.getEnderecos().get(i).setPessoa(pessoaFisica);
				pessoaFisica.getEnderecos().get(i).setEmpresa(pessoaJuridica);
			}
		}

		pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);

		Usuario usuariopf = usuarioRepository.findByUserPessoaPJ(pessoaFisica.getId(), pessoaFisica.getEmail());
		if (usuariopf == null) {
			String contraint = usuarioRepository.consultaContraintAcesso();
			if (contraint != null) {
				jdbcTemplate.execute("begin;alter TABLE usuario_acesso drop constraint " + contraint + "commit;");
			}
			usuariopf = new Usuario();
			usuariopf.setDataAtualSenha(Calendar.getInstance().getTime());
			usuariopf.setEmpresa(pessoaFisica.getEmpresa());
			usuariopf.setPessoa(pessoaFisica);
			usuariopf.setLogin(pessoaFisica.getEmail());
			String senha = "" + Calendar.getInstance().getTime();
			String senhacrit = new BCryptPasswordEncoder().encode(senha);
			usuariopf.setSenha(senhacrit);

			usuariopf = usuarioRepository.save(usuariopf);
			usuarioRepository.insereAcessoUsuario(usuariopf.getId());

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("<b>Login :" + usuariopf.getLogin() + "</b>").append("<b>Senha :" + senha + "</b>");
			try {
				serviceSendEmail.enviarEmail("Acesso ao sistema", senha, pessoaFisica.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return pessoaFisica;
	}

	public PessoaFisica atualizarPessoaFisica(PessoaFisica pessoaFisica) {
		for (int i = 0; i < pessoaFisica.getEnderecos().size(); i++) {
			Endereco enderecotemp = enderecoRepository.consultaCep(pessoaFisica.getEnderecos().get(i).getId());
			if (!enderecotemp.getCep().equals(pessoaFisica.getEnderecos().get(i).getCep())
					&& enderecotemp.getId().equals(pessoaFisica.getEnderecos().get(i).getId())) {
				CepDTO cepDTO = utilitaria.consultaCep(pessoaFisica.getEnderecos().get(i).getCep());
				pessoaFisica.getEnderecos().get(i).setBairro(cepDTO.getBairro());
				pessoaFisica.getEnderecos().get(i).setCidade(cepDTO.getLocalidade());
				pessoaFisica.getEnderecos().get(i).setComplemento(cepDTO.getComplemento());
				pessoaFisica.getEnderecos().get(i).setRuaLogra(cepDTO.getLogradouro());
				pessoaFisica.getEnderecos().get(i).setUf(cepDTO.getUf());
				pessoaFisica.getEnderecos().get(i).setPessoa(pessoaFisica);
				pessoaFisica.getEnderecos().get(i).setEmpresa(pessoaFisica.getEmpresa());

			} else {
				pessoaFisica.getEnderecos().get(i).setEmpresa(pessoaFisica.getEmpresa());
				pessoaFisica.getEnderecos().get(i).setPessoa(pessoaFisica);
			}
		}
		return pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);
	}

	public void deletarPessoaFisica(Long id) {
		pessoaFisicaRepository.deleteById(id);
	}

	public PessoaFisica existeCpfPessoaFisica(PessoaFisica pessoaFisica) {
		pessoaFisica = pessoaFisicaRepository.existeCpfPessoaFisica(pessoaFisica.getCpf());
		return pessoaFisica;
	}
}
