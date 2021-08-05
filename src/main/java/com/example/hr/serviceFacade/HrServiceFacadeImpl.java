package com.example.hr.serviceFacade;

import java.util.ArrayList;
import java.util.HashMap;


import com.example.hr.applicationService.EmpApplicationService;
import com.example.hr.to.EmpInfoTO;
import com.example.hr.to.EmployeeBasicTO;
import com.example.hr.to.EmployeeDetailTO;
import com.example.hr.to.EmployeeSecretTO;
import org.springframework.beans.factory.annotation.Autowired;

public class HrServiceFacadeImpl implements HrServiceFacade {
	// 참조변수 선언

	@Autowired
	private EmpApplicationService empAS;

	@Override
	public ArrayList<EmpInfoTO> getAllEmpList(String searchCondition, String[] paramArray) {

		ArrayList<EmpInfoTO> empList=null;

		empList=empAS.getAllEmpList(searchCondition, paramArray);

		return empList;

	}

	@Override
	public EmpInfoTO getEmpInfo(String companyCode, String empCode) {

		return empAS.getEmpInfo(companyCode, empCode);

	}

	@Override
	public String getNewEmpCode(String companyCode) {

		return empAS.getNewEmpCode(companyCode);

	}

	@Override
	public HashMap<String, Object> batchEmpBasicListProcess(ArrayList<EmployeeBasicTO> empBasicList) {

		return empAS.batchEmpBasicListProcess(empBasicList);

	}

	@Override
	public HashMap<String, Object> batchEmpDetailListProcess(ArrayList<EmployeeDetailTO> empDetailList) {

		return empAS.batchEmpDetailListProcess(empDetailList);

	}

	@Override
	public HashMap<String, Object> batchEmpSecretListProcess(ArrayList<EmployeeSecretTO> empSecretList) {

		return empAS.batchEmpSecretListProcess(empSecretList);

	}

	@Override
	public Boolean checkUserIdDuplication(String companyCode, String newUserId) {

		return empAS.checkUserIdDuplication(companyCode, newUserId);

	}

	@Override
	public Boolean checkEmpCodeDuplication(String companyCode, String newEmpCode) {

		return empAS.checkEmpCodeDuplication(companyCode, newEmpCode);

	}

}
