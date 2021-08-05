package com.example.base.applicationService;

import java.util.ArrayList;

import com.example.base.to.ContractReportTO;
import com.example.base.to.EstimateReportTO;

public interface ReportApplicationService {

    public ArrayList<EstimateReportTO> getEstimateReport(String estimateNo);

    public ArrayList<ContractReportTO> getContractReport(String contractNo);

}
