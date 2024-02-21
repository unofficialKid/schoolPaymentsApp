package com.schoolpayments.maintenance.adapter.rest;

import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.schoolpayments.maintenance.MockData;
import com.schoolpayments.maintenance.domain.model.SchoolSettlement;
import com.schoolpayments.maintenance.domain.port.ParentSettlementPort;
import com.schoolpayments.maintenance.domain.port.SchoolSettlementPort;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PaymentControllerUnitTest {

	    @MockBean
	    private ParentSettlementPort parentPort;

	    @MockBean
	    private SchoolSettlementPort schoolPort;

	    @InjectMocks
	    private PaymentsController controller;
	    
	    @Autowired
	    private MockData mockData;

	    @Test
	    public void shouldReturnSettlementForParent() {
	        // given
	        int schoolId = 32;
	        int parentId = 1;
	        String month = "02-2024";
	        
	        SchoolSettlement expectedSettlement = mockData.mockSchoolSettlement();
	        when(parentPort.getSettlementForParent(month, parentId, schoolId)).thenReturn(expectedSettlement);

	        // when
	        ResponseEntity<SchoolSettlement> response = controller.getPaymentsForParent(schoolId, parentId, month);

	        // then
	        assert response.getStatusCode().equals(HttpStatus.OK);
	        assert response.getBody().equals(expectedSettlement);
	    }

	    @Test
	    public void shouldReturnSettlementForSchool() {
	        // given
	        int schoolId = 32;
	        String date = "02-2024";
	        List<SchoolSettlement> expectedSettlements = List.of(mockData.mockSchoolSettlement());
	        when(schoolPort.getSettlementForSchool(schoolId, date)).thenReturn(expectedSettlements);

	        // when
	        ResponseEntity<List<SchoolSettlement>> response = controller.getSettlementForSchool(schoolId, date);

	        // then
	        assert response.getStatusCode().equals(HttpStatus.OK);
	        assert response.getBody().equals(expectedSettlements);
	    }
	    
	    @Test
	    public void shouldReturnNotFoundStatusWhenResponseIsNull() {
	        // given
	        int schoolId = 32;
	        int parentId = 1;
	        String month = "02-2024";
	        
	        SchoolSettlement expectedSettlement = mockData.mockSchoolSettlement();
	        when(parentPort.getSettlementForParent(month, parentId, schoolId)).thenReturn(null);

	        // when
	        ResponseEntity<SchoolSettlement> response = controller.getPaymentsForParent(schoolId, parentId, month);

	        // then
	        assert response.getStatusCode().equals(HttpStatus.NOT_FOUND);
	    }

	    @Test
	    public void shouldReturnNotFoundStatusWhenResponseIsEmpty() {
	        // given
	        int schoolId = 32;
	        String date = "02-2024";
	        when(schoolPort.getSettlementForSchool(schoolId, date)).thenReturn(Collections.emptyList());

	        // when
	        ResponseEntity<List<SchoolSettlement>> response = controller.getSettlementForSchool(schoolId, date);

	        // then
	        assert response.getStatusCode().equals(HttpStatus.NOT_FOUND);
	    }
}

