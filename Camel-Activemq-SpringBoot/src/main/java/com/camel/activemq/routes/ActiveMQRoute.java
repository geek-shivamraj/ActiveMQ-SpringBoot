package com.camel.activemq.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("timer:active-mq-timer?period=60000").routeId("route-send-message")
			.setBody().constant("[Producer]: Input Message: Hello Message from Apache Camel")
			.log("${body}")
			.to("activemq:my-activemq-queue");
		
		from("activemq:my-activemq-queue").routeId("route-receive-message")
			.log("[Consumer]: Received Message: ${body}");
	}

}
