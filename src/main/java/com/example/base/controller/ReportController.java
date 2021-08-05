package com.example.base.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.example.base.serviceFacade.BaseServiceFacade;
import com.example.base.to.ContractReportTO;
import com.example.base.to.EstimateReportTO;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/base/*")
public class ReportController{

   private ModelAndView modelAndView;
   private ModelMap modelMap = new ModelMap();

   @Autowired
   private BaseServiceFacade baseSF;

   @RequestMapping(value = "/report.pdf", params = "method=estimateReport")
   public ModelAndView estimateReport(HttpServletRequest request, HttpServletResponse response) {
      HashMap<String, Object> parameters = new HashMap<>();

      String estimateNo = request.getParameter("orderDraftNo");

      try {

         ArrayList<EstimateReportTO> estimateList = baseSF.getEstimateReport(estimateNo);
         parameters.put("orderDraftNo", estimateNo);


         JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(estimateList);
         modelMap.put("source", source);
         modelMap.put("format", "pdf");



      } catch (Exception e) {
         e.printStackTrace();
      }

      modelAndView = new ModelAndView("estimatePdfView", modelMap);
      return modelAndView;
   }

   @RequestMapping(value = "/report.pdf", params = "method=contractReport")
   public ModelAndView contractReport(HttpServletRequest request, HttpServletResponse response) {

      String contractNo = request.getParameter("orderDraftNo");

      try {

         ArrayList<ContractReportTO> contractList = baseSF.getContractReport(contractNo);

         JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(contractList);
         modelMap.put("source", source);
         modelMap.put("format", "pdf");

      } catch (Exception e) {

         e.printStackTrace();
      }

      modelAndView = new ModelAndView("contractPdfView", modelMap);
      return modelAndView;
   }
}