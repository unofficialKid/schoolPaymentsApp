package com.schoolpayments.maintenance.adapter.persistance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.schoolpayments.maintenance.adapter.persistance.entity.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer>{
	
	@Query("SELECT c FROM StudentEntity c JOIN FETCH c.parent JOIN FETCH c.school WHERE c.parent.id = :parentId")
	List<StudentEntity> findByParentIdWithParentAndSchool(@Param("parentId") int parentId);
	
	@Query("SELECT c FROM StudentEntity c JOIN FETCH c.parent JOIN FETCH c.school WHERE c.school.id = :schoolId")
	List<StudentEntity> findBySchoolIdWithParentAndSchool(@Param("schoolId") int schoolId);
}
