package com.example.basicInfo.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.basicInfo.to.WorkplaceTO;

public interface WorkplaceApplicationService {
	
	public ArrayList<WorkplaceTO> getWorkplaceList(String companyCode);
	
	public String getNewWorkplaceCode(String companyCode);
	
	public HashMap<String, Object> batchWorkplaceListProcess(ArrayList<WorkplaceTO> workplaceList);
	
}
