package com.greenhi.common.util;

import java.util.Vector;

/**
 * @author gr.park
 * @version 1.0<br>
 *          클래스 설명 <br>
 *          String 관련 기능성 Method를 제공하는 Util Class<br>
 */
public class StringUtil {

	/**
	 * 한글이 포함되어 있는 String의 경우, byte 단위로 SubString을 행한다.
	 * 
	 * @param src 한글이 포함되어 있는 String
	 * @param beginIndex 시작 byte index
	 * @param endIndex 끝 byte index
	 * @return String beginIndex부터 endIndex까지의 String
	 */
	public static String byteSubString( String src,
			int beginIndex,
			int endIndex ) {

		if ( StringUtil.isBlank( src ) ) {
			return "";
		}

		byte[] value = src.getBytes();

		if ( beginIndex < 0 ) {
			throw new StringIndexOutOfBoundsException( beginIndex );
		}

		if ( endIndex > value.length ) {
			throw new StringIndexOutOfBoundsException( endIndex );
		}

		if ( beginIndex > endIndex ) {
			throw new StringIndexOutOfBoundsException( endIndex - beginIndex );
		}

		byte[] tmpByte = new byte[endIndex - beginIndex];
		System.arraycopy( value, beginIndex, tmpByte, 0, tmpByte.length );

		return new String( tmpByte );
	}

	/**
	 * 한글이 포함되어 있는지 여부를 확인하여, 한글 포함 갯수를 반환한다.
	 * 
	 * @param str 한글 포함여부를 확인하고자 하는 String
	 * @return 포함 한글 갯수
	 */
	public static int countHangul( String str ) {

		int cnt = 0;

		if ( isBlank( str ) ) {
			return 0;
		}

		int index = 0;

		while ( index < str.length() ) {
			if ( str.charAt( index++ ) >= 256 ) {
				cnt++;
			}
		}

		return cnt;
	}

	/**
	 * & 를 HTML &amp; 로 변환
	 * 
	 * @param strString 변환할 문자열
	 * @return String 변화된 문자열
	 */
	public static String convert2AMP( String strString ) {

		if ( isBlank( strString ) ) {
			return "";
		}

		return replace( strString, "&", "&amp;" );
	}

	/**
	 * ' 를 &apos; 로 변환
	 * 
	 * @param strString
	 * @return String
	 */
	public static String convert2APOS( String strString ) {

		if ( isBlank( strString ) ) {
			return "";
		}

		return replace( strString, "'", "&apos;" );
	}

	/**
	 * '\r'을 <br>
	 * 로 변환해주는 메소드
	 * 
	 * @param strString String
	 * @return String
	 */
	public static String convert2BR( String strString ) {

		if ( isBlank( strString ) ) {
			return "";
		}

		return replace( strString, "\\n", "<br>" );
	}

	/**
	 * >를 &gt;로 변환
	 * 
	 * @param strString
	 * @return String
	 */
	public static String convert2GT( String strString ) {

		if ( isBlank( strString ) ) {
			return "";
		}

		return replace( strString, ">", "&gt;" );
	}

/**
	 * '<' 를 &lt; 로 변환
	 * @param strString String
	 * @return String
	 */
	public static String convert2LT( String strString ) {

		if ( isBlank( strString ) ) {
			return "";
		}

		return replace( strString, "<", "&lt;" );
	}

	/**
	 * '"' 를 &quot; 로 변환
	 * 
	 * @param strString
	 * @return String
	 */
	public static String convert2QUOT( String strString ) {

		if ( isBlank( strString ) ) {
			return "";
		}

		return replace( strString, "\"", "&quot;" );
	}

	/**
	 * 입력 String에 있는 특정문자를 삭제해준다.
	 * 
	 * @param strString input String
	 * @param strChar special character
	 * @return String 특정문자를 제거한 문자
	 */
	public static String deleteChar( String strString,
			char strChar ) {

		if ( isBlank( strString ) ) {
			return "";
		}

		strString = strString.trim();
		byte[] source = strString.getBytes();
		byte[] result = new byte[source.length];
		int j = 0;
		for ( int i = 0 ; i < source.length ; i++ ) {
			if ( source[i] == ( byte ) strChar ) {
				i++;
			}

			result[j++] = source[i];
		}

		return new String( result ).trim();
	}

	/**
	 * String을 좌측을 기준으로 size크기의 String을 반환한다.
	 * 
	 * @param str 입력String
	 * @param size 획득코저하는 사이즈
	 * @return 좌측을 기준으로 size 만큼의 String
	 */
	public static String getLeft( String str,
			int size ) {

		int tmpStringLength = str.length();

		if ( size >= tmpStringLength ) {
			return str;
		}

		return str.substring( 0, size );
	}

	/**
	 * 스트링을 우측을 기준으로 size크기의 String을 반환한다.
	 * 
	 * @param str 입력String
	 * @param size 획득코저하는 사이즈
	 * @return 우측을 기준으로 size 만큼의 String
	 */
	public static String getRight( String str,
			int size ) {

		int tmpStringLength = str.length();

		if ( size >= tmpStringLength ) {
			return str;
		}

		return str.substring( tmpStringLength - size, str.length() );
	}

	/**
	 * 바꾸고자 하는 스트링의 인덱스 모음을 구한다.
	 * 
	 * @param str String
	 * @param word String
	 * @return Vector tempindexArray
	 */
	public static Vector<Integer> getSelectedTextIndex( String str,
			String word ) {

		int index = 0;
		int fromIndex = 0;
		Vector<Integer> tempIndexArray = new Vector<Integer>();
		do {
			index = str.indexOf( word, fromIndex );
			if ( index != -1 ) {
				tempIndexArray.add( new Integer( index ) );
				fromIndex = index + word.length();
			}
		} while ( index != -1 );
		return tempIndexArray;
	}

	/**
	 * String을 특정 문자를 기준으로 나누어 배열형태로 반환한다.
	 * 
	 * @param strString : input string
	 * @param strDelimeter : delimeter character
	 * @return String[]
	 */
	public static String[] getSplitArray( String strString,
			String strDelimeter ) {

		return (getSplitVector( strString, strDelimeter ).toArray( new String[0] ));
	}

	/**
	 * 스트링을 특정 문자를 기준으로 나누어 Vector형태로 반환한다.
	 * 
	 * @param strString : input string
	 * @param strDelimeter : delimeter character
	 * @return Vector : result string
	 */
	public static Vector<String> getSplitVector( String strString,
			String strDelimeter ) {

		Vector<String> vResult = new Vector<String>();
		int nCount = 0, nLastIndex = 0;
		try {
			nLastIndex = strString.indexOf( strDelimeter );

			if ( nLastIndex == -1 ) {
				vResult.add( 0, strString );
			} else {
				while ( (strString.indexOf( strDelimeter ) > -1) ) {
					nLastIndex = strString.indexOf( strDelimeter );
					vResult.add( nCount, strString.substring( 0, nLastIndex ) );
					strString = strString.substring( nLastIndex + strDelimeter.length(), strString.length() );
					nCount++;
				}
				vResult.add( nCount, strString );
			}
		} catch ( Exception e ) {
			return null;
		}
		return vResult;
	}

	/**
	 * 특수문자를 XML에 맞도록 변환해주는 메소드
	 * 
	 * @param srcText 변환할 문자열
	 * @return 변화된 문자열
	 */
	public static String html2Str( String srcText ) {

		if ( isBlank( srcText ) ) {
			return "";
		}

		String strip = "";
		strip = "&";
		srcText = replace( srcText, strip, "&amp;" );
		strip = "<";
		srcText = replace( srcText, strip, "&lt;" );
		strip = ">";
		srcText = replace( srcText, strip, "&gt;" );
		strip = "\\n";
		srcText = replace( srcText, strip, "<br>" );
		strip = "\"";
		srcText = replace( srcText, strip, "&quot;" );
		strip = "'";
		srcText = replace( srcText, strip, "&apos;" );
		strip = " ";
		srcText = replace( srcText, strip, "&nbsp;" );

		return srcText;
	}

	/**
	 * String의 Blank여부 체크
	 * 
	 * @param tmpString
	 * @return null, "" 일 경우 true, null이 아니며, Blank String일 경우 false
	 */
	public static boolean isBlank( String tmpString ) {

		if ( isNull( tmpString ) ) {
			return true;
		}

		if ( tmpString.length() == 0 ) {
			return true;
		}

		return false;
	}

	/**
	 * String[]의 Blank여부 체크
	 * 
	 * @param tmpString Stirng 배열
	 * @return null, "" 일 경우 true, null이 아니며, Blank String일 경우 false
	 */
	public static boolean isBlank( String[] tmpString ) {

		if ( isNull( tmpString ) ) {
			return true;
		}

		if ( tmpString.length > 0 ) {
			return false;
		}

		return true;
	}

	/**
	 * String의 null이면서 공백이 아닌지 판단
	 * 
	 * @param tmpString
	 * @return null, "", " " 일 경우 true, 아니면 false
	 */
	public static boolean isEmpty( String tmpString ) {

		if ( isNull( tmpString ) ) {
			return true;
		}
		if ( tmpString.trim().length() == 0 ) {
			return true;
		}
		return false;
	}

	/**
	 * String의 Null Check
	 * 
	 * @param tmpString
	 * @return null일 경우 true, null이 아닐경우 false
	 */
	public static boolean isNull( String tmpString ) {

		if ( ObjectUtil.isNull( tmpString ) || tmpString.length() == 0 ) {
			return true;
		}
		return false;
	}

	/**
	 * String[]의 Null Check
	 * 
	 * @param tmpString String 배열
	 * @return null일 경우 true, null이 아닐경우 false
	 */
	public static boolean isNull( String[] tmpString ) {

		return ObjectUtil.isArrayNull( tmpString );
	}

	/**
	 * Object가 null일 경우 Blank String으로 반환한다.
	 * 
	 * @param obj
	 * @return String 입력 Object가 null일 경우 "" 반환, 이외의 경우에는 입력 Object의 toString() 반환
	 */
	public static String null2Blank( Object obj ) {

		if ( !ObjectUtil.isNull( obj ) ) {
			return (obj.toString());
		}

		return "";
	}

	/**
	 * 특정문자를 변환한다.
	 * 
	 * @param source source 문자열
	 * @param pattern 바꿀 문자패턴
	 * @param replace 적용할 문자패턴
	 * @return 변환된 문자
	 */
	public static String replace( String source,
			String pattern,
			String replace ) {

		if ( source != null ) {
			final int len = pattern.length();
			StringBuffer sb = new StringBuffer();
			int found = -1;
			int start = 0;

			while ( (found = source.indexOf( pattern, start )) != -1 ) {
				sb.append( source.substring( start, found ) );
				sb.append( replace );
				start = found + len;
			}

			sb.append( source.substring( start ) );

			return sb.toString();
		}

		return "";
	}

	/**
	 * '&amp;'를 '&'로 변환
	 * 
	 * @param strString 변환할 문자열
	 * @return 변화된 문자열
	 */
	public static String reverse2AMP( String strString ) {

		if ( isBlank( strString ) ) {
			return "";
		}

		return replace( strString, "&amp;", "&" );

	}

	/**
	 * '&apos;'를 '''로 변환
	 * 
	 * @param strString 변환할 문자열
	 * @return 변화된 문자열
	 */
	public static String reverse2APOS( String strString ) {

		if ( isBlank( strString ) ) {
			return "";
		}

		return replace( strString, "&apos;", "'" );
	}

	/**
	 * '&gt;'를 '>'로 변환
	 * 
	 * @param strString 변환할 문자열
	 * @return 변화된 문자열
	 */
	public static String reverse2GT( String strString ) {

		if ( isBlank( strString ) ) {
			return "";
		}

		return replace( strString, "&gt;", ">" );
	}

/**
	 * '&lt'를 '<'로 변환
	 * @param strString 변환할 문자열
	 * @return 변화된 문자열
	 */
	public static String reverse2LT( String strString ) {

		if ( isBlank( strString ) ) {
			return "";
		}

		return replace( strString, "&lt;", "<" );
	}

	/**
	 * '&quot;'를 '\"'로 변환
	 * 
	 * @param strString 변환할 문자열
	 * @return 변화된 문자열
	 */
	public static String reverse2QUOT( String strString ) {

		if ( isBlank( strString ) ) {
			return "";
		}

		return replace( strString, "&quot;", "\"" );
	}

	/**
	 * 왼쪽(Left)에 문자열을 끼어 넣는다.
	 * width는 문자열의 전체 길이를 나타내며 chPad는 끼어 넣을 char
	 * 
	 * @param str 적용할 문자열
	 * @param width 전체 문자열 크기
	 * @param chPad pad 적용할 char
	 * @return leftpad된 String
	 */
	public static String setLeftPad( String str,
			int width,
			char chPad ) {

		StringBuffer paddedValue = new StringBuffer();

		for ( int i = str.length() ; i < width ; i++ ) {
			paddedValue.append( chPad );
		}

		paddedValue.append( str );

		return (paddedValue.toString()).substring( 0, width );
	}

	/**
	 * 오른쪽(right)에 문자열을 끼어 넣는다.
	 * width는 문자열의 전체 길이를 나타내며, chPad는 끼어 넣을 char
	 * 
	 * @param str 적용할 문자열
	 * @param width 전체 길이
	 * @param chPad 삽입할 char
	 * @return String
	 */
	public static String setRightPad( String str,
			int width,
			char chPad ) {

		if ( str.length() >= width ) {
			return str.substring( 0, width );
		}

		StringBuffer paddingValue = new StringBuffer();
		for ( int i = str.length() ; i < width ; i++ ) {
			paddingValue.append( chPad );
		}

		return str + paddingValue.toString();
	}

	public static String str2Html( String srcText ) {

		if ( isBlank( srcText ) ) {
			return "";
		}

		srcText = srcText.replaceAll( "&amp;", "&" );
		srcText = srcText.replaceAll( "&lt;", "<" );
		srcText = srcText.replaceAll( "&gt;", ">" );
		srcText = srcText.replaceAll( "&quot;", "\"" );
		srcText = srcText.replaceAll( "&apos;", "'" );
		srcText = srcText.replaceAll( "&nbsp;", " " );

		return srcText;
	}

	/**
	 * String을 XML로 변환하기 위해 특수문자를 XML 형식으로 변환
	 * 
	 * @param strString 변환할 문자열
	 * @return 변화된 문자열
	 */
	public static String str2XML( String strString ) {

		String result = convert2APOS( convert2QUOT( convert2GT( convert2LT( convert2AMP( strString ) ) ) ) );
		return result;
	}

	public static String stripHtml( String src ) {

		return src.replaceAll( "\\<[\\s]*[^>]*>", "" );
	}

	/**
	 * XML형식으로 변환되어 있는 특수문자를 원본으로 변환
	 * 
	 * @param strString 변환할 문자열
	 * @return 변화된 문자열
	 */
	public static String xml2Str( String strString ) {

		String result = reverse2APOS( reverse2QUOT( reverse2GT( reverse2LT( reverse2AMP( strString ) ) ) ) );
		return result;
	}
}
