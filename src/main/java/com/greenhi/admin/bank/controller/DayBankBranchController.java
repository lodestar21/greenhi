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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greenhi.admin.bank.service.DayBankBranchService;
import com.greenhi.admin.bank.vo.DayBankBranchVO;
import com.greenhi.admin.code.service.CodeService;
import com.greenhi.admin.code.vo.CodeVO;
import com.greenhi.admin.user.vo.UserVO;
import com.greenhi.common.Constants;
import com.greenhi.common.response.ResponseVO;
import com.greenhi.common.util.DateUtil;
import com.greenhi.common.util.StringUtil;


/**
 * 일자별 은행 지점 관리 controller
 * 
 * @author  won.lee
 * @date    2014. 12. 28.
 * @history 
 */
@Controller
@RequestMapping(value = "/DayBankBranch")
public class DayBankBranchController {
	
    private static final Logger logger = LoggerFactory.getLogger(DayBankBranchController.class);

    @Autowired
    private DayBankBranchService dayBankbranchService;

    @Autowired
    private CodeService codeService;
    
	/**
	 * 일자별 은행 지점 리스트 조회
	 * 
	 * @param  search 조회 조건, pageNum 페이지 번호, isFirst 처음 유무
	 * @return 리스트 화면
	 * @throws Exception
	 * @history 
	 */
	@RequestMapping( "/list" )
	public String list( HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res,
			@ModelAttribute( "search" ) DayBankBranchVO search,
			Model model ) throws Exception {
		
		List<DayBankBranchVO> list = null;

		if ( StringUtil.isEmpty( search.getCleanDate() ) ) {
			search.setCleanDate( DateUtil.getFullTimeStr2() );
		}
		
		list = dayBankbranchService.list( search );
		
		// 고객사코드(600)
		CodeVO custCodeVo = new CodeVO();
		custCodeVo.setUperCode( 600 );
		model.addAttribute( "custCodeList", codeService.listChildCode( custCodeVo ) );
		
		model.addAttribute( "list", list );
		model.addAttribute( "search", search );
		
		return "bankbranch/day_list";
	}

	/**
	 * 일자별 은행 지점 삭제
	 * 
	 * @param  bankbranch 내용
	 * @return ResponseVO 결과 내용
	 * @throws Exception
	 * @history 
	 */
	@ResponseBody
	@RequestMapping( value = "/save", method = RequestMethod.POST )
	public ResponseVO save( 
			@ModelAttribute("dayBankBranch") DayBankBranchVO dayBankBranch,
			Model model,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession session ) throws Exception {

		ResponseVO result = new ResponseVO();

		UserVO adminUser = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );
		dayBankBranch.setCreateUser( adminUser.getUserNo() );

		String[] dayBankBranchArray = req.getParameterValues( "branchNoP" );
		
		int i = dayBankbranchService.saveDayBankBranch( dayBankBranch, dayBankBranchArray);
		
		if ( i < 1 ) {
			result.setStatus( 400 );
			result.setMessage( "일자별 은행 지점 저장 중 오류가 발생 했습니다." );
			return result;
		} else {
			result.setStatus( 200 );
			result.setMessage( "일자별 은행 지점 저장 되었습니다." );
		}
		
		return result;
	}
}