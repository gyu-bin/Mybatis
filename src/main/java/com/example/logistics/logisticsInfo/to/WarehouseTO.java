package com.example.logistics.logisticsInfo.to;

import com.example.base.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WarehouseTO extends BaseTO {
	private String warehouseCode;
	private String warehouseName;
	private String warehouseUseOrNot;
	private String description;

}