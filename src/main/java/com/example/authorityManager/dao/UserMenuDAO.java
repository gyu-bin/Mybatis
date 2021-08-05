package com.example.authorityManager.dao;

import java.util.HashMap;
import java.util.List;

import com.example.authorityManager.to.UserMenuTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMenuDAO {

	public List<UserMenuTO> selectUserMenuCodeList(HashMap<String, String> param);

}
