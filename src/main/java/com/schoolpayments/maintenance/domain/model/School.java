package com.schoolpayments.maintenance.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class School {

	private int id;
	
	private String name;
	
	private Double hourPrice;
}
