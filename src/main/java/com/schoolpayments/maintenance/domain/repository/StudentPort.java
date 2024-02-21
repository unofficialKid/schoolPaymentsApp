package com.schoolpayments.maintenance.domain.repository;

import java.util.List;
import java.util.Optional;

import com.schoolpayments.maintenance.domain.model.Student;

public interface StudentPort {

	List<Student> findBySchoolId(int schoolId);
	
	List<Student> findByParentId(int parentId);
}
