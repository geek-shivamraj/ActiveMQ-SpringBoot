package com.activemq.classic.jms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.activemq.classic.model.Department;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Demo_C_TopicConsumer {

	@Value("${spring.activemq.first.topic}")
    String firstTopic;
	
	@Value("${spring.activemq.second.topic}")
    String secondTopic;
	
	
    @JmsListener(destination = "${spring.activemq.first.topic}")
    @SendTo("${spring.activemq.second.topic}")
    public Department receiveAndForwardMessageFromTopic(final Message jsonMessage) throws JMSException, JsonProcessingException, InterruptedException {
    	Thread.sleep(2000L);
        String messageData = null;
        log.info("[Demo-C-Consumer-Topic]: Received message from [{}]: \n{}", firstTopic, jsonMessage);
        if(jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;
            messageData = textMessage.getText();
            log.info("[Demo-C-Consumer-Topic]: [{}] Content: {}\n", firstTopic, messageData);
        }
        return new ObjectMapper().readValue(messageData, Department.class);
    }


    @JmsListener(destination = "${spring.activemq.second.topic}")
    public void receiveMessageFromTopic(final Message jsonMessage) throws JMSException, InterruptedException {
    	Thread.sleep(3000L);
        String messageData = null;
        log.info("[Demo-C-Consumer-Topic]: Received message from [{}]: \n{}", secondTopic, jsonMessage);
        if(jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;
            messageData = textMessage.getText();
            log.info("[Demo-C-Consumer-Topic]: [{}] Content: \n{}\n", secondTopic, messageData);
        }
    }
    
}
