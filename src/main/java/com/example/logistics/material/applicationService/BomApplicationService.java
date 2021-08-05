package com.example.logistics.material.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.logistics.material.to.BomDeployTO;
import com.example.logistics.material.to.BomInfoTO;
import com.example.logistics.material.to.BomTO;

public interface BomApplicationService {

	public ArrayList<BomDeployTO> getBomDeployList(String deployCondition, String itemCode, String itemClassificationCondition);
	
	public ArrayList<BomInfoTO> getBomInfoList(String parentItemCode);
	
	public ArrayList<BomInfoTO> getAllItemWithBomRegisterAvailable();
	
	public HashMap<String, Object> batchBomListProcess(ArrayList<BomTO> batchBomList);

}
