<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html lang="en">

<head>
    <title>Profile</title>
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
                <div class="container">

                    <div class="card o-hidden border-0 shadow-lg my-5">
                        <div class="card-body p-0">
                            <!-- Nested Row within Card Body -->
                            <div class="row">
                                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                                <div class="col-lg-7">
                                    <div class="p-5">
                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-4">Change account!</h1>
                                        </div>
                                        <form class="user" action="profile" method="post">
                                            <div class="form-group row">
                                                <div class="col-sm-6 mb-3 mb-sm-0">
                                                    <label for="exampleFirstName" class="form-label-outside">
                                                        <fmt:message key="first.name"/>
                                                    </label>
                                                    <input type="text" name="firstName" value="${userProfile.firstName}"
                                                           class="form-control form-control-user" id="exampleFirstName"
                                                           placeholder="First Name">
                                                </div>
                                                <div class="col-sm-6">
                                                    <label for="exampleLastName" class="form-label-outside">
                                                        <fmt:message key="last.name"/>
                                                    </label>
                                                    <input type="text" name="lastName" value="${userProfile.lastName}"
                                                           class="form-control form-control-user" id="exampleLastName"
                                                           placeholder="Last Name">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="exampleInputEmail" class="form-label-outside">
                                                    <fmt:message key="email"/>
                                                </label>
                                                <input type="email" name="email" value="${userProfile.email}"
                                                       class="form-control form-control-user" id="exampleInputEmail"
                                                       placeholder="Email Address">
                                            </div>

                                            <c:choose>
                                                <c:when test="${userProfile.id != user.id}">

                                            <label class="form-label-outside">
                                                <fmt:message key="condition"/>
                                            </label>
                                            <div class="switch-wrap d-flex justify-content-between">
                                                <div class="confirm-radio">

                                                    <c:choose>
                                                        <c:when test="${userProfile.active=='true'}">
                                                    <input type="radio" id="confirm-radio"
                                                           name="active" value="true"checked>
                                                    <label for="confirm-radio">Active</label>
                                                    <input type="radio" id="confirm-radio2"
                                                           name="active" value="false">
                                                    <label for="confirm-radio">Blocked</label>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input type="radio" id="confirm-radio"
                                                                   name="active" value="true">
                                                            <label for="confirm-radio">Active</label>
                                                            <input type="radio" id="confirm-radio2"
                                                                   name="active" value="false"checked>
                                                            <label for="confirm-radio">Blocked</label>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div>


                                            <div class="form-group">
                                                <label for="role" class="form-label-outside">
                                                    <fmt:message key="role"/>
                                                </label>
                                                <select id="role" name="role" class="form-control">
                                                    <c:choose>
                                                        <c:when test="${userProfile.role=='ADMIN'}">
                                                            <option value="ADMIN">ADMIN</option>
                                                            <option value="CUSTOMER">CUSTOMER</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="CUSTOMER">CUSTOMER</option>
                                                            <option value="ADMIN">ADMIN</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </select>

                                            </div>
                                                </c:when>
                                                    <c:otherwise>
                                                        <hr>
                                                    </c:otherwise>
                                            </c:choose>

                                            <c:choose>
                                                <c:when test="${userProfile.id == user.id}">

                                            <div class="form-group row">
                                                <div class="col-sm-6 mb-3 mb-sm-0">
                                                    <label for="exampleInputPassword" class="form-label-outside">
                                                        <fmt:message key="password"/>
                                                    </label>
                                                    <input type="password" name="password"
                                                           value="${userProfile.password}"
                                                           class="form-control form-control-user"
                                                           id="exampleInputPassword" placeholder="Password">
                                                </div>

                                                <div class="col-sm-6">
                                                    <label for="exampleRepeatPassword" class="form-label-outside">
                                                        <fmt:message key="confirm.password"/>
                                                    </label>
                                                    <input type="password" name="confirmPassword"
                                                           value="${userProfile.password}"
                                                           class="form-control form-control-user"
                                                           id="exampleRepeatPassword" placeholder="Repeat Password">
                                                </div>
                                            </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <hr>
                                                </c:otherwise>
                                            </c:choose>

                                                <input type="hidden" name="userId" value="${userProfile.id}"/>

                                            <button type="submit" class="btn btn-primary btn-user btn-block">Change
                                            </button>


                                        </form>


                                    </div>
                                </div>
                            </div>
                        </div>
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
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<c:import url="templ/logout-part.jsp"/>
<c:import url="templ/loader-part.jsp"/>

</body>

</html>