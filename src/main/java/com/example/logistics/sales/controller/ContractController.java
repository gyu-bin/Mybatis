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

import com.example.logistics.sales.to.ContractDetailTO;
import com.example.logistics.sales.to.ContractInfoTO;
import com.example.logistics.sales.to.ContractTO;
import com.example.logistics.sales.to.EstimateTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/sales/*")
public class ContractController{

	@Autowired
	private SalesServiceFacade salesSF;
	
	private ModelAndView modelAndView;
	private ModelMap modelMap = new ModelMap();

	// GSON
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // �냽�꽦媛믪씠 null �씤 �냽�꽦�룄 蹂��솚

	@RequestMapping("/searchContract.do")
	public ModelMap searchContract(HttpServletRequest request, HttpServletResponse response) {

		String searchCondition = request.getParameter("searchCondition");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String customerCode = request.getParameter("customerCode");

			ArrayList<ContractInfoTO> contractInfoTOList = null;

			if (searchCondition.equals("searchByDate")) {

				String[] paramArray = { startDate, endDate };
				contractInfoTOList = salesSF.getContractList("searchByDate", paramArray);

			} else if (searchCondition.equals("searchByCustomer")) {

				String[] paramArray = { customerCode };
				contractInfoTOList = salesSF.getContractList("searchByCustomer", paramArray);

			}

			modelMap.put("gridRowJson", contractInfoTOList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		return modelMap;
	}

	@RequestMapping("/searchContractNO.do")
	public ModelMap searchContractNO(HttpServletRequest request, HttpServletResponse response) {

		String searchCondition = request.getParameter("searchCondition");

			ArrayList<ContractInfoTO> contractInfoTOList = null;
			if (searchCondition.equals("searchByDate")) {
				String customerCode = "";
				String[] paramArray = { customerCode };
				contractInfoTOList = salesSF.getContractList("searchByCustomer", paramArray);

			}

			modelMap.put("gridRowJson", contractInfoTOList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		return modelMap;
	}

	@RequestMapping("/searchContractDetail.do")
	public ModelMap searchContractDetail(HttpServletRequest request, HttpServletResponse response) {

		String contractNo = request.getParameter("contractNo");

			ArrayList<ContractDetailTO> contractDetailTOList = salesSF.getContractDetailList(contractNo);

			modelMap.put("gridRowJson", contractDetailTOList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		return modelMap;
	}

	@RequestMapping("/searchEstimateInContractAvailable.do")
	public ModelMap searchEstimateInContractAvailable(HttpServletRequest request, HttpServletResponse response) {

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

			ArrayList<EstimateTO> estimateListInContractAvailable = salesSF.getEstimateListInContractAvailable(startDate, endDate);

			modelMap.put("gridRowJson", estimateListInContractAvailable);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		return modelMap;
	}

	@RequestMapping("/addNewContract.do")
	public HashMap<String, Object> addNewContract(HttpServletRequest request, HttpServletResponse response) {

		String batchList = request.getParameter("batchList");
		String contractDate = request.getParameter("contractDate");//오늘날짜
		String personCodeInCharge = request.getParameter("personCodeInCharge");//EMP-01

		HashMap<String, Object> resultMap = new HashMap<>();

		ContractTO workingContractTO = gson.fromJson(batchList, ContractTO.class);

		resultMap = salesSF.addNewContract(contractDate, personCodeInCharge,workingContractTO);


		return resultMap;
		
	}

	@RequestMapping("/cancleEstimate.do")
	public ModelMap cancleEstimate(HttpServletRequest request, HttpServletResponse response) {

		String estimateNo = request.getParameter("estimateNo");

			salesSF.changeContractStatusInEstimate(estimateNo, "N");

			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");
			modelMap.put("cancledEstimateNo", estimateNo);

		return modelMap;
	}

}
