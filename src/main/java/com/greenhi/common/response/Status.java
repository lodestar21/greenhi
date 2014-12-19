package com.greenhi.common.response;

/**
 * 시스템 처리 결과 코드
 * 
 * @author  kimyu
 * @version 1.0, 7/24/2014
 * @since   1.5
 */
public enum Status {
	/**
	 * 성공
	 */
	SUCCESS (200),
	/**
	 * 실패(등록, 수정, 삭제)
	 */
	FAIL (400),
	/**
	 * 실패(등록)
	 */
	FAIL_INSERT (400),
	/**
	 * 실패(수정)
	 */
	FAIL_UPDATE (400),
	/**
	 * 실패(삭제)
	 */
	FAIL_DELETE (400),
	/**
	 * 인증실패
	 */
	UNAUTHORIZED (401),
	/**
	 * 회원미존재
	 */
	NOT_FOUND (404),
	/**
	 * 서버오류
	 */
	ERROR (500),
	/**
	 * 패스워드 오류
	 */
	WRONG_PASSWORD (300),
	/**
	 * 회원상태오류
	 */
	UNUSUAL_USER_STATUS (301),
	/**
	 * 탈퇴상태
	 */
	WITHDRAW_USER_STATUS (302),
	/**
	 * 일치하는 이메일 정보값이 없을 경우
	 */
	INCORRECT_EMAIL (303),
	/**
	 * 회원 가입 인증전 상태
	 */
	NOT_LGN_CERT (299);
	
	private int code;
	public int getCode() {
		return this.code;
	}
	
	private Status(int code) {
		this.code = code;
	}
}
