package com.example.logistics.sales.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.logistics.production.to.ContractDetailInMpsAvailableTO;
import com.example.logistics.sales.to.ContractDetailTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContractDetailDAO {

	public ArrayList<ContractDetailTO> selectContractDetailList(String contractNo);

	public ArrayList<ContractDetailTO> selectDeliverableContractDetailList(String contractNo);

	public ArrayList<ContractDetailTO> selectContractDetailCount(String contractNo);

	public ArrayList<ContractDetailInMpsAvailableTO> selectContractDetailListInMpsAvailable(HashMap<String,String> param);

	public void changeMpsStatusOfContractDetail(HashMap<String,String> param);

	public void deleteContractDetail(ContractDetailTO TO);

	public HashMap<String,Object> insertContractDetail(HashMap<String,String> param);
}
