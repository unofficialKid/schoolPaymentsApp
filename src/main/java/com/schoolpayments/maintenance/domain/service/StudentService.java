package com.schoolpayments.maintenance.domain.service;

import java.time.Duration;
import java.util.List;

import org.springframework.stereotype.Component;

import com.schoolpayments.maintenance.domain.model.Attendance;
import com.schoolpayments.maintenance.domain.model.Student;
import com.schoolpayments.maintenance.domain.model.StudentSettlement;
import com.schoolpayments.maintenance.domain.model.School;
import com.schoolpayments.maintenance.domain.repository.AttendancePort;
import com.schoolpayments.maintenance.domain.repository.StudentPort;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class StudentService {
	
	private final AttendanceService attendanceService;
	
	private final StudentPort studentPort;

	public List<Student> retrieveStudentBySchoolId(final int schoolId) {
		return studentPort.findBySchoolId(schoolId);
	}
	
	public List<Student> retrieveStudentByParentId(final int parentId) {
		return studentPort.findByParentId(parentId);
	}
	
	public List<StudentSettlement> calculateStudentsSettlements(final List<Student> students, final String month){
		return students.stream()
                .map(student -> createStudentSettlement(student, month))
                .toList();
	}
	
	private StudentSettlement createStudentSettlement(final Student student, final String month) {
		final List<Attendance> attendances = attendanceService.retrieveAttendancesInMonth(student.getId(), month);
        final double fee = calculateFeeForStudent(attendances, student.getSchool().getHourPrice());
        final Duration timeSpendAtSchool = attendanceService.countTimeSpendAtSchool(attendances);
        
        return StudentSettlement.builder()
                .student(student)
                .fee(fee)
                .schoolTime(timeSpendAtSchool)
                .build();
		
	}
	
	private Double calculateFeeForStudent(final List<Attendance> attendancesInMonth, final Double hourPrice) {
		final Long overtimeHours = attendanceService.calculateAllOverhours(attendancesInMonth);
		return overtimeHours * hourPrice;
	}
}
