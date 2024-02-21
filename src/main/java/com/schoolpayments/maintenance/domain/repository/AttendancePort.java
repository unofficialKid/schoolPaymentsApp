package com.schoolpayments.maintenance.domain.repository;

import java.util.List;

import com.schoolpayments.maintenance.domain.model.Attendance;

public interface AttendancePort {

	List<Attendance> attendancesInMonth(int childId, String month);
}
