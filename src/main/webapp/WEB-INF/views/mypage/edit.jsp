<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<html lang="ko">
<head>
	<c:import url="../import/mheader.jsp" />
	<script type="text/javascript">
	
	</script>
</head>
    <body class="skin-black">
		<c:import url="../import/cheader.jsp" />
		
	<div class="wrapper row-offcanvas row-offcanvas-left">
		<c:import url="../import/cleft.jsp" />

        <!-- Right side column. Contains the navbar and content of the page -->
        <aside class="right-side">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                	<span style="font-weight: bold;">마이페이지</span>
                    <small>회원 정보 수정</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="${contextPath}/"><i class="fa fa-dashboard"></i>Home</a></li>
                    <li><a href="${contextPath}/User/list/1">마이페이지</a></li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">
				
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                        	<form name="userFrm" id="userFrm" action="${contextPath}/Mypage/update" method="post" >
							<input type="hidden" name="userNo" id="userNo" value="${user.userNo}"/>
							<input type="hidden" name="userType" id="userType" value="${user.userType}"/>
							<input type="hidden" name="custCode" id="custCode" value="${user.custCode}"/>
							<input type="hidden" name="localCode" id="localCode" value="${user.localCode}"/>
							<input type="hidden" name="userStat" id="userStat" value="${user.userStat}"/>
							<input type="hidden" name="remark" id="remark" value="${user.remark}"/>
							<div class="box-body">
								<div class="form-group">
									<label for="userId" style="width: 150px;">Login ID</label>
									<span style="color: blue;">${user.userId}</span>
									<label for="userName" style="width: 150px;margin-left: 50px;">사용자 이름  </label>
									<input type="text" class="form-control input-sm" name="userName" id="userName" value="${user.userName}" placeholder="이름 입력" style="display: table-caption;width:150px;">
								</div>
								<div class="form-group">
									<label style="width: 150px;">사용자 권한</label>
									<c:choose>
										<c:when test="${user.userType == 101}">
											Administrator
										</c:when>
										<c:when test="${user.userType == 102}">
											은행 관리자 / ${user.custCodeName}
										</c:when>
										<c:when test="${user.userType == 103}">
											일반사용자 / ${user.localCodeName}
										</c:when>
									</c:choose>
								</div>
								<div class="form-group">
									<label for="email" style="width: 150px;">이메일</label>
									<input type="text" class="form-control input-sm" name="email" id="email" value="${user.email}" placeholder="이메일 입력" style="display: table-caption;width:150px;">
								</div>
								<div class="form-group">
									<label for="phoneNumber" style="width: 150px;">연락처  </label>
									<input type="text" class="form-control input-sm" name="phoneNumber" id="phoneNumber" value="${user.phoneNumber}" placeholder="연락처 입력" style="display: table-caption;width:150px;">
								</div>
								<div class="form-group">
									<label for="pswd" style="width: 150px;">비밀번호</label>
									<input type="password" class="form-control input-sm" name="passWord" id="passWord" placeholder="비밀번호 입력" style="display: table-caption;width:150px;">
									<label for="pswdCfm" style="width: 150px;margin-left: 50px;">비밀번호 확인</label>
									<input type="password" class="form-control input-sm" name="passWordCfm" id="passWordCfm" placeholder="비밀번호 확인 입력" style="display: table-caption;width:150px;">
								</div>
								<c:if test="${editMode == 'update'}">
								<div class="form-group has-error">
									<label class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> 비밀번호 두개 모두 입력 시 비밀번호 수정 됩니다.</label>
								</div>
								</c:if>
								<div class="form-group">
									<label for="depositBank" style="width: 150px;">입금 은행</label>
									<select class="form-control input-sm" id="depositBank" name="depositBank" style="display: table-caption;width:150px;">
										<c:forEach var="item" items="${depositBankList}" varStatus="status">
										<option value="${item.codeId}" <c:if test="${item.codeId == user.depositBank}">selected</c:if>>${item.codeName}</option>
										</c:forEach>
									</select>
									<label for="accountNum" style="width: 150px;margin-left: 50px;">계좌번호  </label>
									<input type="text" class="form-control input-sm" name="accountNum" id="accountNum" value="${user.accountNum}" placeholder="계좌번호 입력" style="display: table-caption;width:150px;">
								</div>
								
								<div class="box-footer" style="text-align: center;vertical-align: middle;">
									<button type="button" class="btn btn-danger btn-sm" onclick="javascript:user.mypageModify('userFrm');">회원 정보 수정</button>
								</div>
								
							</div>
							</form>
						</div>
					</div>
				</div>
		<c:import url="../import/cfooter.jsp" />
				
            </section><!-- /.content -->
        </aside><!-- /.right-side -->
    </div><!-- ./wrapper -->
		
		
	</body>
</html>
