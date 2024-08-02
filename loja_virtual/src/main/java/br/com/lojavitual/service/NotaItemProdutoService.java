package br.com.lojavitual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.NotaItemProduto;
import br.com.lojavitual.repository.NotaItemProdutoRepository;

@Service
public class NotaItemProdutoService {

	@Autowired
	private NotaItemProdutoRepository notaItemProdutoRepository;

	public List<NotaItemProduto> buscarTodasNotaItemProduto() {
		List<NotaItemProduto> listabuscarTodasNotaItemProduto = notaItemProdutoRepository.findAll();
		return listabuscarTodasNotaItemProduto;
	}

	public List<NotaItemProduto> buscarNotaItemProdutoPorProduto(Long id) {
		List<NotaItemProduto> listabuscarNotaItemProdutoPorProduto = notaItemProdutoRepository.buscarNotaItemProdutoPorProduto(id);
		return listabuscarNotaItemProdutoPorProduto;
	}

	public List<NotaItemProduto> buscarNotaItemProdutoPorEmpresa(Long id) {
		List<NotaItemProduto> listabuscarNotaItemProdutoPorEmpresa = notaItemProdutoRepository.buscarNotaItemProdutoPorEmpresa(id);
		return listabuscarNotaItemProdutoPorEmpresa;
	}

	public List<NotaItemProduto> buscarNotaItemProdutoPorContaPagar(Long id) {
		List<NotaItemProduto> listabuscarNotaItemProdutoPorContaPagar = notaItemProdutoRepository.buscarNotaItemProdutoPorContaPagar(id);
		return listabuscarNotaItemProdutoPorContaPagar;
	}

	public void deletarNotaFiscalCompraPorId(Long id) {
		notaItemProdutoRepository.deleteById(id);
		
	}

	public NotaItemProduto salvarNotaItemProduto(NotaItemProduto notaItemProduto) throws ExceptionMentoriaJava {
		
		if(notaItemProduto == null) {
			throw new ExceptionMentoriaJava("Informe a Nota Item Produto");
		}

		if(notaItemProduto.getEmpresa().getId() == null || notaItemProduto.getEmpresa().getId()<0) {
			throw new ExceptionMentoriaJava("Informe a Empresa para a Nota Item Produto");
		}
		if(notaItemProduto.getProduto().getId() == null || notaItemProduto.getProduto().getId()<0) {
			throw new ExceptionMentoriaJava("Informe uma Pessoa para a Nota Item Produto");
		}
		if(notaItemProduto.getNotaFiscalCompra().getId() == null || notaItemProduto.getNotaFiscalCompra().getId()<0) {
			throw new ExceptionMentoriaJava("Informe um Fornecedor para a Nota Item Produto");
		}
		
		notaItemProduto = notaItemProdutoRepository.save(notaItemProduto);
		notaItemProduto=notaItemProdutoRepository.findById(notaItemProduto.getId()).get();
		return notaItemProduto;
	}
}
