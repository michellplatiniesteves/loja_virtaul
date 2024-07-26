package br.com.lojavitual.service;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.lojavitual.DTO.CepDTO;
import br.com.lojavitual.DTO.ConsultaCnpjDTO;
import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.Endereco;
import br.com.lojavitual.model.PessoaJuridica;
import br.com.lojavitual.model.Usuario;
import br.com.lojavitual.repository.EnderecoRepository;
import br.com.lojavitual.repository.PessoaJuridicaRepository;
import br.com.lojavitual.repository.UsuarioRepository;
import br.com.lojavitual.util.Utilitaria;
import br.com.lojavitual.util.ValidaCNPJ;

@Service
public class PessoaJuridicaService {

	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	ServiceSendEmail serviceSendEmail;

	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	Utilitaria utilitaria;

	public PessoaJuridica salvarPessoaJuridica(PessoaJuridica pessoaJuridica)
			throws UnsupportedEncodingException, ExceptionMentoriaJava {
		if (pessoaJuridica == null) {
			throw new ExceptionMentoriaJava("Pessoa juridica nao pode ser vazia");
		}
		if (pessoaJuridica.getTipoPessoa() == null) {
			throw new ExceptionMentoriaJava("Informe o valor para o campo Tipo Pessoa");
		}
		if (pessoaJuridica.getId() == null && existeCnpj(pessoaJuridica) != null) {
			throw new ExceptionMentoriaJava("CNPJ já cadastrada");
		}
		if (pessoaJuridica.getId() == null && existeLogin(pessoaJuridica) != null) {
			throw new ExceptionMentoriaJava("Login já cadastrada");
		}
		if (!ValidaCNPJ.isCNPJ(pessoaJuridica.getCnpj())) {
			throw new ExceptionMentoriaJava("CNPJ invlido");
		}
		if (pessoaJuridica.getId() == null || pessoaJuridica.getId() <= 0) {
			for (int i = 0; i < pessoaJuridica.getEnderecos().size(); i++) {
				CepDTO cepDTO = utilitaria.consultaCep(pessoaJuridica.getEnderecos().get(i).getCep());
				pessoaJuridica.getEnderecos().get(i).setBairro(cepDTO.getBairro());
				pessoaJuridica.getEnderecos().get(i).setCidade(cepDTO.getLocalidade());
				pessoaJuridica.getEnderecos().get(i).setComplemento(cepDTO.getComplemento());
				pessoaJuridica.getEnderecos().get(i).setRuaLogra(cepDTO.getLogradouro());
				pessoaJuridica.getEnderecos().get(i).setUf(cepDTO.getUf());
				pessoaJuridica.getEnderecos().get(i).setPessoa(pessoaJuridica);
				pessoaJuridica.getEnderecos().get(i).setEmpresa(pessoaJuridica);
			}
		} 
		pessoaJuridica = pessoaJuridicaRepository.save(pessoaJuridica);

		Usuario usuariopj = usuarioRepository.findByUserPessoaPJ(pessoaJuridica.getId(), pessoaJuridica.getEmail());
		if (usuariopj == null) {
			String contraint = usuarioRepository.consultaContraintAcesso();
			if (contraint != null) {
				jdbcTemplate.execute("begin;alter TABLE usuario_acesso drop constraint " + contraint + "commit;");
			}
			usuariopj = new Usuario();
			usuariopj.setDataAtualSenha(Calendar.getInstance().getTime());
			usuariopj.setEmpresa(pessoaJuridica);
			usuariopj.setPessoa(pessoaJuridica);
			usuariopj.setLogin(pessoaJuridica.getEmail());
			String senha = "" + Calendar.getInstance().getTime();
			String senhacrit = new BCryptPasswordEncoder().encode(senha);
			usuariopj.setSenha(senhacrit);

			usuariopj = usuarioRepository.save(usuariopj);
			usuarioRepository.insereAcessoUsuario(usuariopj.getId());

			usuarioRepository.insereAcessoUsuario(usuariopj.getId(), "ROLE_ADMIN");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("<b>Login :" + usuariopj.getLogin() + "</b>").append("<b>Senha :" + senha + "</b>");
			try {
				serviceSendEmail.enviarEmail("Acesso ao sistema", senha, pessoaJuridica.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return pessoaJuridica;
	}

	public void deletarPessoaJuridica(Long id) {
		pessoaJuridicaRepository.deleteById(id);
	}

	public PessoaJuridica buscarPessoaJuridicaPorId(Long id) {
		PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findById(id).get();
		return pessoaJuridica;
	}

	public List<PessoaJuridica> buscarTodasPessoaJuridica() {
		List<PessoaJuridica> listapj = (List<PessoaJuridica>) pessoaJuridicaRepository.findAll();
		return listapj;
	}

	public List<PessoaJuridica> listaPessoaJuridicaPorCnpj(String cnpj ) {
		List<PessoaJuridica> listapj = (List<PessoaJuridica>) pessoaJuridicaRepository.listaPessoaJuridicaPorCnpj(cnpj);
		return listapj;
	}
	public List<PessoaJuridica> listaPessoaJuridicaPorNome(String nome) {
		List<PessoaJuridica> listapj = (List<PessoaJuridica>) pessoaJuridicaRepository.listaPessoaJuridicaPorNome(nome);
		return listapj;
	}

	public List<PessoaJuridica> listaPessoaJuridicaPorNomeFastasia(String nomeFastasia) {
		List<PessoaJuridica> listapj = (List<PessoaJuridica>) pessoaJuridicaRepository.listaPessoaJuridicaPorNomeFastasia(nomeFastasia);
		return listapj;
	}
	public PessoaJuridica existeCnpj(PessoaJuridica pessoaJuridica) {
		pessoaJuridica = pessoaJuridicaRepository.existeCnpj(pessoaJuridica.getCnpj());
		return pessoaJuridica;
	}

	public PessoaJuridica existeLogin(PessoaJuridica pessoaJuridica) {
		pessoaJuridica = pessoaJuridicaRepository.existeLogin(pessoaJuridica.getEmail());
		return pessoaJuridica;
	}

	public PessoaJuridica atualizaPessoaJuridica(PessoaJuridica pessoaJuridica)
			throws UnsupportedEncodingException, ExceptionMentoriaJava {
		
		for (int i = 0; i < pessoaJuridica.getEnderecos().size(); i++) {
			Endereco enderecotemp = enderecoRepository.consultaCep(
					pessoaJuridica.getEnderecos().get(i).getId());
			if (! enderecotemp.getCep().equals(pessoaJuridica.getEnderecos().get(i).getCep()) 
				&& enderecotemp.getId().equals(pessoaJuridica.getEnderecos().get(i).getId())) {
				CepDTO cepDTO = utilitaria.consultaCep(pessoaJuridica.getEnderecos().get(i).getCep());
				pessoaJuridica.getEnderecos().get(i).setBairro(cepDTO.getBairro());
				pessoaJuridica.getEnderecos().get(i).setCidade(cepDTO.getLocalidade());
				pessoaJuridica.getEnderecos().get(i).setComplemento(cepDTO.getComplemento());
				pessoaJuridica.getEnderecos().get(i).setRuaLogra(cepDTO.getLogradouro());
				pessoaJuridica.getEnderecos().get(i).setUf(cepDTO.getUf());
				pessoaJuridica.getEnderecos().get(i).setPessoa(pessoaJuridica);
				pessoaJuridica.getEnderecos().get(i).setEmpresa(pessoaJuridica);

			}else {
				pessoaJuridica.getEnderecos().get(i).setEmpresa(pessoaJuridica);
				pessoaJuridica.getEnderecos().get(i).setPessoa(pessoaJuridica);
			}
		}
		return pessoaJuridica = pessoaJuridicaRepository.save(pessoaJuridica);
	}

	public ConsultaCnpjDTO consultaCnpjReceitaFederalWs(String cnpj) {
		ConsultaCnpjDTO consultaCnpjDTO = utilitaria.consultaCnjpReceita(cnpj);
		return consultaCnpjDTO;
	}


}
