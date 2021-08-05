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

import com.example.logistics.production.to.ProductionPerformanceInfoTO;
import com.example.logistics.production.to.WorkOrderInfoTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/production/*")
public class WorkOrderController{

	@Autowired
	private ProductionServiceFacade productionSF;

	// gson 라이브러리
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환
	
	private ModelMap modelMap = new ModelMap();

	@RequestMapping("/getWorkOrderableMrpList.do")
	public HashMap<String, Object> getWorkOrderableMrpList(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> resultMap = new HashMap<>();
		try {

			resultMap = productionSF.getWorkOrderableMrpList();

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		}

		return null;
	}

	@RequestMapping("/showWorkOrderDialog.do")
	public HashMap<String, Object> showWorkOrderDialog(HttpServletRequest request, HttpServletResponse response) {

		String mrpGatheringNo = request.getParameter("mrpGatheringNo");;
		
		HashMap<String,Object> resultMap = new HashMap<>();

		try {
			resultMap = productionSF.getWorkOrderSimulationList(mrpGatheringNo);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		}
		return resultMap;
	}
	@RequestMapping("/workOrder.do")
	public HashMap<String, Object> workOrder(HttpServletRequest request, HttpServletResponse response) {

		String mrpGatheringNo= request.getParameter("mrpGatheringNo");//소요량취합번호
		String workPlaceCode = request.getParameter("workPlaceCode"); //사업장코드
		String productionProcess = request.getParameter("productionProcessCode"); //생산공정코드:PP002
		HashMap<String,Object> resultMap = new HashMap<>();

		try {

			resultMap = productionSF.workOrder(workPlaceCode,productionProcess,mrpGatheringNo);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		}
		return resultMap;
	}

	@RequestMapping("/showWorkOrderInfoList.do")
	public ModelMap showWorkOrderInfoList(HttpServletRequest request, HttpServletResponse response) {

		ArrayList<WorkOrderInfoTO> workOrderInfoList = null;

		try {

			workOrderInfoList = productionSF.getWorkOrderInfoList();

			modelMap.put("gridRowJson", workOrderInfoList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

	@RequestMapping("/workOrderCompletion.do")
	public HashMap<String, Object> workOrderCompletion(HttpServletRequest request, HttpServletResponse response) {

		String workOrderNo=request.getParameter("workOrderNo");
		String actualCompletionAmount=request.getParameter("actualCompletionAmount");
		HashMap<String, Object> resultMap = new HashMap<>();

		try {

			resultMap = productionSF.workOrderCompletion(workOrderNo,actualCompletionAmount);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		}
		return resultMap;
	}
	@RequestMapping("/getProductionPerformanceInfoList.do")
	public ModelMap getProductionPerformanceInfoList(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<ProductionPerformanceInfoTO> productionPerformanceInfoList = null;

		try {

			productionPerformanceInfoList = productionSF.getProductionPerformanceInfoList();

			modelMap.put("gridRowJson", productionPerformanceInfoList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}
	@RequestMapping("/showWorkSiteSituation.do")
	public HashMap<String, Object> showWorkSiteSituation(HttpServletRequest request, HttpServletResponse response) {

		HashMap<String, Object> resultMap = new HashMap<>();

		String workSiteCourse = request.getParameter("workSiteCourse");//원재료검사:RawMaterials,제품제작:Production,판매제품검사:SiteExamine
		String workOrderNo = request.getParameter("workOrderNo");//작업지시일련번호
		String itemClassIfication = request.getParameter("itemClassIfication");//품목분류:완제품,반제품,재공품

		try {

			resultMap = productionSF.showWorkSiteSituation(workSiteCourse,workOrderNo,itemClassIfication);

		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		}
		return resultMap;
	}
	@RequestMapping("/workCompletion.do")
	public ModelMap workCompletion(HttpServletRequest request, HttpServletResponse response) {
		
		String workOrderNo = request.getParameter("workOrderNo");
		String itemCode = request.getParameter("itemCode");
		String itemCodeList = request.getParameter("itemCodeList");
		ArrayList<String> itemCodeListArr = gson.fromJson(itemCodeList,
				new TypeToken<ArrayList<String>>() {}.getType());

		try {

			productionSF.workCompletion(workOrderNo,itemCode,itemCodeListArr);

		} catch (Exception e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		return modelMap;
	}

	@RequestMapping("/workSiteLogList.do")
	public HashMap<String, Object> workSiteLogList(HttpServletRequest request, HttpServletResponse response) {
		String workSiteLogDate = request.getParameter("workSiteLogDate");
		HashMap<String, Object> resultMap = new HashMap<>();
		//PrintWriter out = null;
		try {
			//out = response.getWriter();
			resultMap=productionSF.workSiteLogList(workSiteLogDate);
		} catch (Exception e2) {
			e2.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", e2.getMessage());

		} /*
		 * finally { out.println(gson.toJson(resultMap)); out.close(); }
		 */
		return resultMap;
	}

}
