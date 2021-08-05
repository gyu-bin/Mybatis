package com.example.hr.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.hr.to.EmployeeSecretTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeSecretDAO {

	public ArrayList<EmployeeSecretTO> selectEmployeeSecretList(HashMap<String, String> param);

	public EmployeeSecretTO selectUserPassWord(HashMap<String, String> param);

	public void insertEmployeeSecret(EmployeeSecretTO TO);

	public int selectUserPassWordCount(HashMap<String, String> param);

}
