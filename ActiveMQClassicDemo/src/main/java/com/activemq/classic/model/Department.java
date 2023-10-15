package com.activemq.classic.model;

import lombok.Data;

@Data
public class Department {

	private int departmentId;
	private String departmentName;
	private int noOfEmployees;

	public Department() {}

	public Department(int departmentId, String departmentName, int noOfEmployees) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.noOfEmployees = noOfEmployees;
	}
	
}
