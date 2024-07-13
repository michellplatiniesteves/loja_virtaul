package br.com.lojavitual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.lojavitual.repository.UsuarioRepository;

@Service
public class ImplementacaoUserDetailsService  implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findBtLogin(username);
        if(usuario==null) {
        	throw new UsernameNotFoundException("Usuario nao localizado");
        }
		return new User(usuario.getLogin(), usuario.getPassword(), usuario.getAuthorities());
	}

}
