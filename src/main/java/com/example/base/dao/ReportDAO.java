package com.example.base.dao;

import java.util.ArrayList;

import com.example.base.to.ContractReportTO;
import com.example.base.to.EstimateReportTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportDAO {

    public ArrayList<EstimateReportTO> selectEstimateReport(String estimateNo);

    public ArrayList<ContractReportTO> selectContractReport(String contractNo);

}
