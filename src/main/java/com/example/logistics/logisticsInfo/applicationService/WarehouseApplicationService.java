package com.example.logistics.logisticsInfo.applicationService;

import java.util.ArrayList;

import com.example.logistics.logisticsInfo.to.WarehouseTO;

public interface WarehouseApplicationService {
	
	public ArrayList<WarehouseTO> getWarehouseInfoList();

	public void modifyWarehouseInfo(WarehouseTO warehouseTO);

	public String findLastWarehouseCode();
}
