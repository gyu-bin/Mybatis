package com.example.logistics.sales.to;

import com.example.base.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DeliveryInfoTO extends BaseTO {
	
	private String deliveryNo;
	private String estimateNo;
	private String contractNo;
	private String contractDetailNo;
	private String customerCode;
	private String personCodeInCharge;
	private String itemCode;
	private String itemName;
	private String unitOfDelivery;
	private String deliveryAmount;
	private String unitPrice;
	private String sumPrice;
	private String deliverydate;
	private String deliveryPlaceName;
	
}