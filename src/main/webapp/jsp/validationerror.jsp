<!--error-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<spring:form commandName="roleWithResource">
<spring:errors path="roleName"/>
</spring:form>
<spring:form commandName="roles">
<spring:errors path="roleName"/>
</spring:form>
<spring:form commandName="groups">
<spring:errors path="groupName"/>
</spring:form>
<spring:form commandName="groupWithRole">
<spring:errors path="groupName"/>
</spring:form>
<spring:form commandName="simpleUsers">
<spring:errors path="username"/>
<spring:errors path="givenName"/>
<spring:errors path="telephone"/>
<spring:errors path="email"/>
</spring:form>
<spring:form commandName="simpleUserWithGroup">
<spring:errors path="username"/>
<spring:errors path="givenName"/>
<spring:errors path="telephone"/>
<spring:errors path="email"/>
<spring:errors path="customer"/>
</spring:form>
<spring:form commandName="simpleUserWithGroupAndPassword">
<spring:errors path="username"/>
<spring:errors path="givenName"/>
<spring:errors path="telephone"/>
<spring:errors path="email"/>
<spring:errors path="customer"/>
</spring:form>
<spring:form commandName="assetType">
<spring:errors path="code"/>
<spring:errors path="description"/>
</spring:form>
<spring:form commandName="department">
<spring:errors path="departmentName"/>
<spring:errors path="departmentDesc"/>
<spring:errors path="customer"/>
</spring:form>
<spring:form commandName="simpleAsset">
<spring:errors path="assetType"/>
<spring:errors path="status"/>
<spring:errors path="assetName"/>
<spring:errors path="assetNum"/>
</spring:form>