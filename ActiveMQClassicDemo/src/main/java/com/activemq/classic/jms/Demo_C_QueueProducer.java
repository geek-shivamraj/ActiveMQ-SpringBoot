package com.activemq.classic.jms;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.activemq.classic.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class Demo_C_QueueProducer {

	@Value("${spring.activemq.topic}")
    String topic;

    @Value("${spring.activemq.first.queue}")
    String queue;

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendToQueue() throws JsonProcessingException {
    	log.info("[Demo-C-Producer-Queue]: Sending to Queue name: [{}]", queue);
        try {
            Employee employee = new Employee("Will", "Smith", 20000L, new Date(), 25);
            String jsonObj = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(employee);
            log.info("[Demo-C-Producer-Queue]: Sending Content to Queue [{}]: \n{}", queue, jsonObj);
            jmsTemplate.send(queue, messageCreator -> {
                TextMessage message = messageCreator.createTextMessage();
                message.setText(jsonObj);
                return message;
            });
            log.info("[Demo-C-Producer-Queue]: Sent to Queue name: [{}]!!\n", queue);
        }
        catch (Exception ex) {
            log.error("[Demo-C-Producer-Queue]: ERROR in sending message to queue: [{}]", queue);
        }
    }

}
