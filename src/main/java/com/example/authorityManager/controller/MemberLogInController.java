package com.example.authorityManager.controller;

import com.example.authorityManager.exception.IdNotFoundException;
import com.example.authorityManager.exception.PwMissMatchException;
import com.example.authorityManager.exception.PwNotFoundException;
import com.example.hr.to.EmpInfoTO;
import com.example.authorityManager.serviceFacade.AuthorityManagerServiceFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class MemberLogInController{

    @Autowired
    private AuthorityManagerServiceFacade authorityManagerSF;

    private ModelMap modelMap = new ModelMap();

    @RequestMapping("/login.do")
    public ModelAndView LogInCheck(HttpServletRequest request, HttpServletResponse response)throws IdNotFoundException, PwMissMatchException, PwNotFoundException  {

        String viewName = null;
        HttpSession session = request.getSession();
        String companyCode = request.getParameter("companyCode");      //COM-01
        String workplaceCode = request.getParameter("workplaceCode");  //BRC-01
        String userId = request.getParameter("userId");                //1111
        String userPassword = request.getParameter("userPassword");    //1111

            EmpInfoTO TO = authorityManagerSF.accessToAuthority(companyCode, workplaceCode, userId, userPassword);

            if (TO != null) {
                ServletContext application = request.getServletContext();          // 로그인 성공 했을 경우 그사람의 정보를 클라이언트가 종료되기전 까지 저장 해준다
                session.setAttribute("sessionID", session.getId());
                session.setAttribute("userId", TO.getUserId());                //유저ID
                session.setAttribute("empCode", TO.getEmpCode());              //사원번호
                session.setAttribute("empName", TO.getEmpName());              //사원이름
                session.setAttribute("deptCode", TO.getDeptCode());            //부서코드
                session.setAttribute("deptName", TO.getDeptName());            //부서명
                session.setAttribute("positionCode", TO.getPositionCode());    //직급코드
                session.setAttribute("positionName", TO.getPositionName());    //직급명
                session.setAttribute("companyCode", TO.getCompanyCode());      //회사코드
                session.setAttribute("workplaceCode", workplaceCode);          //사업장코드
                session.setAttribute("workplaceName", TO.getWorkplaceName());  //사업장명

                String menuCode=authorityManagerSF.getUserMenuCode(workplaceCode,TO.getDeptCode(),TO.getPositionCode(),application);
                session.setAttribute("menuCode",menuCode);

                viewName = "redirect:" + request.getContextPath() + "/hello2.html";
                System.out.println("로그인성공");
                System.out.println(request.getContextPath());
            }
        return new ModelAndView(viewName, modelMap);
    }
}