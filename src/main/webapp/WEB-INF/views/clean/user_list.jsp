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
		});

		function regist() {

			if ( $("#cleanDate").val() == '' ){
				alert("작업일을 선택해주세요.");
				 $("#cleanDate").focus();
				return;
			}
			$('#searchFrom').submit();			
		}

		function search() {
	
			if ( $("#cleanDate").val() == '' ){
				alert("작업일을 선택해주세요.");
				 $("#cleanDate").focus();
				return;
			}
			window.location = contextPath + "/UserCleanInfo/cleanList/1";
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
                	<span style="font-weight: bold;">청소 데이터 등록</span>
                    <small>청소 데이터 등록 및 조회</small>
                </h1>
            </section>

            <!-- Main content -->
            <section class="content">
				
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
            				<form name="searchFrom" id="searchFrom" method="post" action="${contextPath}/UserCleanInfo/userList" id="search">
                            <div class="box-header" style="margin-top: 10px;">
                            	<label style="margin: 5px;">작업일</label>
                            	<input type="text" class="form-control input-sm" id="cleanDate" name="cleanDate" value="${search.cleanDate}" style="width:100px;display: table-caption;">
								<!-- <button type="submit" class="btn btn-primary btn-sm" title="조회">조회</button> -->
								<button type="button" class="btn btn-success btn-sm" onclick="javascript:search();" style="margin-left: 10px;">청소 진행 현황 조회</button>
                            </div><!-- /.box-header -->
                            </form>
                        </div>
					</div>
				</div>
                   <div class="row">
				<c:forEach var="item" items="${list}" varStatus="status">
					<c:if test="${item.cleanNo > 0}">
                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-green">
                                <div class="inner">
                                    <h4>
                                        <span style="color: #585858;font-weight: bold;">${item.siteName}</span>
                                    </h4>
                                    <p>${item.custCodeName} / ${item.bankCodeName} / ${item.localCodeName}</p>
                                    <p>작업일 : <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
                                </div>
                                <div class="icon">
                                    <i class="ion ion-stats-bars"></i>
                                </div>
                                <a href="javascript:clean.get(${item.cleanDate}, ${item.branchNo}, ${item.cleanNo});" class="small-box-footer">
                                    작업 수정 <i class="fa fa-arrow-circle-right"></i>
                                </a>
                            </div>
                        </div><!-- ./col -->
					</c:if>
					<c:if test="${item.cleanNo < 1}">
                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-aqua">
                                <div class="inner">
                                    <h4>
                                        <span style="color: #585858;font-weight: bold;">${item.siteName}</span>
                                    </h4>
                                    <p>${item.custCodeName} / ${item.bankCodeName} / ${item.localCodeName}</p>
                                    <p>작업일 : 없음</p>
                                </div>
                                <div class="icon">
                                    <i class="ion ion-stats-bars"></i>
                                </div>
                                <a href="javascript:clean.get('${item.cleanDate}', ${item.branchNo}, ${item.cleanNo});" class="small-box-footer">
                                    작업 등록 <i class="fa fa-arrow-circle-right"></i>
                                </a>
                            </div>
                        </div><!-- ./col -->
					</c:if>
				</c:forEach>
					</div><!-- /.row -->
					<form name="getForm" id="getForm" action="${contextPath}/UserCleanInfo/get" id="searchGet">
                           	<input type="hidden" id="cleanDateParam" name="cleanDateParam" value="" />
                           	<input type="hidden" id="branchNoParam" name=branchNoParam value="" />
                           	<input type="hidden" id="cleanNoParam" name=cleanNoParam value="" />
                     </form>
		<c:import url="../import/cfooter.jsp" />
				
            </section><!-- /.content -->
        </aside><!-- /.right-side -->
    </div><!-- ./wrapper -->
		
		
	</body>
</html>
