package com.schoolpayments.maintenance.domain.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.schoolpayments.maintenance.domain.model.Student;
import com.schoolpayments.maintenance.domain.model.StudentSettlement;
import com.schoolpayments.maintenance.domain.model.Parent;
import com.schoolpayments.maintenance.domain.model.SchoolSettlement;
import com.schoolpayments.maintenance.domain.port.ParentSettlementPort;
import com.schoolpayments.maintenance.domain.repository.StudentPort;
import com.schoolpayments.maintenance.domain.validation.DateFormatValidator;
import com.schoolpayments.maintenance.domain.validation.exception.BusinessException;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ParentSettlementService implements ParentSettlementPort{
		
	private final DateFormatValidator dateValidator;
	private final StudentService studentService;
	
	@Override
	public SchoolSettlement getSettlementForParent(final String month, final int parentId, final int schoolId) {
		dateValidator.validateYearMonthFormat(month);
		
		List<Student> students = studentService.retrieveStudentByParentId(parentId);
		if (students.isEmpty()) {
			throw new BusinessException("No student found for the given parent.");
	    }

		Parent parent = getFullParentData(students);
		
		List<StudentSettlement> studentSettlements = studentService.calculateStudentsSettlements(students, month);
		Double totalFee = studentSettlements.stream().map(StudentSettlement::getFee).reduce(0.0, Double::sum);
		return SchoolSettlement.builder().parent(parent).totalFee(totalFee).studentSettlement(studentSettlements).build();
	}
	
	private Parent getFullParentData(List<Student> students){
        return students.stream().findFirst().map(Student::getParent).orElseThrow();
    }
}
