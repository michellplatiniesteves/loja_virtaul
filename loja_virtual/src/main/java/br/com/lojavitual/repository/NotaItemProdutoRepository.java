package br.com.lojavitual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.lojavitual.model.NotaItemProduto;

@Repository
public interface NotaItemProdutoRepository extends JpaRepository<NotaItemProduto, Long> {

	@Query(value = "select ntc from NotaItemProduto ntc where ntc.produto.id = ?1 ")
	List<NotaItemProduto> buscarNotaItemProdutoPorProduto(Long id);

	@Query(value = "select ntc from NotaItemProduto ntc where ntc.notaFiscalCompra.id = ?1 ")
	List<NotaItemProduto> buscarNotaItemProdutoPorEmpresa(Long id);

	@Query(value = "select ntc from NotaItemProduto ntc where ntc.empresa.id = ?1 ")
	List<NotaItemProduto> buscarNotaItemProdutoPorContaPagar(Long id);


}
