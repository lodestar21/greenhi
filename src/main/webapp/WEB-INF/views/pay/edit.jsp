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

		$(function() {
		  $( "#payDate" ).datepicker({
		    dateFormat: 'yy-mm-dd'
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
                	<span style="font-weight: bold;">지급 관리</span>
                    <small>지급 ${ editMode == 'update' ? '수정' : '등록' }</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="${contextPath}/"><i class="fa fa-dashboard"></i>Home</a></li>
                    <li><a href="${contextPath}/Pay/list/1">지급 관리</a></li>
                    <li class="active"><a href="${contextPath}/Pay/list/1">지급 관리 리스트</a></li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">
				
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                        	<form name="payFrm" id="payFrm" action="${contextPath}/Pay/${ editMode == 'update' ? 'updateProcess' : 'addProcess' }" method="post" >
							<input type="hidden" name="payNo" id="payNo" value="${pay.payNo}"/>
							<div class="box-body">
								<div class="form-group">
									<label for="payDate" style="width: 150px;">지급일</label>
									<input type="text" class="form-control input-sm" name="payDate" id="payDate" value="${pay.payDate}" placeholder="지급일 선택" style="display: table-caption;width:100px;">
								</div>
								<div class="form-group">
									<label style="width: 150px;">출금 은행</label>
									<select class="form-control input-sm" id="withdrawBank" name="withdrawBank" style="display: table-caption;width:150px;">
										<option value="0" <c:if test="${pay.withdrawBank == 0}">selected</c:if>>은행 선택</option>
										<c:forEach var="item" items="${bankCodeList}" varStatus="status">
										<option value="${item.codeId}" <c:if test="${item.codeId == pay.withdrawBank}">selected</c:if>>${item.codeName}</option>
										</c:forEach>
									</select>
									<label style="width: 150px;margin-left: 50px;">입금 은행</label>
									<select class="form-control input-sm" id="depositBank" name="depositBank" style="display: table-caption;width:150px;">
										<option value="0" <c:if test="${pay.depositBank == 0}">selected</c:if>>은행 선택</option>
										<c:forEach var="item" items="${bankCodeList}" varStatus="status">
										<option value="${item.codeId}" <c:if test="${item.codeId == pay.depositBank}">selected</c:if>>${item.codeName}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label style="width: 150px;">수급자</label>
									<input type="text" class="form-control input" name="reveiveName" id="reveiveName" value="${pay.reveiveName}" style="display: table-caption;width:150px;" readonly="readonly">
									<input type="hidden" id="reveiveNo" name="reveiveNo" value="${pay.reveiveNo}">
									<button class="btn btn-info btn-flat" type="button" onclick="javascript:dialog.iframe('수급자 선택', '/popup/reveiveUser')">선택</button>	
									<label style="width: 150px;margin-left: 50px;">수급액</label>
									<input type="text" class="form-control input-sm" name="receiveMoney" id="receiveMoney" value="${pay.receiveMoney}" placeholder="수급액 입력" style="display: table-caption;width:100px;">
								</div>
								<div class="form-group">
									<label for="remark" style="width: 150px;">특이사항</label>
									<input type="text" class="form-control input-sm" name="remark" id="remark" value="${pay.remark}" placeholder="특이사항 입력" style="display: table-caption;width:50%;">
								</div>
					         	<c:if test="${editMode == 'update'}">
								<div class="form-group">
									<label for="userId" style="width: 150px;">등록일자</label>
									<span style="color: blue;"><fmt:formatDate value="${pay.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
								</div>
								<div class="form-group">
									<label for="userId" style="width: 150px;">수정일자</label>
									<span style="color: blue;"><fmt:formatDate value="${pay.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
								</div>
					         	</c:if>
								
								<div class="box-footer" style="text-align: center;vertical-align: middle;">
					         		<c:if test="${editMode == 'update'}">
									<button type="button" class="btn btn-danger btn-sm" onclick="javascript:pay.modify('payFrm');">수정</button>
									<button type="button" class="btn btn-warning btn-sm" onclick="javascript:pay.isDelete(${pay.payNo}, 'Y');">삭제</button>
					         		</c:if>
					         		<c:if test="${editMode != 'update'}">
									<button type="button" class="btn btn-success btn-sm" onclick="javascript:pay.regist('payFrm');">등록</button>
					         		</c:if>
								  <button type="button" class="btn btn-default btn-sm" onclick="javascript:window.location.href='${contextPath}/Pay/list/1'">목록</button>
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
