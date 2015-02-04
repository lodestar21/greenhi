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
		});

		function search() {
	
			$('#searchFrom').submit();			
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
                    <small>청소 진행 현황 조회</small>
                </h1>
            </section>
			
            <!-- Main content -->
            <section class="content">
				
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
            				<form name="searchFrom" id="searchFrom" method="post" action="${contextPath}/UserCleanInfo/cleanList/1" id="search">
                            <div class="box-header" style="margin-top: 10px;margin-left: 25px;">
                            	<label style="margin: 5px;">작업일</label>
                            	<input type="text" class="form-control input-sm" id="cleanDate" name="cleanDate" value="${search.cleanDate}" style="width:100px;display: table-caption;">
								<select id="isClean" name="isClean" class="form-control input-sm" style="width:100px;display: table-caption;">
									<option value="">완료여부</option>
									<option value="Y" <c:if test="${search.isClean == 'Y'}">selected</c:if>>완료</option>
									<option value="N" <c:if test="${search.isClean == 'N'}">selected</c:if>>미완료</option>
								</select>
								<button type="button" class="btn btn-primary btn-sm" onclick="javascript:search();" style="margin-left: 10px;">조회</button>
                            </div><!-- /.box-header -->
                            </form>
                            <div class="box-body">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th rowspan=2 style="text-align:center;vertical-align: middle;">No</th>
                                        <th rowspan=2 style="text-align:center;vertical-align: middle;">작업일</th>
                                        <th rowspan=2 style="text-align:center;vertical-align: middle;">은행명</th>
                                        <th rowspan=2 style="text-align:center;vertical-align: middle;">지역</th>
                                        <th rowspan=2 style="text-align:center;vertical-align: middle;">Site명</th>
                                        <th rowspan=2 style="text-align:center;vertical-align: middle;">완료여부</th>
                                        <th colspan=5 style="text-align:center;vertical-align: middle;">시설물 체크</th>
                                        <th rowspan=2 style="text-align:center;vertical-align: middle;">등록 일시</th>
                                    </tr>
                                    <tr>
                                        <th style="text-align:center;">천정조명</th>
                                        <th style="text-align:center;">기기조명</th>
                                        <th style="text-align:center;">대기선</th>
                                        <th style="text-align:center;">인터폰</th>
                                        <th style="text-align:center;">냉난방</th>
                                    </tr>
                                    </thead>
                                    <tbody>
									<c:forEach var="item" items="${list}" varStatus="status">
                                    <tr>
                                        <td style="text-align: center;">${totalCount-((pageNum-1)*recPerPage+status.count-1)}</td>
                                        <td style="text-align: center;">${item.cleanDate}</td>
                                        <td style="text-align: center;">${item.bankCodeName}</td>
                                        <td style="text-align: center;">${item.localCodeName}</td>
                                        <td style="text-align: center;"><a href="javascript:clean.get('${item.cleanDate}', ${item.branchNo}, ${item.cleanNo});">${item.siteName}</a></td>
                                        <td style="text-align: center;">
                                        	<c:if test="${item.cleanNo>0}"><span class="label label-success">완료</span></c:if>
                                        	<c:if test="${item.cleanNo<1}"><span class="label label-danger">미완료</span></c:if>
                                        </td>
                                        <td style="text-align: center;"><span class="label label-success">${item.stateCodeName1}</span></td>
                                        <td style="text-align: center;"><span class="label label-warning">${item.stateCodeName2}</span></td>
                                        <td style="text-align: center;"><span class="label label-danger">${item.stateCodeName3}</span></td>
                                        <td style="text-align: center;"><span class="label label-success">${item.stateCodeName4}</span></td>
                                        <td style="text-align: center;"><span class="label label-success">${item.stateCodeName5}</span></td>
                                        <td style="text-align: center;"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                    </tr>
									</c:forEach>
                                    </tbody>
								</table>
							</div>
					 		<c:import url="../import/paging.jsp?pageNum=${pageNum}&totalCount=${totalCount}&pm=clean.listPage" />
                        </div>
					</div>
					<form name="getForm" id="getForm" action="${contextPath}/UserCleanInfo/get" id="searchGet">
                           	<input type="hidden" id="cleanDateParam" name="cleanDateParam" value="" />
                           	<input type="hidden" id="branchNoParam" name=branchNoParam value="" />
                           	<input type="hidden" id="cleanNoParam" name=cleanNoParam value="" />
                     </form>
				</div>
		<c:import url="../import/cfooter.jsp" />
				
            </section><!-- /.content -->
        </aside><!-- /.right-side -->
    </div><!-- ./wrapper -->
		
		
	</body>
</html>
