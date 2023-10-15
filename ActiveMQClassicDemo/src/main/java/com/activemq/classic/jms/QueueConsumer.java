package com.activemq.classic.jms;

import org.springframework.stereotype.Component;

import com.activemq.classic.model.Employee;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class QueueConsumer implements MessageListener {

	@Override
	public void onMessage(Message message) {
		
		try {
			ObjectMessage objectMessage = (ObjectMessage) message;
			Employee employee = (Employee) objectMessage.getObject();
			
			log.info("Received Message from Topic: {}", employee);
		} catch (JMSException e) {
			log.error("Received Exception while processing message: {}", e);
		}
	}
}
