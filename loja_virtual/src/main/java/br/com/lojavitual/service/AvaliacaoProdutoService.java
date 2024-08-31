package br.com.lojavitual.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.lojavitual.DTO.AvaliacaoProdutoDTO;
import br.com.lojavitual.excecoes.ExceptionMentoriaJava;
import br.com.lojavitual.model.AvaliacaoProduto;
import br.com.lojavitual.repository.AvaliacaoProdutoRepository;

@Service
public class AvaliacaoProdutoService {

	@Autowired
	AvaliacaoProdutoRepository avaliacaoProdutoRepository;
	
	@Autowired
	private PessoaFisicaService pessoaFisicaService;

	@Autowired
	private ModelMapper modelMapper;
	
	public List<AvaliacaoProdutoDTO> buscaAvaliacaoPessoa(Long idPessoa) {

		List<AvaliacaoProduto> buscaAvaliacaoPessoa = avaliacaoProdutoRepository.buscaAvaliacaoPessoa(idPessoa); 
		AvaliacaoProdutoDTO dto = new AvaliacaoProdutoDTO();
	    List<AvaliacaoProdutoDTO>listadto = new ArrayList<>();
		for (AvaliacaoProduto avaliacaoProduto : buscaAvaliacaoPessoa) {
			
			listadto.add(dto.coverterDto(avaliacaoProduto));
		}
		return listadto;

	}

	public List<AvaliacaoProduto> buscaAvaliacaoProduto(Long idProduto) {
		
		List<AvaliacaoProduto>listabuscaAvaliacaoProduto = avaliacaoProdutoRepository.buscaAvaliacaoProduto(idProduto);
		return listabuscaAvaliacaoProduto;
	}

	public List<AvaliacaoProduto> buscaAvaliacaoProdutoPorPessoa(Long idProduto, Long idPessoa) {

		List<AvaliacaoProduto>listabuscaAvaliacaoProdutoPorPessoa = avaliacaoProdutoRepository.buscaAvaliacaoProdutoPorPessoa(idProduto,idPessoa);
		return listabuscaAvaliacaoProdutoPorPessoa;
	}

	public String deletarAvaliacaoProduto(Long id) {
		String msg="";
		if(!avaliacaoProdutoRepository.existsById(id)) {
			return msg = "Avaliação ja foi deletada ou nao existe";
		}else {
			avaliacaoProdutoRepository.deleteById(id);
			return msg = "Deletado com sucesso";
		}

	}

	public AvaliacaoProdutoDTO salvarAvaliacaoProduto(AvaliacaoProduto avaliacaoProduto) throws ExceptionMentoriaJava {
		ModelMapper mapper = new ModelMapper();
		AvaliacaoProdutoDTO avaliacaoProdutoDTO = new AvaliacaoProdutoDTO();
		if(avaliacaoProduto == null) {
			throw new ExceptionMentoriaJava("Informe a Avaliacao Produto");
		}
		if(avaliacaoProduto.getEmpresa() == null || (avaliacaoProduto.getEmpresa().getId()<=0 && avaliacaoProduto.getEmpresa()!= null )) {
			throw new ExceptionMentoriaJava("Informe a Empresa");
		}
		if(avaliacaoProduto.getPessoa() == null || (avaliacaoProduto.getPessoa().getId()<=0 && avaliacaoProduto.getPessoa()!= null )|| !pessoaFisicaService.existePessoaFisica(avaliacaoProduto.getPessoa().getId())) {
			throw new ExceptionMentoriaJava("Informe a Pessoa");
		}
		if(avaliacaoProduto.getProduto() == null || (avaliacaoProduto.getProduto().getId()<=0 && avaliacaoProduto.getProduto()!= null )) {
			throw new ExceptionMentoriaJava("Informe o Produto");
		}
		avaliacaoProduto = avaliacaoProdutoRepository.save(avaliacaoProduto);
		avaliacaoProdutoDTO = mapper.map(avaliacaoProduto, avaliacaoProdutoDTO.getClass());
		return avaliacaoProdutoDTO;
	}
}
