package com.example.base.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.base.dao.AddressDAO;
import com.example.base.to.AddressTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressApplicationServiceImpl implements AddressApplicationService {
	// DAO 참조변수
	@Autowired
	private AddressDAO addressDAO;

	@Override
	public ArrayList<AddressTO> getAddressList(String sidoName, String searchAddressType, String searchValue,
                                               String mainNumber) {

		HashMap<String,String> param=new HashMap<>();
		ArrayList<AddressTO> addressList = null;

		String sidoCode = addressDAO.selectSidoCode(sidoName);

		switch (searchAddressType) {

		case "roadNameAddress":
			String buildingMainNumber = mainNumber;
			param.put("sidoCode",sidoCode);
			param.put("searchValue:",searchValue);
			param.put("buildingMainNumber",buildingMainNumber);

			addressList = addressDAO.selectRoadNameAddressList(param);

			break;

		case "jibunAddress":

			String jibunMainAddress = mainNumber;

			addressList = addressDAO.selectJibunAddressList(param);

			break;

		}

		return addressList;

	}

}
