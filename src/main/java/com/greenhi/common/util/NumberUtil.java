package com.greenhi.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gr.park
 * @version 1.0 클래스 설명<br>
 *          Number 관련 기능성 Method를 제공하는 Util Class<br>
 */
public class NumberUtil {

	/** 통화 단위로 변환해 주는 NumberFormat <code>df</code> */
	private static final DecimalFormat df = new DecimalFormat( "###,###.####" );

	/** 숫자를 6자리로 만들어 주는 NumberFormatter */
	private static final DecimalFormat digit6df = new DecimalFormat( "000000" );

	private static Random random = new Random( System.currentTimeMillis() );

	/**
	 * 두개의 숫자를 String으로 입력받아 비교하는 메소드 num1 > num2 : true, num1 <= num2 : false
	 * 
	 * @param num1 기준 숫자
	 * @param num2 비교 숫자
	 * @return num1 > num2 : true, num1 <= num2 : false;
	 */
	public static boolean compare( String num1,
			String num2 ) {

		long tmpNum1 = str2long( num1 );
		long tmpNum2 = str2long( num2 );

		if ( tmpNum1 > tmpNum2 ) {
			return true;
		}

		return false;
	}

	/**
	 * 숫자값에 콤마(,)를 찍어 스트링으로 반환한다.
	 * 
	 * @param num 변환될 double
	 * @return Money Type으로 변환된 String
	 */
	public static String getMoneyType( double num ) {

		return df.format( num );
	}

	/**
	 * 숫자값에 콤마(,)를 찍어 스트링으로 반환한다.
	 * 
	 * @param rd_data 변환될 float
	 * @return String ','찍힌 String
	 */

	public static String getMoneyType( float rd_data ) {

		return df.format( rd_data );
	}

	/**
	 * 숫자값에 콤마(,)를 찍어 스트링으로 반환한다.
	 * 
	 * @param num 변환될 int
	 * @return Money Type으로 변환된 String
	 */
	public static String getMoneyType( int num ) {

		return df.format( num );
	}

	/**
	 * 숫자값에 콤마(,)를 찍어 스트링으로 반환한다.
	 * 
	 * @param num 변환될 long
	 * @return Money Type으로 변환된 String
	 */
	public static String getMoneyType( long num ) {

		return df.format( num );
	}

	/**
	 * 숫자값에 콤마(,)를 찍어 스트링으로 반환한다.
	 * 
	 * @param num 변환될 String
	 * @return Money Type으로 변환된 String
	 */
	public static String getMoneyType( String num ) {

		String moneyNum;

		moneyNum = StringUtil.null2Blank( num );

		BigDecimal dc_data = new BigDecimal( num );
		moneyNum = df.format( dc_data );

		return moneyNum;
	}

	/**
	 * 문자값에서 콤마(,)를 삐고 String 반환
	 * 
	 * @param str ','제거될 문자열
	 * @return ','제거된 숫자 문자열
	 * @exception NumberFormatException
	 */
	public static String getOnlyNumberStr( String str ) throws NumberFormatException {

		return StringUtil.deleteChar( str, ',' );
	}

	/**
	 * double 여부를 확인한다.
	 * 
	 * @param str 확인할 String
	 * @return double 여부(true : 성공, false : 실패)
	 */
	public static boolean isDouble( String str ) {

		if ( StringUtil.isBlank( str ) ) {
			return false;
		}

		try {
			Double.parseDouble( str );
			return true;
		} catch ( NumberFormatException nfe ) {
			return false;
		}
	}

	/**
	 * float 여부를 확인한다.
	 * 
	 * @param str 확인할 String
	 * @return double 여부(true : 성공, false : 실패)
	 */
	public static boolean isFloat( String str ) {

		if ( StringUtil.isBlank( str ) ) {
			return false;
		}

		try {
			Float.parseFloat( str );
			return true;
		} catch ( NumberFormatException nfe ) {
			return false;
		}
	}

	/**
	 * int 여부를 확인한다.
	 * 
	 * @param str 확인할 String
	 * @return double 여부(true : 성공, false : 실패)
	 */
	public static boolean isInt( String str ) {

		if ( StringUtil.isBlank( str ) ) {
			return false;
		}

		try {
			Integer.parseInt( str );
			return true;
		} catch ( NumberFormatException nfe ) {
			return false;
		}
	}

	/**
	 * long 여부를 확인한다.
	 * 
	 * @param str 확인할 String
	 * @return double 여부(true : 성공, false : 실패)
	 */
	public static boolean isLong( String str ) {

		if ( StringUtil.isBlank( str ) ) {
			return false;
		}

		try {
			Long.parseLong( str );
			return true;
		} catch ( NumberFormatException nfe ) {
			return false;
		}
	}

	/**
	 * 숫자인지의 여부를 확인한다.
	 * 
	 * @param str 확인할 String
	 * @return double 여부(true : 성공, false : 실패)
	 */
	public static boolean isNumber( String str ) {

		if ( StringUtil.isEmpty( str ) ) {
			return false;
		}

		char[] chars = str.toCharArray();
		boolean isNumber = false;
		boolean dotInserted = false;

		// if blank string, it isn't a number
		if ( str.trim().length() == 0 ) {
			return false;
		}

		// if the last char in the string is a dot, it isn't a number!
		if ( str.charAt( str.length() - 1 ) == '.' ) {
			return false;
		}

		for ( int i = 0 ; i < chars.length ; i++ ) {
			// a dot inside the string can be a number as well
			// although it can only occur once.
			if ( chars[i] == 46 ) {
				if ( !dotInserted ) {
					dotInserted = true;
				} else {
					return false;
				}
			} else if ( chars[i] < 48 || chars[i] > 57 ) // 48 is ASCII for 0, 57 for 9
			{
				return false;
			} else {
				isNumber = true;
			}
		}

		return isNumber;
	}

	/**
	 * 6자리 Integer 수를 반환하는 메소드.
	 * 
	 * @return
	 */
	public static String random6DigitInt() {

		int randomInt = random.nextInt( 1000000 );

		return digit6df.format( randomInt );
	}

	/**
	 * double 타입의 변수값을 지정된 소수점 위치의 이하값을 rounding half up하고 double 형으로 반환한다. scale은 양의 정수이며 소수점 자리수를 의미한다. float 형도 사용가능하다.
	 * 
	 * @param num halfup할 숫자
	 * @param scale 소수점 자리수
	 * @return double halfup된 숫자
	 * @exception ArithmeticException
	 */
	public static double roundHalfUp( double num,
			int scale ) throws ArithmeticException {

		BigDecimal returnBigDec = null;
		BigDecimal tmpBigDec = new BigDecimal( num );
		returnBigDec = tmpBigDec.setScale( scale, BigDecimal.ROUND_HALF_UP );
		return returnBigDec.doubleValue();
	}

	/**
	 * double 타입의 변수값을 지정된 소수점 위치의 이하값을 rounding down하고 double 형으로 반환한다. scale은 양의 정수이며 소수점 자리수를 의미한다. float 형도 사용가능하다.
	 * 
	 * @param num roundDown 할 숫자
	 * @param scale 소수점 자리수
	 * @return double rounddown된 숫자
	 * @exception ArithmeticException
	 */
	public static double roundRoundDown( double num,
			int scale ) throws ArithmeticException {

		BigDecimal returnBigDec = null;
		BigDecimal tmpBigDec = new BigDecimal( num );
		returnBigDec = tmpBigDec.setScale( scale, BigDecimal.ROUND_DOWN );
		return returnBigDec.doubleValue();
	}

	/**
	 * 숫자를 웹페이지에서 출력할 수 있도록 배열로 바꿔서 반환
	 * 
	 * @param rate
	 * @return
	 */
	public static List<String> setNumberFormat( double rate ) {

		NumberFormat formatter = new DecimalFormat( "0" );
		String rateStr = formatter.format( rate );
		List<String> rateList = new ArrayList<String>();
		int rateStrLength = rateStr.length();
		for ( int i = 0 ; i < rateStrLength ; i++ ) {
			rateList.add( Character.toString( rateStr.charAt( i ) ) );
		}
		return rateList;
	}

	/**
	 * 가격을 웹페이지에서 출력할 수 있도록 배열로 바꿔서 반환
	 * 
	 * @param price
	 * @return
	 */
	public static List<String> setPriceFormat( long price ) {

		NumberFormat formatter = NumberFormat.getCurrencyInstance( Locale.KOREA );
		String priceStr = formatter.format( price );
		List<String> sellPriceList = new ArrayList<String>();
		int priceStrLength = priceStr.length();
		for ( int i = 0 ; i < priceStrLength ; i++ ) {
			if ( priceStr.charAt( i ) == ',' ) {
				sellPriceList.add( "comma" );
			} else {
				sellPriceList.add( Character.toString( priceStr.charAt( i ) ) );
			}
		}
		return sellPriceList;
	}

	/**
	 * 문자값에서 콤마(,)를 제거하고 double 반환
	 * 
	 * @param num double로 변환할 문자열
	 * @return double 변환된 double
	 * @exception NumberFormatException
	 */
	public static double str2double( String num ) throws NumberFormatException {

		if ( !StringUtil.isBlank( num ) ) {
			return new Double( getOnlyNumberStr( num ) ).doubleValue();
		}

		return 0.0;
	}

	/**
	 * 문자값에서 콤마(,)를 제거하고 float로 반환
	 * 
	 * @param num float로 변환할 문자열
	 * @return float 변환된 float
	 * @exception NumberFormatException
	 */
	public static float str2float( String num ) throws NumberFormatException {

		if ( !StringUtil.isBlank( num ) ) {
			return new Float( NumberUtil.getOnlyNumberStr( num ) ).floatValue();
		}

		return 0;
	}

	/**
	 * 문자값에서 콤마(,)를 제거하고 int 반환
	 * 
	 * @param num int로 변환할 문자열
	 * @return int 변환된 int
	 * @exception NumberFormatException
	 */
	public static int str2int( String num ) throws NumberFormatException {

		if ( !StringUtil.isBlank( num ) ) {
			return new Integer( getOnlyNumberStr( num ) ).intValue();
		}

		return 0;
	}

	/**
	 * 문자값에서 콤마(,)를 제거하고 long 반환
	 * 
	 * @param num long으로 변환할 문자열
	 * @return long 변환된 long
	 * @exception NumberFormatException
	 */
	public static long str2long( String num ) throws NumberFormatException {

		if ( !StringUtil.isBlank( num ) ) {
			return new Long( getOnlyNumberStr( num ) ).longValue();
		}

		return 0;
	}

	/**
	 * double 타입의 변수값을 소수점 이하를 자르고 long형으로 반환한다.
	 * 
	 * @param num 정수로 변환할 문자열
	 * @return long
	 */
	public static long toIntegerNumber( double num ) {

		DecimalFormat ldf = new DecimalFormat( "000" );
		return Long.parseLong( ldf.format( num ) );
	}

	/**
	 * float 타입의 변수값을 소수점 이하를 자르고 long 형으로 반환한다.
	 * 
	 * @param num 정수로 변환할 문자열
	 * @return long
	 */
	public static long toIntegerNumber( float num ) {

		DecimalFormat ldf = new DecimalFormat( "000" );
		return Long.parseLong( ldf.format( num ) );
	}

	/**
	 * String 숫자형 변수값을 소수점 이하를 잘라서 String으로 반환한다.
	 * 
	 * @param num 정수로 변환할 문자열
	 * @return String
	 */
	public static String toIntegerNumber( String num ) {

		DecimalFormat ldf = new DecimalFormat( "000" );
		return ldf.format( Double.parseDouble( num ) );
	}

	/**
	 * 길이에 맞춰서 대쉬(-)가 들어간 핸드폰 문자열 번호로 만들어줌
	 * 
	 * @param cellPhone 핸드폰번호(- 없음)
	 * @return
	 */
	public static String setCellPhoneNumber( String cellPhone ) {

		if ( StringUtil.isEmpty( cellPhone ) ) {
			return "";
		}

		if ( cellPhone.indexOf( "-" ) > -1 ) { // 이미 - 있는 문자열
			return cellPhone;
		}

		Pattern pattern = Pattern.compile( "(^0\\d{2})([1-9]\\d{2,3})(\\d{4}$)" );
		Matcher m = pattern.matcher( cellPhone );

		if ( !m.matches() ) {
			return cellPhone;
		}

		StringBuffer result = new StringBuffer();
		m.appendReplacement( result, "$1-$2-$3" );

		return result.toString();
	}
}