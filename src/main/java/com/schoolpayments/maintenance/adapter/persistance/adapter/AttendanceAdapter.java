package com.schoolpayments.maintenance.adapter.persistance.adapter;

import java.time.YearMonth;
import java.util.List;

import org.springframework.stereotype.Component;
import com.schoolpayments.maintenance.adapter.persistance.entity.AttendanceEntity;
import com.schoolpayments.maintenance.adapter.persistance.mapper.AttendanceMapper;
import com.schoolpayments.maintenance.adapter.persistance.repository.AttendanceRepository;
import com.schoolpayments.maintenance.domain.model.Attendance;
import com.schoolpayments.maintenance.domain.repository.AttendancePort;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AttendanceAdapter implements AttendancePort{

	private final AttendanceRepository attendanceRepository;
	private final AttendanceMapper attendanceMapper;
	
	@Override
	 public List<Attendance> attendancesInMonth(int studentId, String date) {
        YearMonth yearMonth = YearMonth.parse(date);
        int month = yearMonth.getMonthValue();
        int year = yearMonth.getYear();
        
        List<AttendanceEntity> attendances = attendanceRepository.findByChildIdAndMonth(studentId, month, year);
        return attendances.stream().map(attendance -> attendanceMapper.mapToDomainObject(attendance)).toList();
    }
}
