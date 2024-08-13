package br.com.lojavitual.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.lojavitual.model.ImagemProduto;
import br.com.lojavitual.service.ImagemProdutoService;

@RequestMapping("ImagemProduto")
@RestController
public class ImagemProdutoController {

	@Autowired
	ImagemProdutoService imagemProdutoService; 
	
	@ResponseBody
	@GetMapping(value = "/buscarImagensPorProduto/{idProduto}")
	public ResponseEntity<List<ImagemProduto>>buscarImagensPorProduto(@PathVariable("idProduto")Long idProduto){
		List<ImagemProduto>listabuscarImagens=imagemProdutoService.buscarImagensPorProduto(idProduto);
		return new ResponseEntity<List<ImagemProduto>>(listabuscarImagens,HttpStatus.OK);
	}
	@ResponseBody
	@GetMapping(value = "/buscarImagem/{id}")
	public ResponseEntity<ImagemProduto>buscarImagem(@PathVariable("id")Long id){
		ImagemProduto buscarImagem=imagemProdutoService.buscarImagem(id);
		return new ResponseEntity<ImagemProduto>(buscarImagem,HttpStatus.OK);
	}
	@ResponseBody
	@PostMapping(value = "/salvarImagens")
	public ResponseEntity<ImagemProduto>salvarImagens(@RequestBody @Valid ImagemProduto imagemProduto){
		imagemProduto=imagemProdutoService.salvarImagens(imagemProduto);
		return new ResponseEntity<ImagemProduto>(imagemProduto,HttpStatus.OK);
	}
	@ResponseBody
	@DeleteMapping(value = "/deletarImagens/{id}")
	public ResponseEntity<String>deletarImagens(@PathVariable("id")Long id){
		String resposta = imagemProdutoService.deletarImagens(id);
		return new ResponseEntity<String>(resposta,HttpStatus.OK);
	}
	@ResponseBody
	@DeleteMapping(value = "/deletarImagensPorProdutos/{id}")
	public ResponseEntity<String>deletarImagensPorProdutos(@PathVariable("id")Long id){
		imagemProdutoService.deletarImagensPorProdutos(id);
		return new ResponseEntity<String>("Deletado com sucesso",HttpStatus.OK);
	}
}
