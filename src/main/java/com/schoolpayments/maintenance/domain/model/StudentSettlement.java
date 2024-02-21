package com.schoolpayments.maintenance.domain.model;

import java.time.Duration;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class StudentSettlement {

	private Student student;
	private Duration schoolTime;
	private Double fee;
}
