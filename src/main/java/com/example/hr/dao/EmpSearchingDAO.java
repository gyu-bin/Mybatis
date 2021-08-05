package com.example.hr.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.hr.to.EmpInfoTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpSearchingDAO {

	public ArrayList<EmpInfoTO> selectAllEmpList(HashMap<String, String> param);

	public ArrayList<EmpInfoTO> getTotalEmpInfo(HashMap<String, String> param);
	
	
}
