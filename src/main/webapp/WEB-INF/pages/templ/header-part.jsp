<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-paw"></i>
        </div>
        <div class="sidebar-brand-text mx-3">PETBANK</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item active">
        <a class="nav-link" href="index">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">




    <!-- Nav Item - Tables -->

    <c:choose>
        <c:when test="${user.role=='CUSTOMER'}">
            <li class="nav-item">
                <a class="nav-link" href="cards">
                    <i class="fas fa-fw fa-table"></i>
                    <span>Cards</span></a>

            </li>

            <li class="nav-item">
                <a class="nav-link" href="statements">
                    <i class="fas fa-fw fa-table"></i>
                    <span>Statements</span></a>
            </li>
            <!-- Nav Item - Charts -->
            <li class="nav-item">
                <a class="nav-link" href="payments">
                    <i class="fas fa-fw fa-chart-area"></i>
                    <span>Payments</span></a>
            </li>
        </c:when>
        <c:when test="${user.role=='ADMIN'}">
            <li class="nav-item">
                <a class="nav-link" href="users">
                    <i class="fas fa-users"></i>
                    <span>Users</span></a>
            </li>
        </c:when>
        <c:otherwise>
            <a href=""></a>
        </c:otherwise>
    </c:choose>
    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

    <!-- Sidebar Message -->


</ul>
<!-- End of Sidebar -->
