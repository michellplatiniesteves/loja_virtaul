package br.com.lojavitual.service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ServiceSendEmail {
   private String userName="michellplatini.java@gmail.com";
   private String senha="Th@r120386";
   
   @Async
   public void enviarEmail(String assunto, String mensagem, String emailDestino) throws UnsupportedEncodingException, MessagingException {
	   Properties props = new Properties();
	    props.put("mail.smtp.ssl.trust", "*");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls", "false");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "465");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	    
	    Session session = Session.getInstance(props, new Authenticator() {
	    	@Override
	    	protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, senha);
	    		
	    	}
		});
	    session.setDebug(true);
	    Address[] toUser = InternetAddress.parse(emailDestino);
	    Message message = new MimeMessage(session);
	    message.setFrom(new InternetAddress(userName, "Thor ","UTF-8"));
	    message.setRecipients(Message.RecipientType.TO, toUser);
	    message.setSubject(assunto);
	    message.setContent(message, "text/html; charset=utf-8");
	    
	    Transport.send(message) ;
   }
}
