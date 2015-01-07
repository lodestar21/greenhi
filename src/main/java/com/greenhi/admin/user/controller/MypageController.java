package com.greenhi.admin.user.controller;

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

import com.greenhi.admin.code.service.CodeService;
import com.greenhi.admin.code.vo.CodeVO;
import com.greenhi.admin.user.service.UserService;
import com.greenhi.admin.user.vo.UserVO;
import com.greenhi.common.Constants;
import com.greenhi.common.response.ResponseVO;


/**
 * 마이페이지 controller
 * 
 * @author  won.lee
 * @date    2014. 8. 7. 
 * @history  
 */
@Controller
@RequestMapping(value = "/Mypage")
public class MypageController {
	
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CodeService codeService;
    
	/**
	 * 사용자 정보 상세 페이지
	 * 
	 * @param  userNo 사용자번호
	 * @return 상세 페이지 이동
	 * @throws Exception
	 * @history 
	 */
	@RequestMapping( value = "/get" )
	public String info( HttpSession session,
			Model model ) throws Exception {

		UserVO user = new UserVO();
		
		UserVO adminUser = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );
		user.setUserNo( adminUser.getUserNo() );
		user = userService.get( user );
		
		// 사용자_유형코드(100)
		CodeVO cypcdVo = new CodeVO();
		cypcdVo.setUperCode( 100 );
		model.addAttribute( "userTypeList", codeService.listChildCode( cypcdVo ) );

		// 사용자_상태코드(200)
		CodeVO sttuscdVo = new CodeVO();
		sttuscdVo.setUperCode( 200 );
		model.addAttribute( "userStatList", codeService.listChildCode( sttuscdVo ) );

		// 지역코드(300)
		CodeVO localCodeVo = new CodeVO();
		localCodeVo.setUperCode( 300 );
		model.addAttribute( "localCodeList", codeService.listChildCode( localCodeVo ) );

		// 은행코드(400)
		CodeVO depositBankVo = new CodeVO();
		depositBankVo.setUperCode( 400 );
		model.addAttribute( "depositBankList", codeService.listChildCode( depositBankVo ) );

		// 지역코드(600)
		CodeVO custCodeVo = new CodeVO();
		custCodeVo.setUperCode( 600 );
		model.addAttribute( "custCodeList", codeService.listChildCode( custCodeVo ) );
		
		// 사용자 정보
		model.addAttribute( "user", user );
		
		return "mypage/edit";
	}
	
	/**
	 * 회원 정보 수정
	 * 
	 * @param  user 회원 정보
	 * @return ResponseVO 결과 정보
	 * @throws Exception
	 * @history 
	 */
	@ResponseBody
	@RequestMapping( value = "/update", method = RequestMethod.POST )
	public ResponseVO updateProcess( 
			@ModelAttribute( "UserVO" ) UserVO user,
			Model model,
			HttpServletResponse res,
			HttpSession session ) throws Exception {

		ResponseVO result = new ResponseVO();

		UserVO adminUser = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );
		
		if ( user.getUserNo() != adminUser.getUserNo() ) {
			result.setStatus( 400 );
			result.setMessage( "회원 수정 중 심각한 오류가 발생 했습니다." );
			return result;
		}
		
		user.setModifyUser( adminUser.getUserNo() );
		int resultChek = userService.updateUser( user );
		
		if ( resultChek < 1 ) {
			result.setStatus( 400 );
			result.setMessage( "회원 수정 중 오류가 발생 했습니다." );
			return result;
		} else {
			result.setStatus( 200 );
			result.setMessage( "회원 수정 되었습니다." );
			return result;
		}
		
	}
}