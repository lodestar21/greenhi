/************************************************************************************
 * 파일이름 : code.js
 * 파일설명 : 코드와 관련된 기능을 모아놓은 파일
 * 파일작성 : 이원 
 ***********************************************************************************/
var code = {
		
	listPage : function(getParameter){
		var url = contextPath + "/Code/list/" + getParameter;
		$("#searchFrom").attr("action",url);
		$("#searchFrom").submit();
	},
	
	regist : function(formName) {

		// 필수 항목 체크
		if( $("#codeId").val() == "" ){
			alert("코드 ID를 입력해주세요.");
			$("#codeId").focus();
			return false;
		}
		
		if ( Number($("#codeId").val()) % 100 != 0 || Number($("#codeId").val()) < 100 ) {
			alert("코드는 100 단위로 입력해 주세요.");
			$("#codeId").focus();
			return false;
		}
		
		if( $("#codeName").val() == "" ){
			alert("코드 이름을 입력해주세요.");
			$("#codeName").focus();
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
					//window.location = contextPath + "/Code/get/" + res.data;
					window.location = contextPath + "/Code/list/1";
				}
			},
			error : function(data, status, e) {
				alert("코드 등록에 실패했습니다.");
			}
		});
		$('#' + formName).submit();
	},

	modify : function(formName) {

		// 필수 항목 체크
		if( $("#codeName").val() == "" ){
			alert("코드 이름을 입력해주세요.");
			$("#codeName").focus();
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
					//window.location = contextPath + "/Code/get/" + $("#codeId").val();
					window.location = contextPath + "/Code/list/1";
				}
			},
			error : function(data, status, e) {
				alert("코드 수정에 실패했습니다.");
			}
		});
		$('#' + formName).submit();
	},
	
	registChild : function(formName) {

		// 필수 항목 체크
		if( $("#codeId").val() == "" ){
			alert("코드 ID를 입력해주세요.");
			$("#codeId").focus();
			return false;
		}
		
		if ( Number($("#codeId").val()) <= Number($("#uperCode").val()) || Number($("#codeId").val()) >= (Number($("#uperCode").val()) + 100) ) {
			alert("코드는 상위 코드 초과에서 (상위 코드 + 99 이하)로 입력하세요.");
			$("#codeId").focus();
			return false;
		}
		
		if( $("#codeName").val() == "" ){
			alert("코드 이름을 입력해주세요.");
			$("#codeName").focus();
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
					//window.location = contextPath + "/Code/get/" + $("#uperCode").val() + "/" + res.data;
					window.location = contextPath + "/Code/get/" + $("#uperCode").val();
				}
			},
			error : function(data, status, e) {
				alert("코드 등록에 실패했습니다.");
			}
		});
		$('#' + formName).submit();
	},

	modifyChild : function(formName) {

		// 필수 항목 체크
		if( $("#codeName").val() == "" ){
			alert("코드 이름을 입력해주세요.");
			$("#codeName").focus();
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
					//window.location = contextPath + "/Code/get/" + $("#uperCode").val() + "/" + $("#codeId").val();
					window.location = contextPath + "/Code/get/" + $("#uperCode").val();
				}
			},
			error : function(data, status, e) {
				alert("코드 수정에 실패했습니다.");
			}
		});
		$('#' + formName).submit();
	},

	isDelete : function( codeId, isDelete ) {

		if ( !confirm("코드를 삭제 하시겠습니까?") ) {
			return false;
		}

		$.ajax({
			url : contextPath + "/Code/delete",
			data : {
				codeId : codeId,
				isDelete : isDelete
			},
			type : "post",
			dataType : "json",
			success : function(data, status) {
				var res = data;
				alert(res.message);
				if ( res.status == 200 ) {
					window.location = contextPath + "/Code/list/1";
				}
			},
			error : function(data, status, e) {
				alert("코드 삭제 실패했습니다.");
			}
		});
		
	},

	isDeleteChild : function( codeId, isDelete ) {

		if ( !confirm("코드를 삭제 하시겠습니까?") ) {
			return false;
		}

		$.ajax({
			url : contextPath + "/Code/delete",
			data : {
				codeId : codeId,
				isDelete : isDelete
			},
			type : "post",
			dataType : "json",
			success : function(data, status) {
				var res = data;
				alert(res.message);
				if ( res.status == 200 ) {
					window.location = contextPath + "/Code/get/" + $("#uperCode").val();
				}
			},
			error : function(data, status, e) {
				alert("코드 삭제 실패했습니다.");
			}
		});
		
	}
};