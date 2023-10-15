package com.activemq.classic.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.activemq.classic.model.Email;

import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class Demo_B_Consumer {

	@JmsListener(destination = "gmailbox", containerFactory = "jmsListenerContainerFactory")
	public void receiveMessage(Email email) {
		log.info("[Demo-B-Consumer]: Received <{}>", email);
	}
	
}
