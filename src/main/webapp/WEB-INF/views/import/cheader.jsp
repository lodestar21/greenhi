<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

    <!-- header logo: style can be found in header.less -->
    <header class="header">
        <a href="${contextPath}/" class="logo">
            <!-- Add the class icon to your logo image or logo icon to add the margining -->
            Greenhi system
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <div class="navbar-right">
                <ul class="nav navbar-nav">
                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="glyphicon glyphicon-user"></i>
                            <span>${sessionScope._USER_INFO_.userId} <i class="caret"></i></span>
                        </a>
                        <ul class="dropdown-menu" style="width: 200px;min-width: 200px">
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                    <div class="pull-left">
                                        <a href="#" class="btn btn-default btn-flat">정보 수정</a>
                                    </div>
                                <div class="pull-right">
                                    <a href="#" onclick="javascript:user.logout();" class="btn btn-default btn-flat">Log out</a>
                                </div>
                            </li>
                        </ul>
					</li>
                </ul>
            </div>
        </nav>
    </header>