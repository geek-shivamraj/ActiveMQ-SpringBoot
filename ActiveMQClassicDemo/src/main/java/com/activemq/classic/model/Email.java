package com.activemq.classic.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Email implements Serializable {

	private String to;
	private String body;
	
	public Email() {}
	public Email(String to, String body) {
		this.to = to;
		this.body = body;
	}
}
