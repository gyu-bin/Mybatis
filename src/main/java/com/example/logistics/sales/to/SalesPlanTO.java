package com.example.logistics.sales.to;

import com.example.base.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SalesPlanTO extends BaseTO {
	 private int unitPriceOfSales;
	 private int salesAmount;
	 private String salesPlanNo;
	 private String description;
	 private String salesPlanDate;
	 private int sumPriceOfSales;
	 private String itemCode;
	 private String dueDateOfSales;
	 private String unitOfSales;
	 private String mpsApplyStatus;
	 private String itemName;

}