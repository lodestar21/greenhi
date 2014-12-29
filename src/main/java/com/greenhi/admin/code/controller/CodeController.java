package com.greenhi.admin.code.controller;

import java.util.List;

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
import com.greenhi.admin.user.vo.UserVO;
import com.greenhi.common.Constants;
import com.greenhi.common.response.ResponseVO;
import com.greenhi.common.util.StringUtil;


/**
 * 코드 관리 controller
 * 
 * @author  won.lee
 * @date    2014. 12. 28.
 * @history 
 */
@Controller
@RequestMapping(value = "/Code")
public class CodeController {
	
    private static final Logger logger = LoggerFactory.getLogger(CodeController.class);

    @Autowired
    private CodeService codeService;
    
	/**
	 * 코드 리스트 조회
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
			@ModelAttribute( "search" ) CodeVO search,
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

		List<CodeVO> list = null;
		
		list = codeService.list( search );
		totalCount = codeService.listCount( search );

		model.addAttribute( "startIdx", search.getStartIdx() );
		model.addAttribute( "recPerPage", search.getRecordPerPage() );
		model.addAttribute( "isEqualSearch", isEqualSearch );
		model.addAttribute( "list", list );
		model.addAttribute( "pageNum", pageNum );
		model.addAttribute( "totalCount", totalCount );
		model.addAttribute( "search", search );
		
		return "code/list";
	}

	/**
	 * 코드 상세 페이지
	 * 
	 * @param  codeId 코드번호
	 * @return 상세 페이지 이동
	 * @throws Exception
	 * @history 
	 */
	@RequestMapping( value = "/get/{codeId}" )
	public String info( @PathVariable( "codeId" ) long codeId,
			HttpSession session,
			Model model ) throws Exception {

		CodeVO code = new CodeVO();
		List<CodeVO> listChildCode = null;
		
		// 수정 모드
		if ( codeId > 0 ) {
			code.setCodeId( codeId );
			code = codeService.get( code );
			model.addAttribute( "editMode", "update" );

			CodeVO childCodeVo = new CodeVO();
			childCodeVo.setUperCode( codeId );
			listChildCode = codeService.listChildCode( childCodeVo );
		}
		
		model.addAttribute( "code", code );
		model.addAttribute( "listChildCode", listChildCode );
		
		return "code/edit";
	}

	/**
	 * 코드 등록
	 * 
	 * @param  code 내용
	 * @return ResponseVO 결과 내용
	 * @throws Exception
	 * @history 
	 */
	@ResponseBody
	@RequestMapping( value = "/addProcess", method = RequestMethod.POST )
	public ResponseVO addProcess( 
			@ModelAttribute( "CodeVO" ) CodeVO code,
			Model model,
			HttpServletResponse res,
			HttpSession session ) throws Exception {

		ResponseVO result = new ResponseVO();

		UserVO adminUser = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );
		code.setCreateUser( adminUser.getUserNo() );
		
		long codeId = codeService.insertCode( code );

		if ( codeId == -1 ) {
			result.setStatus( 400 );
			result.setMessage( "중복된 코드가 존재합니다." );
			return result;
		} else if ( codeId < 1 ) {
			result.setStatus( 400 );
			result.setMessage( "코드 등록 중 오류가 발생 했습니다." );
			return result;
		} else {
			result.setStatus( 200 );
			result.setData( codeId );
			result.setMessage( "코드 등록이 완료 되었습니다." );
			return result;
		}
		
	}

	/**
	 * 코드 수정
	 * 
	 * @param  code 내용
	 * @return ResponseVO 결과 내용
	 * @throws Exception
	 * @history 
	 */
	@ResponseBody
	@RequestMapping( value = "/updateProcess", method = RequestMethod.POST )
	public ResponseVO updateProcess( 
			@ModelAttribute( "CodeVO" ) CodeVO code,
			Model model,
			HttpServletResponse res,
			HttpSession session ) throws Exception {

		ResponseVO result = new ResponseVO();

		UserVO adminUser = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );
		code.setModifyUser( adminUser.getUserNo() );
		
		if ( !codeService.updateCode( code ) ) {
			result.setStatus( 400 );
			result.setMessage( "코드 수정 중 오류가 발생 했습니다." );
			return result;
		} else {
			result.setStatus( 200 );
			result.setMessage( "코드 수정 되었습니다." );
			return result;
		}
		
	}

	/**
	 * 코드 상세 페이지
	 * 
	 * @param  codeId 코드번호
	 * @return 상세 페이지 이동
	 * @throws Exception
	 * @history 
	 */
	@RequestMapping( value = "/get/{uperCode}/{codeId}" )
	public String infoChild( @PathVariable( "uperCode" ) long uperCode,
			@PathVariable( "codeId" ) long codeId,
			HttpSession session,
			Model model ) throws Exception {

		CodeVO code = new CodeVO();
		CodeVO codeUpr = new CodeVO();
		
		// 수정 모드
		if ( codeId > 0 ) {
			code.setCodeId( codeId );
			code = codeService.get( code );
			model.addAttribute( "editMode", "update" );
		}
		
		// 상위 코드 정보 조회
		codeUpr.setCodeId( uperCode );
		model.addAttribute( "codeUpr", codeService.get( codeUpr ) );
		
		model.addAttribute( "code", code );
		
		return "code/edit_child";
	}

	/**
	 * 코드 삭제
	 * 
	 * @param  code 내용
	 * @return ResponseVO 결과 내용
	 * @throws Exception
	 * @history 
	 */
	@ResponseBody
	@RequestMapping( value = "/delete", method = RequestMethod.POST )
	public ResponseVO delete( 
			@ModelAttribute( "CodeVO" ) CodeVO code,
			Model model,
			HttpServletResponse res,
			HttpSession session ) throws Exception {

		ResponseVO result = new ResponseVO();

		UserVO adminUser = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );
		code.setModifyUser( adminUser.getUserNo() );
		
		if ( !codeService.deleteCode( code ) ) {
			result.setStatus( 400 );
			result.setMessage( "코드 삭제 중 오류가 발생 했습니다." );
			return result;
		} else {
			result.setStatus( 200 );
			result.setMessage( "코드 삭제 되었습니다." );
			return result;
		}
		
	}
}