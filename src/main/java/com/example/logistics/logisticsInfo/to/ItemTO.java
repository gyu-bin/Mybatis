package com.example.logistics.logisticsInfo.to;

import com.example.base.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ItemTO extends BaseTO {
	 private String itemGroupCode;
	 private String leadTime;
	 private String unitOfStock;
	 private int standardUnitPrice;
	 private String description;
	 private String itemCode;
	 private String itemClassification;
	 private String lossRate;
	 private String itemName;
	 
}