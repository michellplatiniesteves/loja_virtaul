package br.com.lojavitual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.lojavitual.model.PessoaJuridica;

@Repository
public interface PessoaJuridicaRepository extends CrudRepository<PessoaJuridica, Long> {
	
	@Query(value = "select pj from PessoaJuridica pj where pj.cnpj = ?1")
	public PessoaJuridica existeCnpj(String cnpj);
	
	@Query(value = "select pj from PessoaJuridica pj where pj.cnpj = ?1")
	public List<PessoaJuridica> listaPessoaJuridicaPorCnpj(String cnpj);
	
	@Query(value = "select pj from PessoaJuridica pj where pj.email = ?1")
	public PessoaJuridica existeLogin(String email);
	
	@Query(value = "select pj from PessoaJuridica pj where upper(trim(pj.nome)) like %?1%")
	public List<PessoaJuridica> listaPessoaJuridicaPorNome(String nome);
	
	@Query(value = "select pj from PessoaJuridica pj where upper(trim(pj.nomeFastasia)) like %?1%")
	public List<PessoaJuridica> listaPessoaJuridicaPorNomeFastasia(String nomeFantasia);
	
}
