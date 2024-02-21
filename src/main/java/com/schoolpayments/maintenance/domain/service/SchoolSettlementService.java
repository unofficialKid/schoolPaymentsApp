package com.schoolpayments.maintenance.domain.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.schoolpayments.maintenance.domain.model.Student;
import com.schoolpayments.maintenance.domain.model.StudentSettlement;
import com.schoolpayments.maintenance.domain.model.Parent;
import com.schoolpayments.maintenance.domain.model.SchoolSettlement;
import com.schoolpayments.maintenance.domain.port.SchoolSettlementPort;
import com.schoolpayments.maintenance.domain.repository.StudentPort;
import com.schoolpayments.maintenance.domain.validation.DateFormatValidator;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SchoolSettlementService implements SchoolSettlementPort{
	
	private final StudentService studentService;
	private final DateFormatValidator dateValidator;
	
	@Override
	public List<SchoolSettlement> getSettlementForSchool(final int schoolId, final String month) {
		dateValidator.validateYearMonthFormat(month);
		
		List<Student> children = studentService.retrieveStudentBySchoolId(schoolId);
		Map<Parent, List<Student>> studentsByParentId = groupStudentsByParent(children);
		
		List<SchoolSettlement> settlements = studentsByParentId.entrySet().stream()
				.map(entry -> createSchoolSettlement(entry.getKey(), entry.getValue(), month))
				.toList();

		return settlements;
	}
	
	private Map<Parent, List<Student>> groupStudentsByParent(final List<Student> students) {
		return students.stream().collect(Collectors.groupingBy(Student::getParent));
	}
	
	private SchoolSettlement createSchoolSettlement(final Parent parent, final List<Student> students, final String month) {
		List<StudentSettlement> studentSettlements = studentService.calculateStudentsSettlements(students, month);
		Double totalFee = studentSettlements.stream().map(StudentSettlement::getFee).reduce(0.00, Double::sum);
		
		return SchoolSettlement.builder().parent(parent).totalFee(totalFee).studentSettlement(studentSettlements).build();
	}
}
