package com.example.logistics.sales.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.logistics.sales.to.ContractInfoTO;
import com.example.logistics.sales.to.ContractTO;
import com.example.logistics.sales.to.EstimateTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContractDAO {

	public ArrayList<EstimateTO> selectEstimateListInContractAvailable(HashMap<String,String> param);

	public ArrayList<ContractInfoTO> selectContractListByDate(HashMap<String,String> param);

	public ArrayList<ContractInfoTO> selectContractListByCustomer(String customerCode);

	public ArrayList<ContractInfoTO> selectDeliverableContractListByDate(HashMap<String,String> param);

	public ArrayList<ContractInfoTO> selectDeliverableContractListByCustomer(String customerCode);


	public int selectContractCount(String contractDate);

	public void insertContract(ContractTO TO);

	public void updateContract(ContractTO TO);

	public void deleteContract(ContractTO TO);

}
