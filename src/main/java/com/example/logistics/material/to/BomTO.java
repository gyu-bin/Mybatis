package com.example.logistics.material.to;

import com.example.base.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BomTO extends BaseTO {

	private String itemCode;
	private String parentItemCode;
	private int no;
	private int netAmount;
	private String description;

}