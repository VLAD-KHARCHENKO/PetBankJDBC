<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html lang="en">

<head>
     <title>Payments</title>
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

                <div class="range range-sm-center danger" > <h5>
                    <c:if test="${not empty notification}">
                        <div class ="text-danger"><c:out value ="${notification}" /></div>
                    </c:if>
                </h5></div>

                <div class="container-fluid">
                    <form class="row g-3" action="payments" method="post">
                        <div class="row">

                            <div class="col-xl-4 col-md-6 mb-4">
                                <div class="card border-left-primary shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xl font-weight-bold text-primary text-uppercase mb-2">
                                                    <fmt:message key="from.the.card"/>
                                                </div>

                                                <select name="cardId"  class="form-control mb-3">

                                                    <c:forEach items="${cards}" var="card">
                                                        <option value="${card.id}">${card.cardName} - ${card.number} -
                                                            ${card.account.balance}$
                                                        </option>

                                                    </c:forEach>
                                                </select>
                                                <div class="input-group">
                                                    <input name="amount" class="form-control"
                                                                aria-label="Dollar amount (with dot and two decimal places)"/>
                                                    <span class="input-group-text">$</span>
                                                    <span class="input-group-text">0.00</span>

                                                </div>
                                                <div class="mb-3">
                                                    <label for="exampleFormControlTextarea1"
                                                           class="form-label"><fmt:message key="description"/></label>
                                                    <textarea name="description" class="form-control" id="exampleFormControlTextarea1"
                                                                   rows="3"></textarea>

                                                </div>

                                            </div>

                                        </div>
                                    </div>
                                </div>

                            </div>

                            <!-- Earnings (Annual) Card Example -->
                            <div class="col-xl-4 col-md-6 mb-4">
                                <div class="card border-left-success shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xl font-weight-bold text-success text-uppercase mb-2">
                                                    <fmt:message key="recipient.card"/>
                                                </div>

                                                <div class="input-group">
                                                    <input name="debit" type="text" class="form-control"
                                                                aria-label="Dollar amount (with dot and two decimal places)"/>
                                                    <span class="input-group-text"> <fmt:message key="card.number"/></span>

                                                </div>
                                                <br/>
                                                <h5 class="card-title">Special title treatment</h5>
                                                <p class="card-text">With supporting text below as a natural lead-in to
                                                    additional content.</p>
                                                <div class="d-grid gap-2 col-6 mx-auto">
                                                    <input id="userId" name="userId" type="hidden"
                                                           value="${user.id}"/>
                                                    <button class="btn btn-success" type="submit"> <fmt:message key="submit"/></button>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </form>
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
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <c:import url="templ/logout-part.jsp"/>
    <c:import url="templ/loader-part.jsp"/>

</body>

</html>