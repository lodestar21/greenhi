package com.greenhi.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 서블렛에서 데이터 추출해주는 유틸
 * 
 * @author  won.lee
 * @version 2014. 8. 8. 
 * @history  
 */
public class ServletUtil {

	/**
	 * RequestHeader의 IP 추출
	 * 
	 * @param req
	 * @return
	 */
	public static String getIp( HttpServletRequest request ) {
		
		String clientIp = request.getHeader("HTTP_X_FORWARDED_FOR");
		if(null == clientIp || clientIp.length() == 0 
		   || clientIp.toLowerCase().equals("unknown")){
			clientIp = request.getHeader("REMOTE_ADDR");
		}
		 
		if(null == clientIp || clientIp.length() == 0 
		   || clientIp.toLowerCase().equals("unknown")){
			clientIp = request.getRemoteAddr();
		}
		
		return clientIp;
	}

	public static String getPlatformString( HttpServletRequest request ) {
		String userAgent = request.getHeader( "User-Agent" );
		String platform = "ETC";
		if ( !StringUtil.isEmpty( userAgent ) ) {
			userAgent = userAgent.toLowerCase();
			if ( userAgent.contains( "android" ) ) {
				if ( userAgent.contains( "mobile" ) ) {
					platform = "ANDROID_PHONE";
				} else {
					platform = "ANDROID_TABLET";
				}
			} else if ( userAgent.contains( "iphone" ) ) {
				platform = "IPHONE";
			} else if ( userAgent.contains( "ipod" ) ) {
				platform = "IPOD";
			} else if ( userAgent.contains( "ipad" ) ) {
				platform = "IPAD";
			} else if ( userAgent.contains( "macintosh" ) ) {
				platform = "MAC";
			} else if ( userAgent.contains( "windows" ) ) {
				platform = "WINDOWS";
			}
		}
		return platform;
	}
}
