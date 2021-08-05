package com.example.logistics.material.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.logistics.material.to.StockLogTO;
import com.example.logistics.material.to.StockTO;

public interface StockApplicationService {
	
	public ArrayList<StockTO> getStockList();
	
	public ArrayList<StockLogTO> getStockLogList(String startDate, String endDate);
	
	public HashMap<String,Object> warehousing(ArrayList<String> orderNoArr);
	
/*	public HashMap<String, Object> changeSafetyAllowanceAmount(String itemCode, String itemName,
                                                               String safetyAllowanceAmount);*/
}
