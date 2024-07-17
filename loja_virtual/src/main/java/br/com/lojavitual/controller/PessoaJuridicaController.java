package br.com.lojavitual.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.PessoaJuridica;
import br.com.lojavitual.service.PessoaJuridicaService;

@RestController
public class PessoaJuridicaController {

	@Autowired
	PessoaJuridicaService pessoaJuridicaService;
	
	@ResponseBody
	@PostMapping("**/salvarPessoaJuridica")
	public ResponseEntity<PessoaJuridica> salvarPessoaJuridica(@RequestBody PessoaJuridica pessoaJuridica)
			throws ExceptionMentoriaJava, UnsupportedEncodingException {

		if (pessoaJuridica == null) {
			throw new ExceptionMentoriaJava("Pessoa juridica nao pode ser vazia");
		}
		if (pessoaJuridica.getId() == null && pessoaJuridicaService.existeCnpj(pessoaJuridica) != null) {
			throw new ExceptionMentoriaJava("CNPJ já cadastrada");
		}
		if(pessoaJuridica.getId() == null && pessoaJuridicaService.existeLogin(pessoaJuridica)!= null) {
			throw new ExceptionMentoriaJava("Login já cadastrada");
		}
		pessoaJuridica = pessoaJuridicaService.salvarPessoaJuridica(pessoaJuridica);

		return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);

	}

	@ResponseBody
	@DeleteMapping("**/deletarPessoaJuridica/{id}")
	public ResponseEntity<?> deletarPessoaJuridica(@PathVariable("id") Long id) {
		pessoaJuridicaService.deletarPessoaJuridica(id);
		return new ResponseEntity<>("deletado com sucesso", HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("**/buscarPessoaJuridicaPorId/{id}")
	public ResponseEntity<PessoaJuridica> buscarPessoaJuridicaPorId(@PathVariable("id") Long id) {
		PessoaJuridica pessoaJuridica = pessoaJuridicaService.buscarPessoaJuridicaPorId(id);
		return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("**/buscarTodasPessoaJuridica")
	public ResponseEntity<List<PessoaJuridica>> buscarTodasPessoaJuridica() {
		List<PessoaJuridica> listapj = pessoaJuridicaService.buscarTodasPessoaJuridica();
		return new ResponseEntity<List<PessoaJuridica>>(listapj, HttpStatus.OK);
	}

}
