package br.com.lojavitual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.lojavitual.model.ImagemProduto;

public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto, Long> {
	
   @Query( value = " select ip from ImagemProduto ip where ip.produto.id =?1 ")
	List<ImagemProduto> findByimagensPorProduto(Long idProduto);
   
   @Query(nativeQuery = true,value = " delete from ImagemProduto ip where ip.produto.id = ?1 ")
   void deleteByProduto(Long id);

}
