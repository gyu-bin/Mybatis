package com.example.logistics.sales.to;

import com.example.base.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EstimateDetailTO extends BaseTO {
	private String unitOfEstimate;
	private String estimateNo;
	private int unitPriceOfEstimate;
	private String estimateDetailNo;
	private int sumPriceOfEstimate;
	private String description;
	private String itemCode;
	private int estimateAmount;
	private String dueDateOfEstimate;
	private String itemName;

}