package com.example.logistics.logisticsInfo.dao;

import java.util.ArrayList;

import com.example.logistics.logisticsInfo.to.WarehouseTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WarehouseDAO {
	public ArrayList<WarehouseTO> selectWarehouseList();
}
