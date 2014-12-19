package com.greenhi.common.request;

/**
 * api 요청  VO
 * 
 * @author  kimyu
 * @version 1.0, 7/24/2014
 * @since   1.5
 */
public class RequestVO {
	
	/**
     * column : LANG_CTCD
     * 언어_구분코드 (500)
     *  - 501 : 한글
     *  - 502 : 영어
     */
    private int lang_ctcd;
    
    /**
     * column : STR_CTCD
     * 콘텐츠-앱스토어(3100)
     *  - 3101 : 구글
     *  - 3102 : 애플
     */
    private int str_ctcd;

	public RequestVO() {
	}
    
    /**
     * column : LANG_CTCD
     * 언어_구분코드 (500)
     *  - 501 : 한글
     *  - 502 : 영어
     *
     * @param lang_ctcd 언어_구분코드 (500)
     */
    public void setLang_ctcd(int lang_ctcd) {
        this.lang_ctcd = lang_ctcd;
    }
        
    /**
     * column : LANG_CTCD
     * 언어_구분코드 (500)
     *  - 501 : 한글
     *  - 502 : 영어
     *
     * @return int 언어_구분코드 (500)
     */
    public int getLang_ctcd() {
        return this.lang_ctcd;
    }
    
    /**
     * column : STR_CTCD
     * 콘텐츠-앱스토어(3100)
     *  - 3101 : 구글
     *  - 3102 : 애플
     *
     * @param str_ctcd 콘텐츠-앱스토어(3100)
     */
    public void setStr_ctcd(int str_ctcd) {
        this.str_ctcd = str_ctcd;
    }
        
    /**
     * column : STR_CTCD
     * 콘텐츠-앱스토어(3100)
     *  - 3101 : 구글
     *  - 3102 : 애플
     *
     * @return int 콘텐츠-앱스토어(3100)
     */
    public int getStr_ctcd() {
        return this.str_ctcd;
    }
}
