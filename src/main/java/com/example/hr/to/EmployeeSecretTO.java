package com.example.hr.to;

import com.example.base.to.BaseTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeSecretTO extends BaseTO {
	
	 private String companyCode;
	 private String empCode;
	 private int seq;
	 private String userPassword;
}