package com.example.logistics.production.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.logistics.production.to.ProductionPerformanceInfoTO;
import com.example.logistics.production.to.WorkOrderInfoTO;
import com.example.logistics.production.to.WorkSiteLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkOrderDAO {

	public HashMap<String,Object> getWorkOrderableMrpList(HashMap<String, String> param);

	public HashMap<String,Object> getWorkOrderSimulationList(HashMap<String, String> param);

	public HashMap<String,Object> workOrder(HashMap<String, String> param);

	public ArrayList<WorkOrderInfoTO> selectWorkOrderInfoList();

	public HashMap<String,Object> workOrderCompletion(HashMap<String, String> param);

	public ArrayList<ProductionPerformanceInfoTO> selectProductionPerformanceInfoList();

	public HashMap<String,Object> selectWorkSiteSituation(HashMap<String, String> param);

	public void updateWorkCompletionStatus(HashMap<String, String> param);

	public ArrayList<WorkSiteLog> workSiteLogList(String workSiteLogDate);
}
