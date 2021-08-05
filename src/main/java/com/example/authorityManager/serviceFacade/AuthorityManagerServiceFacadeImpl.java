package com.example.authorityManager.serviceFacade;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.authorityManager.applicationService.LogInApplicationService;
import com.example.authorityManager.applicationService.UserMenuApplicationService;
import com.example.authorityManager.exception.IdNotFoundException;
import com.example.authorityManager.exception.PwMissMatchException;
import com.example.authorityManager.exception.PwNotFoundException;
import com.example.hr.to.EmpInfoTO;
import org.springframework.stereotype.Service;

@Service
public class AuthorityManagerServiceFacadeImpl implements AuthorityManagerServiceFacade {

	// AS 참조변수 선언
	@Autowired
	private  LogInApplicationService logInAS;
	@Autowired
	private  UserMenuApplicationService userMenuAS;

	@Override
	public EmpInfoTO accessToAuthority(String companyCode, String workplaceCode, String inputId, String inputPassWord)
			throws IdNotFoundException, PwMissMatchException, PwNotFoundException {

		return	logInAS.accessToAuthority(companyCode, workplaceCode, inputId, inputPassWord);		//문제가 없을 경우 로그인 한 사람의 정보가 담겨짐

	}

	@Override
	public String getUserMenuCode(String workplaceCode, String deptCode, String positionCode,
			ServletContext application) {

			return userMenuAS.getUserMenuCode(workplaceCode, deptCode, positionCode, application);
	}

}
