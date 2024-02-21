package com.schoolpayments.maintenance.domain.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SchoolSettlement {

	private Parent parent;
	private Double totalFee;
	private List<StudentSettlement> studentSettlement;
}
