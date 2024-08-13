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
import br.com.lojavitual.model.CupDesc;
import br.com.lojavitual.model.FormaPagamento;
import br.com.lojavitual.service.CupDescService;

@RequestMapping("CupDesc/")
@RestController
public class CupDescController {

	@Autowired
	CupDescService cupDescService;
	
	@ResponseBody
	@GetMapping(value = "buscaCupDescTodas")
	public ResponseEntity<List<CupDesc>>buscaCupDescTodas(){
	
		List<CupDesc>listabuscaFormaPagamentoTodas = cupDescService.buscaCupDescTodas();
		return new ResponseEntity<List<CupDesc>>(listabuscaFormaPagamentoTodas,HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "buscaCupDescPorId/{id}")
	public ResponseEntity<CupDesc>buscaCupDescPorId(@PathVariable("id")Long id){
	
		CupDesc buscaCupDescPorId=cupDescService.buscaCupDescPorId(id);
		return new ResponseEntity<CupDesc>(buscaCupDescPorId,HttpStatus.OK);
	}

	@ResponseBody
	@DeleteMapping(value = "deletarCupDesc/{id}")
	public ResponseEntity<String>deletarCupDesc(@PathVariable("id")Long id){
	
		String mensagem = cupDescService.deletarCupDesc(id);
		return new ResponseEntity<String>(mensagem,HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping(value = "salvarCupDesc")
	public ResponseEntity<CupDesc>salvarCupDesc(@RequestBody @Valid CupDesc cupDesc) throws ExceptionMentoriaJava{
	
		cupDesc = cupDescService.salvarCupDesc(cupDesc);
		return new ResponseEntity<CupDesc>(cupDesc,HttpStatus.OK);
	}
}
