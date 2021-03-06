package com.example.logistics.sales.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.logistics.sales.to.EstimateTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EstimateDAO {
	public ArrayList<EstimateTO> selectEstimateList(HashMap<String,String> param);

	public EstimateTO selectEstimate(String estimateNo);

	public int selectEstimateCount(String estimateDate);

	public void insertEstimate(EstimateTO TO);

	public void updateEstimate(EstimateTO TO);

	public void changeContractStatusOfEstimate(HashMap<String,String> param);

}
