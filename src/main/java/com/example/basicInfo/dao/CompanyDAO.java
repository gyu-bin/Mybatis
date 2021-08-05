package com.example.basicInfo.dao;

import java.util.ArrayList;

import com.example.basicInfo.to.CompanyTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyDAO{
	
	public ArrayList<CompanyTO> selectCompanyList();
	
	public void insertCompany(CompanyTO TO);
	
	public void updateCompany(CompanyTO TO);

	public void deleteCompany(CompanyTO TO);
	
}
