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

import br.com.lojavitual.DTO.AvaliacaoProdutoDTO;
import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.AvaliacaoProduto;
import br.com.lojavitual.service.AvaliacaoProdutoService;

@RequestMapping("AvaliacaoProduto/")
@RestController
public class AvaliacaoProdutoController {

	@Autowired
	AvaliacaoProdutoService avaliacaoProdutoService;
	
	@ResponseBody
	@GetMapping(value = "buscaAvaliacaoPessoa/{idPessoa}")
	public ResponseEntity<List<AvaliacaoProduto>>buscaAvaliacaoPessoa(@PathVariable("idPessoa")Long idPessoa){
	
		List<AvaliacaoProduto>listabuscaAvaliacaoPessoa=avaliacaoProdutoService.buscaAvaliacaoPessoa(idPessoa);
		return new ResponseEntity<List<AvaliacaoProduto>>(listabuscaAvaliacaoPessoa,HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "buscaAvaliacaoProdutoPorPessoa/{idProduto}/{idPessoa}")
	public ResponseEntity<List<AvaliacaoProduto>>buscaAvaliacaoProdutoPorPessoa(@PathVariable("idProduto")Long idProduto,@PathVariable("idPessoa")Long idPessoa){
	
		List<AvaliacaoProduto>listabuscaAvaliacaoProdutoPorPessoa=avaliacaoProdutoService.buscaAvaliacaoProdutoPorPessoa(idProduto,idPessoa);
		return new ResponseEntity<List<AvaliacaoProduto>>(listabuscaAvaliacaoProdutoPorPessoa,HttpStatus.OK);
	}
	@ResponseBody
	@GetMapping(value = "buscaAvaliacaoProduto/{idProduto}")
	public ResponseEntity<List<AvaliacaoProduto>>buscaAvaliacaoProduto(@PathVariable("idProduto")Long idProduto){
	
		List<AvaliacaoProduto>listabuscaAvaliacaoProduto=avaliacaoProdutoService.buscaAvaliacaoProduto(idProduto);
		return new ResponseEntity<List<AvaliacaoProduto>>(listabuscaAvaliacaoProduto,HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping(value = "deletarAvaliacaoProduto/{id}")
	public ResponseEntity<String>deletarAvaliacaoProduto(@PathVariable("id")Long id){
	
		String mensagem = avaliacaoProdutoService.deletarAvaliacaoProduto(id);
		return new ResponseEntity<String>(mensagem,HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping(value = "salvarAvaliacaoProduto")
	public ResponseEntity<AvaliacaoProduto>salvarAvaliacaoProduto(@RequestBody @Valid AvaliacaoProduto avaliacaoProduto) throws ExceptionMentoriaJava{
	
		avaliacaoProduto = avaliacaoProdutoService.salvarAvaliacaoProduto(avaliacaoProduto);
		return new ResponseEntity<AvaliacaoProduto>(avaliacaoProduto,HttpStatus.OK);
	}
}
