package com.example.logistics.production.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.logistics.production.to.MrpGatheringTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MrpGatheringDAO {

	public ArrayList<MrpGatheringTO> getMrpGathering(String mrpNoList);

	public ArrayList<MrpGatheringTO> selectMrpGatheringList(HashMap<String, String> param);

	public int selectMrpGatheringCount(String mrpGatheringRegisterDate);

	public void insertMrpGathering(MrpGatheringTO TO);

	public void updateMrpGathering(MrpGatheringTO TO);

	public void deleteMrpGathering(MrpGatheringTO TO);

	public void updateMrpGatheringContract(HashMap<String,String> parameter);

	public int getMrpGatheringSeq();
}
