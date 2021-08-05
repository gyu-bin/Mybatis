package com.example.logistics.production.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.logistics.production.to.MrpTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MrpDAO {

	public ArrayList<MrpTO> selectMrpListAll(HashMap<String, String> param) ;

	public ArrayList<MrpTO> selectMrpList(HashMap<String, String> param);

	public ArrayList<MrpTO> selectMrpListAsMrpGatheringNo(String mrpGatheringNo);

	public HashMap<String,Object> openMrp(HashMap<String, String> param);

	public int selectMrpCount(String mrpRegisterDate);

	public void insertMrp(MrpTO TO);

	public void updateMrp(MrpTO TO);

	public void  changeMrpGatheringStatus(HashMap<String, String> param);

	public void deleteMrp(MrpTO TO);
}
