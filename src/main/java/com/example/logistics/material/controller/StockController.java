package com.example.logistics.material.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.logistics.material.serviceFacade.MaterialServiceFacade;
import com.example.logistics.material.to.StockLogTO;
import com.example.logistics.material.to.StockTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/material/*")
public class StockController{

	// serviceFacade 참조변수 선언
	@Autowired
	private MaterialServiceFacade materialSF;

	private ModelMap modelMap = new ModelMap();

	// GSON 라이브러리
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환

	@RequestMapping("/searchStockList.do")
	public ModelMap searchStockList(HttpServletRequest request, HttpServletResponse response) {

		try {

			ArrayList<StockTO> stockList = materialSF.getStockList();

			modelMap.put("gridRowJson", stockList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

	@RequestMapping("/searchStockLogList.do")
	public ModelMap searchStockLogList(HttpServletRequest request, HttpServletResponse response) {

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		try {

			ArrayList<StockLogTO> stockLogList = materialSF.getStockLogList(startDate, endDate);

			modelMap.put("gridRowJson", stockLogList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

	@RequestMapping("/warehousing.do")
	public HashMap<String, Object> warehousing(HttpServletRequest request, HttpServletResponse response) {

		String orderNoListStr = request.getParameter("orderNoList");

		ArrayList<String> orderNoArr = gson.fromJson(orderNoListStr, new TypeToken<ArrayList<String>>() {
		}.getType());

		HashMap<String, Object> resultMap = new HashMap<>();

		try {

			resultMap = materialSF.warehousing(orderNoArr);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		}
		return resultMap;
	}

/*	public ModelAndView safetyAllowanceAmountChange(HttpServletRequest request, HttpServletResponse response) {

		String itemCode = request.getParameter("itemCode");
		String itemName = request.getParameter("itemName");
		String safetyAllowanceAmount = request.getParameter("safetyAllowanceAmount");

		log.info(
				"itemCode:" + itemCode + "itemName:" + itemName + "safetyAllowanceAmount:" + safetyAllowanceAmount);

		HashMap<String, Object> resultMap = new HashMap<>();

		try {

			resultMap = materialSF.changeSafetyAllowanceAmount(itemCode, itemName, safetyAllowanceAmount);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		}
		return new ModelAndView("jsonView", resultMap);
	}*/
}
