package br.com.lojavitual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.lojavitual.controller.PessoaJuridicaController;
import br.com.lojavitual.model.PessoaJuridica;
import br.com.lojavitual.repository.PessoaFisicaRepository;
import br.com.lojavitual.repository.PessoaJuridicaRepository;
import br.com.lojavitual.repository.UsuarioRepository;
import junit.framework.TestCase;

@SpringBootTest(classes = LojaVirtualApplication.class)
public class CadastroPessoaTest extends TestCase {
	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;
	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	PessoaJuridicaController pessoaJuridicaController;
	@Test
	public void CadastrarPessoa() throws ExceptionMentoriaJava{
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setCategoria("alimentos");
		pessoaJuridica.setCnpj("4546544464565464");
		pessoaJuridica.setEmail("thor@gmail.com");
		pessoaJuridica.setInscEstadual("43242342");
		pessoaJuridica.setInscMunicipal("6546456546");
		pessoaJuridica.setNome("thor racoes");
		pessoaJuridica.setNomeFastasia("thor");
		pessoaJuridica.setRazaoSocial("4564546456");
		pessoaJuridica.setTelefone("5345353533");
		pessoaJuridica.setTipoPessoa("PJ");
		
		pessoaJuridicaController.salvarPessoaJuridica(pessoaJuridica);
	}

}
