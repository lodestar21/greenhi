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
                	<span style="font-weight: bold;">기준 정보 관리</span>
                    <small>기준 정보 등록 및 조회</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="${contextPath}/"><i class="fa fa-dashboard"></i>Home</a></li>
                    <li><a href="${contextPath}/Code/list/1">기준 정보 관리</a></li>
                    <li class="active">기준 정보 등록 및 조회</li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">
				
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
            				<form name="searchFrom" id="searchFrom" method="post" action="${contextPath}/Code/list/1" id="search">
                            <div class="box-header" style="margin-top: 10px;margin-left: 25px;">
								<select id="isUse" name="isUse" class="form-control input-sm" style="width:100px;display: table-caption;">
									<option value="">사용여부</option>
									<option value="Y" <c:if test="${search.isUse == 'Y'}">selected</c:if>>사용</option>
									<option value="N" <c:if test="${search.isUse == 'N'}">selected</c:if>>미사용</option>
								</select>
								<select id="searchField" name="searchField" class="form-control input-sm" style="width:100px;display: table-caption;">
									<option value="0">전체</option>
									<option value="1" <c:if test="${search.searchField == '1'}">selected</c:if>>코드명</option>
									<option value="2" <c:if test="${search.searchField == '2'}">selected</c:if>>코드ID</option>
								</select>
								<input type="text" class="form-control input-sm" id="searchWord" name="searchWord" value="${search.searchWord}" style="width:150px;display: table-caption;">
								<button type="submit" class="btn btn-primary btn-sm" title="조회">조회</button>
								<label><input type="checkbox" name="isEqualSearch" id="isEqualSearch" <c:if test="${isEqualSearch=='1'}">checked="checked"</c:if> value="1">&nbsp;Equal Serarch</label>
								<label><input type="checkbox" name="orderBy" id="orderBy" <c:if test="${search.orderBy=='ASC'}">checked="checked"</c:if> value="ASC">&nbsp;Order By</label>
								<input type="hidden" id="isFirst" name="isFirst" value="false">
								<a href="${contextPath }/Code/get/0" style="margin-left: 10px"><button type="button" class="btn btn-success btn-sm">신규</button></a> 
                            </div><!-- /.box-header -->
                            </form>
                            <div class="box-body">
                                <table class="table table-bordered">
                                    <tr>
                                        <th style="text-align: center;">No</th>
                                        <th style="text-align: center;">코드ID</th>
                                        <th style="text-align: center;">코드명</th>
                                        <th style="text-align: center;">사용여부</th>
                                        <th style="text-align: center;">등록일시</th>
                                    </tr>
									<c:forEach var="item" items="${list}" varStatus="status">
                                    <tr>
                                        <td style="text-align: center;">${totalCount-((pageNum-1)*recPerPage+status.count-1)}</td>
                                        <td style="text-align: center;">${item.codeId}</td>
                                        <td style="text-align: center;"><a href="${contextPath }/Code/get/${item.codeId}">${item.codeName}</a></td>
                                        <td style="text-align: center;">${item.isUse == 'Y' ? '사용' : '미사용'}</td>
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
