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
import com.example.logistics.material.to.OrderInfoTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/material/*")
public class OrderController{

	// serviceFacade 참조변수 선언
	@Autowired
	private MaterialServiceFacade MaterialSF;

	private ModelMap modelMap = new ModelMap();

	// GSON 라이브러리
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환

	@RequestMapping("/getOrderList.do")
	public HashMap<String, Object> getOrderList(HttpServletRequest request, HttpServletResponse response) {

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		HashMap<String, Object> resultMap = new HashMap<>();

		try {

			resultMap = MaterialSF.getOrderList(startDate, endDate);

		} catch (Exception e2) {

			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		}

		return resultMap;

	}

	@RequestMapping("/showOrderDialog.do")
	public HashMap<String, Object> openOrderDialog(HttpServletRequest request, HttpServletResponse response) {

		String mrpNoListStr = request.getParameter("mrpGatheringNoList");
		ArrayList<String> mrpNoArr = gson.fromJson(mrpNoListStr, new TypeToken<ArrayList<String>>() {}.getType());
		// 제너릭 클래스를 사용할경우 정해지지 않은 제너릭타입을 명시하기위해서 TypeToken을 사용
		HashMap<String, Object> resultMap = new HashMap<>();

		try {

			resultMap = MaterialSF.getOrderDialogInfo(mrpNoArr);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		}

		return resultMap;

	}

	@RequestMapping("/showOrderInfo.do")
	public ModelMap showOrderInfo(HttpServletRequest request, HttpServletResponse response) {

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		try {

			ArrayList<OrderInfoTO> orderInfoList = MaterialSF.getOrderInfoList(startDate, endDate);
			modelMap.put("gridRowJson", orderInfoList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

	@RequestMapping("/searchOrderInfoListOnDelivery.do")
	public ModelMap searchOrderInfoListOnDelivery(HttpServletRequest request, HttpServletResponse response) {

		try {

			ArrayList<OrderInfoTO> orderInfoListOnDelivery = MaterialSF.getOrderInfoListOnDelivery();
			modelMap.put("gridRowJson", orderInfoListOnDelivery);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

	@RequestMapping("order.do")
	public HashMap<String, Object> order(HttpServletRequest request, HttpServletResponse response) {

		String mrpGatheringNoListStr = request.getParameter("mrpGatheringNoList");
		HashMap<String, Object> resultMap = new HashMap<>();

		ArrayList<String> mrpGaNoArr = gson.fromJson(mrpGatheringNoListStr, new TypeToken<ArrayList<String>>() {
		}.getType());
		// 제너릭 클래스를 사용할경우 정해지지 않은 제너릭타입을 명시하기위해서 TypeToken을 사용
		try {

///////////////////////////////시작부
			resultMap = MaterialSF.order(mrpGaNoArr);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		}
		return resultMap;
	}

	@RequestMapping("/optionOrder.do")
	public HashMap<String, Object> optionOrder(HttpServletRequest request, HttpServletResponse response) {

		HashMap<String, Object> resultMap = new HashMap<>();

		try {

			String itemCode = request.getParameter("itemCode");
			String itemAmount = request.getParameter("itemAmount");

			resultMap = MaterialSF.optionOrder(itemCode, itemAmount);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		}
		return resultMap;
	}

}
