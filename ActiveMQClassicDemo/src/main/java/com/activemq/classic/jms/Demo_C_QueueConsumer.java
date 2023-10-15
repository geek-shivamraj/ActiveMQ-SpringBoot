package com.activemq.classic.jms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.activemq.classic.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class Demo_C_QueueConsumer {

	@Value("${spring.activemq.first.queue}")
    String firstQueue;
	
	@Value("${spring.activemq.second.queue}")
    String secondQueue;

	
    @JmsListener(destination = "${spring.activemq.first.queue}")
    @SendTo("${spring.activemq.second.queue}")
    public Employee receiveAndForwardMessageFromQueue(final Message jsonMessage) throws JMSException, InterruptedException, JsonProcessingException {
    	Thread.sleep(2000L);
        String messageData = null;
        log.info("[Demo-C-Consumer-Queue]: Received message from [{}]: \n{}", firstQueue, jsonMessage);
        if(jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;
            messageData = textMessage.getText();
            log.info("[Demo-C-Consumer-Queue]: [{}] Content: {}\n", firstQueue, messageData);
        }
        return new ObjectMapper().readValue(messageData, Employee.class);
    }

    
    @JmsListener(destination = "${spring.activemq.second.queue}")
    public void receiveForwardedMessageFromQueue(final Message jsonMessage) throws JMSException, InterruptedException, JsonProcessingException {
    	Thread.sleep(3000L);
        String messageData = null;
        log.info("[Demo-C-Consumer-Queue]: Received message from [{}]: \n{}", secondQueue, jsonMessage);
        if(jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;
            messageData = textMessage.getText();
            log.info("[Demo-C-Consumer-Queue]: [{}] Content:\n {}\n", secondQueue, messageData);
        }
        
    }
    
    
}
