package com.mac.bry.crud.utilitis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.stereotype.Component;

@Component
public class EmailSender {

	public static void configureMail(String reciver, String newPassword) {
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream stream = classLoader.getResourceAsStream("mail.properties");
		try {
			properties.load(stream);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		final String fromEmail = properties.getProperty("sender"); 
		final String password = properties.getProperty("password"); 
		String toEmail = reciver; 

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.wp.pl"); 
		props.put("mail.smtp.socketFactory.port", "465"); 
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.port", "465"); 

		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getDefaultInstance(props, auth);
		EmailUtil.sendEmail(session, toEmail, "Password Reset - CRUD APP",
				"Your new Password is: " + newPassword);
	}
}
