package com.example.logistics.production.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.logistics.production.serviceFacade.ProductionServiceFacade;

import com.example.logistics.production.to.MrpGatheringTO;
import com.example.logistics.production.to.MrpTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/production/*")
public class MrpController{
	@Autowired
	private ProductionServiceFacade productionSF;

	// gson 라이브러리
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환

	private ModelMap modelMap = new ModelMap();

	@RequestMapping(value="/getMrpList.do")
	public ModelMap getMrpList(HttpServletRequest request, HttpServletResponse response) {
		String mrpGatheringStatusCondition = request.getParameter("mrpGatheringStatusCondition");
		String dateSearchCondition = request.getParameter("dateSearchCondition");
		String mrpStartDate = request.getParameter("mrpStartDate");
		String mrpEndDate = request.getParameter("mrpEndDate");
		String mrpGatheringNo = request.getParameter("mrpGatheringNo");
		//PrintWriter out = null;
		try {
			//out = response.getWriter();
			ArrayList<MrpTO> mrpList = null;
			if(mrpGatheringStatusCondition != null ) {
				//여기 null이라는 스트링값이 담겨저왔으니 null은 아님. 객체가있는상태.

				mrpList = productionSF.searchMrpList(mrpGatheringStatusCondition);

			} else if (dateSearchCondition != null) {

				mrpList = productionSF.searchMrpList(dateSearchCondition, mrpStartDate, mrpEndDate);

			} else if (mrpGatheringNo != null) {

				mrpList = productionSF.searchMrpListAsMrpGatheringNo(mrpGatheringNo);

			}

			modelMap.put("gridRowJson", mrpList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		} /*
		 * finally { System.out.println("getMrpList - 취합대기 검색/취합 클릭시 값확인 : "
		 * +mrpGatheringStatusCondition); out.println(gson.toJson(modelMap));
		 * out.close(); }
		 */
		return modelMap;
	}

	@RequestMapping(value="/openMrp.do")
	public HashMap<String,Object> openMrp(HttpServletRequest request, HttpServletResponse response) {
		String mpsNoListStr = request.getParameter("mpsNoList");
		System.out.println("mpsNoListStr 값확인 : "+mpsNoListStr);

		ArrayList<String> mpsNoArr = gson.fromJson(mpsNoListStr,
				new TypeToken<ArrayList<String>>() { }.getType());
		//제너릭 클래스를 사용할경우 정해지지 않은 제너릭타입을  명시하기위해서 TypeToken을 사용
		System.out.println("mpsNoArr 값확인 : "+mpsNoArr);
		HashMap<String, Object> resultMap = new HashMap<>();
		//PrintWriter out = null;
		try {
			//out = response.getWriter();

			resultMap = productionSF.openMrp(mpsNoArr);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		} /*
		 * finally { out.println(gson.toJson(resultMap)); out.close(); }
		 */
		return resultMap;
	}

	@RequestMapping(value="/registerMrp.do")
	public ModelMap registerMrp(HttpServletRequest request, HttpServletResponse response) {
		String batchList = request.getParameter("batchList");
		String mrpRegisterDate = request.getParameter("mrpRegisterDate");
		ArrayList<MrpTO> newMrpList	= gson.fromJson(batchList,
				new TypeToken<ArrayList<MrpTO>>() { }.getType());
		//제너릭 클래스를 사용할경우 정해지지 않은 제너릭타입을  명시하기위해서 TypeToken을 사용
		System.out.println("결과 MPS에 등록  batchList -> newMrpList : "+batchList+"->"+newMrpList);
		System.out.println("mrpRegisterDate"+mrpRegisterDate);
		//PrintWriter out = null;
		try {
			//out = response.getWriter();
			HashMap<String, Object> resultMap = productionSF.registerMrp(mrpRegisterDate, newMrpList);
			System.out.println("resultMap : "+resultMap);

			modelMap.put("result", resultMap);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		} /*
		 * finally { out.println(gson.toJson(modelMap)); out.close(); }
		 */
		return modelMap;
	}

	@RequestMapping(value="/getMrpGatheringList.do")
	public ModelMap getMrpGatheringList(HttpServletRequest request, HttpServletResponse response) {
		String mrpNoList = request.getParameter("mrpNoList");
		ArrayList<String> mrpNoArr = gson.fromJson(mrpNoList,
				new TypeToken<ArrayList<String>>() { }.getType());
		//제너릭 클래스를 사용할경우 정해지지 않은 제너릭타입을  명시하기위해서 TypeToken을 사용
		//PrintWriter out = null;
		try {
			//out = response.getWriter();
			ArrayList<MrpGatheringTO> mrpGatheringList = productionSF.getMrpGathering(mrpNoArr);

			modelMap.put("gridRowJson", mrpGatheringList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		} /*
		 * finally { out.println(gson.toJson(modelMap)); out.close(); }
		 */
		return modelMap;
	}

	@RequestMapping(value="/registerMrpGathering.do")
	public ModelMap registerMrpGathering(HttpServletRequest request, HttpServletResponse response) {
		String mrpGatheringRegisterDate = request.getParameter("mrpGatheringRegisterDate"); //선택한날짜
		String batchList = request.getParameter("batchList"); //소요량 취합결과 그리드에 뿌려진 데이터값
		String mrpNoAndItemCodeList = request.getParameter("mrpNoAndItemCodeList"); //mprNO : ItemCode
		System.out.println("mrpRegisterAndGather.jsp -> 넘어온 파라미터값 확인 ↓ ");
		System.out.println("mrpGatheringRegisterDate : "+mrpGatheringRegisterDate);
		System.out.println("batchList : "+batchList);
		System.out.println("mrpNoAndItemCodeList : "+mrpNoAndItemCodeList);
		System.out.println(" TYPE Token 값 확인  ↓");


		ArrayList<MrpGatheringTO> newMrpGatheringList
				= gson.fromJson(batchList, //소요량 취합결과 그리드에 뿌려진 데이터값
				new TypeToken<ArrayList<MrpGatheringTO>>() { }.getType());
		//제너릭 클래스를 사용할경우 정해지지 않은 제너릭타입을  명시하기위해서 TypeToken을 사용
		HashMap<String, String> mrpNoAndItemCodeMap
				=  gson.fromJson(mrpNoAndItemCodeList, //mprNO : ItemCode
				new TypeToken<HashMap<String, String>>() { }.getType());
		System.out.println("newMrpGatheringList : "+newMrpGatheringList);
		System.out.println("mrpNoAndItemCodeMap : "+mrpNoAndItemCodeMap);
		//PrintWriter out = null;
		try {
			//out = response.getWriter();

			HashMap<String, Object> resultMap
					= productionSF.registerMrpGathering(mrpGatheringRegisterDate, newMrpGatheringList, mrpNoAndItemCodeMap);
//														선택한날짜                  				getRowData		MRP-NO : DK-AP01
			System.out.println("resultMap : " + resultMap);
			modelMap.put("result", resultMap);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		} /*
		 * finally { out.println(gson.toJson(modelMap)); out.close(); }
		 */
		return modelMap;
	}

	@RequestMapping(value="/searchMrpGathering.do")
	public ModelMap searchMrpGathering(HttpServletRequest request, HttpServletResponse response) {
		String searchDateCondition = request.getParameter("searchDateCondition");
		String startDate = request.getParameter("mrpGatheringStartDate");
		String endDate = request.getParameter("mrpGatheringEndDate");
		//PrintWriter out = null;
		try {
			//out = response.getWriter();
			ArrayList<MrpGatheringTO> mrpGatheringList =
					productionSF.searchMrpGatheringList(searchDateCondition, startDate, endDate);

			modelMap.put("gridRowJson", mrpGatheringList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		} /*
		 * finally { out.println(gson.toJson(modelMap)); out.close(); }
		 */
		return modelMap;
	}


}
