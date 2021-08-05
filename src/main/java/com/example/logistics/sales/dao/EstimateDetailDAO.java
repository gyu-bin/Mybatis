package com.example.logistics.sales.dao;

import java.util.ArrayList;

import com.example.logistics.sales.to.EstimateDetailTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EstimateDetailDAO {

	public ArrayList<EstimateDetailTO> selectEstimateDetailList(String estimateNo);

	public int selectEstimateDetailCount(String estimateNo);

	public void insertEstimateDetail(EstimateDetailTO TO);

	public void updateEstimateDetail(EstimateDetailTO TO);

	public void deleteEstimateDetail(EstimateDetailTO TO);

}