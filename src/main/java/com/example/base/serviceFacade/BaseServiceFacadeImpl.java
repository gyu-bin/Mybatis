package com.example.base.serviceFacade;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.base.applicationService.AddressApplicationService;
import com.example.base.applicationService.CodeApplicationService;
import com.example.base.applicationService.ReportApplicationService;
import com.example.base.to.AddressTO;
import com.example.base.to.CodeDetailTO;
import com.example.base.to.CodeTO;
import com.example.base.to.ContractReportTO;
import com.example.base.to.EstimateReportTO;

@Service
public class BaseServiceFacadeImpl implements BaseServiceFacade {

	// 참조변수 선언
	@Autowired
	private CodeApplicationService codeAS;
	@Autowired
	private AddressApplicationService addressAS;
	@Autowired
	private ReportApplicationService reportAS;


	@Override
	public ArrayList<CodeDetailTO> getDetailCodeList(String divisionCode) {

		return	codeAS.getDetailCodeList(divisionCode);

	}

	@Override
	public ArrayList<CodeTO> getCodeList() {

		return codeAS.getCodeList();
	}

	@Override
	public Boolean checkCodeDuplication(String divisionCode, String newDetailCode) {
		return null;
	}


	@Override
	public HashMap<String, Object> batchCodeListProcess(ArrayList<CodeTO> codeList) {
		return codeAS.batchCodeListProcess(codeList);

	}

	@Override
	public HashMap<String, Object> batchDetailCodeListProcess(ArrayList<CodeDetailTO> detailCodeList) {

		return codeAS.batchDetailCodeListProcess(detailCodeList);

	}

	@Override
	public HashMap<String, Object> changeCodeUseCheckProcess(ArrayList<CodeDetailTO> detailCodeList) {


		return  codeAS.changeCodeUseCheckProcess(detailCodeList);

	}

	@Override
	public ArrayList<AddressTO> getAddressList(String sidoName, String searchAddressType, String searchValue, String mainNumber) {

		return addressAS.getAddressList(sidoName, searchAddressType, searchValue, mainNumber);

	}


	@Override
	public ArrayList<EstimateReportTO> getEstimateReport(String estimateNo) {

		return reportAS.getEstimateReport(estimateNo);
	}

	@Override
	public ArrayList<ContractReportTO> getContractReport(String contractNo) {

		return reportAS.getContractReport(contractNo);
	}

}
