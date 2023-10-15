package com.activemq.classic.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import jakarta.jms.ConnectionFactory;

@Configuration
@EnableJms
public class ActiveMQConfig {

	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;
	
	
	@Bean
	public ConnectionFactory connectionFactory() {
		
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(brokerUrl);
		
		return activeMQConnectionFactory;
	}
	
	
	@Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setMessageConverter(jacksonJmsMessageConverter());

        //factory.setPubSubDomain(true);
        return factory;
    }

	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	
}
