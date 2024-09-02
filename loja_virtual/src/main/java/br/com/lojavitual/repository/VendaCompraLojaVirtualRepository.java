package br.com.lojavitual.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.lojavitual.model.VendaCompraLojaVirtual;

@Repository
public interface VendaCompraLojaVirtualRepository extends JpaRepository<VendaCompraLojaVirtual, Long> {
	
	@Query(value = "select vclv from VendaCompraLojaVirtual vclv where vclv.pessoa.id = ?1")
	List<VendaCompraLojaVirtual> buscarVendaCompraLojaVirtualPorPessoa(Long id);
	
	@Query( value=" select vclv from VendaCompraLojaVirtual vclv  where vclv.id = ?1 ")
	VendaCompraLojaVirtual buscarVendaCompraLojaVirtualPorId(Long id);
	
    @Query(value = "select vclv from VendaCompraLojaVirtual vclv where vclv.empresa.id= ?1")
	List<VendaCompraLojaVirtual> buscarVendaCompraLojaVirtualPorEmpresa(Long id);

    @Query(value = "select vclv from VendaCompraLojaVirtual vclv where vclv.notaFiscalVenda.id= ?1")
	List<VendaCompraLojaVirtual> buscarVendaCompraLojaVirtualPorNotaFiscalVenda(Long id);

    @Query(value = "select vclv from VendaCompraLojaVirtual vclv where vclv.formaPagamento.id= ?1")
	List<VendaCompraLojaVirtual> buscarVendaCompraLojaVirtualPorFormaPagamento(Long id);

    
	@Query( value=" select vclv from VendaCompraLojaVirtual vclv  where vclv.id = ?1 and vclv.excluido = false")
	VendaCompraLojaVirtual buscaLogicaVendaCompraLojaVirtualPorId(Long id);

	@Query( value=" select vclv from VendaCompraLojaVirtual vclv  where vclv.dataVenda >= ?1 and vclv.dataVenda <= ?2 ")
	List<VendaCompraLojaVirtual> buscarVendaCompraLojaVirtualPorIntervalo(Date data1Formatada,
			Date data2form);

}
