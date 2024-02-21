package com.schoolpayments.maintenance.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {

	private int id;
	private String firstName;
	private String lastName;
	private Parent parent;
	private School school;
}
