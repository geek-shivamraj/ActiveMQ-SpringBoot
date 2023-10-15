package com.activemq.classic.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.activemq.classic.model.Email;

import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class Demo_B_Producer implements CommandLineRunner{

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Override
	public void run(String... args) throws Exception {
		log.info("[Demo-B-Producer]: Sending an email message...");
		jmsTemplate.convertAndSend("gmailbox", new Email("activeMq@gmail.com", "Hello Gmail"));
		log.info("[Demo-B-Producer]: Email sent!!");
	}

}
