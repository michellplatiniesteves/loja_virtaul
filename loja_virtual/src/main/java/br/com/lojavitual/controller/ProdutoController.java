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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.Produto;
import br.com.lojavitual.service.ProdutoService;

@RestController
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;
	
	@ResponseBody
	@GetMapping("Produto/buscarTodosProdutos")
	public ResponseEntity<List<Produto>>buscarTodosProdutos(){
		List<Produto> produtos =produtoService.buscarTodosProdutos(); 
		return new  ResponseEntity<List<Produto>>(produtos,HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("Produto/buscarProdutosPorId/{id}")
	public ResponseEntity<Produto>buscarProdutosPorId(@PathVariable("id")Long id){
		Produto produto =produtoService.buscarProdutosPorId(id); 
		return new  ResponseEntity<Produto>(produto,HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("Produto/buscarProdutosNome/{nome}")
	public ResponseEntity<List<Produto>>buscarProdutosNome(@PathVariable("nome")String nome){
		List<Produto> produtos =produtoService.buscarProdutosNome(nome); 
		return new  ResponseEntity<List<Produto>>(produtos,HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping("Produto/deletarProdutosPorId/{id}")
	public ResponseEntity<String>deletarProdutosPorId(@PathVariable("id") Long id){
		produtoService.deletarProdutosPorId(id);
		return new  ResponseEntity<String>("Deletado com sucesso",HttpStatus.OK);
	}
	@ResponseBody
	@PostMapping("Produto/salvarProdutos")
	public ResponseEntity<Produto>salvarProdutos(@RequestBody @Valid Produto produto) throws ExceptionMentoriaJava{
	    produto =produtoService.salvarProdutos(produto); 
		return new  ResponseEntity<Produto>(produto,HttpStatus.OK);
	}
}
