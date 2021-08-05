package com.example.basicInfo.to;

import com.example.base.to.BaseTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepartmentTO extends BaseTO  {
	 private String workplaceName;
	 private String deptName;
	 private String deptCode;
	 private String workplaceCode;
	 private String companyCode;
	 private String deptEndDate;
	 private String deptStartDate;
}