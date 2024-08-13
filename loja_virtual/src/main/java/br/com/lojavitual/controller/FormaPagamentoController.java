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
import br.com.lojavitual.model.FormaPagamento;
import br.com.lojavitual.service.FormaPagamentoService;

@RequestMapping("FormaPagamento/")
@RestController
public class FormaPagamentoController {

	@Autowired
	FormaPagamentoService formaPagamentoService;
	
	@ResponseBody
	@GetMapping(value = "buscaFormaPagamentoTodas")
	public ResponseEntity<List<FormaPagamento>>buscaFormaPagamentoTodas(){
	
		List<FormaPagamento>listabuscaFormaPagamentoTodas = formaPagamentoService.buscaFormaPagamentoTodas();
		return new ResponseEntity<List<FormaPagamento>>(listabuscaFormaPagamentoTodas,HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "buscaFormaPagamentoPorId/{id}")
	public ResponseEntity<FormaPagamento>buscaFormaPagamentoPorId(@PathVariable("id")Long id){
	
		FormaPagamento buscaFormaPagamentoPorId=formaPagamentoService.buscaFormaPagamentoPorId(id);
		return new ResponseEntity<FormaPagamento>(buscaFormaPagamentoPorId,HttpStatus.OK);
	}

	@ResponseBody
	@DeleteMapping(value = "deletarFormaPagamento/{id}")
	public ResponseEntity<String>deletarFormaPagamento(@PathVariable("id")Long id){
	
		String mensagem = formaPagamentoService.deletarFormaPagamento(id);
		return new ResponseEntity<String>(mensagem,HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping(value = "salvarFormaPagamento")
	public ResponseEntity<FormaPagamento>salvarFormaPagamento(@RequestBody @Valid FormaPagamento formaPagamento) throws ExceptionMentoriaJava{
	
		formaPagamento = formaPagamentoService.salvarFormaPagamento(formaPagamento);
		return new ResponseEntity<FormaPagamento>(formaPagamento,HttpStatus.OK);
	}
}
