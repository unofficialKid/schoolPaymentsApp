package com.schoolpayments.maintenance.adapter.persistance.mapper;

import org.springframework.stereotype.Component;

import com.schoolpayments.maintenance.adapter.persistance.entity.StudentEntity;
import com.schoolpayments.maintenance.domain.model.Student;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class StudentMapper {

	private final ParentMapper parentMapper;
    private final SchoolMapper schoolMapper;
    
    public Student mapToDomainObject(StudentEntity studentEntity) {
        return Student.builder()
                .id(studentEntity.getId())
                .firstName(studentEntity.getFirstName())
                .parent(parentMapper.mapToDomainObject(studentEntity.getParent()))
                .school(schoolMapper.mapToDomainObject(studentEntity.getSchool()))
                .lastName(studentEntity.getLastName())
                .build();
    }
}
