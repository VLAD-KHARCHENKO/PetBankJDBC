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
                            <th>
                                <fmt:message key="card.name"/>
                            </th>
                            <th>
                                <fmt:message key="number"/>
                            </th>
                            <th>
                                <fmt:message key="balance"/>
                            </th>
                            <th>
                                <fmt:message key="condition"/>
                            </th>
                            <th>
                                <fmt:message key="change"/>
                            </th>

                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${cards}" var="card">
                            <tr>
                                <td><a href="statements?accountId=${card.account.id}" class="search_link">${card.cardName}</a></td>
                                <td>${card.number}</td>
                                <td>${card.account.balance}</td>
                                <td>${card.cardCondition}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${card.cardCondition == 'ACTIVE'}">
                                            <c:url var="blockedUrl" value="/cards/blocked"/>
                                            <form id="${cardFormId}" action="${blockedUrl}" method="post">
                                                <input id="cardId" name="cardId" type="hidden"
                                                       value="${card.id}"/>
                                                <c:choose>
                                                    <c:when test="${user.role == 'ADMIN'}">
                                                        <input id="userId" name="userId" type="hidden"
                                                               value="${card.user.id}"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input id="userId" name="userId" type="hidden"
                                                               value="${user.id}"/>
                                                    </c:otherwise>
                                                </c:choose>
                                                <button class="btn btn-danger" type="submit">
                                                    <fmt:message key="blocked.card"/>
                                                </button>
                                            </form>


                                        </c:when>

                                        <c:when test="${card.cardCondition == 'PENDING'}">
                                            <c:if test="${user.role=='ADMIN'}">
                                                <c:url var="activatedUrl" value="/cards/activated"/>
                                                <form id="${cardFormId}" action="${activatedUrl}" method="post">
                                                    <input id="cardId" name="cardId" type="hidden"
                                                           value="${card.id}"/>
                                                    <input id="userId" name="userId" type="hidden"
                                                           value="${card.user.id}"/>
                                                    <button class="btn btn-success" type="submit">
                                                        <fmt:message key="activate"/>
                                                    </button>
                                                </form>
                                            </c:if>
                                        </c:when>
                                        <c:when test="${card.cardCondition == 'BLOCKED'}">
                                            <c:if test="${user.role=='CUSTOMER'}">
                                                <c:url var="pendingUrl" value="/cards/pending"/>
                                                <form id="${cardFormId}" action="${pendingUrl}" method="post">
                                                    <input id="cardId" name="cardId" type="hidden"
                                                           value="${card.id}"/>
                                                    <input id="userId" name="userId" type="hidden"
                                                           value="${user.id}"/>
                                                    <button class="btn btn-warning" type="submit">
                                                        <fmt:message key="activating.request"/>
                                                    </button>
                                                </form>
                                            </c:if>
                                            <c:if test="${user.role=='ADMIN'}">
                                                <c:url var="activatedUrl" value="/cards/activated"/>
                                                <form id="${cardFormId}" action="${activatedUrl}" method="post">
                                                    <input id="cardId" name="cardId" type="hidden"
                                                           value="${card.id}"/>
                                                    <input id="userId" name="userId" type="hidden"
                                                           value="${card.user.id}"/>
                                                    <button class="btn btn-success" type="submit">
                                                        <fmt:message key="activate"/>
                                                    </button>
                                                </form>
                                            </c:if>
                                        </c:when>



                                    </c:choose>

                                </a>
                            </td>
                            </tr>
                        </c:forEach>


                        </tbody>
                    </table>

            </div>
            <form:form class="row g-3" modelAttribute="cardForm" action="/cards/new" method="post">
                <div class="col-auto">
                    <div class="input-group">
                        <span class="input-group-text"><fmt:message key="top.up.account"/></span>
                        <form:select path="cardName" class="form-control">
                            <c:forEach items="${cardName}" var="value">
                                <form:option value="${value}">${value}</form:option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="cardName"/>
                    </div>
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-primary mb-3">
                        <fmt:message key="card"/>
                        <i class="fas fa-fw  fa-credit-card"></i>
                    </button>
                </div>
            </form:form>


            <form:form class="row g-3" modelAttribute="replenishmentForm" action="cards" method="post">
                <div class="col-auto">
                    <div class="input-group">
                        <span class="input-group-text"><fmt:message key="create.new"/></span>
                        <form:select path="cardNumber" class="form-control">
                            <c:forEach items="${activeCards}" var="activeCard">
                                <form:option value="${activeCard.number}">${activeCard.cardName} - ${activeCard.number} -
                                    ${activeCard.account.balance}$
                                </form:option>

                            </c:forEach>
                        </form:select>
                        <form:errors path="cardNumber"/>

                        <form:input path="amount" class="form-control"
                                    aria-label="Dollar amount (with dot and two decimal places)"/>
                        <span class="input-group-text">$</span>
                        <span class="input-group-text">0.00</span>
                        <form:errors path="amount"/>

                    </div>
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-primary mb-3">
                        <fmt:message key="card"/>
                        <i class="fas fa-fw  fa-credit-card"></i>
                    </button>
                </div>
            </form:form>
        </div>
    </div>
</div>

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