package br.com.lojavitual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.lojavitual.model.CategoriaProduto;
@Repository
public interface CategoriaProdutoRepository extends CrudRepository<CategoriaProduto, Long>{

	@Query(value = "select cp from CategoriaProduto cp where upper(trim(cp.nomeDesc)) like %?1% ")
	public List<CategoriaProduto> buscarProdutosPorDesc(String desc);

	@Query(value = " select count(1) > 0 from CategoriaProduto where upper(trim(nomeDesc)) =  upper(trim(?1))")
	public boolean descricaoCadastrada(String nomeDesc);

}
