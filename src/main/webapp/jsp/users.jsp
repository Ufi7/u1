<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="sidebar">
	<ul class="leftmenu">
		<a href='addusers'><li class="arrow-grey">新建用户信息</li></a>
		<a href="users"><li class="selectedli arrow-blue">用户信息查询</li></a>
	</ul>
</div>
<div class="insidecontent">
	<div class="u1title1"><h2>用户信息查询</h2></div>
	<div class="contentbox">
		<spring:form commandName="simpleUsers">
		<table>
			<tr>
				<td>
					<spring:input path="username" size="20" placeholder="查询用户名称" autocomplete="off" cssClass="shadow"/>
					<spring:input path="givenName" size="20" placeholder="查询用户姓名" autocomplete="off" cssClass="shadow"/>
					<spring:input path="telephone" size="20" placeholder="查询用户电话" autocomplete="off" cssClass="shadow"/>
					<spring:input path="email" size="20" placeholder="查询用户邮件地址" autocomplete="off" cssClass="shadow"/>
				</td>
				<td>
					<button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only shadow u1background" >
						<span class="ui-button-text">查询</span>
					</button>
				<td>
			</tr>
		</table>
		</spring:form>
		<script>
        $("#simpleUsers").submit(function() {
        	clear_popup_content_layout();
        	ajax_call_post("userssearch", $(this).serialize(), true, update_list_table);
			return false;
        });
		</script>
		<div id="datadiv">
			<c:import url="userslist.jsp"></c:import>
		</div>
	</div>
</div>