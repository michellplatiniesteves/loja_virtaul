package br.com.lojavitual.controller;

import java.util.List;

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
import br.com.lojavitual.model.MarcaProduto;
import br.com.lojavitual.service.MarcaProdutoService;

@RestController
public class MarcaProdutoController {

	
	@Autowired
	MarcaProdutoService marcaProdutoService;
	
	@ResponseBody
	@GetMapping("MarcaProduto/buscaTodasMarcaProduto")
	public ResponseEntity<List<MarcaProduto>>buscaTodasMarcaProduto(){
		List<MarcaProduto>listaMarcaProduto = marcaProdutoService.buscaTodasMarcaProduto();
	    return new ResponseEntity<List<MarcaProduto>>(listaMarcaProduto,HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("MarcaProduto/buscaMarcaProdutoPorNome/{nomeDesc}")
	public ResponseEntity<List<MarcaProduto>>buscaTodasMarcaProdutoPorNome(@PathVariable("nomeDesc")String nomeDesc){
		List<MarcaProduto>listaMarcaProduto = marcaProdutoService.buscaMarcaProdutoPorNome(nomeDesc);
	    return new ResponseEntity<List<MarcaProduto>>(listaMarcaProduto,HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("MarcaProduto/buscaMarcaProdutoPorId/{id}")
	public ResponseEntity<MarcaProduto>buscaMarcaProdutoPorId(@PathVariable("id")Long id){
		MarcaProduto marcaProduto = marcaProdutoService.buscaTodasMarcaProdutoPorId(id);
	    return new ResponseEntity<MarcaProduto>(marcaProduto,HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping("MarcaProduto/deletarMarcaProdutoPorId/{id}")
	public ResponseEntity<String>deletarMarcaProdutoPorId(@PathVariable("id")Long id){
		 marcaProdutoService.deletarMarcaProdutoPorId(id);
	    return new ResponseEntity<String>("Deletado com sucesso",HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping("MarcaProduto/salvarMarcaProduto")
	public ResponseEntity<MarcaProduto>salvarMarcaProduto(@RequestBody MarcaProduto marcaProduto) throws ExceptionMentoriaJava{
		marcaProduto = marcaProdutoService.salvarMarcaProduto(marcaProduto);
	    return new ResponseEntity<MarcaProduto>(marcaProduto,HttpStatus.OK);
	}
}
