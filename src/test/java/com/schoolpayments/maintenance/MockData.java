package com.schoolpayments.maintenance;

import java.time.Duration;
import java.util.List;

import org.springframework.stereotype.Component;

import com.schoolpayments.maintenance.domain.model.Student;
import com.schoolpayments.maintenance.domain.model.StudentSettlement;
import com.schoolpayments.maintenance.domain.model.Parent;
import com.schoolpayments.maintenance.domain.model.School;
import com.schoolpayments.maintenance.domain.model.SchoolSettlement;

@Component
public class MockData {

	public Parent mockParent() {
		return Parent.builder().id(1).firstName("Jan").lastName("Kowalski").build();
	}
	
	public School mockSchool() {
		return School.builder().id(32).name("My School").hourPrice(150.00).build();
	}
	
	public Student mockStudent() {
		return Student.builder().id(1).firstName("Paweł").lastName("Kowalski").parent(mockParent()).school(mockSchool()).build();
	}
	
	public StudentSettlement mockStudentSettlement() {
		return StudentSettlement.builder().student(mockStudent()).fee(500.00).schoolTime(Duration.ofHours(4)).build();
	}
	
	public SchoolSettlement mockSchoolSettlement() {
		return SchoolSettlement.builder().parent(mockParent()).studentSettlement(List.of(mockStudentSettlement())).totalFee(1000D).build();
	}
}
