package com.example.basicInfo.dao;

import java.util.ArrayList;

import com.example.basicInfo.to.CustomerTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerDAO {

	public ArrayList<CustomerTO> selectCustomerListByCompany();

	public ArrayList<CustomerTO> selectCustomerListByWorkplace(String workplaceCode);
	
	public void insertCustomer(CustomerTO TO);

	public void updateCustomer(CustomerTO TO);

	public void deleteCustomer(CustomerTO TO);
}
