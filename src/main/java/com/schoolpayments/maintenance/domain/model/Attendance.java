package com.schoolpayments.maintenance.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Attendance {

	private int id;
	private int studentId;
	
	private LocalDateTime entryDate;
	private LocalDateTime exitDate;
}
