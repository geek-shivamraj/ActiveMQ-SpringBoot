package com.activemq.classic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

import com.activemq.classic.model.Email;

import lombok.extern.slf4j.Slf4j;

/*
 * 
 * Isolated Example of ActiveMQ
 * Both Producer & Consumer here
 * 
 * This example is independent of any other class files.
 * 
 */

@SpringBootApplication
@Slf4j
public class Demo_A_ActiveMQ {

	public static void main(String[] args) {

		// Launch the application
		ConfigurableApplicationContext context = SpringApplication.run(Demo_B_ConsumerProducer.class, args);

		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

		// Send a message with a POJO - the template reuse the message converter
		log.info("[Demo-A-Producer]: Sending a Yahoo message...");
		jmsTemplate.convertAndSend("yahooMailBox", new Email("abcd@yahoo.com", "Hello this is a demo email!!"));
		log.info("[Demo-A-Producer]: Yahoo mail sent!");
	}
	
	@JmsListener(destination = "yahooMailBox", containerFactory = "jmsListenerContainerFactory")
	public void receiveMessage(Email email) {
		log.info("[Demo-A-Consumer]: Received <{}>", email);
	}

//	@Bean
//	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
//			DefaultJmsListenerContainerFactoryConfigurer configurer) {
//		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//		// This provides all auto-configured defaults to this factory, including the
//		// message converter
//		// factory.setMessageConverter(jacksonJmsMessageConverter());
//		configurer.configure(factory, connectionFactory);
//		// You could still override some settings if necessary.
//		return factory;
//	}
//
//	@Bean // Serialize message content to json using TextMessage
//	public MessageConverter jacksonJmsMessageConverter() {
//		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//		converter.setTargetType(MessageType.TEXT);
//		converter.setTypeIdPropertyName("_type");
//		return converter;
//	}

}
