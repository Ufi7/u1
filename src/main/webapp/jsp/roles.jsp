<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="sidebar">
	<ul class="leftmenu">
		<a href='addroles'><li class="arrow-grey">新建用户角色</li></a>
		<a href="roles"><li class="selectedli arrow-blue">用户角色查询</li></a>
	</ul>
</div>
<div class="insidecontent">
	<div class="u1title1"><h2>用户角色查询</h2></div>
	<div class="contentbox">
		<spring:form commandName="roles">
		<table>
			<tr>
				<td>
					<spring:input path="roleName" size="20" placeholder="查询用户角色名称" autocomplete="off" cssClass="shadow"/>
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
        $("#roles").submit(function() {
        	clear_popup_content_layout();
        	ajax_call_post("rolessearch", $(this).serialize(), true, update_list_table);
			return false;
        });
		</script>
		<div id="datadiv">
			<c:import url="roleslist.jsp"></c:import>
		</div>
	</div>
</div>