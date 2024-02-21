package com.schoolpayments.maintenance.domain.validation;

import java.time.LocalDate;
import java.time.Month;
import org.springframework.stereotype.Component;

import com.schoolpayments.maintenance.domain.validation.exception.InvalidDateException;

@Component
public class DateFormatValidator {  
	
	 private static final LocalDate MIN_DATE = LocalDate.of(2000, Month.JANUARY, 1);
	 
	 private static final String INVALID_DATE_MESSAGE="Invalid month or year format. Please, provide date in format 02-2024";
	 private static final String OUT_OF_RANGE_DATE_MESSAGE="Date is out of range. Please, provide date from 01-2000 till current month";
	 
	 public void validateYearMonthFormat(String value) {
	        try {
	            String[] parts = value.split("-");
	            int month = Integer.parseInt(parts[0]);
	            int year = Integer.parseInt(parts[1]);
	            if (isInvalidDateFormat(month, year)) {
	                throw new InvalidDateException(INVALID_DATE_MESSAGE);
	            }
	            if(isOutOfRangeDate(month, year)) {
	            	throw new InvalidDateException(OUT_OF_RANGE_DATE_MESSAGE);
	            }
	        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
	            throw new InvalidDateException("Invalid date format");
	        }
	    }
	 
	 private static boolean isInvalidDateFormat(final int month, final int year) {
         return !(month >= 1 && month <= 12 && year >= 1000 && year <= 9999);
	 }
	 
	 private static boolean isOutOfRangeDate(final int month, final int year) {
		 
		 LocalDate date = LocalDate.of(year, Month.of(month), 1);
         LocalDate currentDate = LocalDate.now();
         
         return date.isBefore(MIN_DATE) || date.isAfter(currentDate);
	 }
	 
}
