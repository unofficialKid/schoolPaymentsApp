package com.schoolpayments.maintenance.adapter.persistance.mapper;

import org.springframework.stereotype.Component;

import com.schoolpayments.maintenance.adapter.persistance.entity.ParentEntity;
import com.schoolpayments.maintenance.domain.model.Parent;

@Component
public class ParentMapper {
	public Parent mapToDomainObject(ParentEntity parentEntity) {
		return Parent.builder()
				.id(parentEntity.getId())
	            .firstName(parentEntity.getFirstName())
	            .lastName(parentEntity.getLastName())
	            .build();
	}
}
