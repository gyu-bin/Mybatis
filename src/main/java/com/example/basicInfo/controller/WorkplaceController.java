package com.example.basicInfo.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.basicInfo.serviceFacade.BasicInfoServiceFacade;

import com.example.basicInfo.to.WorkplaceTO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/basicInfo/*")
public class WorkplaceController{

	// serviceFacade 참조변수 선언
	@Autowired
	private BasicInfoServiceFacade basicInfoSF;

	private ModelMap modelMap = new ModelMap();

	// GSON 라이브러리
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 JSON 변환

	@RequestMapping("/searchWorkplace.do")
	public ModelMap searchWorkplaceList(HttpServletRequest request, HttpServletResponse response) {

		String companyCode = request.getParameter("companyCode");

		ArrayList<WorkplaceTO> workplaceList = null;

		try {

			workplaceList = basicInfoSF.getWorkplaceList(companyCode);

			modelMap.put("gridRowJson", workplaceList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

	@RequestMapping(value="/batchWorkplaceListProcess.do", method = RequestMethod.POST)
	public ModelMap batchListProcess(HttpServletRequest request, HttpServletResponse response) {

		String batchList = request.getParameter("batchList");

		ArrayList<WorkplaceTO> workplaceList = gson.fromJson(batchList, new TypeToken<ArrayList<WorkplaceTO>>() {
		}.getType());

		try {

			HashMap<String, Object> resultMap = basicInfoSF.batchWorkplaceListProcess(workplaceList);

			modelMap.put("result", resultMap);
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
