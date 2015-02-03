/************************************************************************************
 * 파일이름 : clean.js
 * 파일설명 : 청소와 관련된 기능을 모아놓은 파일
 * 파일작성 : 이원 
 ***********************************************************************************/
var clean = {
		
	get : function( cleanDate, branchNo, cleanNo ) {

		$("#cleanDateParam").val(cleanDate);
		$("#branchNoParam").val(branchNo);
		$("#cleanNoParam").val(cleanNo);
		$("#getForm").submit();
		
	},
	
	imgUpload : function(id, viewImgUrl) {
		
		var reg = new RegExp('Upload', 'gi');
		var hiddenId = id.replace(reg, '');
		var previewId = hiddenId + "Preview";
		var hiddenIdInfo = hiddenId + "Info";

		var fileName = $("#" + id).val();

		var extName = fileName.substr(fileName.length - 3, 3).toLowerCase();

		if(extName != "jpg") {
			alert("EXIF 추출이 가능한 JPG만 등록 가능합니다.");
			return false;
		}
		
		var url = "../Upload/" + id;
		
		$.ajaxFileUpload({
			url : url,
			secureuri : true,
			fileElementId : id,
			dataType : 'JSON',
			success : function(data, status) {
				var regExp1 = new RegExp('<pre>', 'gi');
				var regExp2 = new RegExp('</pre>', 'gi');

				data = data.replace(regExp1, '');
				data = data.replace(regExp2, '');

				var res = eval("(" + data + ")");

				if(res.result == "1") {
					var imgSrc = viewImgUrl + res.imgSaveName;
					$("#" + hiddenId).val(res.imgSaveName);
					$("#" + hiddenIdInfo).val(res.tagDesc);
					$("#" + previewId).attr("src", imgSrc);
					alert(res.message);
				} else {
					alert(res.message);
				}
			},
			error : function(data, status, e) {
				alert("파일업로드를 실패했습니다.");
			}
		});

	},

	delImg : function(id) {
		var preview = id + "Preview";
		var hiddenIdInfo = id + "Info";
		$("#" + id).val('');
		$("#" + hiddenIdInfo).val('');
		$("#" + preview).attr("src", contextPath + "/resources/images/avartar.png");
		$("#" + id + "Upload").val('');
	},
	
	regist : function(formName) {

		$('#' + formName).ajaxForm({
			type : "POST",
			async : false,
			cache : false,
			dataType : "json",
			success : function(data, status) {
				var res = data;
				alert(res.message);
				if ( res.status == 200 ) {
					window.location = contextPath + "/UserCleanInfo/userList";
				}
			},
			error : function(data, status, e) {
				alert("청소 데이터 등록에 실패했습니다.");
			}
		});
		$('#' + formName).submit();
	},

	modify : function(formName) {
		
		$('#' + formName).ajaxForm({
			type : "POST",
			async : false,
			cache : false,
			dataType : "json",
			success : function(data, status) {
				var res = data;
				alert(res.message);
				if ( res.status == 200 ) {
					window.location = contextPath + "/UserCleanInfo/userList";
				}
			},
			error : function(data, status, e) {
				alert("청소 데이터 수정에 실패했습니다.");
			}
		});
		$('#' + formName).submit();
	},

	isDelete : function( cleanNo, isDelete ) {

		if ( !confirm("청소 데이터를 삭제 하시겠습니까?") ) {
			return false;
		}

		$.ajax({
			url : contextPath + "/BankBranch/delete",
			data : {
				cleanNo : cleanNo,
				isDelete : isDelete
			},
			type : "post",
			dataType : "json",
			success : function(data, status) {
				var res = data;
				alert(res.message);
				if ( res.status == 200 ) {
					window.location = contextPath + "/UserCleanInfo/userList";
				}
			},
			error : function(data, status, e) {
				alert("청소 데이터 삭제 실패했습니다.");
			}
		});
		
	}
};