package com.example.logistics.production.to;

import com.example.base.to.BaseTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MrpTO extends BaseTO {

	private String mrpNo;
	private String mpsNo;
	private String mrpGatheringNo;
	private String itemClassification;
	private String itemCode;
	private String itemName;
	private String unitOfMrp;
	private int requiredAmount;
	private String orderDate;
	private String requiredDate;
	private String mrpGatheringStatus;

}