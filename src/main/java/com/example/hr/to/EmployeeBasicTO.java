package com.example.hr.to;

import com.example.base.to.BaseTO;

import lombok.Getter;
import lombok.Setter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class EmployeeBasicTO extends BaseTO {
	 private String companyCode;
	 private String empCode;
	 private String empName;
	 private String empEngName;
	 private String socialSecurityNumber;
	 private String hireDate;
	 private String retirementDate;
	 private String userOrNot;
	 private String birthDate;
	 private String gender;
}