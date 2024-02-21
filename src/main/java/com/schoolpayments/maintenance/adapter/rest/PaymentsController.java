package com.schoolpayments.maintenance.adapter.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.schoolpayments.maintenance.domain.model.SchoolSettlement;
import com.schoolpayments.maintenance.domain.port.ParentSettlementPort;
import com.schoolpayments.maintenance.domain.port.SchoolSettlementPort;
import com.schoolpayments.maintenance.domain.validation.exception.InvalidDateException;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/payments/api")
@AllArgsConstructor
public class PaymentsController {
    
    private final ParentSettlementPort parentPort;
	
    private final SchoolSettlementPort schoolPort;

    @GetMapping("/{schoolId}/parents/{parentId}/{yearMonth}")
    public ResponseEntity<SchoolSettlement> getPaymentsForParent(
            @PathVariable("schoolId") int schoolId,
            @PathVariable("parentId") int parentId,
            @PathVariable("yearMonth")String month) {
    	 
        SchoolSettlement settlement = parentPort.getSettlementForParent(month, parentId, schoolId);
        if (settlement == null) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(settlement);
    }
    
    @GetMapping("/{schoolId}/{date}")
    public ResponseEntity<List<SchoolSettlement>> getSettlementForSchool(
            @PathVariable("schoolId") int schoolId,
            @PathVariable("date") String date) {

        List<SchoolSettlement> settlements = schoolPort.getSettlementForSchool(schoolId, date);
        if (settlements.isEmpty()) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(settlements);
    }
}