package com.greenhi.admin.bank.controller;

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

import com.greenhi.admin.bank.service.BankBranchService;
import com.greenhi.admin.bank.vo.BankBranchVO;
import com.greenhi.admin.code.service.CodeService;
import com.greenhi.admin.code.vo.CodeVO;
import com.greenhi.admin.user.vo.UserVO;
import com.greenhi.common.Constants;
import com.greenhi.common.response.ResponseVO;
import com.greenhi.common.util.StringUtil;


/**
 * 은행 지점 관리 controller
 * 
 * @author  won.lee
 * @date    2014. 12. 28.
 * @history 
 */
@Controller
@RequestMapping(value = "/BankBranch")
public class BankBranchController {
	
    private static final Logger logger = LoggerFactory.getLogger(BankBranchController.class);

    @Autowired
    private BankBranchService bankbranchService;

    @Autowired
    private CodeService codeService;
    
	/**
	 * 은행 지점 리스트 조회
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
			@ModelAttribute( "search" ) BankBranchVO search,
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

		List<BankBranchVO> list = null;
		
		list = bankbranchService.list( search );
		totalCount = bankbranchService.listCount( search );

		// 고객사코드(600)
		CodeVO custCodeVo = new CodeVO();
		custCodeVo.setUperCode( 600 );
		model.addAttribute( "custCodeList", codeService.listChildCode( custCodeVo ) );
		
		// 은행 코드(400)
		CodeVO cypcdVo = new CodeVO();
		cypcdVo.setUperCode( 400 );
		model.addAttribute( "bankCodeList", codeService.listChildCode( cypcdVo ) );

		// 지점형태 코드(500)
		CodeVO sttuscdVo = new CodeVO();
		sttuscdVo.setUperCode( 500 );
		model.addAttribute( "branchStateList", codeService.listChildCode( sttuscdVo ) );
		
		// 지역코드(300)
		CodeVO localCodeVo = new CodeVO();
		localCodeVo.setUperCode( 300 );
		model.addAttribute( "localCodeList", codeService.listChildCode( localCodeVo ) );

		model.addAttribute( "startIdx", search.getStartIdx() );
		model.addAttribute( "recPerPage", search.getRecordPerPage() );
		model.addAttribute( "isEqualSearch", isEqualSearch );
		model.addAttribute( "list", list );
		model.addAttribute( "pageNum", pageNum );
		model.addAttribute( "totalCount", totalCount );
		model.addAttribute( "search", search );
		
		return "bankbranch/list";
	}

	/**
	 * 은행 지점 상세 페이지
	 * 
	 * @param  branchNo 은행 지점번호
	 * @return 상세 페이지 이동
	 * @throws Exception
	 * @history 
	 */
	@RequestMapping( value = "/get/{branchNo}" )
	public String info( @PathVariable( "branchNo" ) long branchNo,
			HttpSession session,
			Model model ) throws Exception {

		BankBranchVO bankbranch = new BankBranchVO();
		
		// 수정 모드
		if ( branchNo > 0 ) {
			bankbranch.setBranchNo( branchNo );
			bankbranch = bankbranchService.get( bankbranch );
			model.addAttribute( "editMode", "update" );
		}

		// 고객사코드(600)
		CodeVO custCodeVo = new CodeVO();
		custCodeVo.setUperCode( 600 );
		model.addAttribute( "custCodeList", codeService.listChildCode( custCodeVo ) );
		
		// 은행 코드(400)
		CodeVO cypcdVo = new CodeVO();
		cypcdVo.setUperCode( 400 );
		model.addAttribute( "bankCodeList", codeService.listChildCode( cypcdVo ) );

		// 지점형태 코드(500)
		CodeVO sttuscdVo = new CodeVO();
		sttuscdVo.setUperCode( 500 );
		model.addAttribute( "branchStateList", codeService.listChildCode( sttuscdVo ) );
		
		// 지역코드(300)
		CodeVO localCodeVo = new CodeVO();
		localCodeVo.setUperCode( 300 );
		model.addAttribute( "localCodeList", codeService.listChildCode( localCodeVo ) );

		model.addAttribute( "bankbranch", bankbranch );
		
		return "bankbranch/edit";
	}

	/**
	 * 은행 지점 등록
	 * 
	 * @param  bankbranch 내용
	 * @return ResponseVO 결과 내용
	 * @throws Exception
	 * @history 
	 */
	@ResponseBody
	@RequestMapping( value = "/addProcess", method = RequestMethod.POST )
	public ResponseVO addProcess( 
			@ModelAttribute( "BankBranchVO" ) BankBranchVO bankbranch,
			Model model,
			HttpServletResponse res,
			HttpSession session ) throws Exception {

		ResponseVO result = new ResponseVO();

		UserVO adminUser = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );
		bankbranch.setCreateUser( adminUser.getUserNo() );
		
		long branchNo = bankbranchService.insertBankBranch( bankbranch );

		if ( branchNo < 1 ) {
			result.setStatus( 400 );
			result.setMessage( "은행 지점 등록 중 오류가 발생 했습니다." );
			return result;
		} else {
			result.setStatus( 200 );
			result.setData( branchNo );
			result.setMessage( "은행 지점 등록이 완료 되었습니다." );
			return result;
		}
		
	}

	/**
	 * 은행 지점 수정
	 * 
	 * @param  bankbranch 내용
	 * @return ResponseVO 결과 내용
	 * @throws Exception
	 * @history 
	 */
	@ResponseBody
	@RequestMapping( value = "/updateProcess", method = RequestMethod.POST )
	public ResponseVO updateProcess( 
			@ModelAttribute( "BankBranchVO" ) BankBranchVO bankbranch,
			Model model,
			HttpServletResponse res,
			HttpSession session ) throws Exception {

		ResponseVO result = new ResponseVO();

		UserVO adminUser = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );
		bankbranch.setModifyUser( adminUser.getUserNo() );
		
		if ( bankbranchService.updateBankBranch( bankbranch ) < 1 ) {
			result.setStatus( 400 );
			result.setMessage( "은행 지점 수정 중 오류가 발생 했습니다." );
			return result;
		} else {
			result.setStatus( 200 );
			result.setMessage( "은행 지점 수정 되었습니다." );
			return result;
		}
		
	}

	/**
	 * 은행 지점 삭제
	 * 
	 * @param  bankbranch 내용
	 * @return ResponseVO 결과 내용
	 * @throws Exception
	 * @history 
	 */
	@ResponseBody
	@RequestMapping( value = "/delete", method = RequestMethod.POST )
	public ResponseVO delete( 
			@ModelAttribute( "BankBranchVO" ) BankBranchVO bankbranch,
			Model model,
			HttpServletResponse res,
			HttpSession session ) throws Exception {

		ResponseVO result = new ResponseVO();

		UserVO adminUser = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );
		bankbranch.setModifyUser( adminUser.getUserNo() );
		
		if ( bankbranchService.deleteBankBranch( bankbranch ) < 1) {
			result.setStatus( 400 );
			result.setMessage( "은행 지점 삭제 중 오류가 발생 했습니다." );
			return result;
		} else {
			result.setStatus( 200 );
			result.setMessage( "은행 지점 삭제 되었습니다." );
			return result;
		}
		
	}
}