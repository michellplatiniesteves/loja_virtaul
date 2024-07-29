package br.com.lojavitual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.lojavitual.model.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

	public List<Produto> findByNome(String nome);
    @Query(value = "select count(1) > 0 from Produto where upper(trim(nome)) = upper(trim(?1)) and empresa.id = ?2")
	public boolean existeProdutoCadastrado(String nome,Long idempresa);

}
