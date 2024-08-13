package br.com.lojavitual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojavitual.model.ImagemProduto;
import br.com.lojavitual.repository.ImagemProdutoRepository;

@Service
public class ImagemProdutoService {
	
	@Autowired
	ImagemProdutoRepository imagemProdutoRepository;
	
	public  ImagemProduto salvarImagens(ImagemProduto imagemProduto) {
		imagemProduto = imagemProdutoRepository.saveAndFlush(imagemProduto);
		return imagemProduto;
	}

	public String deletarImagens(Long id) {
		String resposta;
		if(!imagemProdutoRepository.existsById(id)) {
			return resposta ="Imagem já foi deletado";
		}else {
		imagemProdutoRepository.deleteById(id);
		return resposta ="Deletado com sucesso";
		}
	}

	public void deletarImagensPorProdutos(Long id) {
		imagemProdutoRepository.deleteByProduto(id);
		
	}

	public ImagemProduto buscarImagem(Long id) {
		ImagemProduto imagemProduto=imagemProdutoRepository.findById(id).get();
		return imagemProduto;
	}

	public List<ImagemProduto> buscarImagensPorProduto(Long idProduto) {
		List<ImagemProduto>listabuscarImagens=imagemProdutoRepository.findByimagensPorProduto(idProduto);
		return listabuscarImagens;
	}

}
