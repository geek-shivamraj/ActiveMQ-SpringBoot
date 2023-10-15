package com.activemq.classic.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderTransaction {

	//@Id
	private String id;
	private String from;
	private String to;
	private BigDecimal amount;
}
