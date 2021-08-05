package com.example.logistics.material.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.logistics.material.dao.StockDAO;

import com.example.logistics.material.to.StockLogTO;
import com.example.logistics.material.to.StockTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockApplicationServiceImpl implements StockApplicationService {

	// DAO 참조변수 선언
	@Autowired
	private StockDAO stockDAO;

	@Override
	public ArrayList<StockTO> getStockList() {

		return stockDAO.selectStockList();

	}

	@Override
	public ArrayList<StockLogTO> getStockLogList(String startDate, String endDate) {

		HashMap<String,String> map=new HashMap<>();

		map.put("startDate", startDate);

		map.put("endDate", endDate);

		return stockDAO.selectStockLogList(map);

	}

	@Override
	public HashMap<String, Object> warehousing(ArrayList<String> orderNoArr) {

		HashMap<String,String> map = new HashMap<>();

		String orderNoList = orderNoArr.toString().replace("[", "").replace("]", "");

		map.put("orderNoList",orderNoList);

		return stockDAO.warehousing(map);

	}
/*
	@Override
	public HashMap<String, Object> changeSafetyAllowanceAmount(String itemCode, String itemName,
			String safetyAllowanceAmount) {

		return stockDAO.updatesafetyAllowance(itemCode, itemName, safetyAllowanceAmount);

	}*/

}
