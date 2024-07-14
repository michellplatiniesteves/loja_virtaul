package br.com.lojavitual.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.lojavitual.model.PessoaJuridica;
import br.com.lojavitual.model.Usuario;
import br.com.lojavitual.repository.PessoaJuridicaRepository;
import br.com.lojavitual.repository.UsuarioRepository;

@Service
public class PessoaJuridicaService {

	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public PessoaJuridica salvarPessoaJuridica(PessoaJuridica pessoaJuridica) {

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

	public PessoaJuridica existeCnpj(PessoaJuridica pessoaJuridica) {
		pessoaJuridica = pessoaJuridicaRepository.existeCnpj(pessoaJuridica.getCnpj());
		return pessoaJuridica;
	}

}
