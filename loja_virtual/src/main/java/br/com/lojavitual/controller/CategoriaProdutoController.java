package br.com.lojavitual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.CategoriaProduto;
import br.com.lojavitual.service.CategoriaProdutoService;


@RestController
public class CategoriaProdutoController {

	@Autowired
	CategoriaProdutoService categoriaProdutoService;
	
	@ResponseBody
	@GetMapping("**/CategoriaProduto/buscarTodosProdutos")
	public ResponseEntity<List<CategoriaProduto>> buscarTodosProdutos() {
		List<CategoriaProduto>listaProdutos = categoriaProdutoService.buscarTodosProdutos();
		return new ResponseEntity<List<CategoriaProduto>>(listaProdutos, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("**/CategoriaProduto/buscarProdutosPorId/{id}")
	public ResponseEntity<CategoriaProduto> buscarProdutosPorId(@PathVariable("id")Long id) {
		CategoriaProduto categoriaProduto = categoriaProdutoService.buscarProdutosPorId(id);
		return new ResponseEntity<CategoriaProduto>(categoriaProduto, HttpStatus.OK);
	}
	@ResponseBody
	@GetMapping("**/CategoriaProduto/buscarProdutosPorDesc/{desc}")
	public ResponseEntity<List<CategoriaProduto>> buscarProdutosPorDesc(@PathVariable("desc")String desc){
		List<CategoriaProduto>listaProdutos = categoriaProdutoService.buscarProdutosPorDesc(desc.trim().toUpperCase());
		return new ResponseEntity<List<CategoriaProduto>>(listaProdutos,HttpStatus.OK);
	}
	@ResponseBody
	@DeleteMapping("**/CategoriaProduto/deletarProdutosPorId/{id}")
	public ResponseEntity<?> deletarProdutosPorId(@PathVariable("id")Long id){
		 categoriaProdutoService.deletarProdutosPorId(id);
		return new ResponseEntity<>("Deletado com sucesso",HttpStatus.OK);
	}
	@ResponseBody
	@PostMapping("**/CategoriaProduto/salvarCategoriaProdutos")
	public ResponseEntity<CategoriaProduto>salvarCategoriaProdutos(@RequestBody CategoriaProduto categoriaProduto) throws ExceptionMentoriaJava{

		categoriaProduto=categoriaProdutoService.salvarCategoriaProduto(categoriaProduto);
		return new ResponseEntity<CategoriaProduto>(categoriaProduto,HttpStatus.OK);
	}
	
	@ResponseBody
	@PutMapping("**/CategoriaProduto/atualizarCategoriaProdutos")
	public ResponseEntity<CategoriaProduto>atualizarCategoriaProdutos(@RequestBody CategoriaProduto categoriaProduto) throws ExceptionMentoriaJava{

		categoriaProduto=categoriaProdutoService.atualizarCategoriaProdutos(categoriaProduto);
		return new ResponseEntity<CategoriaProduto>(categoriaProduto,HttpStatus.OK);
	}
}
