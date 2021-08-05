package com.example.basicInfo.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.basicInfo.to.FinancialAccountAssociatesTO;

public interface FinancialAccountAssociatesApplicationService {

	public ArrayList<FinancialAccountAssociatesTO> getFinancialAccountAssociatesList(String searchCondition,
                                                                                     String workplaceCode);

	public String getNewFinancialAccountAssociatesCode();

	public HashMap<String, Object> batchFinancialAccountAssociatesListProcess(
            ArrayList<FinancialAccountAssociatesTO> financialAccountAssociatesList);
}
