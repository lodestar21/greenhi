/*******************************************************************************
 * 파일이름 : util.js
 * 파일설명 : 공통적으로 사용할만한 기능을 모아놓은 파일
 * 파일작성 : 이원
 ******************************************************************************/

/**
 * 문자(Text) 관련 함수모음
 */
var text = {
	/**
	 * 공백을 포함하고 있는지 검사
	 * 
	 * @param str
	 * @returns {Boolean}
	 */
	checkSpace : function(str) {
		if (str.search(/\s/) != -1) {
			return true;
		} else {
			return false;
		}
	},

	/**
	 * 한글만 입력되어있는지 검사
	 * 
	 * @param str
	 * @returns {Boolean}
	 */
	isHangul : function(str) {
		var len = str.length;

		for ( var i = 0; i < len; i++) {
			if (str.charCodeAt(i) != 32
					&& (str.charCodeAt(i) < 44032 || str.charCodeAt(i) > 55203))
				return false;
		}

		return true;
	},

	/**
	 * 숫자만 입력되었는지 검사
	 * 
	 * @param str
	 * @returns
	 */
	isNumeric : function(str) {
		var chars = "0123456789";
		return this.containsCharsOnly(str, chars);
	},

	/**
	 * 정해진 만큼 글자가 입력되면 다음 폼으로 이동(주민번호, 전화번호등)
	 * 
	 * @param fromId
	 *            현재 입력중인 요소의 ID
	 * @param toId
	 *            다음 입력할 요소의 ID
	 * @param maxSize
	 *            현재 입력중인 요소의 최대 길이
	 * @returns {Boolean}
	 */
	moveNext : function(fromId, toId, maxSize) {
		var NEXT_GO = true;
		var CUR_VAR = null;

		var cur = $('#' + fromId).val();
		curSize = cur.length;
		numFlag = this.isNumeric(cur);

		if (!numFlag && curSize >= 1 && cur != '00' && cur != '000') {
			alert('숫자가 아니거나 올바르지 않은 값을 입력했습니다.');
			document.getElementById(fromId).value = '';
			document.getElementById(fromId).focus();
			return false;
		}
		if (curSize == maxSize) {
			if (NEXT_GO || CUR_VAR != cur) {
				CUR_VAR = cur;
				NEXT_GO = false;
				document.getElementById(toId).focus();
			}
			return true;
		}
		NEXT_GO = true;
	},

	/**
	 * 입력된 값이 정해진 문자열로만 이루어졌는지 확인
	 * 
	 * @param str
	 *            확인할 문자열
	 * @param chars
	 *            제한할 문자열들
	 * @returns {Boolean}
	 */
	containsCharsOnly : function(str, chars) {
		for ( var i = 0; i < str.length; i++) {
			if (chars.indexOf(str.charAt(i)) == -1)
				return false;
		}
		return true;
	},

	/**
	 * 문자열을 특정 기호로 나눠서 출력
	 * 
	 * @param STR
	 *            입력 문자열
	 * @param SYM
	 *            구분기호
	 * @param ID
	 *            출력할 요소의 ID
	 * @param OPT
	 *            null : <input></input>, FirstSelect
	 * @returns {Boolean}
	 */
	division : function(STR, SYM, ID, OPT) {
		if (!STR)
			return false;
		var divNo = STR.split(SYM);

		// 옵션이 없을떄
		if (OPT == null || OPT == '') {
			for ( var i = 0; i < divNo.length; i++) {
				$('#' + ID + (i + 1)).val(divNo[i]);
			}
		}

		// 옵션이 Select일때
		else if (OPT == 'Select') {
			for ( var i = 0; i < divNo.length; i++) {
				if (i == 0) {
					$('#' + ID + (i + 1) + ' > option').each(function() {
						if ($(this).val() == divNo[i]) {
							$(this).attr("selected", "selected");
						}
					});
				} else
					$('#' + ID + (i + 1)).val(divNo[i]);

			}
		} else
			return false;
	},

	/**
	 * 천천단위 콤마(,) 넣기
	 * 
	 * @param str
	 * @returns
	 */
	addComma : function(str) {
		var reg = /(^[+-]?\d+)(\d{3})/;
		str += '';

		while (reg.test(str)) {
			str = str.replace(reg, '$1,$2');
		}
		return str;
	},

	/**
	 * 문자열 byte check
	 * 
	 * @param type
	 *            str : 글자, id, class, name
	 * @param value
	 *            확인할 문자열
	 * @param byte
	 *            제한할 바이트
	 * @param alt
	 *            yes : 경고창 및 지정된 byte를 넘으면 지움, no : 현재 입력된 글의 byte 리턴
	 * @param rtn
	 *            str : 자른 텍스트를 반환함
	 * @returns {Number}
	 */
	checkByte : function(type, value, byte, alt, rtn) {
		var i;
		var str;
		var elmt;
		var one_char;
		var str_byte = 0;
		var str_length = 0;
		switch (type) {
		case 'id':
			elmt = $('#' + value);
			str = elmt.val();
			break;
		case 'class':
			elmt = $('.' + value).val();
			str = elmt.val();
			break;
		case 'name':
			elmt = $('input[name="' + value + '"]');
			str = elmt.val();
			break;

		default: str = value;
			break;
		}
		if(str.length == 0) return 0;
		for (i = 0; i < str.length; i++) {
			// 입력 문자열의 한글자
			one_char = str.charAt(i);

			// 한글이면 2바이트
			if (escape(one_char).length > 4)
				str_byte += 2;
			// 그외는 1바이트
			else
				str_byte += 1;

			// 전체 크기가 제한할 byte를 넘지 않으면
			if (str_byte <= byte)
				str_length = i + 1;
			// 전체 크기가 제한할 byte를 넘으면
			if (str_byte > byte) {
				// 문자를 반환한다면 ( rtn 값이 'str'이면 )
				if(rtn == 'str') return str.substr(0, str_length) + '..';
				// 반환 옵션이 없다면
				else {
					// alert 경고를 띄우라고 했다면 경고를 띄운 후 제한된 글자만큼 반환
					if(alt == 'yes') {
						elmt.val(str.substr(0, str_length));
						alert('글자를 초과 입력할 수 없습니다.');
						return byte;
					}
					else {
						return false;
					}
				}
			}
		}
		if (str_byte <= byte && rtn == 'str') return str;
		else return str_byte;
	}
};

/**
 * 특별한 기능을 모아놓은 함수
 */
var util = {

	/**
	 * 올바른 이메일 주소인지 확인
	 * 
	 * @param obj
	 * @returns {Boolean}
	 */
	emailcheck : function( strValue ) {
		var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
		
		//입력을 안했으면
		if( strValue.lenght == 0 ) {
			return false;
		}
		
		//이메일 형식에 맞지않으면
		if ( !strValue.match(regExp) ) {
			return false;
		}
		return true;
	},
	
	/**
	 * 전화번호 형식을 하이픈(-)을 넣어 반환
	 * 
	 * @param str
	 * @returns
	 */
	PhonNumStr : function(str) {
		var RegNotNum = /[^0-9]/g;
		var RegPhonNum = "";
		var DataForm = "";

		// return blank
		if (str == "" || str == null)
			return "";

		// delete not number
		str = str.replace(RegNotNum, '');

		if (str.length < 4)
			return str;

		if (str.length > 3 && str.length < 7) {
			DataForm = "$1-$2";
			RegPhonNum = /([0-9]{3})([0-9]+)/;
		} else if (str.length == 7) {
			DataForm = "$1-$2";
			RegPhonNum = /([0-9]{3})([0-9]{4})/;
		} else if (str.length == 8) {
			DataForm = "$1-$2-$3";
			RegPhonNum = /([0-9]{4})([0-9]{2})([0-9]+)/;
		} else if (str.length == 9) {
			DataForm = "$1-$2-$3";
			RegPhonNum = /([0-9]{2})([0-9]{3})([0-9]+)/;
		} else if (str.length == 10) {
			DataForm = "$1-$2-$3";
			RegPhonNum = /([0-9]{3})([0-9]{3})([0-9]+)/;
		} else {
			DataForm = "$1-$2-$3";
			RegPhonNum = /([0-9]{3})([0-9]{4})([0-9]+)/;
		}

		while (RegPhonNum.test(str)) {
			str = str.replace(RegPhonNum, DataForm);
		}
		return str;
	},
	/**
	 * date 객체의 날짜를 시간(:구분)으로 리턴한다. 
	 * @param time
	 */
	HHMMStr : function (time) {
		var date = new Date(time);
		var hourPart = date.getHours();
		var hourPartStr = "0" + hourPart;
		hourPartStr = hourPartStr.substring(hourPartStr.length-2, hourPartStr.length);
		var minPart = date.getMinutes();
		var minPartStr = "0" + minPart;
		minPartStr = minPartStr.substring(minPartStr.length-2, minPartStr.length);
		
		return hourPartStr +":" + minPartStr;
	},
	/**
	 * date 객체의 날짜를 날짜(.구분) 으로 리턴한다.
	 * @param day
	 */
	yyyyMMddStr : function(day) {
		var date = new Date(day);
		var yearPart = date.getFullYear();
		var monPart = date.getMonth()+1;
		var monPartStr = "0"+monPart;
		monPartStr = monPartStr.substring(monPartStr.length-2, monPartStr.length);
		var datePart = date.getDate();
		var datePartStr = "0" + datePart;
		datePartStr = datePartStr.substring(datePartStr.length-2, datePartStr.length);
		
		return yearPart+"."+monPartStr+"."+datePartStr;
	}
};
