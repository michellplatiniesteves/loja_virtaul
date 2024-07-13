package br.com.lojavitual.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.lojavitual.ApplicationContextLoad;
import br.com.lojavitual.model.Usuario;
import br.com.lojavitual.repository.UsuarioRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Service
public class JWTTokenAutenticacaoService {

	private static final long EXPIRATION_TIME=959990000;
	private static final String SECRET ="THOR";
	private static final String TOKEN_PREFIX="Bearer";
	private static final String HEADER_STRING ="Authorization";
	
	
	public void addAuthorization(HttpServletResponse response,String username) throws IOException {
		
		
		String JWT = Jwts.builder()
				    .setSubject(username)
				    .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
				    .signWith(SignatureAlgorithm.HS512, SECRET)
				    .compact();
		
	   String token	= TOKEN_PREFIX + " "+JWT;
	   response.addHeader(HEADER_STRING, token);
	   liberacaoCors(response);
	   
	   response.getWriter().write("{\"Authorization\": \"" + token +"\"}");
	}
	
	public Authentication getAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String token = request.getHeader(HEADER_STRING);
		try {
		if(token!=null) {
			String tokenlimpo =token.replace(TOKEN_PREFIX,"").trim();
			String user = Jwts.parser()
					      .setSigningKey(SECRET)
					      .parseClaimsJws(tokenlimpo)
					      .getBody()
					      .getSubject();
			if(user!=null) {
				Usuario usuario = ApplicationContextLoad
						        .getApplicationContext()
						        .getBean(UsuarioRepository.class)
						        .findBtLogin(user);
				if(usuario!=null) {
					return new UsernamePasswordAuthenticationToken(
							   usuario.getLogin(),
							   usuario.getPassword(),
							   usuario.getAuthorities()
							);
				}
			}
		}
		}catch (SignatureException e) {
			response.getWriter().write("Token invalido");
		}catch (ExpiredJwtException e) {
			response.getWriter().write("Token expirado");
		}	
		finally {
			liberacaoCors(response);
		}
		
		return null;
	}
	
	private void liberacaoCors(HttpServletResponse response) {
		if(response.getHeader("Access-Control-Allow-Origin")==null) {
			response.addHeader("Access-Control-Allow-Origin","*");
		}
		if(response.getHeader("Access-Control-Allow-Headers")==null) {
			response.addHeader("Access-Control-Allow-Headers","*");
		}
		if(response.getHeader("Access-Control-Request-Headers")==null) {
			response.addHeader("Access-Control-Request-Headers","*");
		}
		if(response.getHeader("Access-Control-Allow-Methods")==null) {
			response.addHeader("Access-Control-Allow-Methods","*");
		}
	}
}
