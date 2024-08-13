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
import br.com.lojavitual.model.NotaFiscalVenda;
import br.com.lojavitual.service.NotaFiscalVendaService;

@RequestMapping("NotaFiscalVenda/")
@RestController
public class NotaFiscalVendaController {

	@Autowired
	NotaFiscalVendaService notaFiscalVendaService;
	
	@ResponseBody
	@GetMapping(value = "buscaNotaFiscalVendaTodas")
	public ResponseEntity<List<NotaFiscalVenda>>buscaNotaFiscalVendaTodas(){
	
		List<NotaFiscalVenda>listabuscaNotaFiscalVendaTodas = notaFiscalVendaService.buscaNotaFiscalVendaTodas();
		return new ResponseEntity<List<NotaFiscalVenda>>(listabuscaNotaFiscalVendaTodas,HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "buscaNotaFiscalVendaPorId/{id}")
	public ResponseEntity<NotaFiscalVenda>buscaNotaFiscalVendaPorId(@PathVariable("id")Long id){
	
		NotaFiscalVenda buscaNotaFiscalVendaPorId=notaFiscalVendaService.buscaNotaFiscalVendaPorId(id);
		return new ResponseEntity<NotaFiscalVenda>(buscaNotaFiscalVendaPorId,HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "buscaNotaFiscalVendaPorEmpresa/{id}")
	public ResponseEntity<List<NotaFiscalVenda>>buscaNotaFiscalVendaPorEmpresa(@PathVariable("id")Long id){
	
		List<NotaFiscalVenda> buscaNotaFiscalVendaPorEmpresa=notaFiscalVendaService.buscaNotaFiscalVendaPorEmpresa(id);
		return new ResponseEntity<List<NotaFiscalVenda>>(buscaNotaFiscalVendaPorEmpresa,HttpStatus.OK);
	}
	@ResponseBody
	@DeleteMapping(value = "deletarNotaFiscalVenda/{id}")
	public ResponseEntity<String>deletarNotaFiscalVenda(@PathVariable("id")Long id){
	
		String mensagem = notaFiscalVendaService.deletarNotaFiscalVenda(id);
		return new ResponseEntity<String>(mensagem,HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping(value = "salvarNotaFiscalVenda")
	public ResponseEntity<NotaFiscalVenda>salvarNotaFiscalVenda(@RequestBody @Valid NotaFiscalVenda notaFiscalVenda) throws ExceptionMentoriaJava{
	
		notaFiscalVenda = notaFiscalVendaService.salvarNotaFiscalVenda(notaFiscalVenda);
		return new ResponseEntity<NotaFiscalVenda>(notaFiscalVenda,HttpStatus.OK);
	}
}
