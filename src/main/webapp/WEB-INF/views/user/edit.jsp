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

		$(document).ready(function(){
			
			if( $('#userType').val() == 102 ) {
				$('#custName').show();
				$('#localCode').hide();
			} else if( $('#userType').val() == 103 ) {
				$('#custName').hide();
				$('#localCode').show();
			} else {
				$('#custName').hide();
				$('#localCode').hide();
			}
			
			$('#userType').change(function() {
				if( $('#userType').val() == 102 ) {
					$('#custName').show();
					$('#localCode').hide();
				} else if( $('#userType').val() == 103 ) {
					$('#custName').hide();
					$('#localCode').show();
				} else {
					$('#custName').hide();
					$('#localCode').hide();
				}
				
			});
		});
	
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
                	<span style="font-weight: bold;">회원 관리</span>
                    <small>회원 ${ editMode == 'update' ? '수정' : '등록' }</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="${contextPath}/"><i class="fa fa-dashboard"></i>Home</a></li>
                    <li><a href="${contextPath}/User/list/1">회원 관리</a></li>
                    <li class="active"><a href="${contextPath}/User/list/1">회원 리스트</a></li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">
				
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                        	<form name="userFrm" id="userFrm" action="${contextPath}/User/${ editMode == 'update' ? 'updateProcess' : 'addProcess' }" method="post" >
							<input type="hidden" name="userNo" id="userNo" value="${user.userNo}"/>
							<div class="box-body">
								<div class="form-group">
									<label for="userId" style="width: 150px;">Login ID</label>
					         		<c:if test="${editMode == 'update'}">
									<span style="color: blue;">${user.userId}</span>
					         		</c:if>
					         		<c:if test="${editMode != 'update'}">
									<input type="text" class="form-control input-sm" name="userId" id="userId" value="${user.userId}" placeholder="ID 입력" style="display: table-caption;width:150px;">
					         		</c:if>
									<label for="userName" style="width: 150px;margin-left: 50px;">사용자 이름  </label>
									<input type="text" class="form-control input-sm" name="userName" id="userName" value="${user.userName}" placeholder="이름 입력" style="display: table-caption;width:150px;">
								</div>
								<div class="form-group">
									<label style="width: 150px;">사용자 권한</label>
									<select class="form-control input-sm" id="userType" name="userType" style="display: table-caption;width:150px;">
										<c:forEach var="item" items="${userTypeList}" varStatus="status">
										<option value="${item.codeId}" <c:if test="${item.codeId == user.userType}">selected</c:if>>${item.codeName}</option>
										</c:forEach>
									</select>
									<input type="text" class="form-control input-sm" name="custName" id="custName" value="${user.custName}" placeholder="고객사명 입력" style="display: table-caption;width:150px;">
									<select class="form-control input-sm" id="localCode" name="localCode" style="display: table-caption;width:150px;">
										<c:forEach var="item" items="${localCodeList}" varStatus="status">
										<option value="${item.codeId}" <c:if test="${item.codeId == user.localCode}">selected</c:if>>${item.codeName}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label for="email" style="width: 150px;">이메일</label>
									<input type="text" class="form-control input-sm" name="email" id="email" value="${user.email}" placeholder="이메일 입력" style="display: table-caption;width:150px;">
									<label style="width: 150px;margin-left: 50px;">회원 상태</label>
									<select class="form-control input-sm" id="userStat" name="userStat" style="display: table-caption;width:150px;">
										<c:forEach var="item" items="${userStatList}" varStatus="status">
										<option value="${item.codeId}" <c:if test="${item.codeId == user.userStat}">selected</c:if>>${item.codeName}</option>
										</c:forEach>
									</select>
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
								<div class="form-group">
									<label for="remark" style="width: 150px;">특이사항</label>
									<input type="text" class="form-control input-sm" name="remark" id="remark" value="${user.remark}" placeholder="특이사항 입력" style="display: table-caption;width:50%;">
								</div>
								
								<div class="box-footer" style="text-align: center;vertical-align: middle;">
					         		<c:if test="${editMode == 'update'}">
									<button type="button" class="btn btn-danger btn-sm" onclick="javascript:user.modify('userFrm');">회원 정보 수정</button>
					         		</c:if>
					         		<c:if test="${editMode != 'update'}">
									<button type="button" class="btn btn-success btn-sm" onclick="javascript:user.regist('userFrm');">회원 등록</button>
					         		</c:if>
								  <button type="button" class="btn btn-default btn-sm" onclick="javascript:window.location.href='${contextPath}/User/list/1'">목록</button>
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
