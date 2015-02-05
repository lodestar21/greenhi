package com.greenhi.admin.main.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenhi.admin.user.vo.UserVO;
import com.greenhi.common.Constants;

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
	public String main(HttpServletRequest req,HttpSession session, 
			HttpServletResponse res, Locale locale, Model model) {

		try {

			UserVO user = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );
			if ( user.getUserType() == 102 ) {
				res.sendRedirect( req.getContextPath() + "/CustCleanInfo/cleanList/1" );
			} else if ( user.getUserType() == 103) {
				res.sendRedirect( req.getContextPath() + "/UserCleanInfo/userList" );
			} else {
				res.sendRedirect( req.getContextPath() + "/User/list/1" );
			}
		} catch (Exception e) {
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
	public String main(HttpSession session, HttpServletRequest req, HttpServletResponse res, Locale locale, Model model) {

		try {

			UserVO user = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );
			if ( user.getUserType() == 102 ) {
				res.sendRedirect( req.getContextPath() + "/CustCleanInfo/cleanList/1" );
			} else if ( user.getUserType() == 103) {
				res.sendRedirect( req.getContextPath() + "/UserCleanInfo/userList" );
			} else {
				res.sendRedirect( req.getContextPath() + "/User/list/1" );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
