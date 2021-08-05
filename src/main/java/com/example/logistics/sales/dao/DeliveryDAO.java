package com.example.logistics.sales.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.logistics.sales.to.DeliveryInfoTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeliveryDAO {

	public ArrayList<DeliveryInfoTO> selectDeliveryInfoList();

	public HashMap<String,Object> deliver(HashMap<String,String> param);

	public void insertDeliveryResult(DeliveryInfoTO TO);

	public void updateDeliveryResult(DeliveryInfoTO TO);

	public void deleteDeliveryResult(DeliveryInfoTO TO);
}
