package br.com.lojavitual.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.lojavitual.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	@Query("select u from Usuario u where u.login = ?1")
	Usuario findBtLogin(String login);

	@Query("select u from Usuario u where u.pessoa.id = ?1 or u.pessoa.email= ?2")
	Usuario findByUserPessoaPJ(Long long1, String email);
    @Query(value = "select constraint_name \r\n"
    		+ "      from information_schema.constraint_column_usage  \r\n"
    		+ "      where table_name='usuario_acesso' \r\n"
    		+ "      and constraint_name <>'unique_acesso_user';",nativeQuery = true)
    
	String consultaContraintAcesso();
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "insert into usuario_acesso (usuario_id, acesso_id) values(?1, (select a.id from acesso a where a.descricao='ROLE_USER'))")
	void insereAcessoUsuario(Long id);
}
