package com.greenhi.common;

/**
 * 조이웰에서 관리하는 상수 정보(코드 정보 등)
 * 
 * @author  kimyu
 * @version 1.0, 8/01/2014
 * @since   1.5
 */
public class Constants {

	/**
	 * Server Name
	 */
	public static String SERVER_NAME;
	public void setSERVER_NAME(String pSERVER_NAME) {
		SERVER_NAME = pSERVER_NAME;
	}
	
	/**
	 * 사용자 세션키
	 */
	public final static String ADMIN_INFO_KEY = "_USER_INFO_";
	
	/**
	 * 로그인 아이디 쿠키 변수
	 */
	public final static String ADMIN_SAVE_ID_KEY = "_ADMIN_SAVE_ID_";
	
	/**
	 * 사용자 이전 URL
	 */
	public final static String RETURN_URL_KEY = "_RETURN_URL_";
	
	/**
	 * 암호화 구분
	 */
	public static final int SALT_VALUE = 12;
	
	/**
	 * 사용자_유형코드 (100)
	 * 관리자
	 */
	public static final int USER_TYPCD_101 = 101;
	
	/**
	 * 사용자_유형코드 (100)
	 * 은행 관리자
	 */
	public static final int USER_TYPCD_102 = 102;
	
	/**
	 * 사용자_유형코드 (100)
	 * 사용자
	 */
	public static final int USER_TYPCD_103 = 103;
	
	/**
	 * 회원상태코드(200)
	 * 재직
	 */
	public static final int USER_STAT_201 = 201;

}
