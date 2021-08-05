package com.example.logistics.logisticsInfo.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.logistics.logisticsInfo.to.ItemInfoTO;
import com.example.logistics.logisticsInfo.to.ItemTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemDAO {

	public ArrayList<ItemInfoTO> selectAllItemList();
	
	public ArrayList<ItemInfoTO> selectItemList(HashMap<String, String> param);
	
	public void insertItem(ItemTO TO);
	
	public void updateItem(ItemTO TO);
	
	public void deleteItem(ItemTO TO);
	
	public int getStandardUnitPrice(String itemCode);
	
	public int getStandardUnitPriceBox(String itemCode);
	
}
