package com.example.basicInfo.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.basicInfo.to.DepartmentTO;

public interface DepartmentApplicationService {
	
	public ArrayList<DepartmentTO> getDepartmentList(String searchCondition, String companyCode,
                                                     String workplaceCode);
	
	public String getNewDepartmentCode(String companyCode);
	
	public HashMap<String, Object> batchDepartmentListProcess(ArrayList<DepartmentTO> departmentList);

}
