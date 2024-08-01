package br.com.lojavitual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lojavitual.model.NotaFiscalCompra;

@Repository
public interface NotaFiscalCompraRepository extends JpaRepository<NotaFiscalCompra, Long>{

	@Query(value = "select ntc from NotaFiscalCompra ntc where upper(trim(ntc.descricaoObs)) = upper(trim(?1)) ")
	List<NotaFiscalCompra> buscarNotaFiscalCompraDesc(String desc);
	
	@Query(value = "select count(1)>0 from NotaFiscalCompra ntc where upper(trim(ntc.descricaoObs)) = upper(trim(?1)) ")
	Boolean existeNotaFiscalCompra(String desc);

	@Query(value = "select ntc from NotaFiscalCompra ntc where upper(trim(ntc.numeroNota)) = upper(trim(?1)) ")
	List<NotaFiscalCompra> buscarNotaFiscalCompraNumeroNota(String numeroNota);
	
	@Query(value = "select ntc from NotaFiscalCompra ntc where ntc.id = ?1 ")
	List<NotaFiscalCompra> buscarNotaFiscalCompraPorId(Long id);
	
	@Query(value = "select ntc from NotaFiscalCompra ntc where ntc.empresa.id = ?1 ")
	List<NotaFiscalCompra> buscarNotaFiscalCompraPorEmpresa(Long id);
	
	@Query(value = "select ntc from NotaFiscalCompra ntc where ntc.contaPagar.id = ?1 ")
	List<NotaFiscalCompra> buscarNotaFiscalCompraPorContaPagar(Long id);
	
	@Query(value = "select ntc from NotaFiscalCompra ntc where ntc.pessoa.id = ?1 ")
	List<NotaFiscalCompra> buscarNotaFiscalCompraPorPessoa(Long id);

	@Transactional()
	@Modifying(flushAutomatically = true,clearAutomatically = true)
	@Query(nativeQuery =  true,value = " delete from nota_item_produto where id = ?1")
	void deletarItemNotaItemProduto(Long id);
}
