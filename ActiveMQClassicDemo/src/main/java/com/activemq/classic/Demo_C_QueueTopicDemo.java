package com.activemq.classic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.activemq.classic.jms.Demo_C_QueueProducer;
import com.activemq.classic.jms.Demo_C_TopicConsumer;
import com.activemq.classic.jms.Demo_C_TopicProducer;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootApplication
public class Demo_C_QueueTopicDemo {

	public static void main(String[] args) throws JsonProcessingException {
		
		ConfigurableApplicationContext appContext = SpringApplication.run(Demo_C_QueueTopicDemo.class, args);
		
//		Demo_C_QueueProducer queueProducerService = appContext.getBean(Demo_C_QueueProducer.class);
//		queueProducerService.sendToQueue();
		
		Demo_C_TopicProducer topicProducerService = appContext.getBean(Demo_C_TopicProducer.class);
		topicProducerService.sendToTopic();
	}
}
