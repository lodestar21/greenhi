package com.greenhi.admin.user.controller;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greenhi.admin.user.service.UserService;
import com.greenhi.admin.user.vo.UserVO;
import com.greenhi.common.Constants;
import com.greenhi.common.response.ResponseVO;
import com.greenhi.common.util.StringUtil;


/**
 * 사용자관리 controller
 * table : T_USER
 * 
 * @author  won.lee
 * @date    2014. 8. 7. 
 * @history  
 */
@Controller
@RequestMapping(value = "/User")
public class UserController {
	
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

	/**
	 * 로그인 화면 호출
	 * 
	 * @param  
	 * @return 로그인 페이지
	 * @throws Exception
	 * @history 
	 */
	@RequestMapping( value = "/login", method = RequestMethod.GET )
	public String login( HttpServletRequest req,
			HttpSession session,
			Model model,
			HttpServletResponse res ) throws Exception {
		
		return "user/login";
	}

    /**
     * 로그인 처리
     * 
     * @param  data 로그인 정보
     * @return 로그인 또는 메인 페이지 이동
     * @throws Exception
     * @history 
     */
    @RequestMapping(value = "/login", method=RequestMethod.POST)
    public String login(@ModelAttribute( "loginUser" ) UserVO data, 
    		HttpSession session,
    		HttpServletRequest req, 
			HttpServletResponse res,
    		Model model) throws Exception {
		if(logger.isDebugEnabled())
			logger.debug("UserController login [{}]", data != null ? data.toString() : "NULL");

		if ( StringUtil.isEmpty( data.getUserId() ) || StringUtil.isEmpty( data.getPassWord() ) ) {
			model.addAttribute( "failMessage", "아이디/비밀번호를 입력해 주세요" );
			return "user/login";
		}
		
		try {
			
			int resultLogin= userService.login( data, session, req );
			
			if ( resultLogin == 1 ) {

				// 아이디 저장 쿠키 처리
				if ( data.isSaveIdFlag() ) {
					Cookie saveIdCookie = new Cookie( Constants.ADMIN_SAVE_ID_KEY, data.getUserId() );
					saveIdCookie.setMaxAge( 60 * 60 * 24 );
					res.addCookie( saveIdCookie );
				} else {
					Cookie saveIdCookie = new Cookie( Constants.ADMIN_SAVE_ID_KEY, "" );
					saveIdCookie.setMaxAge( 0 );
					res.addCookie( saveIdCookie );
				}
				
				res.sendRedirect( req.getContextPath() + "/main" );
				
				return null;
			} else if ( resultLogin == -1 ) {
				model.addAttribute( "failMessage", "존재하지 않는 아이디 입니다." );
			} else if ( resultLogin == -2 ) {
				model.addAttribute( "failMessage", "비밀번호를 다시 입력하세요." );
			}
			
		} catch ( NullPointerException ne ) {
			logger.info( "login", "계정정보 없음 " + data );
			model.addAttribute( "failMessage", "해당 계정이 존재하지 않습니다." );
		} catch ( Exception e ) {
			logger.error( "Thread[" + Thread.currentThread().getId() + "]" + "login fail", e );
			model.addAttribute( "failMessage", "로그인 실패" );
		}
		
		return "user/login";
		
    }

	/**
	 * 사용자 로그아웃 처리
	 * 
	 * @param  session
	 * @return ResponseVO 로그아웃 결과 정보
	 * @throws Exception
	 * @history 
	 */
    @RequestMapping(value = "/logout")
	@ResponseBody
	public ResponseVO logout( HttpSession session ) throws Exception {

		ResponseVO result = new ResponseVO();
		
		@SuppressWarnings("unchecked")
		Enumeration<String> sessionKeys = session.getAttributeNames();
		
		while ( sessionKeys.hasMoreElements() ) {
			String key = sessionKeys.nextElement();

			session.removeAttribute( key );
		}

		result.setStatus( 200 );
		result.setMessage( "로그아웃 되었습니다." );

		return result;
	}
	
}