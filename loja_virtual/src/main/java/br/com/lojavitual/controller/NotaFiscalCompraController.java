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
import br.com.lojavitual.model.NotaFiscalCompra;
import br.com.lojavitual.service.NotaFiscalCompraService;

@RequestMapping("NotaFiscalCompra/")
@RestController
public class NotaFiscalCompraController {

	@Autowired
	private NotaFiscalCompraService notaFiscalCompraService;

	@ResponseBody
	@GetMapping("buscarTodasNotaFiscalCompra")
	public ResponseEntity<List<NotaFiscalCompra>> buscarTodasNotaFiscalCompra() {
		List<NotaFiscalCompra> listabuscarTodasNotaFiscalCompra = notaFiscalCompraService.buscarTodasNotaFiscalCompra();
		return new ResponseEntity<List<NotaFiscalCompra>>(listabuscarTodasNotaFiscalCompra, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("buscarNotaFiscalCompraNumeroNota/{numeroNota}")
	public ResponseEntity<List<NotaFiscalCompra>> buscarNotaFiscalCompraNumeroNota(@PathVariable("numeroNota") String numeroNota) {
		List<NotaFiscalCompra> listabuscarNotaFiscalCompraNumeroNota = notaFiscalCompraService.buscarNotaFiscalCompraNumeroNota(numeroNota);
		return new ResponseEntity<List<NotaFiscalCompra>>(listabuscarNotaFiscalCompraNumeroNota, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("buscarNotaFiscalCompraDesc/{desc}")
	public ResponseEntity<List<NotaFiscalCompra>> buscarNotaFiscalCompraDesc(@PathVariable("desc") String desc) {
		List<NotaFiscalCompra> listabuscarNotaFiscalCompraDesc = notaFiscalCompraService
				.buscarNotaFiscalCompraDesc(desc);
		return new ResponseEntity<List<NotaFiscalCompra>>(listabuscarNotaFiscalCompraDesc, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("buscarNotaFiscalCompraPorId/{id}")
	public ResponseEntity<List<NotaFiscalCompra>> buscarNotaFiscalCompraPorId(@PathVariable("id") Long id) {
		List<NotaFiscalCompra> listabuscarNotaFiscalCompraPorId = notaFiscalCompraService.buscarNotaFiscalCompraPorId(id);
		return new ResponseEntity<List<NotaFiscalCompra>>(listabuscarNotaFiscalCompraPorId, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("buscarNotaFiscalCompraPorEmpresa/{id}")
	public ResponseEntity<List<NotaFiscalCompra>> buscarNotaFiscalCompraPorEmpresa(@PathVariable("id") Long id) {
		List<NotaFiscalCompra> listabuscarNotaFiscalCompraPorEmpresa = notaFiscalCompraService.buscarNotaFiscalCompraPorEmpresa(id);
		return new ResponseEntity<List<NotaFiscalCompra>>(listabuscarNotaFiscalCompraPorEmpresa, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("buscarNotaFiscalCompraPorContaPagar/{id}")
	public ResponseEntity<List<NotaFiscalCompra>> buscarNotaFiscalCompraPorContaPagar(@PathVariable("id") Long id) {
		List<NotaFiscalCompra> listabuscarNotaFiscalCompraPorContaPagar = notaFiscalCompraService.buscarNotaFiscalCompraPorContaPagar(id);
		return new ResponseEntity<List<NotaFiscalCompra>>(listabuscarNotaFiscalCompraPorContaPagar, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("buscarNotaFiscalCompraPorPessoa/{id}")
	public ResponseEntity<List<NotaFiscalCompra>> buscarNotaFiscalCompraPorPessoa(@PathVariable("id") Long id) {
		List<NotaFiscalCompra> listabuscarNotaFiscalCompraPorPessoa = notaFiscalCompraService.buscarNotaFiscalCompraPorPessoa(id);
		return new ResponseEntity<List<NotaFiscalCompra>>(listabuscarNotaFiscalCompraPorPessoa, HttpStatus.OK);
	}
	@ResponseBody
	@DeleteMapping("deletarNotaFiscalCompraPorId/{id}")
	public ResponseEntity<String> deletarNotaFiscalCompraPorId(@PathVariable("id") Long id) {
		notaFiscalCompraService.deletarNotaFiscalCompraPorId(id);
		return new ResponseEntity<String>("deletado com sucesso", HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping("salvarNotaFiscalCompra")
	public ResponseEntity<NotaFiscalCompra> salvarNotaFiscalCompra(@RequestBody @Valid NotaFiscalCompra notaFiscalCompra) throws ExceptionMentoriaJava {
		notaFiscalCompra = notaFiscalCompraService.salvarNotaFiscalCompra(notaFiscalCompra);
		return new ResponseEntity<NotaFiscalCompra>(notaFiscalCompra, HttpStatus.OK);
	}
}
