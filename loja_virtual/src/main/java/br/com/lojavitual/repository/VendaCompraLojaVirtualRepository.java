package br.com.lojavitual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.lojavitual.model.VendaCompraLojaVirtual;

@Repository
public interface VendaCompraLojaVirtualRepository extends JpaRepository<VendaCompraLojaVirtual, Long> {
	
	@Query(value = "select vclv from VendaCompraLojaVirtual vclv where vclv.pessoa.id = ?1")
	List<VendaCompraLojaVirtual> buscarVendaCompraLojaVirtualPorPessoa(Long id);
	
    @Query(value = "select vclv from VendaCompraLojaVirtual vclv where vclv.empresa.id= ?1")
	List<VendaCompraLojaVirtual> buscarVendaCompraLojaVirtualPorEmpresa(Long id);

    @Query(value = "select vclv from VendaCompraLojaVirtual vclv where vclv.notaFiscalVenda.id= ?1")
	List<VendaCompraLojaVirtual> buscarVendaCompraLojaVirtualPorNotaFiscalVenda(Long id);

    @Query(value = "select vclv from VendaCompraLojaVirtual vclv where vclv.formaPagamento.id= ?1")
	List<VendaCompraLojaVirtual> buscarVendaCompraLojaVirtualPorFormaPagamento(Long id);

}
