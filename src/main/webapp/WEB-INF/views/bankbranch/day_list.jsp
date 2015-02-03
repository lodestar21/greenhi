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

		$(document).ready(function(){

			  $( "#cleanDate" ).datepicker({
			    dateFormat: 'yy-mm-dd'
			  });
			  
			  $( "#cleanDate" ).change(function() {
				  $('#searchFrom').submit();
				});
			  $( "#custCode" ).change(function() {
				  $('#searchFrom').submit();
				});
			  
			  $('input[type="checkbox"][name="checkAll"]').on('ifChecked', function(event){
				  $("[id*='branchNo_']:checkbox").each(function() {
					  $("#branchNo_" + $(this).val()).iCheck('check');
					});
			  });
			  $('input[type="checkbox"][name="checkAll"]').on('ifUnchecked', function(event){
				  $("[id*='branchNo_']:checkbox").each(function() {
					  $("#branchNo_" + $(this).val()).iCheck('uncheck');
					});
		      });
			  
		});
	
		function regist() {

			if ( $("#cleanDateP").val() == '' ){
				alert("작업일을 선택해주세요.");
				 $("#cleanDate").focus();
				return;
			}
			if ( $("#custCodeP").val() == '' || $("#custCodeP").val() == 0 ){
				alert("고객사를 선택해주세요.");
				 $("#custCode").focus();
				return;
			}

			if(confirm("작업일을 저장 하시겠습니까?")){
				
				$('#saveForm').ajaxForm({
					url :  contextPath + "/DayBankBranch/save"  ,
					type : "POST",
					dataType : "json",
					async : false,
					cache : false,
					success : function(data, status) {
						var res = data;
						alert(res.message);
						if ( res.status == 200 ) {
							$('#searchFrom').submit();
						}
					},
					error : function(data, status, e) {
						alert("작업일 등록에 실패했습니다.");
					}
				});
				
				$('#saveForm').submit();
				
			}
			
		}
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
                	<span style="font-weight: bold;">작업일 관리</span>
                    <small>작업일 등록 및 조회</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="${contextPath}/"><i class="fa fa-dashboard"></i>Home</a></li>
                    <li><a href="${contextPath}/DayBankBranch/list/1">작업일 관리</a></li>
                    <li class="active">작업일 관리 리스트</li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">
				
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
            				<form name="searchFrom" id="searchFrom" method="post" action="${contextPath}/DayBankBranch/list" id="search">
                            <div class="box-header" style="margin-top: 10px;margin-left: 25px;">
                            	<label>작업일</label>
                            	<input type="text" class="form-control input-sm" id="cleanDate" name="cleanDate" value="${search.cleanDate}" style="width:100px;display: table-caption;">
                            	<label>고객사</label>
								<select id="custCode" name="custCode" class="form-control input-sm" style="width:120px;display: table-caption;">
									<option value="0">선택</option>
									<c:forEach var="item" items="${custCodeList}" varStatus="status">
									<option value="${item.codeId}" <c:if test="${item.codeId == search.custCode}">selected</c:if>>${item.codeName}</option>
									</c:forEach>
								</select>
								<!-- <button type="submit" class="btn btn-primary btn-sm" title="조회">조회</button> -->
								<button type="button" class="btn btn-success btn-sm" onclick="javascript:regist();" style="margin-left: 10px;">저장</button>
                            </div><!-- /.box-header -->
                            </form>
                            <form name="saveForm" id="saveForm">
                            <div class="box-body">
                            	<input type="hidden" id="cleanDateP" name="cleanDateP" value="${search.cleanDate}" />
                            	<input type="hidden" id="custCodeP" name="custCodeP" value="${search.custCode}" />
                                <table class="table table-bordered">
                                    <tr>
                                        <th style="text-align: center;"><input type="checkbox" id="checkAll" name="checkAll" /></th>
                                        <th style="text-align: center;">No</th>
                                        <th style="text-align: center;">고객사명</th>
                                        <th style="text-align: center;">은행명</th>
                                        <th style="text-align: center;">담당자명</th>
                                        <th style="text-align: center;">등록일자</th>
                                    </tr>
									<c:forEach var="item" items="${list}" varStatus="status">
                                    <tr>
                                        <td style="text-align: center;"><input id='branchNo_${item.branchNo}'  type='checkbox' value='${item.branchNo}' name='branchNoP' <c:if test="${!empty(item.cleanDate) && item.cleanDate != '' }">checked="checked"</c:if>/></td>
                                        <td style="text-align: center;">${status.count}</td>
                                        <td style="text-align: center;">${item.custCodeName}</td>
                                        <td style="text-align: center;">${item.bankCodeName}</td>
                                        <td style="text-align: center;">${item.cleanUserName}</td>
                                        <td style="text-align: center;"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                    </tr>
									</c:forEach>
								</table>
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
