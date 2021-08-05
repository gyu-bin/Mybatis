package com.example.logistics.sales.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.logistics.sales.serviceFacade.SalesServiceFacade;

import com.example.logistics.sales.to.EstimateDetailTO;
import com.example.logistics.sales.to.EstimateTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/sales/*")
public class EstimateController{

	@Autowired
	private SalesServiceFacade salesSF;
	
	private ModelAndView modelAndView;
	private ModelMap modelMap = new ModelMap();

	// GSON 라이브러리
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환

	@RequestMapping("/searchEstimateInfo.do")
	public ModelMap searchEstimateInfo(HttpServletRequest request, HttpServletResponse response) {

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String dateSearchCondition = request.getParameter("dateSearchCondition");

		try {

			ArrayList<EstimateTO> estimateTOList = salesSF.getEstimateList(dateSearchCondition, startDate, endDate);

			modelMap.put("gridRowJson", estimateTOList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}

		return modelMap;
	}

	@RequestMapping("/searchEstimateDetailInfo.do")
	public ModelMap searchEstimateDetailInfo(HttpServletRequest request, HttpServletResponse response) {
		
		String estimateNo = request.getParameter("estimateNo");

		try {

			ArrayList<EstimateDetailTO> estimateDetailTOList = salesSF.getEstimateDetailList(estimateNo);

			modelMap.put("gridRowJson", estimateDetailTOList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}

		return modelMap;
	}

	@RequestMapping("/addNewEstimate.do")
	public ModelMap addNewEstimate(HttpServletRequest request, HttpServletResponse response) {

		String estimateDate = request.getParameter("estimateDate");
		String newEstimateInfo = request.getParameter("newEstimateInfo");
		EstimateTO newEstimateTO = gson.fromJson(newEstimateInfo, EstimateTO.class);

		try {

			HashMap<String, Object> resultList = salesSF.addNewEstimate(estimateDate, newEstimateTO);
			//견적일자와 견적,견적상세의 json객체를 EstimateTO로 변환한 newEstimateTO를 map에 담음
			modelMap.put("result", resultList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}

		return modelMap;
	}

	@RequestMapping("/batchEstimateDetailListProcess.do")
	public ModelMap batchListProcess(HttpServletRequest request, HttpServletResponse response) {

		String batchList = request.getParameter("batchList");

		ArrayList<EstimateDetailTO> estimateDetailTOList = gson.fromJson(batchList,
				new TypeToken<ArrayList<EstimateDetailTO>>() {
				}.getType());

		try {

			HashMap<String, Object> resultList = salesSF.batchEstimateDetailListProcess(estimateDetailTOList);

			modelMap.put("result", resultList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "�꽦怨�");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}


		return modelMap;
	}
	}
