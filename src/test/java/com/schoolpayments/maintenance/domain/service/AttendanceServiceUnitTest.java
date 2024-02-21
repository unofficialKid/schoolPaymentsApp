package com.schoolpayments.maintenance.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.schoolpayments.maintenance.domain.model.Attendance;
import com.schoolpayments.maintenance.domain.repository.AttendancePort;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AttendanceServiceUnitTest {
   
	@MockBean
	private AttendancePort attendancePort;
	
	@InjectMocks
    private AttendanceService attendanceService;
	
	@Test
	public void shouldBeNoOverhours() {
		// given
		List<Attendance> attendances = new ArrayList<>();
	    attendances.add(createAttendance(LocalTime.of(7, 0), LocalTime.of(12, 0))); 

	    // when
	    long totalOverhours = attendanceService.calculateAllOverhours(attendances);

	    // then
	    assertEquals(0, totalOverhours);
    }
	
	@Test
	public void shouldBeMorningOverhour() {
		// given
		List<Attendance> attendances = new ArrayList<>();
	    attendances.add(createAttendance(LocalTime.of(6, 59), LocalTime.of(12, 0))); 

	    // when
	    long totalOverhours = attendanceService.calculateAllOverhours(attendances);

	    // then
	    assertEquals(1, totalOverhours);
    }
	
	@Test
	public void shouldBeAfternoonOverhour() {
		// given
		List<Attendance> attendances = new ArrayList<>();
	    attendances.add(createAttendance(LocalTime.of(7, 0), LocalTime.of(12, 1))); 

	    // when
	    long totalOverhours = attendanceService.calculateAllOverhours(attendances);

	    // then
	    assertEquals(1, totalOverhours);
    }

	@Test
	public void shouldProperlyCountOverhours() {
		// given
		List<Attendance> attendances = new ArrayList<>();
	    attendances.add(createAttendance(LocalTime.of(6, 59), LocalTime.of(12, 1))); 

	    // when
	    long totalOverhours = attendanceService.calculateAllOverhours(attendances);

	    // then
	    assertEquals(2, totalOverhours);
    }
	

	@Test
	public void shouldReturnZeroWhenExitDateBeforeEnterDate() {
		// given
		List<Attendance> attendances = new ArrayList<>();
	    attendances.add(createAttendance(LocalTime.of(9, 0), LocalTime.of(8, 30))); 

	    // when
	    long totalOverhours = attendanceService.calculateAllOverhours(attendances);

	    // then
	    assertEquals(0, totalOverhours);
    }
	
    @Test
    public void testCountTimeSpendAtSchool() {
        // given
        List<Attendance> attendances = new ArrayList<>();
        Attendance yesterday =  Attendance.builder()
                .entryDate(LocalTime.of(9, 0).atDate(LocalDate.now().minusDays(1))) 
                .exitDate(LocalTime.of(13, 0).atDate(LocalDate.now().minusDays(1)))
                .build();
        attendances.add(yesterday);
        attendances.add(createAttendance(LocalTime.of(7, 0), LocalTime.of(13, 55)));  

        // when
        Duration totalDuration = attendanceService.countTimeSpendAtSchool(attendances);

        // then
        assertEquals(Duration.ofHours(10).plusMinutes(55), totalDuration);
    }

    private Attendance createAttendance(LocalTime entryTime, LocalTime exitTime) {
    	return Attendance.builder()
    					.entryDate(entryTime.atDate(LocalDate.now()))
    					.exitDate(exitTime.atDate(LocalDate.now()))
    					.build();
    }
}
