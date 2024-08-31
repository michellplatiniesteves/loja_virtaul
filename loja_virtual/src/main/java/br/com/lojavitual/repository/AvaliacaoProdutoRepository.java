package br.com.lojavitual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.lojavitual.model.AvaliacaoProduto;

@Repository
public interface AvaliacaoProdutoRepository extends JpaRepository<AvaliacaoProduto, Long> {

	@Query(value = "select ap from AvaliacaoProduto ap where ap.pessoa.id = ?1 ")
	public List<AvaliacaoProduto> buscaAvaliacaoPessoa(Long idPessoa);
	
	@Query(value = "select ap from AvaliacaoProduto ap where ap.produto.id = ?1 and ap.pessoa.id = ?2 ")
	public List<AvaliacaoProduto>buscaAvaliacaoProdutoPorPessoa(Long idProduto,Long idPessoa);
	
	@Query(value = "select ap from AvaliacaoProduto ap where ap.produto.id = ?1 ")
	public List<AvaliacaoProduto>buscaAvaliacaoProduto(Long idProduto);

}
