package com.example.base.dao;

import java.util.ArrayList;

import com.example.base.to.CodeTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CodeDAO {

	public ArrayList<CodeTO> selectCodeList();

	public void insertCode(CodeTO codeTO);

	public void updateCode(CodeTO codeTO);

	public void deleteCode(CodeTO codeTO);

}
