package com.example.authorityManager.serviceFacade;

import javax.servlet.ServletContext;

import com.example.authorityManager.exception.IdNotFoundException;
import com.example.authorityManager.exception.PwMissMatchException;
import com.example.authorityManager.exception.PwNotFoundException;
import com.example.hr.to.EmpInfoTO;

public interface AuthorityManagerServiceFacade {

	public EmpInfoTO accessToAuthority(String companyCode, String workplaceCode, String inputId, String inputPassWord)
			throws IdNotFoundException, PwMissMatchException, PwNotFoundException;

	public String getUserMenuCode(String workplaceCode, String deptCode, String positionCode,
                                  ServletContext application);

}
