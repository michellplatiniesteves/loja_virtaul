package br.com.lojavitual.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.ContaReceber;
import br.com.lojavitual.service.ContaReceberService;

@RequestMapping("ContaReceber/")
@RestController
public class ContaReceberController {

	@Autowired
	private ContaReceberService contaReceberService;

	@ResponseBody
	@GetMapping("buscarPorDesc/{desc}")
	public ResponseEntity<List<ContaReceber>> buscarPorDesc(@PathVariable("desc") String desc) {
		List<ContaReceber> listabuscarPorDesc = contaReceberService.buscarPorDesc(desc.toUpperCase().trim());
		return new ResponseEntity<List<ContaReceber>>(listabuscarPorDesc, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("buscarPorPessoa/{id}")
	public ResponseEntity<ContaReceber> buscarPorPessoa(@PathVariable("id") Long id) {
		ContaReceber buscarPorPessoa = contaReceberService.buscarPorPessoa(id);
		return new ResponseEntity<ContaReceber>(buscarPorPessoa, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("buscarPorEmpresa/{id}")
	public ResponseEntity<ContaReceber> buscarPorEmpresa(@PathVariable("id") Long id) {
		ContaReceber buscarPorEmpresa = contaReceberService.buscarPorEmpresa(id);
		return new ResponseEntity<ContaReceber>(buscarPorEmpresa, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("buscarContaReceber/{id}")
	public ResponseEntity<ContaReceber> buscarContaPagar(@PathVariable("id") Long id) {
		ContaReceber buscarContaReceber = contaReceberService.buscarContaReceber(id);
		return new ResponseEntity<ContaReceber>(buscarContaReceber, HttpStatus.OK);
	}

	@ResponseBody
	@DeleteMapping("deletarContaPagar/{id}")
	public ResponseEntity<String> deletarContaPagar(@PathVariable("id") Long id) {
		contaReceberService.deletarContaPagar(id);
		return new ResponseEntity<String>("Deletado com sucesso", HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping("salvarContaPagar")
	public ResponseEntity<ContaReceber> salvarContaPagar(@RequestBody @Valid ContaReceber contaReceber) throws ExceptionMentoriaJava {
		contaReceber = contaReceberService.salvarContaPagar(contaReceber);
		return new ResponseEntity<ContaReceber>(contaReceber, HttpStatus.OK);
	}
}
