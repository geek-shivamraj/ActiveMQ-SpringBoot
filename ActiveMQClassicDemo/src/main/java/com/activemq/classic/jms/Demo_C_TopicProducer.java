package com.activemq.classic.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.activemq.classic.model.Department;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Demo_C_TopicProducer {

	@Value("${spring.activemq.first.topic}")
    String topic;

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendToTopic() throws JsonProcessingException {
    	log.info("[Demo-C-Producer-Topic]: Sending to Topic name: [{}]", topic);
        try {
            Department department = new Department(1,"HR",100);
            String jsonObj = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(department);
            log.info("[Demo-C-Producer-Topic]: Sending Content to Topic [{}]: \n{}", topic, jsonObj);
            jmsTemplate.send(topic, messageCreator -> {
                TextMessage message = messageCreator.createTextMessage();
                message.setText(jsonObj);
                return message;
            });
            log.info("[Demo-C-Producer-Topic]: Sent to Topic name: [{}]!!\n", topic);
        }
        catch (Exception ex) {
            log.error("[Demo-C-Producer-Topic]: ERROR in sending message to Topic: [{}]\n", topic);
        }
    }
    
    
}
