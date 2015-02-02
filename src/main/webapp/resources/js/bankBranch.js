/************************************************************************************
 * 파일이름 : bankBranch.js
 * 파일설명 : 은행 지점와 관련된 기능을 모아놓은 파일
 * 파일작성 : 이원 
 ***********************************************************************************/
var bankBranch = {
		
	listPage : function(getParameter){
		var url = contextPath + "/BankBranch/list/" + getParameter;
		$("#searchFrom").attr("action",url);
		$("#searchFrom").submit();
	},
	
	regist : function(formName) {

		// 필수 항목 체크
		if( $('#custCode').val() == 0 ) {
			alert("고객사명을 선택해주세요.");
			$("#custCode").focus();
			return false;
		}
		if( $('#bankCode').val() == 0 ) {
			alert("은행명을 선택해주세요.");
			$("#bankCode").focus();
			return false;
		}
		if( $('#branchStateCode').val() == 0 ) {
			alert("형태를 선택해주세요.");
			$("#branchStateCode").focus();
			return false;
		}
		if( $('#localCode').val() == 0 ) {
			alert("지역을 선택해주세요.");
			$("#localCode").focus();
			return false;
		}
		if( $("#siteName").val() == "" ){
			alert("사이트명을 입력해주세요.");
			$("#siteName").focus();
			return false;
		}
		if( $('#cleanUserNo').val() == 0 || $("#cleanUserNo").val() == "") {
			alert("청소담당자를 선택해주세요.");
			$("#cleanUserNo").focus();
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
					//window.location = contextPath + "/BankBranch/get/" + res.data;
					window.location = contextPath + "/BankBranch/list/0";
				}
			},
			error : function(data, status, e) {
				alert("은행 지점 등록에 실패했습니다.");
			}
		});
		$('#' + formName).submit();
	},

	modify : function(formName) {

		// 필수 항목 체크
		if( $('#custCode').val() == 0 ) {
			alert("고객사명을 선택해주세요.");
			$("#custCode").focus();
			return false;
		}
		if( $('#bankCode').val() == 0 ) {
			alert("은행명을 선택해주세요.");
			$("#bankCode").focus();
			return false;
		}
		if( $('#branchStateCode').val() == 0 ) {
			alert("형태를 선택해주세요.");
			$("#branchStateCode").focus();
			return false;
		}
		if( $('#localCode').val() == 0 ) {
			alert("지역을 선택해주세요.");
			$("#localCode").focus();
			return false;
		}
		if( $("#siteName").val() == "" ){
			alert("사이트명을 입력해주세요.");
			$("#siteName").focus();
			return false;
		}
		if( $('#cleanUserNo').val() == 0 || $("#cleanUserNo").val() == "") {
			alert("청소담당자를 선택해주세요.");
			$("#cleanUserNo").focus();
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
					//window.location = contextPath + "/BankBranch/get/" + $("#codeId").val();
					window.location = contextPath + "/BankBranch/list/0";
				}
			},
			error : function(data, status, e) {
				alert("은행 지점 수정에 실패했습니다.");
			}
		});
		$('#' + formName).submit();
	},

	isDelete : function( branchNo, isDelete ) {

		if ( !confirm("은행 지점를 삭제 하시겠습니까?") ) {
			return false;
		}

		$.ajax({
			url : contextPath + "/BankBranch/delete",
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
					window.location = contextPath + "/BankBranch/list/0";
				}
			},
			error : function(data, status, e) {
				alert("은행 지점 삭제 실패했습니다.");
			}
		});
		
	}
};