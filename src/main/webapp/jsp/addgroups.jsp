<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="sidebar">
	<ul class="leftmenu">
		<a href='addgroups'><li class="selectedli arrow-blue">新建用户分组</li></a>
		<a href="groups"><li class="arrow-grey">用户分组查询</li></a>
	</ul>
</div>
<div class="insidecontent">
	<div class="u1title1"><h2>新建用户分组</h2></div>
	<div class="contentbox">
		<spring:form commandName="groupWithRole">
		<spring:input path="groupName" size="20" autocomplete="off" placeholder="请输入用户分组名称" cssClass="shadow"/><br>
		<hr>
		分组角色<br>
		<c:import url="3selecttemplate.jsp"></c:import>
		<button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow" >
			<span class="ui-button-text">保存</span>
		</button>
		</spring:form>
	</div>
</div>
<script type="text/javascript">
$("#groupWithRole").submit( function() {
	ajax_call_post("addgroupssubmit", $(this).serialize(), true, afteraddnewgroups);
	return false;
});
function afteraddnewgroups(data){
	if(!validateText(data)){
		clearnewgroupinput();
		show_message(data);
	}
}
function clearnewgroupinput(){
	$("#groupWithRole input").val("");
	if($("#select_2 option")!=null){
		multi_select_switch($("#select_2 option"),$("#select_1"),$("#select_2"),$("#select_3"));
	}
}
</script>