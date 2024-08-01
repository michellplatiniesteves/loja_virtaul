package br.com.lojavitual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.lojavitual.model.ContaPagar;

@Repository
public interface ContaPagarRepository extends JpaRepository<ContaPagar, Long> {

	@Query(value = " select cp from ContaPagar cp where upper(trim(cp.descricao)) = upper(trim(?1)) ")
	List<ContaPagar> buscarPorDesc(String desc);
	
	@Query(value = " select count(1) > 0 from ContaPagar cp where upper(trim(cp.descricao)) = upper(trim(?1)) ")
	boolean existeContaPagar(String desc);
	
	@Query(value = " select cp from ContaPagar cp where cp.pessoa.id = ?1 ")
	ContaPagar buscarPorPessoa(Long id);
	
	@Query(value = " select cp from ContaPagar cp where cp.pessoaFornecedor.id = ?1 ")
	ContaPagar buscarPorFornecedor(Long id);
	
	@Query(value = " select cp from ContaPagar cp where cp.empresa.id = ?1 ")
	ContaPagar buscarPorEmpresa(Long id);
	
	@Query(value = " select cp from ContaPagar cp where cp.id = ?1 ")
	ContaPagar buscarContaPagar(Long id);

}
