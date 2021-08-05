package com.example.basicInfo.dao;

import java.util.ArrayList;

import com.example.basicInfo.to.DepartmentTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentDAO {

	public ArrayList<DepartmentTO> selectDepartmentListByCompany(String companyCode);

	public ArrayList<DepartmentTO> selectDepartmentListByWorkplace(String workplaceCode);

	public void insertDepartment(DepartmentTO TO);

	public void updateDepartment(DepartmentTO TO);

	public void deleteDepartment(DepartmentTO TO);
}
