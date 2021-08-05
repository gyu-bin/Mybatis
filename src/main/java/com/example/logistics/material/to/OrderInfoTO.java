package com.example.logistics.material.to;

import lombok.Data;

@Data
public class OrderInfoTO {
	
	private String orderNo;
	private String orderDate;
	private String orderInfoStatus;
	private String orderSort;
	private String itemCode;
	private String itemName;
	private String orderAmount;
	private String inspectionStatus;
	
}
