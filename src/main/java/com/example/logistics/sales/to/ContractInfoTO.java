package com.example.logistics.sales.to;

import com.example.base.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
public class ContractInfoTO extends BaseTO {

	private String contractNo;
	private String estimateNo;
	private String contractType;
	private String contractTypeName;
	private String customerCode;
	private String customerName;
	private String estimateDate;
	private String contractDate;
	private String contractRequester;
	private String personCodeInCharge;
	private String empNameInCharge;
	private String description;
	private ArrayList<ContractDetailTO> contractDetailTOList;
	private String deliveryCompletionStatus;
	
}
