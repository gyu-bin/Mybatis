package com.example.logistics.material.to;

import lombok.Data;

@Data
public class OrderTempTO {
	private String mrpGatheringNo;
	private String itemCode;
	private String itemName;
	private String unitOfMrp;
	private int requiredAmount;
	private int stockAmount;
	private String orderDate;
	private String requiredDate;
	
}
