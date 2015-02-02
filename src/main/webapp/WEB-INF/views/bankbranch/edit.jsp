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
                	<span style="font-weight: bold;">은행 지점 관리</span>
                    <small>은행 지점 ${ editMode == 'update' ? '수정' : '등록' }</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="${contextPath}/"><i class="fa fa-dashboard"></i>Home</a></li>
                    <li><a href="${contextPath}/BankBranch/list/1">은행 지점 관리</a></li>
                    <li class="active"><a href="${contextPath}/BankBranch/list/1">은행 지점 리스트</a></li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">
				
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                        	<form name="bankBranchFrm" id="bankBranchFrm" action="${contextPath}/BankBranch/${ editMode == 'update' ? 'updateProcess' : 'addProcess' }" method="post" >
							<input type="hidden" name="branchNo" id="branchNo" value="${bankbranch.branchNo}"/>
							<div class="box-body">
								<div class="form-group">
									<label style="width: 150px;">고객사 명</label>
									<select class="form-control input-sm" id="custCode" name="custCode" style="display: table-caption;width:150px;">
										<option value="0" <c:if test="${bankbranch.custCode == 0}">selected</c:if>>고객사 선택</option>
										<c:forEach var="item" items="${custCodeList}" varStatus="status">
										<option value="${item.codeId}" <c:if test="${item.codeId == bankbranch.custCode}">selected</c:if>>${item.codeName}</option>
										</c:forEach>
									</select>
									<label style="width: 150px;margin-left: 50px;">은행명</label>
									<select class="form-control input-sm" id="bankCode" name="bankCode" style="display: table-caption;width:150px;">
										<option value="0" <c:if test="${bankbranch.bankCode == 0}">selected</c:if>>은행 선택</option>
										<c:forEach var="item" items="${bankCodeList}" varStatus="status">
										<option value="${item.codeId}" <c:if test="${item.codeId == bankbranch.bankCode}">selected</c:if>>${item.codeName}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label style="width: 150px;">형태</label>
									<select class="form-control input-sm" id="branchStateCode" name="branchStateCode" style="display: table-caption;width:150px;">
										<option value="0" <c:if test="${bankbranch.branchStateCode == 0}">selected</c:if>>형태 선택</option>
										<c:forEach var="item" items="${branchStateList}" varStatus="status">
										<option value="${item.codeId}" <c:if test="${item.codeId == bankbranch.branchStateCode}">selected</c:if>>${item.codeName}</option>
										</c:forEach>
									</select>
									<label style="width: 150px;margin-left: 50px;">지역</label>
									<select class="form-control input-sm" id="localCode" name="localCode" style="display: table-caption;width:150px;">
										<option value="0" <c:if test="${bankbranch.localCode == 0}">selected</c:if>>지역 선택</option>
										<c:forEach var="item" items="${localCodeList}" varStatus="status">
										<option value="${item.codeId}" <c:if test="${item.codeId == bankbranch.localCode}">selected</c:if>>${item.codeName}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label for="siteName" style="width: 150px;">Site 명</label>
									<input type="text" class="form-control input-sm" name="siteName" id="siteName" value="${bankbranch.siteName}" placeholder="사이트명 입력" style="display: table-caption;width:150px;">
									<label style="width: 150px;margin-left: 50px;">청소 담당자</label>
									<input type="text" class="form-control input" name="cleanUserName" id="cleanUserName" value="${bankbranch.cleanUserName}" style="display: table-caption;width:150px;" readonly="readonly">
									<input type="hidden" id="cleanUserNo" name="cleanUserNo" value="${bankbranch.cleanUserNo}">
									<button class="btn btn-info btn-flat" type="button" onclick="javascript:dialog.iframe('청소 담당자 선택', '/popup/cleanUser')">선택</button>									
								</div>
								<div class="form-group">
									<label for="branchAddress" style="width: 150px;">주소</label>
									<input type="text" class="form-control input-sm" name="branchAddress" id="branchAddress" value="${bankbranch.branchAddress}" placeholder="주소 입력" style="display: table-caption;width:50%;">
								</div>
								<div class="form-group">
									<label for="chargeMoney" style="width: 150px;">청구 단가</label>
									<input type="text" class="form-control input-sm" name="chargeMoney" id="chargeMoney" value="${bankbranch.chargeMoney}" placeholder="청구 단가 입력" style="display: table-caption;width:100px;">
									<label style="width: 150px;margin-left: 50px;">지급 단가</label>
									<input type="text" class="form-control input-sm" name="payMoney" id="payMoney" value="${bankbranch.payMoney}" placeholder="지급 단가 입력" style="display: table-caption;width:100px;">
								</div>
								<div class="form-group">
									<label for="remark" style="width: 150px;">특이사항</label>
									<input type="text" class="form-control input-sm" name="remark" id="remark" value="${bankbranch.remark}" placeholder="특이사항 입력" style="display: table-caption;width:50%;">
								</div>
					         	<c:if test="${editMode == 'update'}">
								<div class="form-group">
									<label for="userId" style="width: 150px;">등록일자</label>
									<span style="color: blue;"><fmt:formatDate value="${bankbranch.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
								</div>
								<div class="form-group">
									<label for="userId" style="width: 150px;">수정일자</label>
									<span style="color: blue;"><fmt:formatDate value="${bankbranch.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
								</div>
					         	</c:if>
								
								<div class="box-footer" style="text-align: center;vertical-align: middle;">
					         		<c:if test="${editMode == 'update'}">
									<button type="button" class="btn btn-danger btn-sm" onclick="javascript:bankBranch.modify('bankBranchFrm');">수정</button>
									<button type="button" class="btn btn-warning btn-sm" onclick="javascript:bankBranch.isDelete(${bankbranch.branchNo}, 'Y');">삭제</button>
					         		</c:if>
					         		<c:if test="${editMode != 'update'}">
									<button type="button" class="btn btn-success btn-sm" onclick="javascript:bankBranch.regist('bankBranchFrm');">등록</button>
					         		</c:if>
								  <button type="button" class="btn btn-default btn-sm" onclick="javascript:window.location.href='${contextPath}/BankBranch/list/1'">목록</button>
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
