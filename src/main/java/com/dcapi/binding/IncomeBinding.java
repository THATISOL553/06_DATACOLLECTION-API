package com.dcapi.binding;

import lombok.Data;

@Data
public class IncomeBinding {
	private Long caseNum;

	private Double empIncome;

	private Double propertyIncome;

	private Double rentIncome;
}
