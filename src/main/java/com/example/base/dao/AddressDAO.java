package com.example.base.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.base.to.AddressTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressDAO {

	public String selectSidoCode(String sidoName);
	
	public ArrayList<AddressTO> selectRoadNameAddressList(HashMap<String, String> param);
	
	public ArrayList<AddressTO> selectJibunAddressList(HashMap<String, String> param);
}
