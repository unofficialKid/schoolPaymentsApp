package com.schoolpayments.maintenance.adapter.persistance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.schoolpayments.maintenance.adapter.persistance.entity.AttendanceEntity;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Integer>{
	
	@Query("SELECT a FROM AttendanceEntity a WHERE a.student_id = :studentId AND " +
	           "MONTH(a.entryDate) = :month AND YEAR(a.entryDate) = :year")
	List<AttendanceEntity> findByChildIdAndMonth(@Param("studentId") int studentId, 
                                                 @Param("month") int month,
                                                 @Param("year") int year);
}
