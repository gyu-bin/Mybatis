package com.example.logistics.production.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.logistics.production.to.ProductionPerformanceInfoTO;
import com.example.logistics.production.to.WorkOrderInfoTO;


public interface WorkOrderApplicationService {

	public HashMap<String,Object> getWorkOrderableMrpList();

	public HashMap<String,Object> getWorkOrderSimulationList(String mrpNo);

	public HashMap<String,Object> workOrder(String workPlaceCode,String productionProcess,String mrpGatheringNo);

	public ArrayList<WorkOrderInfoTO> getWorkOrderInfoList();

	public ArrayList<ProductionPerformanceInfoTO> getProductionPerformanceInfoList();

	public HashMap<String,Object> workOrderCompletion(String workOrderNo,String actualCompletionAmount);

	public HashMap<String,Object> showWorkSiteSituation(String workSiteCourse,String workOrderNo, String itemClassIfication);

	public void workCompletion(String workOrderNo,String itemCode , ArrayList<String> itemCodeListArr);

	public HashMap<String,Object> workSiteLogList(String workSiteLogDate);

} 
 