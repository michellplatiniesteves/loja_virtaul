package br.com.lojavitual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.CategoriaProduto;
import br.com.lojavitual.repository.CategoriaProdutoRepository;
import br.com.lojavitual.repository.PessoaJuridicaRepository;

@Service
public class CategoriaProdutoService {

	@Autowired
	CategoriaProdutoRepository categoriaProdutoRepository;
	@Autowired
	PessoaJuridicaRepository pessoaJuridicaRepository;

	public List<CategoriaProduto> buscarTodosProdutos() {
		List<CategoriaProduto> listaprodutos = (List<CategoriaProduto>) categoriaProdutoRepository.findAll();
		return listaprodutos;
	}

	public CategoriaProduto buscarProdutosPorId(Long id) {
		CategoriaProduto categoriaProduto = categoriaProdutoRepository.findById(id).get();
		return categoriaProduto;
	}

	public List<CategoriaProduto> buscarProdutosPorDesc(String desc) {
		List<CategoriaProduto> listaprodutos = (List<CategoriaProduto>) categoriaProdutoRepository.buscarProdutosPorDesc(desc);
		return listaprodutos;
	}

	public void deletarProdutosPorId(Long id) {
		categoriaProdutoRepository.deleteById(id);

	}

	public CategoriaProduto salvarCategoriaProduto(CategoriaProduto categoriaProduto) throws ExceptionMentoriaJava {
		if(categoriaProduto == null) {
			throw new ExceptionMentoriaJava("Nenhum registro foi enviado");
		}
		if(categoriaProduto.getId() == null && categoriaProdutoRepository.descricaoCadastrada(categoriaProduto.getNomeDesc().trim().toUpperCase())) {
			throw new ExceptionMentoriaJava("Nome da descrição ja está cadastrado");
		}
		categoriaProduto = categoriaProdutoRepository.save(categoriaProduto);
		return categoriaProduto;
	}

	public CategoriaProduto atualizarCategoriaProdutos(CategoriaProduto categoriaProduto) throws ExceptionMentoriaJava {
		 categoriaProduto=salvarCategoriaProduto(categoriaProduto);
		return categoriaProduto;
	}
}
