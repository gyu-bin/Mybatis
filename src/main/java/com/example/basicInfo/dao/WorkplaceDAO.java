package com.example.basicInfo.dao;

import java.util.ArrayList;

import com.example.basicInfo.to.WorkplaceTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkplaceDAO {
	
	public ArrayList<WorkplaceTO> selectWorkplaceList(String companyCode);

	public void insertWorkplace(WorkplaceTO TO);
	
	public void updateWorkplace(WorkplaceTO TO);
	
	public void deleteWorkplace(WorkplaceTO TO);
}
