package com.example.logistics.material.to;

import com.example.base.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BomDeployTO extends BaseTO {
	
	private String bomNo;
	private int bomLevel;
	private String parentItemCode;
	private String itemCode;
	private String itemName;
	private String unitOfStock;
	private int netAmount;
	private String lossRate;
	private String necessaryAmount;	
	private String leadTime;
	private String isLeaf;
	private String description;
	
}
