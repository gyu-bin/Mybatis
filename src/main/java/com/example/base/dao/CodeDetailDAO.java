package com.example.base.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.base.to.CodeDetailTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CodeDetailDAO {

	ArrayList<CodeDetailTO> selectDetailCodeList(String divisionCode);

	void insertDetailCode(CodeDetailTO TO);

	void updateDetailCode(CodeDetailTO TO);

	public void deleteDetailCode(CodeDetailTO TO);
	
	public void changeCodeUseCheck(HashMap<String, String> param);
}
