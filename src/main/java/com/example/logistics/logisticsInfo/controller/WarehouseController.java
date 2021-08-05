package com.example.logistics.logisticsInfo.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.logistics.logisticsInfo.serviceFacade.LogisticsInfoServiceFacade;
import com.example.logistics.logisticsInfo.to.WarehouseTO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/logistics/*")
public class WarehouseController{

	// serviceFacade 참조변수 선언
	private LogisticsInfoServiceFacade logisticsSF;

	private ModelMap modelMap = new ModelMap();

	// GSON 라이브러리
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환


	@RequestMapping("/getWarehouseList.do/")
	public ModelMap getWarehouseList(HttpServletRequest request, HttpServletResponse response) {

		try {

			ArrayList<WarehouseTO> WarehouseTOList = logisticsSF.getWarehouseInfoList();
			modelMap.put("gridRowJson", WarehouseTOList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");
		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());
		}
		return modelMap;
	}

	@RequestMapping("/modifyWarehouseInfo.do/")
	public ModelMap modifyWarehouseInfo(HttpServletRequest request, HttpServletResponse response) {

		String batchList = request.getParameter("batchList");

		try {

			WarehouseTO WarehouseTO = gson.fromJson(batchList, WarehouseTO.class);
			logisticsSF.modifyWarehouseInfo(WarehouseTO);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");
		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());
		}
		return modelMap;
	}

	@RequestMapping("/findLastWarehouseCode.do/")
	public ModelMap findLastWarehouseCode(HttpServletRequest request, HttpServletResponse response) {

		try {
			String warehouseCode = logisticsSF.findLastWarehouseCode();
			modelMap.put("lastWarehouseCode", warehouseCode);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");
		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());
		}
		return modelMap;
	}

}
