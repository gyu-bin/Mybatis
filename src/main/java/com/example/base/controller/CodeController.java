package com.example.base.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.example.base.serviceFacade.BaseServiceFacade;
import com.example.base.to.CodeDetailTO;
import com.example.base.to.CodeTO;

@RestController
@RequestMapping("/base/*")
public class CodeController{

	// serviceFacade 참조변수 선언
	@Autowired
	private BaseServiceFacade baseSF;

	private ModelMap modelMap = new ModelMap();

	// gson 라이브러리
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환

	@RequestMapping(value="/searchCodeList.do")
	public ModelMap findCodeList(HttpServletRequest request, HttpServletResponse response) {

		try {

			ArrayList<CodeTO> codeList = baseSF.getCodeList();

			modelMap.put("codeList", codeList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}

		return modelMap;

	}
	@RequestMapping(value="/codeList.do")
	public ModelMap findDetailCodeList(HttpServletRequest request, HttpServletResponse response) {

		String divisionCode = request.getParameter("divisionCodeNo");
		System.out.println(divisionCode);

		try {

			ArrayList<CodeDetailTO> detailCodeList = baseSF.getDetailCodeList(divisionCode);


			modelMap.put("detailCodeList", detailCodeList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		}catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

	@RequestMapping(value="/checkCodeDuplication.do")
	public ModelMap checkCodeDuplication(HttpServletRequest request, HttpServletResponse response) {


		String divisionCode = request.getParameter("divisionCode");
		String newDetailCode = request.getParameter("newCode");


		try {

			Boolean flag = baseSF.checkCodeDuplication(divisionCode, newDetailCode);

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
	@RequestMapping(value="/batchListProcess.do")
	public ModelMap batchListProcess(HttpServletRequest request, HttpServletResponse response) {


		String batchList = request.getParameter("batchList");
		String tableName = request.getParameter("tableName");

		try {

			ArrayList<CodeTO> codeList = null;
			ArrayList<CodeDetailTO> detailCodeList = null;
			HashMap<String, Object> resultMap = null;

			if (tableName.equals("CODE")) {

				codeList = gson.fromJson(batchList, new TypeToken<ArrayList<CodeTO>>() {}.getType());

				resultMap = baseSF.batchCodeListProcess(codeList);

			} else if (tableName.equals("CODE_DETAIL")) {

				detailCodeList = gson.fromJson(batchList, new TypeToken<ArrayList<CodeDetailTO>>() {}.getType());

				resultMap = baseSF.batchDetailCodeListProcess(detailCodeList);

			}

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

	@RequestMapping(value="/changeCodeUseCheckProcess.do")
	public ModelMap changeCodeUseCheckProcess(HttpServletRequest request, HttpServletResponse response) {


		String batchList = request.getParameter("batchList");

		try {

			ArrayList<CodeDetailTO> detailCodeList = null;
			HashMap<String, Object> resultMap = null;

			detailCodeList = gson.fromJson(batchList, new TypeToken<ArrayList<CodeDetailTO>>() {
			}.getType());

			resultMap = baseSF.changeCodeUseCheckProcess(detailCodeList);

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
