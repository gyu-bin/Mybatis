package com.example.authorityManager.applicationService;

import com.example.authorityManager.exception.IdNotFoundException;
import com.example.authorityManager.exception.PwMissMatchException;
import com.example.authorityManager.exception.PwNotFoundException;
import com.example.hr.to.EmpInfoTO;
import org.springframework.dao.DataAccessException;

public interface LogInApplicationService {

	public EmpInfoTO accessToAuthority(String companyCode, String workplaceCode, String inputId, String inputPassWord)
			throws IdNotFoundException, PwMissMatchException, PwNotFoundException, DataAccessException;

}
