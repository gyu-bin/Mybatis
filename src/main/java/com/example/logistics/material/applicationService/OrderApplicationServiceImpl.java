package com.example.logistics.material.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.logistics.material.dao.OrderDAO;

import com.example.logistics.material.to.OrderInfoTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderApplicationServiceImpl implements OrderApplicationService {

	// DAO 참조변수 선언
	@Autowired
	private OrderDAO orderDAO;

	@Override
	public HashMap<String, Object> getOrderList(String startDate, String endDate) {

		HashMap<String,String> param=new HashMap<>();
		HashMap<String,Object> resultMap=new HashMap<>();

		param.put("startDate", startDate);
		param.put("endDate", endDate);
		orderDAO.getOrderList(param);

		resultMap.put("gridRowJson", param.get("RESULT"));
		resultMap.put("errorCode", param.get("ERROR_CODE"));
		resultMap.put("errorMsg", param.get("ERROR_MSG"));
		return resultMap;
	}
	@Override
	public HashMap<String, Object> getOrderDialogInfo(ArrayList<String> mrpNoArr) {
		HashMap<String,String> param=new HashMap<>();
		HashMap<String,Object> resultMap=new HashMap<>();



		String mrpNoList = mrpNoArr.toString().replace("[", "").replace("]", "");
		param.put("mrpNoList", mrpNoList);
		orderDAO.getOrderDialogInfo(param);


		resultMap.put("gridRowJson", param.get("RESULT"));
		resultMap.put("errorCode", param.get("ERROR_CODE"));
		resultMap.put("errorMsg", param.get("ERROR_MSG"));
		return resultMap;
	}


	@Override
	public HashMap<String, Object> order(ArrayList<String> mrpGaNoArr) {

		HashMap<String,Object> resultMap=new HashMap<>();

		String mpsNoList = mrpGaNoArr.toString().replace("[", "").replace("]", "");

		HashMap<String,String> param=new HashMap<>();
		param.put("mpsNoList", mpsNoList);

		orderDAO.order(param);

		resultMap.put("errorCode", param.get("ERROR_CODE"));
		resultMap.put("errorMsg", param.get("ERROR_MSG"));

		return resultMap;

	}

	@Override
	public HashMap<String, Object> optionOrder(String itemCode, String itemAmount) {

		HashMap<String,Object> resultMap=new HashMap<>();
		HashMap<String,String> param=new HashMap<>();
		param.put("itemCode", itemCode);
		param.put("itemAmount", itemAmount);
		orderDAO.optionOrder(param);

		resultMap.put("errorCode", param.get("ERROR_CODE"));
		resultMap.put("errorMsg", param.get("ERROR_MSG"));

		return resultMap;

	}

	@Override
	public ArrayList<OrderInfoTO> getOrderInfoListOnDelivery() {

		return orderDAO.getOrderInfoListOnDelivery();

	}

	@Override
	public ArrayList<OrderInfoTO> getOrderInfoList(String startDate, String endDate) {

		HashMap<String,String> map=new HashMap<>();

		map.put("startDate", startDate);

		map.put("endDate", endDate);

		return orderDAO.getOrderInfoList(map);

	}
}
