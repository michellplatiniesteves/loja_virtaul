package br.com.lojavitual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lojavitual.model.Produto;

@Repository
@Transactional
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	public List<Produto> findByNome(String nome);
	
    @Query(nativeQuery=true,value = "select count(1) > 0 from Produto p where upper(trim(p.nome)) = upper(trim(?1)) and p.empresa_id = ?2 ")
	public boolean existeProdutoCadastrado(String nome,Long idempresa);

}
