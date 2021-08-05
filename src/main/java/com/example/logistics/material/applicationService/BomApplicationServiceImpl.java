package com.example.logistics.material.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.logistics.material.dao.BomDAO;

import com.example.logistics.material.to.BomDeployTO;
import com.example.logistics.material.to.BomInfoTO;
import com.example.logistics.material.to.BomTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BomApplicationServiceImpl implements BomApplicationService {

	// DAO 참조변수 선언
	@Autowired
	private BomDAO bomDAO;

	@Override
	public ArrayList<BomDeployTO> getBomDeployList(String deployCondition, String itemCode, String itemClassificationCondition) {
		HashMap<String,String> map=new HashMap<>();

		map.put("deployCondition", deployCondition);

		map.put("itemCode", itemCode);

		map.put("itemClassificationCondition", itemClassificationCondition);

		return bomDAO.selectBomDeployList(map);
	}

	public ArrayList<BomInfoTO> getBomInfoList(String parentItemCode) {

		return bomDAO.selectBomInfoList(parentItemCode);

	}

	@Override
	public ArrayList<BomInfoTO> getAllItemWithBomRegisterAvailable() {

		return bomDAO.selectAllItemWithBomRegisterAvailable();

	}

	public HashMap<String, Object> batchBomListProcess(ArrayList<BomTO> batchBomList) {

		HashMap<String, Object> resultMap = new HashMap<>();

		int insertCount = 0;
		int updateCount = 0;
		int deleteCount = 0;

		for (BomTO TO : batchBomList) {

			String status = TO.getStatus();

			switch (status) {

			case "INSERT":

				bomDAO.insertBom(TO);

				insertCount++;

				break;

			case "UPDATE":

				bomDAO.updateBom(TO);

				updateCount++;

				break;

			case "DELETE":

				bomDAO.deleteBom(TO);

				deleteCount++;

				break;

			}

		}

		resultMap.put("INSERT", insertCount);
		resultMap.put("UPDATE", updateCount);
		resultMap.put("DELETE", deleteCount);

		return resultMap;
	}

}
