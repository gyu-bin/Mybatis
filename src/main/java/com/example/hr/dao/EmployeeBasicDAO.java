package com.example.hr.dao;

import java.util.ArrayList;
import java.util.HashMap;


import com.example.hr.to.EmployeeBasicTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeBasicDAO {

	public ArrayList<EmployeeBasicTO> selectEmployeeBasicList(String companyCode);
	
	public EmployeeBasicTO selectEmployeeBasicTO(HashMap<String, String> param);
	
	public void insertEmployeeBasic(EmployeeBasicTO TO);
	
	public void changeUserAccountStatus(HashMap<String, String> param);
	
}
