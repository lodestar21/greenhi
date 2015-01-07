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
	
	regist : function(formName) {

		// 필수 항목 체크
		if( $("#userId").val() == "" ){
			alert("아이디를 입력해주세요.");
			$("#userId").focus();
			return false;
		}
		if( $("#userName").val() == "" ){
			alert("이름을 입력해주세요.");
			$("#userName").focus();
			return false;
		}
		if( $('#userType').val() == 102 && $('#custCode').val() == 0 ) {
			alert("고객사명을 선택해주세요.");
			$("#custCode").focus();
			return false;
		}
		if( $('#userType').val() == 103 && $('#localCode').val() == 0 ) {
			alert("지역을 선택해주세요.");
			$("#localCode").focus();
			return false;
		}
		if( $("#email").val() == "" ){
			alert("이메일을 입력해주세요.");
			$("#email").focus();
			return false;
		}
		if( $("#passWord").val() == "" ){
			alert("패스워드를 입력해주세요.");
			$("#passWord").focus();
			return false;
		}
		if( $("#passWordCfm").val() == "" ){
			alert("패스워드(확인)를 입력해주세요.");
			$("#passWordCfm").focus();
			return false;
		}
		if( $("#passWord").val() != $("#passWordCfm").val() ){
			alert("패스워드가 다릅니다. 다시 입력해주세요.");
			$("#passWord").focus();
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
					//window.location = contextPath + "/User/get/" + res.data;
					window.location = contextPath + "/User/list/1";
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
		if( $("#userName").val() == "" ){
			alert("이름을 입력해주세요.");
			$("#userName").focus();
			return false;
		}
		if( $('#userType').val() == 102 && $('#custCode').val() == 0 ) {
			alert("고객사명을 선택해주세요.");
			$("#custCode").focus();
			return false;
		}
		if( $('#userType').val() == 103 && $('#localCode').val() == 0 ) {
			alert("지역을 선택해주세요.");
			$("#localCode").focus();
			return false;
		}
		if( $("#email").val() == "" ){
			alert("이메일을 입력해주세요.");
			$("#email").focus();
			return false;
		}
		if( $("#passWord").val() != "" ){
			if( $("#passWord").val() != $("#passWordCfm").val() ){
				alert("패스워드가 다릅니다. 다시 입력해주세요.");
				$("#passWord").focus();
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
					//window.location = contextPath + "/User/get/" + res.data;
					window.location = contextPath + "/User/list/1";
				}
			},
			error : function(data, status, e) {
				alert("회원 수정에 실패했습니다.");
			}
		});
		$('#' + formName).submit();
	},

	mypageModify : function(formName) {

		// 필수 항목 체크
		if( $("#userName").val() == "" ){
			alert("이름을 입력해주세요.");
			$("#userName").focus();
			return false;
		}
		if( $('#userType').val() == 102 && $('#custCode').val() == 0 ) {
			alert("고객사명을 선택해주세요.");
			$("#custCode").focus();
			return false;
		}
		if( $('#userType').val() == 103 && $('#localCode').val() == 0 ) {
			alert("지역을 선택해주세요.");
			$("#localCode").focus();
			return false;
		}
		if( $("#email").val() == "" ){
			alert("이메일을 입력해주세요.");
			$("#email").focus();
			return false;
		}
		if( $("#passWord").val() != "" ){
			if( $("#passWord").val() != $("#passWordCfm").val() ){
				alert("패스워드가 다릅니다. 다시 입력해주세요.");
				$("#passWord").focus();
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
					window.location = contextPath + "/Mypage/get";
				}
			},
			error : function(data, status, e) {
				alert("회원 수정에 실패했습니다.");
			}
		});
		$('#' + formName).submit();
	}
};