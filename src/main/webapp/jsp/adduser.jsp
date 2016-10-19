<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:out value="${test}"/>
<spring:form commandName="userForAuthOnly" method="POST">
	<spring:label path="username">username</spring:label><spring:input path="username"/>&nbsp;<spring:errors path="username"/><br>
	<spring:label path="password">password</spring:label><spring:input path="password"/>&nbsp;<spring:errors path="password"/>
	<input type="submit"/>
</spring:form>