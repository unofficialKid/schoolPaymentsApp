package com.schoolpayments.maintenance.domain.service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.schoolpayments.maintenance.domain.model.Attendance;
import com.schoolpayments.maintenance.domain.repository.AttendancePort;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AttendanceService {

	private static final LocalTime REGULAR_START_TIME = LocalTime.of(7, 0);
	private static final LocalTime REGULAR_END_TIME = LocalTime.of(12, 0);
	
	private final AttendancePort attendancePort;
	 
	public List<Attendance> retrieveAttendancesInMonth(final int studentId, final String month){
		return attendancePort.attendancesInMonth(studentId, month);
	}
	
	public long calculateAllOverhours(final List<Attendance> attendances) {
 		return attendances.stream()
				.mapToLong(attendance -> calculateOvertime(attendance.getEntryDate().toLocalTime(), attendance.getExitDate().toLocalTime()))
				.sum();
	}
	
	public Duration countTimeSpendAtSchool(final List<Attendance> attendances) {
		return attendances.stream()
				.map(att -> Duration.between(att.getEntryDate().toLocalTime(), att.getExitDate().toLocalTime()))
				.reduce(Duration.ZERO, Duration::plus);
	}
	
	private long calculateOvertime(final LocalTime entryTime, final LocalTime exitTime) {
        long morningOverhours = calculateOverhours(entryTime, REGULAR_START_TIME);
        long afternoonOverhours = calculateOverhours(REGULAR_END_TIME, exitTime);
        return morningOverhours + afternoonOverhours;
    }
	
	 private static long calculateOverhours(final LocalTime startTime, final LocalTime endTime) {
	        if (endTime.isBefore(startTime)) {
	            return 0;
	        }
	        return Duration.between(startTime, endTime).plusMinutes(59).toHours();
	 }   
}
