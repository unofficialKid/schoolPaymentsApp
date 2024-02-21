package com.schoolpayments.maintenance.domain.port;

import java.util.List;

import com.schoolpayments.maintenance.domain.model.SchoolSettlement;

public interface SchoolSettlementPort {

	List<SchoolSettlement> getSettlementForSchool(int schoolId, String month);
}
