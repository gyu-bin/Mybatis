package com.example.logistics.production.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.logistics.production.to.MpsTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MpsDAO {

	public ArrayList<MpsTO> selectMpsList(HashMap<String, String> param);

	public int selectMpsCount(String mpsPlanDate);

	public void insertMps(MpsTO TO);

	public void updateMps(MpsTO TO);

	public void changeMrpApplyStatus(HashMap<String, String> param);

	public void deleteMps(MpsTO TO);

}
