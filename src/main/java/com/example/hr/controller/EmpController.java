package com.example.hr.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hr.serviceFacade.HrServiceFacade;

import com.example.hr.to.EmpInfoTO;
import com.example.hr.to.EmployeeBasicTO;
import com.example.hr.to.EmployeeDetailTO;
import com.example.hr.to.EmployeeSecretTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/hr/*")
public class EmpController{

	// serviceFacade 참조변수 선언
	private HrServiceFacade hrSF;

	private ModelMap modelMap = new ModelMap();

	// GSON 라이브러리
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환

	@RequestMapping("/searchAllEmpList.do/")
	public ModelMap searchAllEmpList(HttpServletRequest request, HttpServletResponse response) {

		String searchCondition = request.getParameter("searchCondition");
		String companyCode = request.getParameter("companyCode");
		String workplaceCode = request.getParameter("workplaceCode");
		String deptCode = request.getParameter("deptCode");

		ArrayList<EmpInfoTO> empList = null;
		String[] paramArray = null;

		try {

			switch (searchCondition) {

			case "ALL":

				paramArray = new String[] { companyCode };
				break;

			case "WORKPLACE":

				paramArray = new String[] { companyCode, workplaceCode };
				break;

			case "DEPT":

				paramArray = new String[] { companyCode, deptCode };
				break;

			case "RETIREMENT":

				paramArray = new String[] { companyCode };
				break;

			}

			empList = hrSF.getAllEmpList(searchCondition, paramArray);

			modelMap.put("gridRowJson", empList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}
	@RequestMapping("/searchEmpInfo.do/")
	public ModelMap searchEmpInfo(HttpServletRequest request, HttpServletResponse response) {

		String companyCode = request.getParameter("companyCode");
		String empCode = request.getParameter("empCode");

		EmpInfoTO empInfoTO = null;

		try {

			empInfoTO = hrSF.getEmpInfo(companyCode, empCode);

			modelMap.put("empInfo", empInfoTO);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;

	}
	@RequestMapping("/checkUserIdDuplication.do/")
	public ModelMap checkUserIdDuplication(HttpServletRequest request, HttpServletResponse response) {

		String companyCode = request.getParameter("companyCode");
		String newUserId = request.getParameter("newUseId");

		try {

			Boolean flag = hrSF.checkUserIdDuplication(companyCode, newUserId);

			modelMap.put("result", flag);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

	@RequestMapping("/checkEmpCodeDuplication.do/")
	public ModelMap checkEmpCodeDuplication(HttpServletRequest request, HttpServletResponse response) {

		String companyCode = request.getParameter("companyCode");
		String newEmpCode = request.getParameter("newEmpCode");

		try {

			Boolean flag = hrSF.checkEmpCodeDuplication(companyCode, newEmpCode);

			modelMap.put("result", flag);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}
	@RequestMapping("/getNewEmpCode.do/")
	public ModelMap getNewEmpCode(HttpServletRequest request, HttpServletResponse response) {

		String companyCode = request.getParameter("companyCode");
		String newEmpCode = null;

		try {

			newEmpCode = hrSF.getNewEmpCode(companyCode);

			modelMap.put("newEmpCode", newEmpCode);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (DataAccessException e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

	@RequestMapping("/batchListProcess.do/")
	public ModelMap batchListProcess(HttpServletRequest request, HttpServletResponse response) {

		String batchList = request.getParameter("batchList");
		String tableName = request.getParameter("tableName");

		try {

			ArrayList<EmployeeBasicTO> empBasicList = null;
			ArrayList<EmployeeDetailTO> empDetailList = null;
			ArrayList<EmployeeSecretTO> empSecretList = null;

			HashMap<String, Object> resultMap = null;

			if (tableName.equals("BASIC")) {

				empBasicList = gson.fromJson(batchList, new TypeToken<ArrayList<EmployeeBasicTO>>() {
				}.getType());

				resultMap = hrSF.batchEmpBasicListProcess(empBasicList);

			} else if (tableName.equals("DETAIL")) {

				empDetailList = gson.fromJson(batchList, new TypeToken<ArrayList<EmployeeDetailTO>>() {
				}.getType());

				resultMap = hrSF.batchEmpDetailListProcess(empDetailList);

			} else if (tableName.equals("SECRET")) {

				empSecretList = gson.fromJson(batchList, new TypeToken<ArrayList<EmployeeSecretTO>>() {
				}.getType());

				resultMap = hrSF.batchEmpSecretListProcess(empSecretList);

			}

			modelMap.put("result", resultMap);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (DataAccessException e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

}
