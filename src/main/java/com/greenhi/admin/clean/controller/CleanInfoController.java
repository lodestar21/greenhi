package com.greenhi.admin.clean.controller;

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
import com.greenhi.admin.clean.service.CleanInfoService;
import com.greenhi.admin.clean.vo.CleanInfoVO;
import com.greenhi.admin.clean.vo.CleanVO;
import com.greenhi.admin.code.service.CodeService;
import com.greenhi.admin.code.vo.CodeVO;
import com.greenhi.admin.user.vo.UserVO;
import com.greenhi.common.Constants;
import com.greenhi.common.response.ResponseVO;
import com.greenhi.common.util.DateUtil;
import com.greenhi.common.util.StringUtil;


/**
 * 청소 관리 controller
 * 
 * @author  won.lee
 * @date    2014. 12. 28.
 * @history 
 */
@Controller
@RequestMapping(value = "/UserCleanInfo")
public class CleanInfoController {
	
    private static final Logger logger = LoggerFactory.getLogger(CleanInfoController.class);

    @Autowired
    private CleanInfoService cleanInfoService;

    @Autowired
    private CodeService codeService;
    
    @Autowired
    private BankBranchService bankbranchService;

	/**
	 * 청소 리스트 조회
	 * 
	 * @param  search 조회 조건, pageNum 페이지 번호, isFirst 처음 유무
	 * @return 리스트 화면
	 * @throws Exception
	 * @history 
	 */
	@RequestMapping( "/cleanList/{pageNum}" )
	public String list( HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res,
			@ModelAttribute( "search" ) CleanInfoVO search,
			@PathVariable( "pageNum" ) int pageNum,
			@RequestParam( value = "isFirst", defaultValue = "true", required = false ) boolean isFirst,
			Model model ) throws Exception {
		
		// 페이지
		if ( pageNum != 0 ) {
			search.setPageNum( pageNum );
		}
		
		int totalCount = 0;

		List<CleanInfoVO> list = null;

		UserVO adminUser = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );
		search.setCleanUserNo( adminUser.getUserNo() );
		
		list = cleanInfoService.list( search );
		totalCount = cleanInfoService.listCount( search );
		
		model.addAttribute( "startIdx", search.getStartIdx() );
		model.addAttribute( "recPerPage", search.getRecordPerPage() );
		model.addAttribute( "list", list );
		model.addAttribute( "pageNum", pageNum );
		model.addAttribute( "totalCount", totalCount );
		model.addAttribute( "search", search );
		
		return "clean/list";
	}
	
	/**
	 * 청소 리스트 조회
	 * 
	 * @param  search 조회 조건, pageNum 페이지 번호, isFirst 처음 유무
	 * @return 리스트 화면
	 * @throws Exception
	 * @history 
	 */
	@RequestMapping( "/userList" )
	public String list( HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res,
			@ModelAttribute( "search" ) CleanInfoVO search,
			Model model ) throws Exception {
		
		List<CleanInfoVO> list = null;
		UserVO adminUser = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );

		if ( StringUtil.isEmpty( search.getCleanDate() ) ) {
			search.setCleanDate( DateUtil.getFullTimeStr2() );
		}
		
		search.setCleanUserNo( adminUser.getUserNo() );
		search.setExcel( true );
		list = cleanInfoService.list( search );
		
		model.addAttribute( "list", list );
		model.addAttribute( "search", search );
		
		return "clean/user_list";
	}

	/**
	 * 청소 데이터 상세 페이지
	 * 
	 * @param  branchNo 청소 데이터번호
	 * @return 상세 페이지 이동
	 * @throws Exception
	 * @history 
	 */
	@RequestMapping( value = "/get" )
	public String info( @ModelAttribute( "CleanVO" ) CleanVO cleanVO,
			HttpSession session,
			Model model ) throws Exception {

		cleanVO.setCleanDate( cleanVO.getCleanDateParam() );
		cleanVO.setBranchNo( cleanVO.getBranchNoParam() );
		cleanVO.setCleanNo( cleanVO.getCleanNoParam() );
		
		BankBranchVO bankbranch = new BankBranchVO();
		bankbranch.setBranchNo( cleanVO.getBranchNoParam() );
		bankbranch = bankbranchService.get( bankbranch );
		
		// 수정 모드
		if ( cleanVO.getCleanNo() > 0 ) {
			cleanVO.setCleanNo( cleanVO.getCleanNo() );
			cleanVO = cleanInfoService.get( cleanVO );
			model.addAttribute( "editMode", "update" );
		}

		// 청소상태 코드(700)
		CodeVO cypcdVo = new CodeVO();
		cypcdVo.setUperCode( 700 );
		model.addAttribute( "cleanCodeList", codeService.listChildCode( cypcdVo ) );

		model.addAttribute( "bankbranch", bankbranch );
		model.addAttribute( "clean", cleanVO );
		
		return "clean/user_edit";
	}

	/**
	 * 청소 데이터 등록
	 * 
	 * @param  bankbranch 내용
	 * @return ResponseVO 결과 내용
	 * @throws Exception
	 * @history 
	 */
	@ResponseBody
	@RequestMapping( value = "/addProcess", method = RequestMethod.POST )
	public ResponseVO addProcess( 
			@ModelAttribute( "CleanVO" ) CleanVO bankbranch,
			Model model,
			HttpServletResponse res,
			HttpSession session ) throws Exception {

		ResponseVO result = new ResponseVO();

		UserVO adminUser = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );
		bankbranch.setCreateUser( adminUser.getUserNo() );
		
		long branchNo = cleanInfoService.insertClean( bankbranch );

		if ( branchNo < 1 ) {
			result.setStatus( 400 );
			result.setMessage( "청소 데이터 등록 중 오류가 발생 했습니다." );
			return result;
		} else {
			result.setStatus( 200 );
			result.setData( branchNo );
			result.setMessage( "청소 데이터 등록이 완료 되었습니다." );
			return result;
		}
		
	}

	/**
	 * 청소 데이터 수정
	 * 
	 * @param  bankbranch 내용
	 * @return ResponseVO 결과 내용
	 * @throws Exception
	 * @history 
	 */
	@ResponseBody
	@RequestMapping( value = "/updateProcess", method = RequestMethod.POST )
	public ResponseVO updateProcess( 
			@ModelAttribute( "CleanVO" ) CleanVO bankbranch,
			Model model,
			HttpServletResponse res,
			HttpSession session ) throws Exception {

		ResponseVO result = new ResponseVO();

		UserVO adminUser = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );
		bankbranch.setModifyUser( adminUser.getUserNo() );
		
		if ( cleanInfoService.updateClean( bankbranch ) < 1 ) {
			result.setStatus( 400 );
			result.setMessage( "청소 데이터 수정 중 오류가 발생 했습니다." );
			return result;
		} else {
			result.setStatus( 200 );
			result.setMessage( "청소 데이터 수정 되었습니다." );
			return result;
		}
		
	}

	/**
	 * 청소 데이터 삭제
	 * 
	 * @param  bankbranch 내용
	 * @return ResponseVO 결과 내용
	 * @throws Exception
	 * @history 
	 */
	@ResponseBody
	@RequestMapping( value = "/delete", method = RequestMethod.POST )
	public ResponseVO delete( 
			@ModelAttribute( "CleanVO" ) CleanVO bankbranch,
			Model model,
			HttpServletResponse res,
			HttpSession session ) throws Exception {

		ResponseVO result = new ResponseVO();

		UserVO adminUser = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );
		bankbranch.setModifyUser( adminUser.getUserNo() );
		
		if ( cleanInfoService.deleteClean( bankbranch ) < 1) {
			result.setStatus( 400 );
			result.setMessage( "청소 데이터 삭제 중 오류가 발생 했습니다." );
			return result;
		} else {
			result.setStatus( 200 );
			result.setMessage( "청소 데이터 삭제 되었습니다." );
			return result;
		}
		
	}
}