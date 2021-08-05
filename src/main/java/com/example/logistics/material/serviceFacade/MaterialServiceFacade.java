package com.example.logistics.material.serviceFacade;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.logistics.material.to.BomDeployTO;
import com.example.logistics.material.to.BomInfoTO;
import com.example.logistics.material.to.BomTO;
import com.example.logistics.material.to.OrderInfoTO;
import com.example.logistics.material.to.StockLogTO;
import com.example.logistics.material.to.StockTO;


public interface MaterialServiceFacade {

	public ArrayList<BomDeployTO> getBomDeployList(String deployCondition, String itemCode, String itemClassificationCondition);
	
	public ArrayList<BomInfoTO> getBomInfoList(String parentItemCode);
	
	public ArrayList<BomInfoTO> getAllItemWithBomRegisterAvailable();
	
	public HashMap<String, Object> batchBomListProcess(ArrayList<BomTO> batchBomList);
	
	public HashMap<String,Object> getOrderList(String startDate, String endDate);
	
	public HashMap<String,Object> getOrderDialogInfo(ArrayList<String> mrpNoArr);
	
	public HashMap<String,Object> order(ArrayList<String> mrpGaNoArr);
	
	public HashMap<String,Object> optionOrder(String itemCode, String itemAmount);
	
	public HashMap<String,Object> warehousing(ArrayList<String> orderNoArr);
	
	public ArrayList<StockTO> getStockList();
	
	public ArrayList<StockLogTO> getStockLogList(String startDate, String endDate);
	
	public ArrayList<OrderInfoTO> getOrderInfoListOnDelivery();
	
	public ArrayList<OrderInfoTO> getOrderInfoList(String startDate, String endDate);

	/*public HashMap<String, Object> changeSafetyAllowanceAmount(String itemCode, String itemName,
                                                               String safetyAllowanceAmount);*/
}
