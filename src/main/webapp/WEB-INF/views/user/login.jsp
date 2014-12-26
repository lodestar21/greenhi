<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<html lang="ko">
<head>
	<c:import url="../import/mheader.jsp" />
		<script type="text/javascript">
			var failureMessage = "${failMessage}";
			$(document).ready(function(){
				if(failureMessage){
					alert(failureMessage);
				}
			});
		</script>
</head>
    <body class="bg-black">

        <div class="form-box" id="login-box">
            <div class="header">Greenhi System Sign In</div>
			<form name="loginForm" id="loginForm" class="form-horizontal" action="${contextPath}/User/login" method="post">
                <div class="body bg-gray">
                    <div class="form-group">
                        <input type="text" name="userId" id="userId" class="form-control" placeholder="ID" value='<c:if test="${not empty cookie._ADMIN_SAVE_ID_.value && cookie._ADMIN_SAVE_ID_.value != ''}">${cookie._ADMIN_SAVE_ID_.value}</c:if>'/>
                    </div>
                    <div class="form-group">
                        <input type="password" name="passWord" id="passWord" class="form-control" placeholder="Password"/>
                    </div>          
                    <div class="form-group">
                        <input type="checkbox" name="saveId" id="saveId" value="Y" 
						<c:if test="${not empty cookie._ADMIN_SAVE_ID_.value && cookie._ADMIN_SAVE_ID_.value != ''}">checked="checked"</c:if>/> Remember me
                    </div>
                </div>
                <div class="footer">                                                               
                    <button type="submit" class="btn bg-olive btn-block">Sign me in</button>
                    <p><a href="#">I forgot my password</a></p>
                </div>
            </form>

        </div>
	</body>
</html>
