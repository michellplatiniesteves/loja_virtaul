package br.com.lojavitual.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.PessoaFisica;
import br.com.lojavitual.service.PessoaFisicaService;
import br.com.lojavitual.util.ValidaCPF;


@RestController
public class PessoaFisicaController {

	@Autowired
	private PessoaFisicaService pessoaFisicaService;

	@ResponseBody
	@GetMapping("**PessoaFisica/buscarTodasPessoaFisica")
	public ResponseEntity<List<PessoaFisica>> buscarTodasPessoaFisica() throws ExceptionMentoriaJava {
		List<PessoaFisica> lista = pessoaFisicaService.buscarTodasPessoaFisica();
		if (lista.isEmpty()) {
			throw new ExceptionMentoriaJava("Não foram encontrado registros.");
		}
		return new ResponseEntity<List<PessoaFisica>>(lista, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping("**PessoaFisica/buscarPessoaFisicaPorNome/{nome}")
	public ResponseEntity<List<PessoaFisica>> buscarPessoaFisicaPorNome(@PathVariable("nome")String nome) throws ExceptionMentoriaJava {
		List<PessoaFisica> lista = pessoaFisicaService.buscarPessoaFisicaPorNome(nome.trim().toUpperCase());
		if (lista.isEmpty()) {
			throw new ExceptionMentoriaJava("Não foram encontrado registros.");
		}
		return new ResponseEntity<List<PessoaFisica>>(lista, HttpStatus.OK);

	}
	
	@ResponseBody
	@GetMapping("**PessoaFisica/buscarPessoaFisicaPorCpf/{cpf}")
	public ResponseEntity<List<PessoaFisica>> buscarPessoaFisicaPorCpf(@PathVariable("cpf") String cpf) throws ExceptionMentoriaJava {
		List<PessoaFisica> lista = pessoaFisicaService.buscarPessoaFisicaPorCpf(cpf.trim().toUpperCase());
		if (lista.isEmpty()) {
			throw new ExceptionMentoriaJava("Não foram encontrado registros.");
		}
		return new ResponseEntity<List<PessoaFisica>>(lista, HttpStatus.OK);

	}
	@ResponseBody
	@GetMapping("**PessoaFisica/buscarPessoaFisicaPorId/{id}")
	public ResponseEntity<PessoaFisica> buscarPessoaFisicaPorId(@PathVariable("id") Long id)
			throws ExceptionMentoriaJava {
		PessoaFisica pessoaFisica = pessoaFisicaService.buscarPessoaFisicaPorId(id);
		if (pessoaFisica == null) {
			throw new ExceptionMentoriaJava("Pessoa Fisica nao encontrada com o código :" + id);
		}
		return new ResponseEntity<PessoaFisica>(pessoaFisica, HttpStatus.OK);

	}

	@ResponseBody
	@PostMapping("**PessoaFisica/salvarPessoaFisica")
	public ResponseEntity<PessoaFisica> salvarPessoaFisica(@RequestBody @Valid PessoaFisica pessoaFisica)
			throws ExceptionMentoriaJava {

		pessoaFisica = pessoaFisicaService.salvarPessoaFisica(pessoaFisica);
		return new ResponseEntity<PessoaFisica>(pessoaFisica, HttpStatus.OK);

	}

	@ResponseBody
	@PutMapping("**PessoaFisica/atualizarPessoaFisica")
	public ResponseEntity<PessoaFisica> atualizarPessoaFisica(@RequestBody @Valid PessoaFisica pessoaFisica) {
		pessoaFisica = pessoaFisicaService.atualizarPessoaFisica(pessoaFisica);
		return new ResponseEntity<PessoaFisica>(pessoaFisica, HttpStatus.OK);

	}

	@ResponseBody
	@PutMapping("**PessoaFisica/deletarPessoaFisica/{id}")
	public ResponseEntity<?> deletarPessoaFisica(@PathVariable("id") Long id) {
		pessoaFisicaService.deletarPessoaFisica(id);
		return new ResponseEntity<>("deletado com sucesso", HttpStatus.OK);

	}

}
