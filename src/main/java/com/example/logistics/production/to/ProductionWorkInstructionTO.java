package com.example.logistics.production.to;

import com.example.base.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductionWorkInstructionTO extends BaseTO  {
	private int workInstructionAmount;
	private String workInstructionNo;
	private String description;
	private String itemCode;
	private String productionStatus;
	private String instructionDate;
	private String mrpGatheringNo;
	private String itemName;
	private String unitOfWorkInstruction;

}