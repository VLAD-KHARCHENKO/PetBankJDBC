<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html lang="en">

<head>
    <title>Pending cards</title>
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

                <!-- Page Heading
                <h1 class="h3 mb-4 text-gray-800">User account Page</h1>-->

            </div>
            <!-- /.container-fluid -->
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>
                                <fmt:message key="number"/>
                            </th>
                            <th>
                                <fmt:message key="condition"/>
                            </th>
                            <th>
                                <fmt:message key="first.name"/>
                            </th>
                            <th>
                                <fmt:message key="last.name"/>
                            </th>

                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${pendingCards}" var="pendingCard">
                            <tr>
                                <td>${pendingCard.id}</td>
                                <td>${pendingCard.number}</td>
                                <td>${pendingCard.user.firstName}</td>
                                <td>${pendingCard.user.lastName}</td>

                                <td>
                                    <c:url var="activatedUrl" value="/pending-cards/activated"/>
                                    <form id="${cardFormId}" action="${activatedUrl}" method="post">
                                        <input id="cardId" name="cardId" type="hidden"
                                               value="${pendingCard.id}"/>
                                        <button class="btn btn-success" type="submit">
                                            <fmt:message key="activate"/>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <c:import url="templ/footer-part.jsp"/>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
</div>

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<c:import url="templ/logout-part.jsp"/>
<c:import url="templ/loader-part.jsp"/>

</body>

        </html>