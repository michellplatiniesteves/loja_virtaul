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
import br.com.lojavitual.model.ContaPagar;
import br.com.lojavitual.service.ContaPagarService;

@RequestMapping("ContaPagar/")
@RestController
public class ContaPagarController {

	@Autowired
	private ContaPagarService contaPagarService;

	@ResponseBody
	@GetMapping("buscarPorDesc/{desc}")
	public ResponseEntity<List<ContaPagar>> buscarPorDesc(@PathVariable("desc") String desc) {
		List<ContaPagar> listabuscarPorDesc = contaPagarService.buscarPorDesc(desc.toUpperCase().trim());
		return new ResponseEntity<List<ContaPagar>>(listabuscarPorDesc, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("buscarPorPessoa/{id}")
	public ResponseEntity<ContaPagar> buscarPorPessoa(@PathVariable("id") Long id) {
		ContaPagar buscarPorPessoa = contaPagarService.buscarPorPessoa(id);
		return new ResponseEntity<ContaPagar>(buscarPorPessoa, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("buscarPorFornecedor/{id}")
	public ResponseEntity<ContaPagar> buscarPorFornecedor(@PathVariable("id") Long id) {
		ContaPagar buscarPorFornecedor = contaPagarService.buscarPorFornecedor(id);
		return new ResponseEntity<ContaPagar>(buscarPorFornecedor, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("buscarPorEmpresa/{id}")
	public ResponseEntity<ContaPagar> buscarPorEmpresa(@PathVariable("id") Long id) {
		ContaPagar buscarPorEmpresa = contaPagarService.buscarPorEmpresa(id);
		return new ResponseEntity<ContaPagar>(buscarPorEmpresa, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("buscarContaPagar/{id}")
	public ResponseEntity<ContaPagar> buscarContaPagar(@PathVariable("id") Long id) {
		ContaPagar buscarContaPagar = contaPagarService.buscarContaPagar(id);
		return new ResponseEntity<ContaPagar>(buscarContaPagar, HttpStatus.OK);
	}

	@ResponseBody
	@DeleteMapping("deletarContaPagar/{id}")
	public ResponseEntity<String> deletarContaPagar(@PathVariable("id") Long id) {
		contaPagarService.deletarContaPagar(id);
		return new ResponseEntity<String>("Deletado com sucesso", HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping("salvarContaPagar")
	public ResponseEntity<ContaPagar> salvarContaPagar(@RequestBody @Valid ContaPagar contaPagar) throws ExceptionMentoriaJava {
		contaPagar = contaPagarService.salvarContaPagar(contaPagar);
		return new ResponseEntity<ContaPagar>(contaPagar, HttpStatus.OK);
	}
}
