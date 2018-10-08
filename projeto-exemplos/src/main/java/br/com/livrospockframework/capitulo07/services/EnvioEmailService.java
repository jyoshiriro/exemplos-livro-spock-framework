package br.com.livrospockframework.capitulo07.services;

import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EnvioEmailService {

	private Email email;
	
	private int enviados;

	public EnvioEmailService(Email email) {
		this.email = email;
	}

	public void enviarEmails(String assunto, String conteudo, List<String> destinatarios) throws EmailException {
		this.email.setSubject(assunto);
		this.email.setMsg(conteudo);
		this.email.addTo(destinatarios.toArray(new String[destinatarios.size()]));

		this.email.send();
		
		this.enviados++;
	}

	
	public int getEnviados() {
		return this.enviados;
	}

	public static void main(String[] args) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(456);
		email.setFrom("jyoshiriro@gmail.com");
		email.setAuthenticator(new DefaultAuthenticator("jyoshiriro", "1234567"));
		email.setSSLOnConnect(true);
		EnvioEmailService service = new EnvioEmailService(email);
		// service.enviarEmail("teste", "conteudo",
		// "jose.yoshihiro@bandtec.com.br");
//		service.enviarEmails("teste", "conteudo",
//				Arrays.asList("jose.yoshiriro@bandtec.com.br", "jyoshiriro@hotmail.com"));
	}

}
