package com.greenhi.admin.main.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 메인 화면
 * 
 * @author  won.lee
 * @date    2014. 8. 6. 
 * @history  
 */
@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	/**
	 * 메인 화면으로 리다이렉트
	 * 
	 * @param  
	 * @return 
	 * @throws 
	 * @history 
	 */
	@RequestMapping(value = "/")
	public String main(HttpServletRequest req,
			HttpServletResponse res, Locale locale, Model model) {
		
		try {
			res.sendRedirect( req.getContextPath() + "/main" );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 메인 화면 호출
	 * 메인 화면에 보여줄 데이터 조회
	 * 
	 * @param  
	 * @return 
	 * @throws 
	 * @history 
	 */
	@RequestMapping( "/main" )
	public String main(Locale locale, Model model) {
			
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "main";
	}
}
