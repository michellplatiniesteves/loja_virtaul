package br.com.lojavitual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.lojavitual.model.PessoaFisica;

public interface PessoaFisicaRepository extends CrudRepository<PessoaFisica, Long> {
	
	@Query(value = "select pf from PessoaFisica pf where pf.cpf= ?1")
	public PessoaFisica existeCpfPessoaFisica(String cpf);
	
	@Query(value = "select pf from PessoaFisica pf where pf.cpf= ?1")
	public List<PessoaFisica> listaCpfPessoaFisica(String cpf);
	
	@Query(value = "select pf from PessoaFisica pf where upper(trim(pf.nome)) like %?1%")
	public List<PessoaFisica> listaNomePessoaFisica(String nome);

}
