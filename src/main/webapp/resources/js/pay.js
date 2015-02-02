/************************************************************************************
 * 파일이름 : pay.js
 * 파일설명 : 지급 관리와 관련된 기능을 모아놓은 파일
 * 파일작성 : 이원 
 ***********************************************************************************/
var pay = {
		
	listPage : function(getParameter){
		var url = contextPath + "/Pay/list/" + getParameter;
		$("#searchFrom").attr("action",url);
		$("#searchFrom").submit();
	},
	
	regist : function(formName) {

		// 필수 항목 체크
		if( $("#payDate").val() == "" ){
			alert("지급일을 입력해주세요.");
			$("#payDate").focus();
			return false;
		}
		if( $('#withdrawBank').val() == 0 ) {
			alert("출금은행을 선택해주세요.");
			$("#withdrawBank").focus();
			return false;
		}
		if( $('#depositBank').val() == 0 ) {
			alert("입금은행을 선택해주세요.");
			$("#depositBank").focus();
			return false;
		}
		if( $('#reveiveNo').val() == 0 || $("#reveiveNo").val() == "") {
			alert("수급자를 선택해주세요.");
			$("#reveiveNo").focus();
			return false;
		}
		
		$('#' + formName).ajaxForm({
			type : "POST",
			async : false,
			cache : false,
			dataType : "json",
			success : function(data, status) {
				var res = data;
				alert(res.message);
				if ( res.status == 200 ) {
					//window.location = contextPath + "/Pay/get/" + res.data;
					window.location = contextPath + "/Pay/list/1";
				}
			},
			error : function(data, status, e) {
				alert("지급 관리 등록에 실패했습니다.");
			}
		});
		$('#' + formName).submit();
	},

	modify : function(formName) {

		// 필수 항목 체크
		if( $("#payDate").val() == "" ){
			alert("지급일을 입력해주세요.");
			$("#payDate").focus();
			return false;
		}
		if( $('#withdrawBank').val() == 0 ) {
			alert("출금은행을 선택해주세요.");
			$("#withdrawBank").focus();
			return false;
		}
		if( $('#depositBank').val() == 0 ) {
			alert("입금은행을 선택해주세요.");
			$("#depositBank").focus();
			return false;
		}
		if( $('#reveiveNo').val() == 0 || $("#reveiveNo").val() == "") {
			alert("수급자를 선택해주세요.");
			$("#reveiveNo").focus();
			return false;
		}
		
		
		$('#' + formName).ajaxForm({
			type : "POST",
			async : false,
			cache : false,
			dataType : "json",
			success : function(data, status) {
				var res = data;
				alert(res.message);
				if ( res.status == 200 ) {
					//window.location = contextPath + "/Pay/get/" + $("#codeId").val();
					window.location = contextPath + "/Pay/list/1";
				}
			},
			error : function(data, status, e) {
				alert("지급 관리 수정에 실패했습니다.");
			}
		});
		$('#' + formName).submit();
	},

	isDelete : function( branchNo, isDelete ) {

		if ( !confirm("지급 관리를 삭제 하시겠습니까?") ) {
			return false;
		}

		$.ajax({
			url : contextPath + "/Pay/delete",
			data : {
				branchNo : branchNo,
				isDelete : isDelete
			},
			type : "post",
			dataType : "json",
			success : function(data, status) {
				var res = data;
				alert(res.message);
				if ( res.status == 200 ) {
					window.location = contextPath + "/Pay/list/1";
				}
			},
			error : function(data, status, e) {
				alert("지급 관리 삭제 실패했습니다.");
			}
		});
		
	}
};