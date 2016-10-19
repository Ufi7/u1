<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="sidebar">
	<ul class="leftmenu">
		<a href='addroles'><li class="selectedli arrow-blue">新建用户角色</li></a>
		<a href="roles"><li class="arrow-grey">用户角色查询</li></a>
	</ul>
</div>
<div class="insidecontent">
	<div class="u1title1"><h2>新建用户角色</h2></div>
	<div class="contentbox">
		<spring:form commandName="roleWithResource">
		<spring:input path="roleName" size="20" autocomplete="off" placeholder="请输入用户角色名称" cssClass="shadow"/><br>
		<hr>
		角色可访问资源 <br>
		<c:import url="3selecttemplate.jsp"></c:import>
		<button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow" >
			<span class="ui-button-text">保存</span>
		</button>
		</spring:form>
	</div>
</div>
<script type="text/javascript">
$("#roleWithResource").submit( function() {
	ajax_call_post("addrolessubmit", $(this).serialize(), true, afteraddnewrole);
	return false;
});
function afteraddnewrole(data){
	if(!validateText(data)){
		clearnewroleinput();
		show_message(data);
	}
}
function clearnewroleinput(){
	$("#roleWithResource input").val("");
	if($("#select_2 option")!=null){
		multi_select_switch($("#select_2 option"),$("#select_1"),$("#select_2"),$("#select_3"));
	}
}
</script>