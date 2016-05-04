<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet"
	href="/vmg-tool/resources/js/bootstrap.min.css">

<link rel="stylesheet"
	href="/vmg-tool/resources/js/bootstrap-theme.min.css">

<script src="/vmg-tool/resources/js/jquery.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"></script>
<script
	src="/vmg-tool/resources/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>
</head>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<tilesx:useAttribute name="current"/>

<div class="container">

    <!-- Static navbar -->
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<spring:url value="/" />">Visual Modelling Group</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li class="${current == 'home' ? 'active' : ''}"><a href='<spring:url value="/" />'>Home</a></li>
               <security:authorize access="isAuthenticated()">
              	<li class="${current == 'studies' ? 'active' : ''}"><a href="<spring:url value="/pages/studies.html" />">My Studies</a></li>
              	<li><a href="<spring:url value="/logout" />">Logout</a></li>
              </security:authorize>
              <security:authorize access="hasRole('ROLE_ADMIN')">
              	<li class="${current == 'users' ? 'active' : ''}"><a href="<spring:url value="/pages/users.html" />">Users</a></li>
              </security:authorize>
               <li class="${current == 'register' ? 'active' : ''}"><a href="<spring:url value="/pages/register.html" />">Register</a></li>
                  <security:authorize access="! isAuthenticated()">
	              <li class="${current == 'login' ? 'active' : ''}"><a href="<spring:url value="/pages/login.html" />">Login</a></li>
              </security:authorize>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>

	<tiles:insertAttribute name="body" />

	<br>
	<br>
	<center>
		<tiles:insertAttribute name="footer" />
	</center>

</div>

</body>
</html>