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
import br.com.lojavitual.model.NotaItemProduto;
import br.com.lojavitual.service.NotaItemProdutoService;

@RequestMapping(value = "NotaItemProduto/")
@RestController
public class NotaItemProdutoController {

	@Autowired
	private NotaItemProdutoService notaItemProdutoService;
	
	@ResponseBody
	@GetMapping("buscarTodasNotaItemProduto")
	public ResponseEntity<List<NotaItemProduto>> notaItemProdutoCompra() {
		List<NotaItemProduto> listabuscarTodasNotaItemProduto = notaItemProdutoService.buscarTodasNotaItemProduto();
		return new ResponseEntity<List<NotaItemProduto>>(listabuscarTodasNotaItemProduto, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("buscarNotaItemProdutoPorProduto/{id}")
	public ResponseEntity<List<NotaItemProduto>> buscarNotaItemProdutoPorProduto(@PathVariable("id") Long id) {
		List<NotaItemProduto> listabuscarNotaItemProdutoPorProduto = notaItemProdutoService.buscarNotaItemProdutoPorProduto(id);
		return new ResponseEntity<List<NotaItemProduto>>(listabuscarNotaItemProdutoPorProduto, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("buscarNotaItemProdutoPorEmpresa/{id}")
	public ResponseEntity<List<NotaItemProduto>> buscarNotaItemProdutoPorEmpresa(@PathVariable("id") Long id) {
		List<NotaItemProduto> listabuscarNotaItemProdutoPorEmpresa = notaItemProdutoService.buscarNotaItemProdutoPorEmpresa(id);
		return new ResponseEntity<List<NotaItemProduto>>(listabuscarNotaItemProdutoPorEmpresa, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("buscarNotaItemProdutoPorContaPagar/{id}")
	public ResponseEntity<List<NotaItemProduto>> buscarNotaItemProdutoPorContaPagar(@PathVariable("id") Long id) {
		List<NotaItemProduto> listabuscarNotaItemProdutoPorContaPagar = notaItemProdutoService.buscarNotaItemProdutoPorContaPagar(id);
		return new ResponseEntity<List<NotaItemProduto>>(listabuscarNotaItemProdutoPorContaPagar, HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping("deletarNotaFiscalCompraPorId/{id}")
	public ResponseEntity<String> deletarNotaFiscalCompraPorId(@PathVariable("id") Long id) {
		notaItemProdutoService.deletarNotaFiscalCompraPorId(id);
		return new ResponseEntity<String>("deletado com sucesso", HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping("salvarNotaItemProduto")
	public ResponseEntity<NotaItemProduto> salvarNotaItemProduto(@RequestBody @Valid NotaItemProduto notaItemProduto) throws ExceptionMentoriaJava {
		notaItemProduto = notaItemProdutoService.salvarNotaItemProduto(notaItemProduto);
		return new ResponseEntity<NotaItemProduto>(notaItemProduto, HttpStatus.OK);
	}
}
