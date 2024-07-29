package br.com.lojavitual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.Produto;
import br.com.lojavitual.repository.ProdutoRepository;

@Service
public class ProdutoService {

	
	@Autowired
	ProdutoRepository produtoRepository;

	public List<Produto> buscarTodosProdutos() {
		List<Produto> produtos = (List<Produto>) produtoRepository.findAll();
		return produtos;
	}

	public Produto buscarProdutosPorId(Long id) {
		Produto produto = produtoRepository.findById(id).get();
		return produto;
	}

	public List<Produto> buscarProdutosNome(String nome) {
		List<Produto> produtos =produtoRepository.findByNome(nome);
		return produtos;
	}

	public void deletarProdutosPorId(Long id) {
		produtoRepository.deleteById(id);
		
	}

	public Produto salvarProdutos(Produto produto) throws ExceptionMentoriaJava {
		if(produto == null ) {
			throw new ExceptionMentoriaJava("Informe um produto");
		}
		if(produto.getEmpresa() == null || produto.getEmpresa().getId()< 0) {
			throw new ExceptionMentoriaJava("Informe a Empresa");
		}
		if(produto.getCategoriaProduto() == null || produto.getCategoriaProduto().getId()< 0) {
			throw new ExceptionMentoriaJava("Informe a Categoria do Produto");
		}
		if(produto.getMarcaProduto() == null || produto.getMarcaProduto().getId()< 0) {
			throw new ExceptionMentoriaJava("Informe a Marca do Produto");
		}
		if(produto.getId() == null && produtoRepository.existeProdutoCadastrado(produto.getNome(),produto.getEmpresa().getId())) {
			throw new ExceptionMentoriaJava("Nome de produto ja cadastrado");
		}
	    produto = produtoRepository.save(produto);
		return produto;
	}
}
