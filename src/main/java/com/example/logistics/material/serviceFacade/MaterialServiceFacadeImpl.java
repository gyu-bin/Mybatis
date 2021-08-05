package com.example.logistics.material.serviceFacade;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.logistics.material.applicationService.BomApplicationService;

import com.example.logistics.material.applicationService.OrderApplicationService;

import com.example.logistics.material.applicationService.StockApplicationService;

import com.example.logistics.material.to.BomDeployTO;
import com.example.logistics.material.to.BomInfoTO;
import com.example.logistics.material.to.BomTO;
import com.example.logistics.material.to.OrderInfoTO;
import com.example.logistics.material.to.StockLogTO;
import com.example.logistics.material.to.StockTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialServiceFacadeImpl implements MaterialServiceFacade {

	// 참조변수 선언
	@Autowired
	private BomApplicationService bomAS;
	@Autowired
	private OrderApplicationService orderAS;
	@Autowired
	private StockApplicationService stockAS;

	@Override
	public ArrayList<BomDeployTO> getBomDeployList(String deployCondition, String itemCode,
                                                   String itemClassificationCondition) {

		return bomAS.getBomDeployList(deployCondition, itemCode, itemClassificationCondition);

	}

	@Override
	public ArrayList<BomInfoTO> getBomInfoList(String parentItemCode) {

		return bomAS.getBomInfoList(parentItemCode);

	}

	@Override
	public HashMap<String, Object> getOrderList(String startDate, String endDate) {

		return orderAS.getOrderList(startDate, endDate);

	}

	@Override
	public ArrayList<BomInfoTO> getAllItemWithBomRegisterAvailable() {

		return bomAS.getAllItemWithBomRegisterAvailable();

	}

	@Override
	public HashMap<String, Object> batchBomListProcess(ArrayList<BomTO> batchBomList) {

		return bomAS.batchBomListProcess(batchBomList);

	}

	@Override
	public HashMap<String, Object> getOrderDialogInfo(ArrayList<String> mrpNoArr) {

		return orderAS.getOrderDialogInfo(mrpNoArr);

	}

	@Override
	public HashMap<String, Object> order(ArrayList<String> mrpGaNoArr) {

		HashMap<String, Object> resultMap = null;

		resultMap = orderAS.order(mrpGaNoArr);

		return resultMap;

	}

	@Override
	public HashMap<String, Object> optionOrder(String itemCode, String itemAmount) {
		// TODO Auto-generated method stub

		return orderAS.optionOrder(itemCode, itemAmount);

	}

	@Override
	public ArrayList<StockTO> getStockList() {

		return stockAS.getStockList();

	}

	@Override
	public ArrayList<StockLogTO> getStockLogList(String startDate, String endDate) {

		return stockAS.getStockLogList(startDate, endDate);

	}

	@Override
	public ArrayList<OrderInfoTO> getOrderInfoListOnDelivery() {

		return orderAS.getOrderInfoListOnDelivery();

	}

	@Override
	public HashMap<String, Object> warehousing(ArrayList<String> orderNoArr) {

		HashMap<String, Object> resultMap = null;

		resultMap = stockAS.warehousing(orderNoArr);

		return resultMap;
	}

	@Override
	public ArrayList<OrderInfoTO> getOrderInfoList(String startDate, String endDate) {

		return orderAS.getOrderInfoList(startDate, endDate);

	}

/*	@Override
	public HashMap<String, Object> changeSafetyAllowanceAmount(String itemCode, String itemName,
			String safetyAllowanceAmount) {

		HashMap<String, Object> resultMap = null;

		resultMap = stockAS.changeSafetyAllowanceAmount(itemCode, itemName, safetyAllowanceAmount);

		return resultMap;
	}*/
}
