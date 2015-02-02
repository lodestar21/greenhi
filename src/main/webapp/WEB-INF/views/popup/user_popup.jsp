<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html lang="ko">
<head>
	<c:import url="../import/mheader.jsp" />
	<script type="text/javascript">

		$(document).ready(function(){
			$('input[name="userName"]').focus();
			
			$("#userName").keydown(function (key) {
                if (key.keyCode == 13) {
                	searchPopup();
                }
            });
		});
		
		function searchPopup(){
			/* 
			if( $('#userName').val() == '' ){
				alert('사용자명을 입력하세요.');
				$('input[name="userName"]').focus();
				return false;
			}
			 */
			$("#searchForm").submit();
		}
	
		function autoSelection( userName, userNo )  {

			$( "#cleanUserName", parent.document ).val( userName );
			$( "#cleanUserNo", parent.document ).val( userNo );
			$( ".ui-button-text", parent.document ).click();
			window.parent.searchForm();
		}
		
	</script>
</head>
<body>
                        <div class="box">
							<form action="${contextPath}/popup/cleanUser" method="post" id="searchForm">
							<div class="box-header" style="margin-top: 10px;margin-left: 25px;">
									<strong>사용자명</strong>
									<input type="text" size="20" name="userName" id="userName" value="${search.userName}" maxlength="20">
								<span class="btn btn_small"><a href="#" onclick="javascript:searchPopup();">조회</a></span>
							</div>
							</form>
                            <div class="box-body">
                                <table class="table table-bordered">
                                    <tr>
										<th style="width: 200px;text-align: center;">사용자명</th>
										<th style="width: 230px;text-align: center;">사용자ID</th>
                                    </tr>
			                        <c:forEach var="item" items="${result}" varStatus="status">
			                        <tr>
										<td style="width: 200px;text-align: center"><a href="javascript:autoSelection('${item.userName}','${item.userNo}')">${item.userName}</a></td>
										<td style="width: 230px;text-align: center">${item.userId}</td>
			                        </tr>
			                		</c:forEach>
			                		<c:if test="${resultCount == 0}">
			                		<tr>
			                			<td colspan="2" style="text-align: center">조회 결과가 없습니다.</td>
			                		</tr>
			                        </c:if>
								</table>
							</div>
            
						</div>
</body>
</html>