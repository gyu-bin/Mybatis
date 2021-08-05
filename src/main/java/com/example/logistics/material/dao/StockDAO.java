package com.example.logistics.material.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.logistics.material.to.StockLogTO;
import com.example.logistics.material.to.StockTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockDAO {

	public ArrayList<StockTO> selectStockList();

	public ArrayList<StockLogTO> selectStockLogList(HashMap<String, String> param);

	public HashMap<String,Object> warehousing(HashMap<String, String> param);

	/*
	 * public HashMap<String, Object> safetyAllowanceAmount(String itemCode, String
	 * itemName, String safetyAllowanceAmount);
	 */
}
