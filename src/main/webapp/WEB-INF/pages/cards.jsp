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

                <!-- Page Heading
                <h1 class="h3 mb-4 text-gray-800">Card Page</h1>-->

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
                                <td><a href="statements?accountId=${card.account.id}&page=0&size=3&sort=id"
                                       class="search_link">${card.cardName}</a>
                                </td>
                                <td>${card.number}</td>
                                <td>${card.account.balance}</td>
                                <td>${card.cardCondition}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${card.cardCondition == 'ACTIVE'}">

                                            <form action="cards" method="post">
                                                <input id="cardId" name="cardId" type="hidden"
                                                       value="${card.id}"/>
                                                <input name="command" type="hidden"
                                                       value="blocked"/>
                                                <c:choose>
                                                    <c:when test="${user.role == 'ADMIN'}">
                                                        <input id="userId" name="userId" type="hidden"
                                                               value="${card.user.id}"/>
                                                        <input name="command" type="hidden"
                                                               value="blocked"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input id="userId" name="userId" type="hidden"
                                                               value="${user.id}"/>
                                                        <input name="command" type="hidden"
                                                               value="blocked"/>
                                                    </c:otherwise>
                                                </c:choose>
                                                <button class="btn btn-danger" type="submit">
                                                    <fmt:message key="blocked.card"/>
                                                </button>
                                            </form>


                                        </c:when>

                                        <c:when test="${card.cardCondition == 'PENDING'}">
                                            <c:if test="${user.role == 'ADMIN'}">

                                                <form action="cards" method="post">
                                                    <input id="cardId" name="cardId" type="hidden"
                                                           value="${card.id}"/>
                                                    <input id="userId" name="userId" type="hidden"
                                                           value="${card.user.id}"/>
                                                    <input name="command" type="hidden"
                                                           value="activate"/>
                                                    <button class="btn btn-success" type="submit">
                                                        <fmt:message key="activate"/>
                                                    </button>
                                                </form>
                                            </c:if>
                                        </c:when>


                                        <c:when test="${card.cardCondition == 'BLOCKED'}">
                                            <c:if test="${user.role == 'CUSTOMER'}">

                                                <form action="cards" method="post">
                                                    <input id="cardId" name="cardId" type="hidden"
                                                           value="${card.id}"/>
                                                    <input id="userId" name="userId" type="hidden"
                                                           value="${user.id}"/>
                                                    <input name="command" type="hidden"
                                                           value="pending"/>
                                                    <button class="btn btn-warning" type="submit">
                                                        <fmt:message key="activating.request"/>
                                                    </button>
                                                </form>
                                            </c:if>
                                            <c:if test="${user.role == 'ADMIN'}">

                                                <form action="cards" method="post">
                                                    <input id="cardId" name="cardId" type="hidden"
                                                           value="${card.id}"/>
                                                    <input id="userId" name="userId" type="hidden"
                                                           value="${card.user.id}"/>
                                                    <input name="command" type="hidden"
                                                           value="activate"/>
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


                <div class="mb-4"></div>


            </div>
            <c:if test="${user.role == 'CUSTOMER'}">

                <form class="row g-3" action="cards" method="post">
                    <div class="col-auto">
                        <div class="input-group">
                            <span class="input-group-text"><fmt:message key="create.new"/></span>
                            <select name="cardName" class="form-control">
                                <c:forEach items="${cardName}" var="value">
                                    <option value="${value}">${value}</option>
                                </c:forEach>
                            </select>
                            <errors path="cardName"/>
                        </div>
                    </div>
                    <div class="col-auto">
                        <input name="command" type="hidden"
                               value="newCard"/>
                        <button type="submit" class="btn btn-primary mb-3">
                            <fmt:message key="card"/>
                            <i class="fas fa-fw  fa-credit-card"></i>
                        </button>
                    </div>
                </form>

                <form class="row g-3" action="cards" method="post">
                    <div class="col-auto">
                        <div class="input-group mb-3">
                            <span class="input-group-text"><fmt:message key="top.up.account"/></span>
                            <select name="cardId" class="form-control">
                                <c:forEach items="${activeCards}" var="activeCard">
                                    <option value="${activeCard.id}">${activeCard.cardName} - ${activeCard.number} -
                                        ${activeCard.account.balance}$
                                    </option>

                                </c:forEach>
                            </select>

                            <input name="amount" class="form-control"
                                   aria-label="Dollar amount (with dot and two decimal places)"/>
                            <span class="input-group-text">.00</span>
                            <span class="input-group-text">$</span>
                        </div>
                    </div>
                    <div class="col-auto">
                        <input name="command" type="hidden"
                               value="refillCard"/>
                        <button type="submit" class="btn btn-primary mb-3">
                            <fmt:message key="card"/>
                            <i class="fas fa-fw  fa-credit-card"></i>
                        </button>
                    </div>

                </form>
            </c:if>


        </div>
    </div>
</div>

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