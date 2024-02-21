package com.schoolpayments.maintenance.adapter.persistance.mapper;

import org.springframework.stereotype.Component;

import com.schoolpayments.maintenance.adapter.persistance.entity.AttendanceEntity;
import com.schoolpayments.maintenance.domain.model.Attendance;

@Component
public class AttendanceMapper {

	public Attendance mapToDomainObject(AttendanceEntity attendanceEntity) {
	
		return Attendance.builder()
				.id(attendanceEntity.getId())
	            .studentId(attendanceEntity.getStudentId())
	            .entryDate(attendanceEntity.getEntryDate())
	            .exitDate(attendanceEntity.getExitDate())
	            .build();
	    }
}
