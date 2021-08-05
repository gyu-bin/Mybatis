package com.example.logistics.production.to;

import com.example.base.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductionResultManagementTO extends BaseTO {
	private String productionResultNo;
	private String workInstructionNo;
	private String description;
	private String productionDate;
	private String itemCode;
	private String unitOfProductionResult;
	private String productionAmount;
	private String itemName;

}