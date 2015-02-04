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
import org.springframework.web.bind.annotation.RequestParam;

import com.greenhi.admin.bank.service.BankBranchService;
import com.greenhi.admin.bank.vo.BankBranchVO;
import com.greenhi.admin.clean.service.CleanInfoService;
import com.greenhi.admin.clean.vo.CleanInfoVO;
import com.greenhi.admin.clean.vo.CleanVO;
import com.greenhi.admin.code.service.CodeService;
import com.greenhi.admin.code.vo.CodeVO;
import com.greenhi.admin.user.vo.UserVO;
import com.greenhi.common.Constants;


/**
 * 청소 관리 controller
 * 
 * @author  won.lee
 * @date    2014. 12. 28.
 * @history 
 */
@Controller
@RequestMapping(value = "/CustCleanInfo")
public class CustCleanInfoController {
	
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
		//search.setCustUserNo( adminUser.getUserNo() );
		
		list = cleanInfoService.list( search );
		totalCount = cleanInfoService.listCount( search );

		// 은행 코드(400)
		CodeVO cypcdVo = new CodeVO();
		cypcdVo.setUperCode( 400 );
		model.addAttribute( "bankCodeList", codeService.listChildCode( cypcdVo ) );
		
		// 지역코드(300)
		CodeVO localCodeVo = new CodeVO();
		localCodeVo.setUperCode( 300 );
		model.addAttribute( "localCodeList", codeService.listChildCode( localCodeVo ) );

		model.addAttribute( "startIdx", search.getStartIdx() );
		model.addAttribute( "recPerPage", search.getRecordPerPage() );
		model.addAttribute( "list", list );
		model.addAttribute( "pageNum", pageNum );
		model.addAttribute( "totalCount", totalCount );
		model.addAttribute( "search", search );
		
		return "clean/cust_list";
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
		
		return "clean/cust_edit";
	}

}