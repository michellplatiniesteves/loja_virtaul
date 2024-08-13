package br.com.lojavitual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.lojavitual.model.NotaFiscalVenda;

@Repository
public interface NotaFiscalVendaRepository extends JpaRepository<NotaFiscalVenda, Long> {
    @Query(value = "select nfv from NotaFiscalVenda nfv where nfv.empresa.id = ?1 ")
	List<NotaFiscalVenda> buscaNotaFiscalVendaPorEmpresa(Long id);

}
