package br.com.lojavitual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.lojavitual.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	@Query(value = "select e from Endereco e where e.id= ?1")
	public Endereco consultaCep(Long id);
	
	@Query(value = "select e from Endereco e where e.empresa.id= ?1")
	public List<Endereco> buscaEnderecosEmpresa(Long id);
	
	@Query(value = "select e from Endereco e where e.pessoa.id= ?1")
	public List<Endereco> buscaEnderecosPessoaFisica(Long id);
	
}
