package br.com.lojavitual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.lojavitual.model.MarcaProduto;

@Repository
public interface MarcaProdutoRepository extends CrudRepository<MarcaProduto, Long>{

	@Query(value = "select mp from MarcaProduto mp where upper(trim(mp.nomeDesc))= upper(trim(?1))")
	List<MarcaProduto> buscaMarcaProdutoPorNome(String nomeDesc);
	
	@Query(value = "select count(1) >0 from MarcaProduto mp where upper(trim(mp.nomeDesc))= upper(trim(?1))")
	boolean existeMarcaProduto(String nomeDesc);

}
