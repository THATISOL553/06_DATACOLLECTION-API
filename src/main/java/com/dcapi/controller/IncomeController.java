package com.dcapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dcapi.binding.IncomeBinding;
import com.dcapi.service.DCService;

@RestController
public class IncomeController {
	
	@Autowired
	private DCService service;
	
	@PostMapping("/saveIncome")
	public ResponseEntity<Long> saveIncome(@RequestBody IncomeBinding incomeBinding){
		Long caseNum = service.saveIncomeData(incomeBinding);
		return new ResponseEntity<> (caseNum, HttpStatus.CREATED);
		
	}
	
}
