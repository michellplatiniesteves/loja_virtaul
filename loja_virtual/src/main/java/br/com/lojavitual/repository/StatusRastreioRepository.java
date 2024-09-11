package br.com.lojavitual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.lojavitual.model.StatusRastreio;

@Repository
public interface StatusRastreioRepository extends JpaRepository<StatusRastreio,Long> {

	@Query(value = "select sr from StatusRastreio sr where sr.vendaCompraLojaVirtual.id= ?1")
	public List<StatusRastreio>buscaStatusRasteioPoVenda(Long id);

}
