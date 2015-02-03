package com.greenhi.common.util;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author gr.park
 * @version 1.0
 *          클래스 설명<br>
 *          Date관련 각종 기능성 Method를 제공하는 Util Class<br>
 * 
 */
public class DateUtil {

	public static final long MILLISECONDS_IN_HOUR = 60L * 60L * 1000L;

	private static final MessageFormat sDashForm = new MessageFormat( "{0}-{1}-{2}" );
	private static final MessageFormat sCommaForm = new MessageFormat( "{0}.{1}.{2}" );
	private static final MessageFormat sSlashForm = new MessageFormat( "{0}/{1}/{2}" );
	private static final MessageFormat sBlankForm = new MessageFormat( "{0}{1}{2}" );

	private static final SimpleDateFormat dDashForm = new SimpleDateFormat( "yyyy-MM-dd" );
	private static final SimpleDateFormat dCommaForm = new SimpleDateFormat( "yyyy.MM.dd" );
	private static final SimpleDateFormat dSlashForm = new SimpleDateFormat( "yyyy/MM/dd" );
	private static final SimpleDateFormat dBlankForm = new SimpleDateFormat( "yyyyMMdd" );
	private static final SimpleDateFormat dSecondForm = new SimpleDateFormat( "yyyy-MM-dd H:m:s" ); // old로 대체
	private static final SimpleDateFormat dFullsSecondForm = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	private static final SimpleDateFormat dFullForm = new SimpleDateFormat( "yyyyMMddHHmmss" );
	private static final SimpleDateFormat dKoreanForm = new SimpleDateFormat( "yyyy년 MM월 dd일" );
	private static final SimpleDateFormat dTimeForm = new SimpleDateFormat( "HHmmss" );
	private static final SimpleDateFormat dFullTimeForm = new SimpleDateFormat( "HH:mm:ss" );

	private static final MessageFormat timeForm = new MessageFormat( "{0,number,00}:00" );

	/**
	 * 현재 일자에 일정 Day를 합한 일자를 YYYYMMDD 형식으로 반환한다.
	 * 
	 * @param day 추가할 일자
	 * @return day가 추가된 일자(YYYYMMDD)
	 */
	public static String addDay( int day ) {

		Calendar cal = getCurrentCalendar();
		cal.add( Calendar.DATE, day );

		Date tmpDate = new Date( cal.getTimeInMillis() );
		return date2Str( tmpDate, ' ' );
	}

	/**
	 * 기준 일자에 일정 Day를 합한 일자를 YYYYMMDD 형식으로 반환한다.
	 * 
	 * @param targetDate 기준일자
	 * @param day 추가할 일자
	 * @return 기준일자에 day가 추가된 일자(YYYYMMDD)
	 */
	public static String addDay( String targetDate,
			int day ) {

		Calendar cal = str2Calendar( targetDate );
		cal.add( Calendar.DATE, day );

		Date tmpDate = new Date( cal.getTimeInMillis() );

		if ( targetDate.indexOf( '-' ) != -1 ) {
			return date2Str( tmpDate, '-' );
		} else if ( targetDate.indexOf( '.' ) != -1 ) {
			return date2Str( tmpDate, '.' );
		} else if ( targetDate.indexOf( '/' ) != -1 ) {
			return date2Str( tmpDate, '/' );
		} else {
			return date2Str( tmpDate, ' ' );
		}
	}

	/**
	 * 기준 일자에 일정 Month를 합한 일자를 YYYYMMDD 형식으로 반환한다.
	 * 
	 * @param targetDate 기준일자
	 * @param month 추가할 월
	 * @return 기준일자에 month가 추가된 일자(YYYYMMDD)
	 */
	public static String addMonth( String targetDate,
			int month ) {

		Calendar cal = str2Calendar( targetDate );
		cal.add( Calendar.MONTH, month );

		Date tmpDate = new Date( cal.getTimeInMillis() );

		if ( targetDate.indexOf( '-' ) != -1 ) {
			return date2Str( tmpDate, '-' );
		} else if ( targetDate.indexOf( '.' ) != -1 ) {
			return date2Str( tmpDate, '.' );
		} else if ( targetDate.indexOf( '/' ) != -1 ) {
			return date2Str( tmpDate, '/' );
		} else {
			return date2Str( tmpDate, ' ' );
		}
	}

	/**
	 * 두개의 날짜를 String으로 입력받아 비교하는 메소드
	 * stdDate > compDate : true, stdDate <= compDate : false;
	 * 
	 * @param stdDate 기준Date
	 * @param compDate 비교Date
	 * @return boolean (stdDate > compDate : true, stdDate <= compDate : false)
	 */
	public static boolean compareDate( String stdDate,
			String compDate ) {

		Calendar cd1 = str2Calendar( stdDate );
		Calendar cd2 = str2Calendar( compDate );

		if ( cd1.after( cd2 ) ) {
			return true;
		}

		return false;
	}

	/**
	 * Date를 Calendar로 변환한다.
	 * Date(yyyy-mm-dd) -> Calendar
	 * 
	 * @param cToDate Date
	 * @return 변환된 Calendar
	 */
	public static final Calendar date2Calendar( Date cToDate ) {

		if ( cToDate != null ) {
			Calendar dateToCal = Calendar.getInstance();
			dateToCal.setTime( cToDate );
			dateToCal.add( Calendar.DATE, 0 );
			return dateToCal;
		}
		return null;
	}

	/**
	 * dSecondForm 형식으로 반환함 (yyyy-MM-dd H:m:s)
	 * 
	 * @param cToDate
	 * @return
	 */
	public static String date2OldSecondStr( Date cToDate ) {

		String returnString = "";

		returnString = dSecondForm.format( cToDate );
		return returnString;
	}

	/**
	 * java.sql.Date를 날짜 String으로 변환하는 기능 제공
	 * maskType으로 '-', '.', '/', ''의 Format을 지원 (':'일시에는 시:분:초 포함)
	 * 
	 * @param cToDate 날짜 String으로 변경코저하는 Date Object
	 * @param maskType 변경코저 하는 maskType
	 * @return 날짜 String
	 */
	public static String date2Str( Date cToDate,
			char maskType ) {

		String returnString = "";

		if ( cToDate != null ) {
			switch ( maskType ) {
				case '-' :
					returnString = dDashForm.format( cToDate );
					break;

				case '.' :
					returnString = dCommaForm.format( cToDate );
					break;

				case '/' :
					returnString = dSlashForm.format( cToDate );
					break;

				case ':' :
					returnString = dFullsSecondForm.format( cToDate );
					break;

				default :
					returnString = dBlankForm.format( cToDate );
					break;
			}
		}

		return returnString;
	}

	/**
	 * Date를 TimeStamp로 변환한다.
	 * Date(yyyy-mm-dd) -> TimeStamp
	 * 
	 * @param cToDate Date
	 * @return Timestamp
	 */
	public static final Timestamp date2Timestamp( Date cToDate ) {

		if ( cToDate != null ) {
			Timestamp tsDate = new Timestamp( cToDate.getTime() );
			return tsDate;
		}
		return null;
	}

	/**
	 * 시작일자와 종료일자 사이의 일수를 구한다.
	 * 
	 * @param d1 시작일자
	 * @param d2 종료일자
	 * @return 시작일자와 종료일자 사이 일수
	 */
	public static long daysBetween( Date d1,
			Date d2 ) {

		return ((d2.getTime() - d1.getTime() + MILLISECONDS_IN_HOUR) / (MILLISECONDS_IN_HOUR * 24));
	}

	/**
	 * 시작일자와 종료일자 사이의 일수를 구한다.<BR>
	 * 일자 형식은 "YYYYMMDD"로 한다.<BR>
	 * 예를 들어 20020501 ~ 20020510 = 9 <BR>
	 * 
	 * @param sFromDate : 시작일 YYYYMMDD<BR>
	 * @param sToDate : 종료일 YYYYMMDD<BR>
	 * @return long : 일수간격<BR>
	 */
	public static long daysBetween( String sFromDate,
			String sToDate ) {

		Date fDate = str2Date( sFromDate );
		Date tDate = str2Date( sToDate );

		return daysBetween( fDate, tDate );
	}

	/**
	 * 현재 시간과의 차이를 Y일 or X시간 or Y분 or Z초 형식의 String으로 반환한다.
	 * 
	 * @param time
	 * @return
	 */
	public static String gapFromCurrentTime( long time ) {

		StringBuffer buf = new StringBuffer();

		long gap = (System.currentTimeMillis() - time) / 1000;
		long reminder = 0;

		long value = gap / (3600 * 24);

		if ( value != 0 ) {
			buf.append( value ).append( "일" );
			return buf.toString();
		}

		value = gap / 3600;
		reminder = gap % 3600;

		if ( value != 0 ) {
			buf.append( value ).append( "시간 " );
			return buf.toString();
		}

		value = reminder / 60;
		reminder = reminder % 60;

		if ( value != 0 ) {
			buf.append( value ).append( "분 " );
			return buf.toString();
		}

		buf.append( reminder + 8 ).append( "초" );

		return buf.toString();
	}

	/**
	 * 두 시간과의 차이를 Y일 or X시간 or Y분 or Z초 형식의 String으로 반환한다.
	 * 
	 * @param time
	 * @return
	 */
	public static String gapFromTime( long time ) {

		StringBuffer buf = new StringBuffer();

		long gap = (time - System.currentTimeMillis()) / 1000;
		long reminder = 0;

		long value = gap / (3600 * 24);

		if ( value != 0 ) {
			buf.append( value ).append( "일" );
			return buf.toString();
		}

		value = gap / 3600;
		reminder = gap % 3600;

		if ( value != 0 ) {
			buf.append( value ).append( "시간 " );
			return buf.toString();
		}

		value = reminder / 60;
		reminder = reminder % 60;

		if ( value != 0 ) {
			buf.append( value ).append( "분 " );
			return buf.toString();
		}

		buf.append( reminder + 8 ).append( "초" );

		return buf.toString();
	}

	public static Calendar getCurrentCalendar() {

		return Calendar.getInstance();
	}

	/**
	 * 현재 일자을 YYYYMMDD 형식으로 반환한다.
	 * 
	 * @return 현재일자(YYYYMMDD)
	 */
	public static String getCurrentDate() {

		Calendar cal = getCurrentCalendar();

		String currentYear = String.valueOf( cal.get( Calendar.YEAR ) );
		String currentMonth = String.valueOf( (cal.get( Calendar.MONTH ) + 1) );
		String currentDay = String.valueOf( cal.get( Calendar.DATE ) );

		if ( Integer.parseInt( currentMonth ) < 10 ) {
			currentMonth = "0" + currentMonth;
		}

		if ( Integer.parseInt( currentDay ) < 10 ) {
			currentDay = "0" + currentDay;
		}

		String vCurrentDate = currentYear + currentMonth + currentDay;

		return vCurrentDate;
	}

	/**
	 * 현재 시간을 HH:mm:ss 형식으로 반환한다.
	 * 
	 * @return
	 */
	public static String getCurrentFullTime() {

		Calendar cal = getCurrentCalendar();
		return dFullTimeForm.format( cal.getTime() );
	}

	public static String getCurrentKoreanDate() {

		Calendar cal = getCurrentCalendar();
		return dKoreanForm.format( cal.getTime() );
	}

	public static String getKoreanDate( Timestamp time ) {

		return dKoreanForm.format( time );
	}

	/**
	 * 현재 시간을 HHmmss 형식으로 반환한다.
	 * 
	 * @return 현재일자(YYYYMMDD)
	 */
	public static String getCurrentTime() {

		Calendar cal = getCurrentCalendar();
		return dTimeForm.format( cal.getTime() );
	}

	/**
	 * 오늘 날짜를 TimeStamp로 반환한다.
	 * 
	 * @return Timestamp
	 */
	public static final Timestamp getCurrentTimestamp() {

		Timestamp ts = new Timestamp( new Date().getTime() );

		return ts;
	}

	/**
	 * 날짜를 주어진 형식대로 파싱해서 Date로 반환한다.
	 * 포맷과 날짜 데이터가 맞지 않으면 그냥 오늘 날짜를 반환
	 * 
	 * @param date 문자열로 된 날짜 정보
	 * @param format 문자열로 된 날짜 표시 정보. {@link SimpleDateFormat}에서 처리한다.
	 * @return 날짜를 {@link java.util.Date} 형으로 반환해준다.
	 * */
	public static Date getDateByFormat( String date,
			String format ) {

		try {
			return new SimpleDateFormat( format ).parse( date );
		} catch ( ParseException e ) {
			// parseException이 나면 그냥 무조건 오늘 날짜가 돌아가게 한다.
			e.printStackTrace(System.out);
		}
		return new Date();
	}

	/**
	 * Date 객체를 주어진 형식대로변환해서 String으로 반환
	 * 날짜 데이터가 없으면, 새로 생성해서 오늘 날짜 지정
	 * 
	 * @param date 날짜 정보
	 * @param format 문자열로 된 날짜 표시 정보. {@link SimpleDateFormat}에서 처리한다.
	 * @return 날짜 정보를 주어진 형식대로 String 형으로 반환해준다.
	 * */
	public static String getDateStringByFormat( Date date,
			String format ) {

		if ( date == null ) {
			date = new Date();
		}
		return (new SimpleDateFormat( format )).format( date );
	}

	/**
	 * 현재 시간을 yyyyMMddHHmmss 형태로 반환한다.
	 * 
	 * @return
	 */
	public static String getFullTimeStr() {

		return dFullForm.format( new Date() );
	}

	public static String getFullTimeStr2() {

		return dDashForm.format( new Date() );
	}
	
	public static String getFullTimeStr( Date date ) {

		return dFullForm.format( date );
	}

	public static String getHourFormat( int value ) {

		Object[] val = { value };
		return timeForm.format( val );
	}

	/**
	 * 주민등록번호 13자리를 입력받아 만나이를 리턴한다.
	 * 
	 * @param idNum
	 * @return
	 */
	public static int getMyAge( String idNum ) {

		String year = "";
		String month = "";
		String day = "";
		String myYear = ""; // 생일
		int myAge = 0; // 만 나이

		try {
			SimpleDateFormat formatY = new SimpleDateFormat( "yyyy", Locale.KOREA );
			SimpleDateFormat formatM = new SimpleDateFormat( "MM", Locale.KOREA );
			SimpleDateFormat formatD = new SimpleDateFormat( "dd", Locale.KOREA );
			year = formatY.format( new Date() );
			month = formatM.format( new Date() );
			day = formatD.format( new Date() );

			if ( idNum.charAt( 6 ) == '1' || idNum.charAt( 6 ) == '2' || idNum.charAt( 6 ) == '5' || idNum.charAt( 6 ) == '6' ) {
				myYear = "19" + idNum.substring( 0, 2 );
			} else {
				myYear = "20" + idNum.substring( 0, 2 );
			}

			if ( Integer.parseInt( month ) > Integer.parseInt( idNum.substring( 2, 4 ) ) ) {
				myAge = Integer.parseInt( year ) - Integer.parseInt( myYear );
			} else if ( Integer.parseInt( month ) == Integer.parseInt( idNum.substring( 2, 4 ) ) ) {
				if ( Integer.parseInt( day ) > Integer.parseInt( idNum.substring( 4, 6 ) ) ) {
					myAge = Integer.parseInt( year ) - Integer.parseInt( myYear );
				} else {
					myAge = Integer.parseInt( year ) - (Integer.parseInt( myYear ) + 1);
				}
			} else {
				myAge = Integer.parseInt( year ) - (Integer.parseInt( myYear ) + 1);
			}
		} catch ( Exception ex ) {
			ex.printStackTrace();
		} finally {
			myYear = "00";
		}

		return myAge;
	}

	/**
	 * 생년월일(yyyyMMdd) 입력받아 만 나이를 리턴한다.
	 * Date보다는 Calendar가 낫지. 다른 메소드명이 생각안나서 2..
	 * 
	 * @param birthDay 생년월일 8자리
	 * @return myAge
	 */
	public static int getMyAge2( String birthDay ) {

		int myAge = 0;

		try {
			Calendar currentDate = Calendar.getInstance( Locale.KOREA );
			Calendar birthDayDate = Calendar.getInstance( Locale.KOREA );
			birthDayDate.setTime( dBlankForm.parse( birthDay ) );
			int originalBirthYear = birthDayDate.get( Calendar.YEAR );
			birthDayDate.set( Calendar.YEAR, currentDate.get( Calendar.YEAR ) );

			if ( currentDate.compareTo( birthDayDate ) > 0 ) { // 생일 전
				myAge = currentDate.get( Calendar.YEAR ) - originalBirthYear;
			} else { // 생일 지남
				myAge = currentDate.get( Calendar.YEAR ) - (originalBirthYear + 1);
			}

		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return myAge;
	}

	/**
	 * 주민등록번호 13자리를 입력받아 만나이를 성별을 리턴한다.
	 * 
	 * @param idNum
	 * @return
	 */
	public static String getMySex( String idNum ) {

		String sex;

		if ( idNum.charAt( 6 ) == '1' || idNum.charAt( 6 ) == '3' || idNum.charAt( 6 ) == '5' || idNum.charAt( 6 ) == '7' ) {
			sex = "M";
		} else {
			sex = "W";
		}

		return sex;
	}

	/**
	 * (yyyy-MM-dd) String형식으로 받은 날짜를 Timestmp로 변환한다.
	 * 
	 * @author 박민철
	 * @param dateStr
	 * @return
	 * @throws Exception
	 */
	public static Timestamp getTimestampFromStr( String dateStr ) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );

		java.util.Date date = null;
		String eventDateTime;
		eventDateTime = dateStr;
		try {
			date = sdf.parse( eventDateTime );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}

		return new Timestamp( date.getTime() );
	}

	/**
	 * 내일 일자을 YYYYMMDD 형식으로 반환한다.
	 * 
	 * @return 내일일자(YYYYMMDD)
	 */
	public static String getTomorrowDate() {

		Calendar cal = getCurrentCalendar();
		cal.add( Calendar.DATE, 1 );

		String currentYear = String.valueOf( cal.get( Calendar.YEAR ) );
		String currentMonth = String.valueOf( (cal.get( Calendar.MONTH ) + 1) );
		String currentDay = String.valueOf( cal.get( Calendar.DATE ) );

		if ( Integer.parseInt( currentMonth ) < 10 ) {
			currentMonth = "0" + currentMonth;
		}

		if ( Integer.parseInt( currentDay ) < 10 ) {
			currentDay = "0" + currentDay;
		}

		String vCurrentDate = currentYear + currentMonth + currentDay;

		return vCurrentDate;
	}

	public static long getTomorrowTime() {

		Calendar cal = getCurrentCalendar();
		cal.add( Calendar.DATE, 1 );
		cal.set( Calendar.HOUR_OF_DAY, 0 );
		cal.set( Calendar.MINUTE, 0 );
		cal.set( Calendar.SECOND, 0 );

		return cal.getTimeInMillis();
	}

	/**
	 * 어제 일자을 YYYYMMDD 형식으로 반환한다.
	 * 
	 * @return 어제일자(YYYYMMDD)
	 */
	public static String getYesterdayDate() {

		Calendar cal = getCurrentCalendar();
		cal.add( Calendar.DATE, -1 );

		String currentYear = String.valueOf( cal.get( Calendar.YEAR ) );
		String currentMonth = String.valueOf( (cal.get( Calendar.MONTH ) + 1) );
		String currentDay = String.valueOf( cal.get( Calendar.DATE ) );

		if ( Integer.parseInt( currentMonth ) < 10 ) {
			currentMonth = "0" + currentMonth;
		}

		if ( Integer.parseInt( currentDay ) < 10 ) {
			currentDay = "0" + currentDay;
		}

		String vCurrentDate = currentYear + currentMonth + currentDay;

		return vCurrentDate;
	}

	/**
	 * 현재 일자에 일정 Day를 뺀 일자를 YYYYMMDD 형식으로 반환한다.
	 * 
	 * @param day 뺄 일자
	 * @return day가 빠진 일자(YYYYMMDD)
	 */
	public static String minusDay( int day ) {

		return addDay( -1 * day );
	}

	/**
	 * 기준 일자에 일정 Day를 뺀 일자를 YYYYMMDD 형식으로 반환한다.
	 * 
	 * @param targetDate 기준일자
	 * @param day 뺄 일자
	 * @return 기준일자에 day가 빠진 일자(YYYYMMDD)
	 */
	public static String minusDay( String targetDate,
			int day ) {

		Calendar cal = str2Calendar( targetDate );
		cal.add( Calendar.DATE, -1 * day );

		Date tmpDate = new Date( cal.getTimeInMillis() );

		if ( targetDate.indexOf( '-' ) != -1 ) {
			return date2Str( tmpDate, '-' );
		} else if ( targetDate.indexOf( '.' ) != -1 ) {
			return date2Str( tmpDate, '.' );
		} else if ( targetDate.indexOf( '/' ) != -1 ) {
			return date2Str( tmpDate, '/' );
		} else {
			return date2Str( tmpDate, ' ' );
		}
	}

	/**
	 * 기준 일자에 일정 Month를 뺀 일자를 YYYYMMDD 형식으로 반환한다.
	 * 
	 * @param targetDate 기준일자
	 * @param month 뺄 월
	 * @return 기준일자에 month가 빠진 일자(YYYYMMDD)
	 */
	public static String minusMonth( String targetDate,
			int month ) {

		Calendar cal = str2Calendar( targetDate );
		cal.add( Calendar.MONTH, -1 * month );

		Date tmpDate = new Date( cal.getTimeInMillis() );

		if ( targetDate.indexOf( '-' ) != -1 ) {
			return date2Str( tmpDate, '-' );
		} else if ( targetDate.indexOf( '.' ) != -1 ) {
			return date2Str( tmpDate, '.' );
		} else if ( targetDate.indexOf( '/' ) != -1 ) {
			return date2Str( tmpDate, '/' );
		} else {
			return date2Str( tmpDate, ' ' );
		}
	}

	/**
	 * yyyy-MM-dd H:m:s 형식의 string을 long으로 파싱
	 * 
	 * @param date
	 * @return
	 */
	public static long oldSecondStr2Long( String date ) {

		Date data = null;
		try {

			data = dSecondForm.parse( date );

			return data.getTime();
		} catch ( Exception e ) {
			return -1;
		}
	}

	/**
	 * 초단위의 시간을 X시간 Y분 Z초 형식의 String으로 반환한다.
	 * 
	 * @param second
	 * @return
	 */
	public static String second2DateStr( long second ) {

		StringBuffer buf = new StringBuffer();

		long value = second / 3600;
		long reminder = second % 3600;

		if ( value != 0 ) {
			buf.append( value ).append( "시간 " );
		}

		value = reminder / 60;
		reminder = reminder % 60;

		if ( value != 0 ) {
			buf.append( value ).append( "분 " );
		}

		buf.append( reminder ).append( "초" );

		return buf.toString();
	}

	/**
	 * 날짜 String을 Calendar 형식으로 변환하는 기능을 제공
	 * 
	 * @param cToStr Date형으로 변환할 날짜 String
	 * @return 변환된 Calendar
	 */
	public static Calendar str2Calendar( String cToStr ) {

		Date tmpDate = str2Date( cToStr );

		if ( !ObjectUtil.isNull( tmpDate ) ) {
			Calendar tmpCalendar = Calendar.getInstance();
			tmpCalendar.setTime( tmpDate );

			return tmpCalendar;
		}

		return null;
	}

	/**
	 * 날짜 String을 java.sql.Date 형식으로 변환하는 기능 제공
	 * 
	 * @param cToStr Date형으로 변환할 날짜 String
	 * @return Date Object
	 */
	public static Date str2Date( String cToStr ) {

		if ( !StringUtil.isBlank( cToStr ) ) {
			String cTemp = str2Str( cToStr, '-' );
			java.sql.Date cToDate = java.sql.Date.valueOf( cTemp );
			return cToDate;
		}

		return null;
	}

	/**
	 * masktype에 해당하는 string date를 long 으로 파싱
	 * 파싱 실패할 경우 -1을 리턴
	 * 
	 * @param date 날짜string
	 * @param maskType ( - , . , / , : ) 디폴트는 블랭크 (마스킹타입 없음)
	 * @return
	 */
	public static long str2Long( String date,
			char maskType ) {

		Date data = null;

		try {
			switch ( maskType ) {
				case '-' :
					data = dDashForm.parse( date );
					break;
				case '.' :
					data = dCommaForm.parse( date );
					break;
				case '/' :
					data = dSlashForm.parse( date );
					break;
				case ':' :
					data = dFullsSecondForm.parse( date );
					break;
				default :
					data = dBlankForm.parse( date );
			}

		} catch ( Exception e ) {

			return -1;
		}

		return data.getTime();
	}

	/**
	 * 날짜 Format을 변경기능을 제공
	 * 
	 * maskType으로 '-', '.', '/', ''의 Format을 지원
	 * 
	 * @param cToStr Format을 변경코저하는 일자 String
	 * @param maskType 변경코저 하는 maskType
	 * @return 변환된 String
	 */
	public static String str2Str( String cToStr,
			char maskType ) {

		String returnString = "";

		if ( cToStr != null && !cToStr.equals( "" ) ) {
			if ( cToStr.length() > 8 ) {
				cToStr = StringUtil.deleteChar( cToStr, '-' );
				cToStr = StringUtil.deleteChar( cToStr, '.' );
				cToStr = StringUtil.deleteChar( cToStr, '/' );
			}

			String[] tmpStrings = { cToStr.substring( 0, 4 ), cToStr.substring( 4, 6 ), cToStr.substring( 6, 8 ) };

			switch ( maskType ) {
				case '-' :
					returnString = sDashForm.format( tmpStrings );
					break;

				case '.' :
					returnString = sCommaForm.format( tmpStrings );
					break;

				case '/' :
					returnString = sSlashForm.format( tmpStrings );
					break;

				default :
					returnString = sBlankForm.format( tmpStrings );
					break;
			}
		}

		return returnString;
	}

	/**
	 * 입력받은 timestamp 값을 yyyyMMddHHmmss 형식으로 리턴
	 * 
	 * @param tmValue
	 * @return
	 */
	public static String timestamp2BlakFullStr( Timestamp tmValue ) {

		return dFullForm.format( tmValue );
	}

	/**
	 * 입력받은 Timestamp값을 HHmmss 형식으로 리턴
	 * 
	 * @param tmValue
	 * @return
	 */
	public static String timestamp2BlankHourStr( Timestamp tmValue ) {

		return dTimeForm.format( tmValue );
	}

	/**
	 * 입력받은 timestamp값을 yyyy-MM-dd hh:mm:ss 형식으로 리턴
	 * 
	 * @param tmValue
	 * @return
	 */
	public static String timestamp2FullStr( Timestamp tmValue ) {

		return dFullsSecondForm.format( tmValue );
	}

	/**
	 * 입력받은 Timestamp값을 HH:mm:ss로 리턴
	 * 
	 * @param tmValue
	 * @return
	 */
	public static String timestamp2HourStr( Timestamp tmValue ) {

		return dFullTimeForm.format( tmValue );
	}

	/**
	 * "yyyyMMddhhmmss" 형태의 string을 timestamp로 변환
	 * 
	 * @param str
	 * @return
	 */
	public static Timestamp getFullTimeStamp( String str ) {

		try {

			Timestamp fullTime = new Timestamp( dFullForm.parse( str ).getTime() );
			return fullTime;

		} catch ( ParseException e ) {
			return new Timestamp( System.currentTimeMillis() );
		}

	}
}