package br.com.lojavitual;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.lojavitual.controller.PessoaJuridicaController;
import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.Endereco;
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
	public void CadastrarPessoa() throws ExceptionMentoriaJava, UnsupportedEncodingException{
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setCategoria("alimentos");
		pessoaJuridica.setCnpj("4546544464565471");
		pessoaJuridica.setEmail("michellplatignni.java@gmail.com");
		pessoaJuridica.setInscEstadual("43242342");
		pessoaJuridica.setInscMunicipal("6546456546");
		pessoaJuridica.setNome("thor racoes");
		pessoaJuridica.setNomeFastasia("thor");
		pessoaJuridica.setRazaoSocial("4564546456");
		pessoaJuridica.setTelefone("5345353533");
		pessoaJuridica.setTipoPessoa("PJ");
		
		List<Endereco> listaendereco = new ArrayList<Endereco>();
		Endereco endereco = new Endereco();
		endereco.setRuaLogra("dsadada");
		endereco.setBairro("dsadada");
		endereco.setCep("dsadada");
		endereco.setCidade("dsadada");
		endereco.setComplemento("dsadada");
		endereco.setNumero("dsadada");
		endereco.setUf("se");
		listaendereco.add(endereco);
		pessoaJuridica.setEnderecos(listaendereco);
		pessoaJuridicaController.salvarPessoaJuridica(pessoaJuridica);
	}

}
