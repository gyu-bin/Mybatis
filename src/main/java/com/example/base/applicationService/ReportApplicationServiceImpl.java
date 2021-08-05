package com.example.base.applicationService;

import java.util.ArrayList;

import com.example.base.dao.ReportDAO;
import com.example.base.to.ContractReportTO;
import com.example.base.to.EstimateReportTO;
import com.example.base.to.ContractReportTO;
import com.example.base.to.EstimateReportTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportApplicationServiceImpl implements ReportApplicationService {

    @Autowired
    private ReportDAO reportDAO;

    @Override
    public ArrayList<EstimateReportTO> getEstimateReport(String estimateNo) {

        return reportDAO.selectEstimateReport(estimateNo);

    }

    @Override
    public ArrayList<ContractReportTO> getContractReport(String contractNo) {

        return reportDAO.selectContractReport(contractNo);
    }

}
