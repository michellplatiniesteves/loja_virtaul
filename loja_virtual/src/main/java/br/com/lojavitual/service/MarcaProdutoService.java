package br.com.lojavitual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.MarcaProduto;
import br.com.lojavitual.repository.MarcaProdutoRepository;

@Service
public class MarcaProdutoService {
	
	@Autowired
    MarcaProdutoRepository marcaProdutoRepository;
	
	public List<MarcaProduto> buscaTodasMarcaProduto() {
		List<MarcaProduto> listaMarcaProduto = (List<MarcaProduto>) marcaProdutoRepository.findAll();
		return listaMarcaProduto;
	}

	public List<MarcaProduto> buscaMarcaProdutoPorNome(String nomeDesc) {
		List<MarcaProduto> listaMarcaProduto = (List<MarcaProduto>) marcaProdutoRepository.buscaMarcaProdutoPorNome(nomeDesc);
		return listaMarcaProduto;
	}

	public MarcaProduto buscaTodasMarcaProdutoPorId(Long id) {
		MarcaProduto marcaProduto =  marcaProdutoRepository.findById(id).get();
		return marcaProduto;
	}

	public void deletarMarcaProdutoPorId(Long id) {
		marcaProdutoRepository.deleteById(id);		
	}

	public MarcaProduto salvarMarcaProduto(MarcaProduto marcaProduto) throws ExceptionMentoriaJava {
		
		if(marcaProduto == null ) {
			throw new ExceptionMentoriaJava("Informe a Marca Produto");
		}
		if(marcaProduto.getEmpresa() == null || marcaProduto.getEmpresa().getId()< 0) {
			throw new ExceptionMentoriaJava("Informe a Empresa");
		}
		if(marcaProduto == null || marcaProdutoRepository.existeMarcaProduto(marcaProduto.getNomeDesc())) {
			throw new ExceptionMentoriaJava("Marca de Produto já cadastrado");
		}
		marcaProduto = marcaProdutoRepository.save(marcaProduto);
		return marcaProduto;
	}

}
