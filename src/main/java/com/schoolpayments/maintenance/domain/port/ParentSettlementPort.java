package com.schoolpayments.maintenance.domain.port;

import com.schoolpayments.maintenance.domain.model.SchoolSettlement;

public interface ParentSettlementPort {

	SchoolSettlement getSettlementForParent(String month, int parentId, int schoolId);
}
