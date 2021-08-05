package com.example.hr.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.hr.to.EmployeeDetailTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeDetailDAO {

	public ArrayList<EmployeeDetailTO> selectEmployeeDetailList(HashMap<String, String> param);
	
	public ArrayList<EmployeeDetailTO> selectUserIdList(String companyCode);
	
	public void insertEmployeeDetail(EmployeeDetailTO TO);
	

}
