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
                	<span style="font-weight: bold;">기준 정보 관리</span>
                    <small>기준 정보 관리 ${ editMode == 'update' ? '수정' : '등록' }</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="${contextPath}/"><i class="fa fa-dashboard"></i>Home</a></li>
                    <li><a href="${contextPath}/Code/list/1">기준 정보 관리 관리</a></li>
                    <li class="active"><a href="${contextPath}/Code/list/1">기준 정보 관리 리스트</a></li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">
				
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                        	<form name="codeFrm" id="codeFrm" action="${contextPath}/Code/${ editMode == 'update' ? 'updateProcess' : 'addProcess' }" method="post" >
							<div class="box-body">
								<c:if test="${editMode != 'update'}">
								<div class="form-group">
									<label for="codeId" style="width: 150px;">코드 ID</label>
									<input type="text" class="form-control input-sm" name="codeId" id="codeId" value="${code.codeId}" placeholder="코드 입력" style="display: table-caption;width:100px;">
									<span style="color: red">※ 100단위로 입력해주세요.</span>
								</div>
								</c:if>
								<c:if test="${editMode == 'update'}">
								<div class="form-group">
									<label for="codeId" style="width: 150px;">코드 ID</label>
									<span style="font-weight: bold;">
										<input type="hidden" name="codeId" id="codeId" value="${code.codeId}"/>
										${code.codeId}
									</span>
								</div>
								</c:if>
								<div class="form-group">
									<label for="codeName" style="width: 150px;">코드 이름</label>
									<input type="text" class="form-control input-sm" name="codeName" id="codeName" value="${code.codeName}" placeholder="코드 이름 입력" style="display: table-caption;width:150px;">
								</div>
								<div class="form-group">
									<label for="sortOrdr" style="width: 150px;">정렬 순서</label>
									<input type="text" class="form-control input-sm" name="sortOrdr" id="sortOrdr" value="${code.sortOrdr}" placeholder="정렬 순서 입력" style="display: table-caption;width:50px;">
								</div>
								<div class="form-group">
									<label for="remark" style="width: 150px;">비고</label>
									<input type="text" class="form-control input-sm" name="remark" id="remark" value="${code.remark}" placeholder="비고 입력" style="display: table-caption;width:50%;">
								</div>
								<div class="form-group">
									<label style="width: 150px;">사용 여부</label>
									<select class="form-control input-sm" id="isUse" name="isUse" style="display: table-caption;width:100px;">
										<option value="N" <c:if test="${empty(code.isUse) || code.isUse == 'N'}">selected</c:if>>미사용</option>
										<option value="Y" <c:if test="${code.isUse == 'Y'}">selected</c:if>>사용</option>
									</select>
								</div>
								
								<div class="box-footer" style="text-align: center;vertical-align: middle;">
					         		<c:if test="${editMode == 'update'}">
									<button type="button" class="btn btn-danger btn-sm" onclick="javascript:code.modify('codeFrm');">코드 정보 수정</button>
									<button type="button" class="btn btn-warning btn-sm" onclick="javascript:code.isDelete(${code.codeId}, 'Y');">코드 정보 삭제</button>
					         		</c:if>
					         		<c:if test="${editMode != 'update'}">
									<button type="button" class="btn btn-success btn-sm" onclick="javascript:code.regist('codeFrm');">코드 등록</button>
					         		</c:if>
								  <button type="button" class="btn btn-default btn-sm" onclick="javascript:window.location.href='${contextPath}/Code/list/1'">목록</button>
								</div>
								
							</div>
							</form>
						</div>
						<c:if test="${editMode == 'update'}">
						<div class="box">
							<div class="box-header" style="margin-top: 10px;margin-left: 25px;">
								<h3 class="box-title">코드 상세 리스트 - <b>${code.codeName}</b></h3>
								<a href="${contextPath }/Code/get/${code.codeId}/0" style="margin-left: 10px"><button type="button" class="btn btn-success btn-sm">상세 코드 신규</button></a>
							</div><!-- /.box-header -->
							<div class="box-body">
								<table class="table table-bordered">
									<tr>
										<th style="text-align: center;">No</th>
										<th style="text-align: center;">코드ID</th>
										<th style="text-align: center;">코드명</th>
										<th style="text-align: center;">정렬순서</th>
										<th style="text-align: center;">사용여부</th>
										<th style="text-align: center;">비고</th>
									</tr>
									<c:forEach var="item" items="${listChildCode}" varStatus="status">
									<tr>
										<td style="text-align: center;">${status.count}</td>
										<td style="text-align: center;">${item.codeId}</td>
										<td style="text-align: center;"><a href="${contextPath }/Code/get/${code.codeId}/${item.codeId}">${item.codeName}</a></td>
										<td style="text-align: center;">${item.sortOrdr}</td>
										<td style="text-align: center;">${item.isUse == 'Y' ? '사용' : '미사용'}</td>
										<td style="text-align: center;">${item.remark}</td>
									</tr>
									</c:forEach>
								</table>
							</div>
						</div>
						</c:if>
					</div>
				</div>
		<c:import url="../import/cfooter.jsp" />
				
            </section><!-- /.content -->
        </aside><!-- /.right-side -->
    </div><!-- ./wrapper -->
		
		
	</body>
</html>
