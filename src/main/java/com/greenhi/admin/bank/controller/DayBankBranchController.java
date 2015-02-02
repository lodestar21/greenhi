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

import com.greenhi.admin.bank.service.DayBankBranchService;
import com.greenhi.admin.bank.vo.DayBankBranchVO;
import com.greenhi.admin.code.service.CodeService;
import com.greenhi.admin.user.vo.UserVO;
import com.greenhi.common.Constants;
import com.greenhi.common.response.ResponseVO;


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
	@RequestMapping( "/list/{pageNum}" )
	public String list( HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res,
			@ModelAttribute( "search" ) DayBankBranchVO search,
			@PathVariable( "pageNum" ) int pageNum,
			@RequestParam( value = "isFirst", defaultValue = "true", required = false ) boolean isFirst,
			Model model ) throws Exception {
		
		List<DayBankBranchVO> list = null;
		
		list = dayBankbranchService.list( search );

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
	@RequestMapping( value = "/delete", method = RequestMethod.POST )
	public ResponseVO delete( 
			@RequestParam(required=true, value="cleanDate") String cleanDate,
			@RequestParam(required=true, value="custCode") int custCode,
			Model model,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession session ) throws Exception {

		ResponseVO result = new ResponseVO();

		UserVO adminUser = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );
		
		DayBankBranchVO dayVo = new DayBankBranchVO();
		dayVo.setCreateUser( adminUser.getUserNo() );
		dayVo.setCleanDate( cleanDate );
		dayVo.setCustCode( custCode );
		
		// 전체 삭제
		dayBankbranchService.deleteDayBankBranch( dayVo );

		String[] branchNoList = req.getParameterValues( "branchNo" );
		
		for( int i=0; i< branchNoList.length; i++ ){
			dayVo.setBranchNo( Long.parseLong( branchNoList[i] ) );
			dayBankbranchService.insertDayBankBranch( dayVo );
		}
		
		result.setStatus( 400 );
		result.setMessage( "일자별 은행 지점 저장 중 오류가 발생 했습니다." );
		return result;
	}
}