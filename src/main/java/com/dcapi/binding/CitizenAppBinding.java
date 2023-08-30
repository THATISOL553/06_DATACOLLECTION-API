package com.dcapi.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CitizenAppBinding {
	private String fullName;
	
	private String email;
	
	private Long phNo;
	
	private String gender;
	
	private Long ssn;
	
	private LocalDate dob;
}
