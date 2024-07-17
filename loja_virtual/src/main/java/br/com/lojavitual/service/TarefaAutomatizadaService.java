package br.com.lojavitual.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.lojavitual.model.Usuario;
import br.com.lojavitual.repository.UsuarioRepository;

@Service
public class TarefaAutomatizadaService {
    @Autowired
	private UsuarioRepository usuarioRepository;
    
	@Autowired
	ServiceSendEmail serviceSendEmail;
	
	//@Scheduled(initialDelay = 2000,fixedDelay = 86400000)
	@Scheduled(cron="0 0 11 * * *",zone = "America/Sao_Paulo")
	public void notificarUsuarioTrocaSenha() throws UnsupportedEncodingException, MessagingException, InterruptedException {
		
		List<Usuario> lista = usuarioRepository.usuarioSenhaVencida();
		for (Usuario usuario : lista) {
			
			StringBuilder msg = new StringBuilder();
			msg.append("Olá").append(usuario.getPessoa().getNome()).append("<b>").append("Sua senha já está vencida").append("</b>");
			serviceSendEmail.enviarEmail("Troca de Senha", msg.toString(),usuario.getLogin());
			Thread.sleep(3000);
		}
	}
}
