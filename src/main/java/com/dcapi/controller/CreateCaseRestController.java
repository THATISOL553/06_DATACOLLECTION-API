package com.dcapi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dcapi.binding.CreateCaseResponse;
import com.dcapi.service.DCService;

@RestController
public class CreateCaseRestController {
	
	@Autowired
	private DCService service;
	
	@GetMapping("/case/{appId}")
	public ResponseEntity<Long> createCaseNum(@PathVariable Integer appId){
		Long caseNum = service.loadCaseNum(appId);
		Map<Integer, String> planMap = service.getPlanNames();
		
		CreateCaseResponse response = new CreateCaseResponse();
		response.setCaseNum(caseNum);
		response.setPlanNames(planMap);
		return new ResponseEntity<> (caseNum, HttpStatus.CREATED);
	}
}
