package com.example.basicInfo.to;

import com.example.base.to.BaseTO;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class FinancialAccountAssociatesTO extends BaseTO {
	private String accountOpenPlace;
	private String financialAccountNote;
	private String financialInstituteName;
	private String cardType;
	private String businessLicenseNumber;
	private String cardNumber;
	private String cardOpenPlace;
	private String accountAssociatesType;
	private String financialInstituteCode;
	private String workplaceCode;
	private String cardMemberName;
	private String accountAssociatesCode;
	private String accountNumber;
	private String accountAssociatesName;

}