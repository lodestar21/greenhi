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
                	<span style="font-weight: bold;">회원 관리</span>
                    <small>회원 리스트</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="${contextPath}/"><i class="fa fa-dashboard"></i>Home</a></li>
                    <li><a href="${contextPath}/User/list/1">회원 관리</a></li>
                    <li class="active">회원 리스트</li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">
				
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
            				<form name="searchFrom" id="searchFrom" method="post" action="${contextPath}/User/list/1" id="search">
                            <div class="box-header" style="margin-top: 10px;margin-left: 25px;">
								<select id="userType" name="userType" class="form-control input-sm" style="width:100px;display: table-caption;">
									<option value="0">유형</option>
									<c:forEach var="item" items="${userTypeList}" varStatus="status">
									<option value="${item.codeId}" <c:if test="${item.codeId == search.userType}">selected</c:if>>${item.codeName}</option>
									</c:forEach>
								</select>
								<select id="userStat" name="userStat" class="form-control input-sm" style="width:100px;display: table-caption;">
									<option value="0">상태</option>
									<c:forEach var="item" items="${userStatList}" varStatus="status">
									<option value="${item.codeId}" <c:if test="${item.codeId == search.userStat}">selected</c:if>>${item.codeName}</option>
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
									<option value="1" <c:if test="${search.searchField == '1'}">selected</c:if>>이름</option>
									<option value="2" <c:if test="${search.searchField == '2'}">selected</c:if>>아이디</option>
									<option value="3" <c:if test="${search.searchField == '3'}">selected</c:if>>이메일</option>
								</select>
								<input type="text" class="form-control input-sm" id="searchWord" name="searchWord" value="${search.searchWord}" style="width:150px;display: table-caption;">
								<button type="submit" class="btn btn-primary btn-sm" title="조회">조회</button>
								<label><input type="checkbox" name="isEqualSearch" id="isEqualSearch" <c:if test="${isEqualSearch=='1'}">checked="checked"</c:if> value="1">&nbsp;Equal Serarch</label>
								<label><input type="checkbox" name="orderBy" id="orderBy" <c:if test="${search.orderBy=='ASC'}">checked="checked"</c:if> value="ASC">&nbsp;Order By</label>
								<input type="hidden" id="isFirst" name="isFirst" value="false">
								<a href="${contextPath }/User/get/0" style="margin-left: 10px"><button type="button" class="btn btn-success btn-sm">신규</button></a> 
                            </div><!-- /.box-header -->
                            </form>
                            <div class="box-body">
                                <table class="table table-bordered">
                                    <tr>
                                        <th style="text-align: center;">No</th>
                                        <th style="text-align: center;">아이디</th>
                                        <th style="text-align: center;">이름</th>
                                        <th style="text-align: center;">연락처</th>
                                        <th style="text-align: center;">이메일</th>
                                        <th style="text-align: center;">유형</th>
                                        <th style="text-align: center;">상태</th>
                                        <th style="text-align: center;">입금은행</th>
                                        <th style="text-align: center;">계좌번호</th>
                                        <th style="text-align: center;">등록일시</th>
                                    </tr>
									<c:forEach var="item" items="${list}" varStatus="status">
                                    <tr>
                                        <td style="text-align: center;">${totalCount-((pageNum-1)*recPerPage+status.count-1)}</td>
                                        <td style="text-align: center;">${item.userId}</td>
                                        <td style="text-align: center;"><a href="${contextPath }/User/get/${item.userNo}">${item.userName}</a></td>
                                        <td style="text-align: center;">${item.phoneNumber}</td>
                                        <td style="text-align: center;">${item.email}</td>
                                        <td style="text-align: center;">${item.userTypeName}</td>
                                        <td style="text-align: center;">${item.userStatName}</td>
                                        <td style="text-align: center;">${item.depositBankName}</td>
                                        <td style="text-align: center;">${item.accountNum}</td>
                                        <td style="text-align: center;"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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
