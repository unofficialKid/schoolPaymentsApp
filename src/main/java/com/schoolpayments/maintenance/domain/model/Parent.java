package com.schoolpayments.maintenance.domain.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Parent {
	private int id;
	private String firstName;
	private String lastName;
}
