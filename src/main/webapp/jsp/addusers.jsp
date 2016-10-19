<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="sidebar">
	<ul class="leftmenu">
		<a href='addusers'><li class="selectedli arrow-blue">新建用户信息</li></a>
		<a href="users"><li class="arrow-grey">用户信息查询</li></a>
	</ul>
</div>
<div class="insidecontent">
	<div class="u1title1"><h2>新建用户信息</h2></div>
	<div class="contentbox">
		<spring:form commandName="simpleUserWithGroup">
		<spring:input path="username" size="20" autocomplete="off" placeholder="请输入用户名" cssClass="shadow"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<spring:input path="givenName" size="20" autocomplete="off" placeholder="请输入用户姓名" cssClass="shadow"/><br>
		<spring:input path="telephone" size="20" autocomplete="off" placeholder="请输入用户电话" cssClass="shadow"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<spring:input path="email" size="20" autocomplete="off" placeholder="请输入用户邮件地址" cssClass="shadow"/><br>
		服务客户：<select name="customer" id="customer" class="shadow">
        </select>
		<hr>
		角色可访问资源 <br>
		<c:import url="3selecttemplate.jsp"></c:import>
		<button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow" >
			<span class="ui-button-text">保存</span>
		</button>
		</spring:form>
		<div style="color:#888888">
			注意:<br>
			<ul style="padding-left:20px;">
				<li>新添加的用户初始密码为所输入的电话号码</li>
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	buildOptionBasedOnAjaxReturn($("#simpleUserWithGroup #customer"),'option/customerlist');
	$("#simpleUserWithGroup").submit( function() {
		ajax_call_post("adduserssubmit", $(this).serialize(), true, afteraddnewusers);
		return false;
	});
});
function afteraddnewusers(data){
	if(!validateText(data)){
		clearnewusersinput();
		show_message(data);
	}
}
function clearnewusersinput(){
	$("#simpleUserWithGroup input").val("");
	if($("#select_2 option")!=null){
		multi_select_switch($("#select_2 option"),$("#select_1"),$("#select_2"),$("#select_3"));
	}
}
</script>