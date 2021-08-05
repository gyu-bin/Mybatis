package com.example.base.controller;

import net.sf.json.JSONObject;
import org.exolab.castor.dsml.XML;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/base/*")
public class ApiExplorer {


	@RequestMapping(value="/openapi", method= RequestMethod.POST)
	public ModelMap openapi(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap=new ModelMap();
		BufferedReader br = null;
		String result=null;


		try{
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			String pageNo=request.getParameter("pageNo");
			String numOfRows=request.getParameter("numOfRows");
			String startCreateDt=request.getParameter("startCreateDt");
			String endCreateDt=request.getParameter("endCreateDt");
			String urlstr = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson"
					+ "?ServiceKey=6zVMxmw7BgNKh8EHAoYcJoKLa7zUg%2FAh92wsksSjivt2VEvqGKG7%2BSRlFMhP7hgWU2kV238R04RoLOEAIiQizw%3D%3D"
					+ "&pageNo="+pageNo+"&numOfRows="+numOfRows+"&startCreateDt="+startCreateDt+"&endCreateDt="+endCreateDt;



			URL url = new URL(urlstr);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
			result ="";
			String line;
			while((line=br.readLine()) != null) {
				result = result+line;
			}
           /* JSONObject xmlJSONObj = XML.toJSONObject(result.toString());*/
			/*modelMap.put("gridRowJson", xmlJSONObj.toString());
			modelMap.put("error_code", 0);
			modelMap.put("error_msg", "성공");*/
		}catch (UnsupportedEncodingException e) {
			modelMap.put("error-code", -1);
			modelMap.put("error-msg", "내부서버오류");
			e.printStackTrace();
		}catch(IOException e) {
			modelMap.put("error-code", -1);
			modelMap.put("error-msg", "내부서버오류");
			e.printStackTrace();
		}catch(Exception e) {
			modelMap.put("error-code", -1);
			modelMap.put("error-msg", "내부서버오류");
			e.printStackTrace();

		}finally {
			modelMap.put("error-code", 0);
			modelMap.put("error-msg", "성공");

		}

		return modelMap;
	}


}

