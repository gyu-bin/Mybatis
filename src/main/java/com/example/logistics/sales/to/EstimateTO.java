package com.example.logistics.sales.to;

import com.example.base.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
public class EstimateTO extends BaseTO {
	private String effectiveDate;
	private String estimateNo;
	private String estimateRequester;
	private String description;
	private String contractStatus;
	private String customerCode;
	private String personCodeInCharge;
	private String personNameCharge;
	private String estimateDate;
	private ArrayList<EstimateDetailTO> estimateDetailTOList;

}