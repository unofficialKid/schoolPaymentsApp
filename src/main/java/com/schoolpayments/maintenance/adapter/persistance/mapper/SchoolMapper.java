package com.schoolpayments.maintenance.adapter.persistance.mapper;

import org.springframework.stereotype.Component;

import com.schoolpayments.maintenance.adapter.persistance.entity.SchoolEntity;
import com.schoolpayments.maintenance.domain.model.School;

@Component
public class SchoolMapper {

	public School mapToDomainObject(SchoolEntity schoolEntity) {
		return School.builder()
	                .id(schoolEntity.getId())
	                .name(schoolEntity.getName())
	                .hourPrice(schoolEntity.getHour_price())
	                .build();
	}
}
