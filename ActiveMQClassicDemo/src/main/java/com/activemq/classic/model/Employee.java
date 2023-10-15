package com.activemq.classic.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private String lastName;
	private Long salary;
	private Date joinedDate;
	private Integer age;
	
	public Employee() {}

	public Employee(String firstName, String lastName, Long salary, Date joinedDate, Integer age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.joinedDate = joinedDate;
		this.age = age;
	}
	
	
	
	
}
