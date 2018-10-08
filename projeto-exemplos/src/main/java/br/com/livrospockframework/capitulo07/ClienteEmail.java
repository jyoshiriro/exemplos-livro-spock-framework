package br.com.livrospockframework.capitulo07;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class ClienteEmail {
	
	public static final String ERRO_AUTENTICACAO = "Login e/ou senha inválidos";
	public static final String ERRO_COMUNICACAO_ENVIO = "Falha na comunicação com o servidor SMTP %s";
	public static final String ERRO_NAO_ESPERADO = "Erro não esperado";

	public void enviar(String destinatario, String assunto, String conteudo) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("jyoshiriro", "jy@r1981"));
		email.setSSLOnConnect(true);
		email.setFrom("jyoshiriro@gmail.com");
		email.setSubject("TestMail");
		email.setMsg("This is a test mail ... :-)");
		email.addTo("jose.yoshihiro@bandtec.com.br");
		email.send();
	}

	public static void main(String[] args) {
//		try {
////			new ClienteEmail().enviar();
//		} catch (EmailException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
