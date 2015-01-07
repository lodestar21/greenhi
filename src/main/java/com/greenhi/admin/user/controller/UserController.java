package com.greenhi.admin.user.controller;

import java.util.Enumeration;
import java.util.List;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greenhi.admin.code.service.CodeService;
import com.greenhi.admin.code.vo.CodeVO;
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

    @Autowired
    private CodeService codeService;
    
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

	/**
	 * 사용자 리스트 조회
	 * 
	 * @param  search 조회 조건, pageNum 페이지 번호, isFirst 처음 유무
	 * @return 리스트 화면
	 * @throws Exception
	 * @history 
	 */
	@RequestMapping( "/list/{pageNum}" )
	public String list( HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res,
			@ModelAttribute( "search" ) UserVO search,
			@PathVariable( "pageNum" ) int pageNum,
			@RequestParam( value = "isFirst", defaultValue = "true", required = false ) boolean isFirst,
			Model model ) throws Exception {
		
		int isEqualSearch = 0; // 완전일치 검색
		String orderBy = "DESC"; // 오래된순 정렬 검색
		
		// 페이지
		if ( pageNum != 0 ) {
			search.setPageNum( pageNum );
		}
		
		// 검색옵션 : 검색어
		if ( search.getSearchWord() == null ) {
			search.setSearchWord( "" );
		}
		
		// 검색옵션 : 완전일치
		if ( !StringUtil.isEmpty( req.getParameter( "isEqualSearch" ) ) && !req.getParameter( "isEqualSearch" ).equals( "0" ) ) {
			isEqualSearch = 1;
			search.setEqualSearch( true );
		}
		
		// 검색옵션 : 오래된순 정렬
		if ( !StringUtil.isEmpty( req.getParameter( "orderBy" ) ) && !req.getParameter( "orderBy" ).equals( "desc" ) ) {
			orderBy = "ASC";
			search.setOrderBy( orderBy );
		}

		int totalCount = 0;
		
		List<UserVO> list = null;

		//if ( !isFirst ) {
			list = userService.list( search );
			totalCount = userService.listCount( search );
		//}
			
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

		// 지역코드(600)
		CodeVO custCodeVo = new CodeVO();
		custCodeVo.setUperCode( 600 );
		model.addAttribute( "custCodeList", codeService.listChildCode( custCodeVo ) );
		
		model.addAttribute( "startIdx", search.getStartIdx() );
		model.addAttribute( "recPerPage", search.getRecordPerPage() );
		model.addAttribute( "isEqualSearch", isEqualSearch );
		model.addAttribute( "list", list );
		model.addAttribute( "pageNum", pageNum );
		model.addAttribute( "totalCount", totalCount );
		model.addAttribute( "search", search );
		
		return "user/list";
	}

	/**
	 * 사용자 정보 상세 페이지
	 * 
	 * @param  userNo 사용자번호
	 * @return 상세 페이지 이동
	 * @throws Exception
	 * @history 
	 */
	@RequestMapping( value = "/get/{userNo}" )
	public String info( @PathVariable( "userNo" ) long userNo,
			HttpSession session,
			Model model ) throws Exception {

		UserVO user = new UserVO();
		
		// 수정 모드
		if ( userNo > 0 ) {
			user.setUserNo( userNo );
			user = userService.get( user );
			model.addAttribute( "editMode", "update" );
		}
		
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
		
		return "user/edit";
	}

	/**
	 * 신규 회원 등록
	 * 
	 * @param  user 회원 정보
	 * @return ResponseVO 결과 정보
	 * @throws Exception
	 * @history 
	 */
	@ResponseBody
	@RequestMapping( value = "/addProcess", method = RequestMethod.POST )
	public ResponseVO addProcess( 
			@ModelAttribute( "UserVO" ) UserVO user,
			Model model,
			HttpServletResponse res,
			HttpSession session ) throws Exception {

		ResponseVO result = new ResponseVO();

		UserVO adminUser = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );
		user.setCreateUser( adminUser.getUserNo() );
		
		long resultUserNo = userService.insertUser( user );
		
		if ( resultUserNo == -1 ) {
			result.setStatus( 400 );
			result.setMessage( "아이디 중복입니다." );
			return result;
		} else if ( resultUserNo < 1 ) {
			result.setStatus( 400 );
			result.setMessage( "회원 등록 중 오류가 발생 했습니다." );
			return result;
		} else {
			result.setStatus( 200 );
			result.setData( resultUserNo );
			result.setMessage( "회원 등록이 완료 되었습니다." );
			return result;
		}
		
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
	@RequestMapping( value = "/updateProcess", method = RequestMethod.POST )
	public ResponseVO updateProcess( 
			@ModelAttribute( "UserVO" ) UserVO user,
			Model model,
			HttpServletResponse res,
			HttpSession session ) throws Exception {

		ResponseVO result = new ResponseVO();

		UserVO adminUser = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );
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