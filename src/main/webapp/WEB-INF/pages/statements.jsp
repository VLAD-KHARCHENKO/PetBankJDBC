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
            <div class="container-fluid">
                <div class="range range-sm-center">
                    <h5>
                        <c:if test="${not empty notification}">
                            <c:out value="${notification}"/>
                        </c:if>
                    </h5>
                </div>

                <!-- Page Heading -->
                <h1 class="h3 mb-4 text-gray-800">
                    <fmt:message key="card.small"/>
                    :
                    ${card.cardName}
                    <fmt:message key="number.small"/>
                    : ${card.number}
                    <fmt:message key="balance"/>
                    : ${card.account.balance}
                    <fmt:message key="condition"/>
                    : ${card.cardCondition}
                </h1>


                <div class="card shadow mb-4">

                    <a href="#collapseCardExample" class="d-block card-header py-3 collapsed" data-toggle="collapse"
                       role="button" aria-expanded="false" aria-controls="collapseCardExample">
                        <h6 class="m-0 font-weight-bold text-primary">
                            <fmt:message key="saved.payments"/>
                        </h6>
                    </a>

                    <div class="collapse" id="collapseCardExample">
                        <div class="card-body">

                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>
                                            <fmt:message key="date"/>
                                        </th>
                                        <th>
                                            <fmt:message key="credit.number"/>
                                        </th>
                                        <th>
                                            <fmt:message key="debit.number"/>
                                        </th>
                                        <th>
                                            <fmt:message key="amount"/>
                                        </th>
                                        <th>
                                            <fmt:message key="description"/>
                                        </th>
                                        <th>
                                            <fmt:message key="pay"/>
                                        </th>
                                        <th>
                                            <fmt:message key="delete"/>
                                        </th>

                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach items="${savedPayments}" var="savedPayment">
                                        <tr>
                                            <td>${savedPayment.id}</td>
                                            <td>${savedPayment.date}</td>
                                            <td>${savedPayment.credit.number}</td>
                                            <td>${savedPayment.debit.number}</td>
                                            <td>${savedPayment.amount}</td>
                                            <td>${savedPayment.description}</td>

                                            <td>
                                                <c:choose>
                                                    <c:when test="${ card.cardCondition == 'ACTIVE'}">
                                                        <div class="d-grid gap-2 col-10 mx-auto">

                                                            <form action="statements" method="post">
                                                                <input id="payId" name="payId" type="hidden"
                                                                       value="${savedPayment.id}"/>
                                                                <input id="cardId" name="cardId" type="hidden"
                                                                       value="${card.id}"/>
                                                                <input name="command" type="hidden"
                                                                       value="pay"/>
                                                                <button class="btn btn-success" type="submit">
                                                                    <fmt:message key="pay"/>
                                                                </button>
                                                            </form>
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise>

                                                    </c:otherwise>
                                                </c:choose>

                                            </td>
                                            <td>
                                                <div class="d-grid gap-2 col-10 mx-auto ">

                                                    <form action="statements" method="post">
                                                        <input name="payId" type="hidden"
                                                               value="${savedPayment.id}"/>
                                                        <input name="cardId" type="hidden"
                                                               value="${card.id}"/>
                                                        <input name="command" type="hidden"
                                                               value="delete"/>
                                                        <button class="btn btn-warning" type="submit">
                                                            <fmt:message key="delete"/>
                                                        </button>
                                                    </form>

                                                </div>

                                            </td>


                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card shadow mb-4">

                    <a href="#collapseCardExample1" class="d-block card-header py-3" data-toggle="collapse"
                       role="button" aria-expanded="true" aria-controls="collapseCardExample1">
                        <h6 class="m-0 font-weight-bold text-primary">
                            <fmt:message key="paid.payments"/>
                        </h6>
                    </a>

                    <div class="collapse show" id="collapseCardExample1">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable1 " width="100%" cellspacing="0">
                                    <thead>
                                    <tr>
                                        <th>
                                            <a href="statements?accountId=${card.id}&page=${currentPage}&size=3&sort=id&direction=${direction}"
                                               class="search_link">ID</a></th>
                                        <th>
                                            <a href="statements?accountId=${card.id}&page=${currentPage}&size=3&sort=date&direction=${direction}"
                                               class="search_link">
                                                <fmt:message key="date"/>
                                            </a>
                                        </th>
                                        <th>
                                            <fmt:message key="credit.number"/>
                                        </th>
                                        <th>
                                            <fmt:message key="debit.number"/>
                                        </th>
                                        <th>
                                            <fmt:message key="amount"/>
                                        </th>
                                        <th>
                                            <fmt:message key="description"/>
                                        </th>


                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach items="${paidPayments}" var="paidPayment">
                                        <c:choose>
                                            <c:when test="${paidPayment.debit.number == card.account.number}">
                                                <tr class="table-success">
                                                    <td>${paidPayment.id}</td>
                                                    <td>${paidPayment.date}</td>
                                                    <td>${paidPayment.credit.number}</td>
                                                    <td></td>
                                                    <td>${paidPayment.amount}</td>
                                                    <td>${paidPayment.description}</td>

                                                </tr>
                                            </c:when>
                                            <c:otherwise>
                                                <tr class="table-danger">
                                                    <td>${paidPayment.id}</td>
                                                    <td>${paidPayment.date}</td>
                                                    <td></td>
                                                    <td>${paidPayment.debit.number}</td>
                                                    <td>${paidPayment.amount}</td>
                                                    <td>${paidPayment.description}</td>

                                                </tr>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                            <!-- Pagination-->
                            <nav aria-label="Page navigation example">
                                <c:if test="${paidPaymentsPages > 1}">
                                    <ul class="pagination">
                                        <c:choose>
                                            <c:when test="${currentPage  != 0 }">
                                                <li class="page-item"><a
                                                        href="statements?accountId=${card.id}&page=${currentPage-1}&size=3&sort=${sort}&direction=${currentDirection}"><span
                                                        class="page-link"><fmt:message key="prev"/></span></a></li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item disabled"><span class="page-link"><spring:message
                                                        code="prev"/></span></li>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:forEach var="numberPage" begin="1" end="${paidPaymentsPages}">
                                            <c:choose>
                                                <c:when test="${currentPage == (numberPage-1) }">
                                                    <li class="page-item active"><a
                                                            href="statements?accountId=${card.id}&page=${numberPage-1}&size=3&sort=${sort}&direction=${currentDirection}"
                                                            class="page-link">${numberPage}</a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="page-item"><a
                                                            href="statements?accountId=${card.id}&page=${numberPage-1}&size=3&sort=${sort}&direction=${currentDirection}"
                                                            class="page-link">${numberPage}</a></li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <c:choose>
                                            <c:when test="${currentPage < (paidPaymentsPages-1) }">
                                                <li class="page-item"><a
                                                        href="statements?accountId=${card.id}&page=${currentPage+1}&size=3&sort=${sort}&direction=${currentDirection}"><span
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


            </div>


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