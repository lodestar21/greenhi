/************************************************************************************
 * 파일이름 : user.js
 * 파일설명 : 회원가입, 로그인, 로그아웃, 탈퇴등 회원과 관련된 기능을 모아놓은 파일
 * 파일작성 : 이원 
 ***********************************************************************************/
var user = {
	goMain : function(){
		window.location = contextPath + "/main";
	},
		
	goLogin : function(){
		window.location = contextPath + "/User/login";
	},
	
	logout : function() {
		$.ajax({
			url: contextPath + "/User/logout",
			type: "post",
			dataType: "json",
			async: true,
			cache: false,
			success: function(data, textStatus) {
				if ( data != null ) {
					alert( data.message );
				}
				user.goLogin();
			}
		});
	},
	
	listPage : function(getParameter){
		var url = contextPath + "/User/list/" + getParameter;
		$("#searchFrom").attr("action",url);
		$("#searchFrom").submit();
	},
	
	historyListPage : function(getParameter){
		var url = contextPath + "/User/LoginHistory/list/" + getParameter;
		$("#searchFrom").attr("action",url);
		$("#searchFrom").submit();
	},
	
	regist : function(formName) {

		// 필수 항목 체크
		if( $("#userNm").val() == "" ){
			alert("이름을 입력해주세요.");
			$("#userNm").focus();
			return false;
		}
		if( $("#emal").val() == "" ){
			alert("이메일을 입력해주세요.");
			$("#emal").focus();
			return false;
		}
		if( $("#pswd").val() == "" ){
			alert("패스워드를 입력해주세요.");
			$("#pswd").focus();
			return false;
		}
		if( $("#pswdCfm").val() == "" ){
			alert("패스워드(확인)를 입력해주세요.");
			$("#pswdCfm").focus();
			return false;
		}
		if( $("#pswd").val() != $("#pswdCfm").val() ){
			alert("패스워드가 다릅니다. 다시 입력해주세요.");
			$("#pswd").focus();
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
					window.location = contextPath + "/User/get/" + res.data;
				}
			},
			error : function(data, status, e) {
				alert("회원 등록에 실패했습니다.");
			}
		});
		$('#' + formName).submit();
	},

	modify : function(formName) {

		// 필수 항목 체크
		if( $("#userNm").val() == "" ){
			alert("이름을 입력해주세요.");
			$("#userNm").focus();
			return false;
		}
		if( $("#emal").val() == "" ){
			alert("이메일을 입력해주세요.");
			$("#emal").focus();
			return false;
		}
		if( $("#pswd").val() != "" ){
			if( $("#pswd").val() != $("#pswdCfm").val() ){
				alert("패스워드가 다릅니다. 다시 입력해주세요.");
				$("#pswd").focus();
				return false;
			}
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
					window.location = contextPath + "/User/get/" + $("#userNo").val();
				}
			},
			error : function(data, status, e) {
				alert("회원 수정에 실패했습니다.");
			}
		});
		$('#' + formName).submit();
	}
};