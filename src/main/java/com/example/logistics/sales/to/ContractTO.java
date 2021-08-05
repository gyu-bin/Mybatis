package com.example.logistics.sales.to;

import com.example.base.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
public class ContractTO extends BaseTO {
	private String contractType;
	private String estimateNo;
	private String contractDate;
	private String description;
	private String contractRequester;
	private String customerCode;
	private String personCodeInCharge;
	private String contractNo;
	private ArrayList<ContractDetailTO> contractDetailTOList;

}