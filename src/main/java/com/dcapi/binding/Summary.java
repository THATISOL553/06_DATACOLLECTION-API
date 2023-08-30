package com.dcapi.binding;

import java.util.List;

import lombok.Data;

@Data
public class Summary {

	private IncomeBinding income;
	
	private EducationBinding education;
	
	private List<ChildBinding> childrens;
	
	private String planName; 
	
	
}
