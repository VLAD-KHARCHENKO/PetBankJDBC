<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html lang="en">

<head>
    <title>Users</title>
    <c:import url="templ/head-part.jsp"/>

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <c:import url="templ/header-part.jsp"/>
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <c:import url="templ/topbar-part.jsp"/>

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->

                <div class="card shadow mb-4">

                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>
                                        <a href="users?page=${currentPage}&size=3&sort=first_name&direction=${direction}"
                                           class="search_link">
                                            <fmt:message key="first.name"/>
                                        </a></th>
                                    <th><a href="users?page=${currentPage}&size=3&sort=last_name&direction=${direction}"
                                           class="search_link">
                                        <fmt:message key="last.name"/>
                                    </a></th>
                                    <th><a href="users?page=${currentPage}&size=3&sort=email&direction=${direction}"
                                           class="search_link">
                                        <fmt:message key="email"/>
                                    </a></th>
                                    <th>
                                        <fmt:message key="condition"/>
                                    </th>
                                    <th>
                                        <fmt:message key="role"/>
                                    </th>
                                    <th>
                                        <fmt:message key="change"/>
                                    </th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${users}" var="user">
                                    <tr>
                                        <td>${user.id}</td>
                                        <td><a href="cards?userId=${user.id}" class="search_link">${user.firstName}</a>
                                        </td>
                                        <td>${user.lastName}</td>
                                        <td>${user.email}</td>
                                        <td>${user.active}</td>
                                        <td>${user.role}</td>
                                        <td>
                                            <a href="profile?id=${user.id}" class="btn btn-primary btn-lg "
                                               tabindex="-1" role="button" aria-disabled="true">
                                                <fmt:message key="change"/>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>

                        <!-- Pagination-->
                        <nav aria-label="Page navigation example">
                            <c:if test="${usersPages > 1}">
                                <ul class="pagination">
                                    <c:choose>
                                        <c:when test="${currentPage  != 0 }">
                                            <li class="page-item"><a
                                                    href="users?page=${currentPage-1}&size=3&sort=${sort}&direction=${currentDirection}"><span
                                                    class="page-link"><fmt:message key="prev"/></span></a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item disabled"><span class="page-link"><fmt:message
                                                    key="prev"/></span></li>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:forEach var="numberPage" begin="1" end="${usersPages}">
                                        <c:choose>
                                            <c:when test="${currentPage == (numberPage-1) }">
                                                <li class="page-item active"><a
                                                        href="users?page=${numberPage-1}&size=3&sort=${sort}&direction=${currentDirection}"
                                                        class="page-link">${numberPage}</a></li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item"><a
                                                        href="users?page=${numberPage-1}&size=3&sort=${sort}&direction=${currentDirection}"
                                                        class="page-link">${numberPage}</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${currentPage < (usersPages-1) }">
                                            <li class="page-item"><a
                                                    href="users?page=${currentPage+1}&size=3&sort=${sort}&direction=${currentDirection}"><span
                                                    class="page-link"><fmt:message key="next"/></span></a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item disabled"><span class="page-link"><fmt:message
                                                    key="next"/></span></li>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>
                            </c:if>
                        </nav>

                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <c:import url="templ/footer-part.jsp"/>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->


</div>

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<c:import url="templ/logout-part.jsp"/>
<c:import url="templ/loader-part.jsp"/>

</body>

        </html>