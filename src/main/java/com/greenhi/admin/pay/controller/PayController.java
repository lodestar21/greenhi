package com.greenhi.admin.pay.controller;

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
import com.greenhi.admin.pay.service.PayService;
import com.greenhi.admin.pay.vo.PayVO;
import com.greenhi.admin.user.vo.UserVO;
import com.greenhi.common.Constants;
import com.greenhi.common.response.ResponseVO;
import com.greenhi.common.util.StringUtil;


/**
 * 지급 정보 관리 controller
 * 
 * @author  won.lee
 * @date    2014. 12. 28.
 * @history 
 */
@Controller
@RequestMapping(value = "/Pay")
public class PayController {
	
    private static final Logger logger = LoggerFactory.getLogger(PayController.class);

    @Autowired
    private PayService payService;

    @Autowired
    private CodeService codeService;
    
	/**
	 * 지급 정보 리스트 조회
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
			@ModelAttribute( "search" ) PayVO search,
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

		List<PayVO> list = null;
		
		list = payService.list( search );
		totalCount = payService.listCount( search );

		// 은행 코드(400)
		CodeVO cypcdVo = new CodeVO();
		cypcdVo.setUperCode( 400 );
		model.addAttribute( "bankCodeList", codeService.listChildCode( cypcdVo ) );

		model.addAttribute( "startIdx", search.getStartIdx() );
		model.addAttribute( "recPerPage", search.getRecordPerPage() );
		model.addAttribute( "isEqualSearch", isEqualSearch );
		model.addAttribute( "list", list );
		model.addAttribute( "pageNum", pageNum );
		model.addAttribute( "totalCount", totalCount );
		model.addAttribute( "search", search );
		
		return "pay/list";
	}

	/**
	 * 지급 정보 상세 페이지
	 * 
	 * @param  payNo 지급 정보번호
	 * @return 상세 페이지 이동
	 * @throws Exception
	 * @history 
	 */
	@RequestMapping( value = "/get/{payNo}" )
	public String info( @PathVariable( "payNo" ) long payNo,
			HttpSession session,
			Model model ) throws Exception {

		PayVO pay = new PayVO();
		
		// 수정 모드
		if ( payNo > 0 ) {
			pay.setPayNo( payNo );
			pay = payService.get( pay );
			model.addAttribute( "editMode", "update" );
		}

		// 은행 코드(400)
		CodeVO cypcdVo = new CodeVO();
		cypcdVo.setUperCode( 400 );
		model.addAttribute( "bankCodeList", codeService.listChildCode( cypcdVo ) );

		model.addAttribute( "pay", pay );
		
		return "pay/edit";
	}

	/**
	 * 지급 정보 등록
	 * 
	 * @param  pay 내용
	 * @return ResponseVO 결과 내용
	 * @throws Exception
	 * @history 
	 */
	@ResponseBody
	@RequestMapping( value = "/addProcess", method = RequestMethod.POST )
	public ResponseVO addProcess( 
			@ModelAttribute( "PayVO" ) PayVO pay,
			Model model,
			HttpServletResponse res,
			HttpSession session ) throws Exception {

		ResponseVO result = new ResponseVO();

		UserVO adminUser = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );
		pay.setCreateUser( adminUser.getUserNo() );
		
		long payNo = payService.insertPay( pay );

		if ( payNo < 1 ) {
			result.setStatus( 400 );
			result.setMessage( "지급 정보 등록 중 오류가 발생 했습니다." );
			return result;
		} else {
			result.setStatus( 200 );
			result.setData( payNo );
			result.setMessage( "지급 정보 등록이 완료 되었습니다." );
			return result;
		}
		
	}

	/**
	 * 지급 정보 수정
	 * 
	 * @param  pay 내용
	 * @return ResponseVO 결과 내용
	 * @throws Exception
	 * @history 
	 */
	@ResponseBody
	@RequestMapping( value = "/updateProcess", method = RequestMethod.POST )
	public ResponseVO updateProcess( 
			@ModelAttribute( "PayVO" ) PayVO pay,
			Model model,
			HttpServletResponse res,
			HttpSession session ) throws Exception {

		ResponseVO result = new ResponseVO();

		UserVO adminUser = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );
		pay.setModifyUser( adminUser.getUserNo() );
		
		if ( payService.updatePay( pay ) < 1 ) {
			result.setStatus( 400 );
			result.setMessage( "지급 정보 수정 중 오류가 발생 했습니다." );
			return result;
		} else {
			result.setStatus( 200 );
			result.setMessage( "지급 정보 수정 되었습니다." );
			return result;
		}
		
	}

	/**
	 * 지급 정보 삭제
	 * 
	 * @param  pay 내용
	 * @return ResponseVO 결과 내용
	 * @throws Exception
	 * @history 
	 */
	@ResponseBody
	@RequestMapping( value = "/delete", method = RequestMethod.POST )
	public ResponseVO delete( 
			@ModelAttribute( "PayVO" ) PayVO pay,
			Model model,
			HttpServletResponse res,
			HttpSession session ) throws Exception {

		ResponseVO result = new ResponseVO();

		UserVO adminUser = ( UserVO ) session.getAttribute( Constants.ADMIN_INFO_KEY );
		pay.setModifyUser( adminUser.getUserNo() );
		
		if ( payService.deletePay( pay ) < 1) {
			result.setStatus( 400 );
			result.setMessage( "지급 정보 삭제 중 오류가 발생 했습니다." );
			return result;
		} else {
			result.setStatus( 200 );
			result.setMessage( "지급 정보 삭제 되었습니다." );
			return result;
		}
		
	}
}