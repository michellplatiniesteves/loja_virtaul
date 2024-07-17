package br.com.lojavitual.service;

import java.io.UnsupportedEncodingException;
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
	@Autowired
	ServiceSendEmail serviceSendEmail;
	public PessoaJuridica salvarPessoaJuridica(PessoaJuridica pessoaJuridica) throws UnsupportedEncodingException {
       for(int i=0; i<pessoaJuridica.getEnderecos().size();i++ ){
    	   pessoaJuridica.getEnderecos().get(i).setPessoa(pessoaJuridica);
    	   pessoaJuridica.getEnderecos().get(i).setEmpresa(pessoaJuridica);
       };
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
			
			usuarioRepository.insereAcessoUsuario(usuariopj.getId(),"ROLE_ADMIN");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("<b>Login :" +usuariopj.getLogin()+"</b>").append("<b>Senha :" +senha+"</b>");
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

	public PessoaJuridica existeCnpj(PessoaJuridica pessoaJuridica) {
		pessoaJuridica = pessoaJuridicaRepository.existeCnpj(pessoaJuridica.getCnpj());
		return pessoaJuridica;
	}
	public PessoaJuridica existeLogin(PessoaJuridica pessoaJuridica) {
		pessoaJuridica = pessoaJuridicaRepository.existeLogin(pessoaJuridica.getEmail());
		return pessoaJuridica;
	}

}
