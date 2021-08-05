package com.example.logistics.material.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.logistics.material.to.OrderInfoTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDAO {

	public HashMap<String,Object> getOrderList(HashMap<String, String> param);

	public HashMap<String,Object> getOrderDialogInfo(HashMap<String, String> param);

	public ArrayList<OrderInfoTO> getOrderInfoListOnDelivery();

	public ArrayList<OrderInfoTO> getOrderInfoList(HashMap<String, String> param);

	public HashMap<String,Object> order(HashMap<String, String> param);

	public HashMap<String,Object> optionOrder(HashMap<String, String> param);
}
