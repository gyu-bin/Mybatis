package com.example.basicInfo.to;

import com.example.base.to.BaseTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerTO extends BaseTO  {
	 private String socialSecurityNumber;
	 private String customerBasicAddress;
	 private String customerBusinessConditions;
	 private String customerZipCode;
	 private String customerDetailAddress;
	 private String customerType;
	 private String customerNote;
	 private String businessLicenseNumber;
	 private String customerCeo;
	 private String customerName;
	 private String customerBusinessItems;
	 private String workplaceCode;
	 private String customerTelNumber;
	 private String customerCode;
	 private String customerFaxNumber;
}