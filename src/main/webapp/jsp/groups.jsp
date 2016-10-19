<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="sidebar">
	<ul class="leftmenu">
		<a href='addgroups'><li class="arrow-grey">新建用户分组</li></a>
		<a href="groups"><li class="selectedli arrow-blue">用户分组查询</li></a>
	</ul>
</div>
<div class="insidecontent">
	<div class="u1title1"><h2>用户分组查询</h2></div>
	<div class="contentbox">
		<spring:form commandName="groups">
		<table>
			<tr>
				<td>
					<spring:input path="groupName" size="20" placeholder="查询分组名称" autocomplete="off" cssClass="shadow"/>
				</td>
				<td>
					<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only shadow u1background" >
						<span class="ui-button-text">查询</span>
					</button>
				<td>
			</tr>
		</table>
		</spring:form>
		<script>
        $("#groups").submit(function() {
        	clear_popup_content_layout();
        	ajax_call_post("groupssearch", $(this).serialize(), true, update_list_table);
			return false;
        });
		</script>
		<div id="datadiv">
			<c:import url="groupslist.jsp"></c:import>
		</div>
	</div>
</div>