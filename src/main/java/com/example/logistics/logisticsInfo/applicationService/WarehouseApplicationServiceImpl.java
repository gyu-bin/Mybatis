package com.example.logistics.logisticsInfo.applicationService;

import java.util.ArrayList;

import com.example.logistics.logisticsInfo.dao.WarehouseDAO;
import com.example.logistics.logisticsInfo.to.WarehouseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WarehouseApplicationServiceImpl implements WarehouseApplicationService {

	@Autowired
	private WarehouseDAO warehouseDAO;

	@Override
	public ArrayList<WarehouseTO> getWarehouseInfoList() {

		ArrayList<WarehouseTO> warehouseList = null;

		warehouseList = warehouseDAO.selectWarehouseList();

		return warehouseList;
	}

	@Override
	public void modifyWarehouseInfo(WarehouseTO warehouseTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public String findLastWarehouseCode() {
		// TODO Auto-generated method stub
		return null;
	}
}
