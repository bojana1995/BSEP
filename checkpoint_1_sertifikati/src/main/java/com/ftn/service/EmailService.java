package com.ftn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ftn.model.Korisnik;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
	//aktivacija korisnickog naloga
	@Async
	public void sendMail(Korisnik korisnik) throws MailException, InterruptedException  {		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("xmlxml12345@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("XML_WS/BSEP: Aktivacija korisnickog naloga");
		mail.setText("Da biste aktivirali Vas korisnicki nalog, molimo posetite sledeci link:\n\n\n http://localhost:9080/korisnik/aktivirajKorisnickiNalog/"+korisnik.getEmail());
		javaMailSender.send(mail);
    }
	
}
