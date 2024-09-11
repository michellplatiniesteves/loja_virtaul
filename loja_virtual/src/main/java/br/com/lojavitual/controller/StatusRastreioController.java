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
import br.com.lojavitual.model.StatusRastreio;
import br.com.lojavitual.service.StatusRastreioService;

@RequestMapping("StatusRastreio/")
@RestController
public class StatusRastreioController {

	@Autowired
	StatusRastreioService statusRastreioService;
	
	@ResponseBody
	@GetMapping(value = "buscaStatusRastreioTodas")
	public ResponseEntity<List<StatusRastreio>>buscaStatusRastreioTodas(){
	
		List<StatusRastreio>listaStatusRastreioTodas = statusRastreioService.buscaStatusRastreioTodas();
		return new ResponseEntity<List<StatusRastreio>>(listaStatusRastreioTodas,HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "buscaStatusRasteioPoVenda/{id}")
	public ResponseEntity<List<StatusRastreio>>buscaStatusRasteioPoVenda(@PathVariable("id")Long id){
	
		List<StatusRastreio>listabuscaStatusRasteioPoVenda = statusRastreioService.buscaStatusRasteioPoVenda(id);
		return new ResponseEntity<List<StatusRastreio>>(listabuscaStatusRasteioPoVenda,HttpStatus.OK);
	}
	@ResponseBody
	@GetMapping(value = "buscaStatusRastreioPorId/{id}")
	public ResponseEntity<StatusRastreio>buscaStatusRastreioPorId(@PathVariable("id")Long id){
	
		StatusRastreio buscaStatusRastreioPorId=statusRastreioService.buscaStatusRastreioPorId(id);
		return new ResponseEntity<StatusRastreio>(buscaStatusRastreioPorId,HttpStatus.OK);
	}

	@ResponseBody
	@DeleteMapping(value = "deletarStatusRastreio/{id}")
	public ResponseEntity<String>deletarStatusRastreio(@PathVariable("id")Long id){
	
		String mensagem = statusRastreioService.deletarStatusRastreio(id);
		return new ResponseEntity<String>(mensagem,HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping(value = "salvarStatusRastreio")
	public ResponseEntity<StatusRastreio>salvarStatusRastreio(@RequestBody @Valid StatusRastreio statusRastreio) throws ExceptionMentoriaJava{
	
		statusRastreio = statusRastreioService.salvarStatusRastreio(statusRastreio);
		return new ResponseEntity<StatusRastreio>(statusRastreio,HttpStatus.OK);
	}
}
