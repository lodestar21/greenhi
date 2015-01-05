<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<jsp:useBean id="now" class="java.util.Date" />
<script type="text/javascript">
	$(document).ready(function() {
		
	});
</script>

        <!-- Left side column. contains the logo and sidebar -->
        <aside class="left-side sidebar-offcanvas">
            <!-- sidebar: style can be found in sidebar.less -->
            <section class="sidebar">
                <!-- Sidebar user panel -->
                <div class="user-panel">
                    <div class="pull-left image">
                        <img src="${contextPath}/resources/images/avartar.png" class="img-circle" alt="User Image" />
                    </div>
                    <div class="pull-left info">
                        <p>${sessionScope._USER_INFO_.userName} - 최고 관리자</p>
						<i class="fa fa-circle text-success"></i> 가입일 : <fmt:formatDate value="${sessionScope._USER_INFO_.createTime}" pattern="yyyy-MM-dd" />
                    </div>
                </div>
                <!-- sidebar menu: : style can be found in sidebar.less -->
                <ul class="sidebar-menu">
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-laptop"></i>
                            <span>Admin</span>
                        </a>
                        <ul class="treeview">
                            <li><a href="${contextPath}/User/list/1"><i class="fa fa-user"></i><span style="margin-left:10px;">회원 관리</span></a></li>
                            <li><a href="${contextPath}/User/list/1"><i class="fa fa-credit-card" ></i><span style="margin-left:10px;">은행 지점 관리</span></a></li>
                            <li><a href="${contextPath}/User/list/1"><i class="fa fa-arrow-right"></i><span style="margin-left:10px;">청소 진행 현황</span></a></li>
                            <li><a href="${contextPath}/User/list/1"><i class="fa fa-krw"></i><span style="margin-left:10px;">지급 관리</span></a></li>
                             <li><a href="${contextPath}/Code/list/1"><i class="fa fa-cogs"></i><span style="margin-left:10px;">기준 정보 관리</span></a></li>
                       </ul>
                    </li>
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-edit"></i> <span>은행별 환경 관리</span>
                          </a>
                        <ul class="treeview">
                           <li><a href="${contextPath}/User/list/1"><i class="fa fa-dashboard"></i><span style="margin-left:10px;">청소 진행 현황</span></a></li>
                        </ul>
                    </li>
                     <li>
                        <a href="${contextPath}/User/list/1">
                          <i class="fa fa-camera"></i> <span>청소 데이터 등록</span>
                        </a>
                    </li>
                </ul>
            </section>
            <!-- /.sidebar -->
        </aside>
