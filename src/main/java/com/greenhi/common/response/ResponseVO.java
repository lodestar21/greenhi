package com.greenhi.common.response;

import java.util.List;

/**
 * api 처리 결과  VO
 * 
 * @author  kimyu
 * @version 1.0, 7/24/2014
 * @since   1.5
 */
public class ResponseVO {

	/**
	 * 상태코드
	 * 
	 * 200 : 성공
	 * 400 : 실패
	 * 401 : 비번오류(로그인 시), 인증실패
	 * 404 : 이메일오류(로그인 시), 이메일중복(회원가입 시), 회원미존재
	 * 500 : 서버오류
	 */
	private int status = 200;
	
	/**
	 * 결과 메시지
	 */
	private String message;
		
	/**
	 * 결과 데이터
	 */
	private Object data;
	
	public ResponseVO() {
	}
	
	/**
	 * 200 : 성공
	 * 400 : 실패
	 * 401 : 비번오류(로그인 시), 인증실패
	 * 404 : 이메일오류(로그인 시), 이메일중복(회원가입 시), 회원미존재
	 * 500 : 서버오류
	 * 
	 * @return 상태코드
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * 200 : 성공
	 * 400 : 실패
	 * 401 : 비번오류(로그인 시), 인증실패
	 * 404 : 이메일오류(로그인 시), 이메일중복(회원가입 시), 회원미존재
	 * 500 : 서버오류
	 * 
	 * @param status 상태코드
	 */
    @com.fasterxml.jackson.annotation.JsonSetter
	public void setStatus(int status) {
		this.status = status;
	}
	
	/**
	 * 200 : 성공
	 * 400 : 실패
	 * 401 : 비번오류(로그인 시), 인증실패
	 * 404 : 이메일오류(로그인 시), 이메일중복(회원가입 시), 회원미존재
	 * 500 : 서버오류
	 * 
	 * @param status 상태코드
	 */
	public void setStatus(Status status) {
		this.status = status.getCode();
	}
	
	/**
	 * 결과 메시지
	 * @return 결과 메시지
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * 결과 메시지
	 * @param message 결과 메시지
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 결과 object 유형
	 * 
	 * 0 : none
	 * 1 : object
	 * 2 : array
	 */
	public int getData_type() {
		if(data == null)
			return 0;
		
		if(data instanceof List)
			return 2;
		return 1;
	}
	
	/**
	 * client json 처리를 위한 함수
	 * @param data_type
	 */
	public void setData_type(int data_type) {
	}
	
	/**
	 * 결과데이터
	 * @return 결과데이터
	 */
	public Object getData() {
		return data;
	}
	
	/**
	 * 결과데이터
	 * @param data 결과데이터
	 */
	public void setData(Object data) {
		this.data = data;
	}
}
