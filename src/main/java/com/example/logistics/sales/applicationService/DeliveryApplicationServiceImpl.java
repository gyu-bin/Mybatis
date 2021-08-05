package com.example.logistics.sales.applicationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.logistics.sales.dao.DeliveryDAO;
import com.example.logistics.sales.to.DeliveryInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryApplicationServiceImpl implements DeliveryApplicationService {

	@Autowired
	private DeliveryDAO deliveryDAO;

	@Override
	public ArrayList<DeliveryInfoTO> getDeliveryInfoList() {

		return deliveryDAO.selectDeliveryInfoList();
	}

	@Override
	public HashMap<String, Object> batchDeliveryListProcess(List<DeliveryInfoTO> deliveryTOList) {
		
		HashMap<String, Object> resultMap = new HashMap<>();

			ArrayList<String> insertList = new ArrayList<>();
			ArrayList<String> updateList = new ArrayList<>();
			ArrayList<String> deleteList = new ArrayList<>();

			for (DeliveryInfoTO bean : deliveryTOList) {

				String status = bean.getStatus();

				switch (status.toUpperCase()) {

				case "INSERT":

					// 새로운 일련번호 생성
					String newDeliveryNo = "새로운";

					// Bean 에 새로운 일련번호 세팅
					bean.setDeliveryNo(newDeliveryNo);
					deliveryDAO.insertDeliveryResult(bean);
					insertList.add(newDeliveryNo);

					break;

				case "UPDATE":

					deliveryDAO.updateDeliveryResult(bean);

					updateList.add(bean.getDeliveryNo());

					break;

				case "DELETE":

					deliveryDAO.deleteDeliveryResult(bean);

					deleteList.add(bean.getDeliveryNo());

					break;

				}

			}

			resultMap.put("INSERT", insertList);
			resultMap.put("UPDATE", updateList);
			resultMap.put("DELETE", deleteList);

		return resultMap;
	}

	@Override
	public HashMap<String,Object> deliver(String contractDetailNo) {

		HashMap<String,String> param=new HashMap<>();
		HashMap<String,Object> resultMap=new HashMap<>();
		param.put("contractDetailNo", contractDetailNo);
		deliveryDAO.deliver(param);
		resultMap.put("errorCode", param.get("ERROR_CODE"));
		resultMap.put("errorMsg", param.get("ERROR_MSG"));

		return resultMap;

	}
	
}
