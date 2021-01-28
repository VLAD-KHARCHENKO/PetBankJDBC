<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html lang="en">

<head>
    <title>Cards</title>
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
                <h1 class="h3 mb-4 text-gray-800">Card Page</h1>

            </div>
            <!-- /.container-fluid -->
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Card name</th>
                            <th>Number</th>
                            <th>isActive</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${cards}" var="card">
                            <tr>
                                <td>${card.id}</td>
                                <td>${card.cardName}</td>
                                <td>${card.number}</td>
                                <td>${user.active}</td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- End of Main Content -->
</div>
    <!-- Footer -->
    <c:import url="templ/footer-part.jsp"/>
    <!-- End of Footer -->


<!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<c:import url="templ/logout-part.jsp"/>
<c:import url="templ/loader-part.jsp"/>

</body>

</html>