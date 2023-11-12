package com.pbt.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailsenderService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(String toEmail,
			              String subject,
			              String body
			              
			) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("rameshchamling277@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
	
		this.mailSender.send(message);
		
		System.out.println("email send success ");
		
	}
	

}
