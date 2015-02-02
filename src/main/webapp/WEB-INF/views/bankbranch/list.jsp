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
                    <small>은행 지점 등록 및 조회</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="${contextPath}/"><i class="fa fa-dashboard"></i>Home</a></li>
                    <li><a href="${contextPath}/BankBranch/list/1">은행 지점 관리</a></li>
                    <li class="active">은행 지점 리스트</li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">
				
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
            				<form name="searchFrom" id="searchFrom" method="post" action="${contextPath}/BankBranch/list/1" id="search">
                            <div class="box-header" style="margin-top: 10px;margin-left: 25px;">
								<select id="custCode" name="custCode" class="form-control input-sm" style="width:100px;display: table-caption;">
									<option value="0">고객사명</option>
									<c:forEach var="item" items="${custCodeList}" varStatus="status">
									<option value="${item.codeId}" <c:if test="${item.codeId == search.custCode}">selected</c:if>>${item.codeName}</option>
									</c:forEach>
								</select>
								<select id="bankCode" name="bankCode" class="form-control input-sm" style="width:100px;display: table-caption;">
									<option value="0">은행명</option>
									<c:forEach var="item" items="${bankCodeList}" varStatus="status">
									<option value="${item.codeId}" <c:if test="${item.codeId == search.bankCode}">selected</c:if>>${item.codeName}</option>
									</c:forEach>
								</select>
								<select id="branchStateCode" name="branchStateCode" class="form-control input-sm" style="width:100px;display: table-caption;">
									<option value="0">형태</option>
									<c:forEach var="item" items="${branchStateList}" varStatus="status">
									<option value="${item.codeId}" <c:if test="${item.codeId == search.branchStateCode}">selected</c:if>>${item.codeName}</option>
									</c:forEach>
								</select>
								<select id="localCode" name="localCode" class="form-control input-sm" style="width:100px;display: table-caption;">
									<option value="0">지역</option>
									<c:forEach var="item" items="${localCodeList}" varStatus="status">
									<option value="${item.codeId}" <c:if test="${item.codeId == search.localCode}">selected</c:if>>${item.codeName}</option>
									</c:forEach>
								</select>
								<select id="searchField" name="searchField" class="form-control input-sm" style="width:100px;display: table-caption;">
									<option value="0">전체</option>
									<option value="1" <c:if test="${search.searchField == '1'}">selected</c:if>>주소</option>
									<option value="2" <c:if test="${search.searchField == '2'}">selected</c:if>>담당자</option>
								</select>
								<input type="text" class="form-control input-sm" id="searchWord" name="searchWord" value="${search.searchWord}" style="width:150px;display: table-caption;">
								<button type="submit" class="btn btn-primary btn-sm" title="조회">조회</button>
								<label><input type="checkbox" name="isEqualSearch" id="isEqualSearch" <c:if test="${isEqualSearch=='1'}">checked="checked"</c:if> value="1">&nbsp;Equal Serarch</label>
								<label><input type="checkbox" name="orderBy" id="orderBy" <c:if test="${search.orderBy=='ASC'}">checked="checked"</c:if> value="ASC">&nbsp;Order By</label>
								<input type="hidden" id="isFirst" name="isFirst" value="false">
								<a href="${contextPath }/BankBranch/get/0" style="margin-left: 10px"><button type="button" class="btn btn-success btn-sm">신규</button></a> 
                            </div><!-- /.box-header -->
                            </form>
                            <div class="box-body">
                                <table class="table table-bordered">
                                    <tr>
                                        <th style="text-align: center;">No</th>
                                        <th style="text-align: center;">고객사명</th>
                                        <th style="text-align: center;">은행명</th>
                                        <th style="text-align: center;">Site명</th>
                                        <th style="text-align: center;">형태</th>
                                        <th style="text-align: center;">지역</th>
                                        <th style="text-align: center;">상세주소</th>
                                        <th style="text-align: center;">청소담당자</th>
                                        <th style="text-align: center;">청구단가</th>
                                        <th style="text-align: center;">지급단가</th>
                                    </tr>
									<c:forEach var="item" items="${list}" varStatus="status">
                                    <tr>
                                        <td style="text-align: center;">${totalCount-((pageNum-1)*recPerPage+status.count-1)}</td>
                                        <td style="text-align: center;">${item.custCodeName}</td>
                                        <td style="text-align: center;">${item.bankCodeName}</td>
                                        <td style="text-align: center;"><a href="${contextPath }/BankBranch/get/${item.branchNo}">${item.siteName}</a></td>
                                        <td style="text-align: center;">${item.branchStateCodeName}</td>
                                        <td style="text-align: center;">${item.localCodeName}</td>
                                        <td style="text-align: center;">${item.branchAddress}</td>
                                        <td style="text-align: center;">${item.cleanUserName}</td>
                                        <td style="text-align: center;"><fmt:formatNumber value="${item.chargeMoney}" type="number"/></td>
                                        <td style="text-align: center;"><fmt:formatNumber value="${item.payMoney}" type="number"/></td>
                                    </tr>
									</c:forEach>
								</table>
							</div>
					 		<c:import url="../import/paging.jsp?pageNum=${pageNum}&totalCount=${totalCount}&pm=user.listPage" />
                        </div>
					</div>
				</div>
		<c:import url="../import/cfooter.jsp" />
				
            </section><!-- /.content -->
        </aside><!-- /.right-side -->
    </div><!-- ./wrapper -->
		
		
	</body>
</html>
