package com.example.logistics.material.to;

import com.example.base.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BomInfoTO extends BaseTO {
	
	private String itemCode;
	private String parentItemCode;
	private int no;
	private String itemName;
	private String itemClassification;
	private String itemClassificationName;
	private int netAmount;
	private String description;
	
}
