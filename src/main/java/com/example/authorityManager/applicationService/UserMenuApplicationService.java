package com.example.authorityManager.applicationService;

import javax.servlet.ServletContext;

public interface UserMenuApplicationService {

	public String getUserMenuCode(String workplaceCode, String deptCode, String positionCode,
                                  ServletContext application);

}
