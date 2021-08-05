package com.example.logistics.material.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.logistics.material.to.BomDeployTO;
import com.example.logistics.material.to.BomInfoTO;
import com.example.logistics.material.to.BomTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BomDAO {

	public ArrayList<BomDeployTO> selectBomDeployList(HashMap<String, String> param);
	
	public ArrayList<BomInfoTO> selectBomInfoList(String parentItemCode);
	
	public ArrayList<BomInfoTO> selectAllItemWithBomRegisterAvailable();
	
	public void insertBom(BomTO TO);
	
	public void updateBom(BomTO TO);
	
	public void deleteBom(BomTO TO);
}
