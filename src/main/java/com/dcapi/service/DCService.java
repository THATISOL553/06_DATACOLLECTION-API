package com.dcapi.service;

import java.util.Map;

import com.dcapi.binding.ChildBinding;
import com.dcapi.binding.ChildRequestBinding;
import com.dcapi.binding.EducationBinding;
import com.dcapi.binding.IncomeBinding;
import com.dcapi.binding.PlanSelectionBinding;
import com.dcapi.binding.Summary;

public interface DCService {
	
	public Long loadCaseNum(Integer appId);
	
	public Map<Integer, String> getPlanNames();
	
	public Long savePlanSelection (PlanSelectionBinding planSelectionBinding);
	
	public Long saveIncomeData(IncomeBinding incomeBinding);
	
	public Long saveEducation(EducationBinding endBinding);
	
	public Long saveChildrenData(ChildRequestBinding childRequestBinding);
	
	public Summary getSummary(Long CaseNum);
}
