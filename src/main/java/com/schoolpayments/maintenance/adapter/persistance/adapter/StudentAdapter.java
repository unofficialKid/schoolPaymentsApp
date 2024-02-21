package com.schoolpayments.maintenance.adapter.persistance.adapter;

import java.util.List;
import org.springframework.stereotype.Service;

import com.schoolpayments.maintenance.domain.model.Parent;
import com.schoolpayments.maintenance.adapter.persistance.entity.StudentEntity;
import com.schoolpayments.maintenance.adapter.persistance.mapper.StudentMapper;
import com.schoolpayments.maintenance.adapter.persistance.repository.StudentRepository;
import com.schoolpayments.maintenance.domain.model.School;
import com.schoolpayments.maintenance.domain.model.Student;
import com.schoolpayments.maintenance.domain.repository.StudentPort;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class StudentAdapter implements StudentPort {

	private StudentRepository studentRepository;
	private StudentMapper studentMapper;
	
	@Override
	public List<Student> findBySchoolId(int schoolId) {
		List<StudentEntity> studentsInSchhol = studentRepository.findByParentIdWithParentAndSchool(schoolId)
		return studentsInSchhol.stream().map(student -> studentMapper.mapToDomainObject(student)).toList();
	}
	
	@Override
	public List<Student> findByParentId(int parentId) {
		List<StudentEntity> studentByParent = studentRepository.findByParentIdWithParentAndSchool(parentId);
		return studentByParent.stream().map(student -> studentMapper.mapToDomainObject(student)).toList();
	}
}
