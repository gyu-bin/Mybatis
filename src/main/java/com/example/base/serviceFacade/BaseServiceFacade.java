package com.example.base.serviceFacade;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.base.to.AddressTO;
import com.example.base.to.CodeDetailTO;
import com.example.base.to.CodeTO;
import com.example.base.to.ContractReportTO;
import com.example.base.to.EstimateReportTO;

public interface BaseServiceFacade {

	public ArrayList<CodeDetailTO> getDetailCodeList(String divisionCode);

	public ArrayList<CodeTO> getCodeList();

	public Boolean checkCodeDuplication(String divisionCode, String newDetailCode);

	public HashMap<String, Object> batchCodeListProcess(ArrayList<CodeTO> codeList);

	public HashMap<String, Object> batchDetailCodeListProcess(ArrayList<CodeDetailTO> detailCodeList);

	public HashMap<String, Object> changeCodeUseCheckProcess(ArrayList<CodeDetailTO> detailCodeList);

	public ArrayList<AddressTO> getAddressList(String sidoName, String searchAddressType, String searchValue, String mainNumber);

	public ArrayList<EstimateReportTO> getEstimateReport(String estimateNo);

	public ArrayList<ContractReportTO> getContractReport(String contractNo);
}
