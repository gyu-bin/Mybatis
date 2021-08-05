package com.example.logistics.material.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.logistics.material.serviceFacade.MaterialServiceFacade;
import com.example.logistics.material.to.BomDeployTO;
import com.example.logistics.material.to.BomInfoTO;
import com.example.logistics.material.to.BomTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/material/*")
public class BomController{

	// serviceFacade 참조변수 선언
	private MaterialServiceFacade materialSF;

	public void setMaterialServiceFacade(MaterialServiceFacade materialSF) {
		this.materialSF = materialSF;
	}

	private ModelMap modelMap = new ModelMap();

	// GSON 라이브러리
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환

	@RequestMapping("/searchBomDeploy.do")
	public ModelMap searchBomDeploy(HttpServletRequest request, HttpServletResponse response) {

		String deployCondition = request.getParameter("deployCondition");
		// log.info(deployCondition);
		// forward 정전개 || reverse 역전개
		String itemCode = request.getParameter("itemCode");
		// log.info(itemCode);
		// CodeController를 사용하여 검색한 후 선택하여 텍스트박스에 들어있던 값을 파라미터로 받아옴
		// ex ] DK-01
		String itemClassificationCondition = request.getParameter("itemClassificationCondition");

		try {

			ArrayList<BomDeployTO> bomDeployList = materialSF.getBomDeployList(deployCondition, itemCode,
					itemClassificationCondition);

			modelMap.put("gridRowJson", bomDeployList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

	@RequestMapping("/searchBomInfo.do")
	public ModelMap searchBomInfo(HttpServletRequest request, HttpServletResponse response) {

		String parentItemCode = request.getParameter("parentItemCode");

		try {

			ArrayList<BomInfoTO> bomInfoList = materialSF.getBomInfoList(parentItemCode);

			modelMap.put("gridRowJson", bomInfoList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (DataAccessException e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;

	}

	@RequestMapping("/searchAllItemWithBomRegisterAvailable.do")
	public ModelMap searchAllItemWithBomRegisterAvailable(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			ArrayList<BomInfoTO> allItemWithBomRegisterAvailable = materialSF.getAllItemWithBomRegisterAvailable();

			modelMap.put("gridRowJson", allItemWithBomRegisterAvailable);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (DataAccessException e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;

	}

	@RequestMapping("/batchBomListProcess.do")
	public ModelMap batchBomListProcess(HttpServletRequest request, HttpServletResponse response) {

		String batchList = request.getParameter("batchList");
		// log.info(batchList);
		ArrayList<BomTO> batchBomList = gson.fromJson(batchList, new TypeToken<ArrayList<BomTO>>() {
		}.getType());
		// 제너릭 클래스를 사용할경우 정해지지 않은 제너릭타입을 명시하기위해서 TypeToken을 사용

		try {

			HashMap<String, Object> resultList = materialSF.batchBomListProcess(batchBomList);

			modelMap.put("result", resultList);
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
