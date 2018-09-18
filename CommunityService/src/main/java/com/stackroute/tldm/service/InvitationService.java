package com.stackroute.tldm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.stackroute.tldm.model.User;


@Service
public class InvitationService {
	
	private JavaMailSender javaMailSender;

	@Autowired
	public InvitationService(JavaMailSender javaMailSender)
{
	this.javaMailSender=javaMailSender;	
}
	public void sendNotification(String userMail,User user)throws MailException
	{
		//send mail
		SimpleMailMessage mail=new SimpleMailMessage();
		
		mail.setTo(userMail);                                 //receiver
		
		mail.setFrom("isamseil9090@gmail.com");               //sender
		
		mail.setSubject("test mail for invitation");
		
		mail.setText("http://pushpalankajaya.blogspot.com") ;

		
		javaMailSender.send(mail);
	}

}
