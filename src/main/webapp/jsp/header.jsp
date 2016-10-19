<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">  
	         <div class="logo">
	         	<img src="img/u1/logo.png"/>
	         </div>
	         <!-- <div class="title"><h3>专注科技创新十余载</h3>
	         </div> --><!--end title -->
	         <div class="toolbar noprint">
	         		<ul>
	         			<li><a href="logout">退出登录</a></li>
	         			<li><a onclick="javascript:$('#passwordchangediv').toggle();">更改密码</a>
	         				<div id='passwordchangediv' style="position:absolute;" class="uparrowpassword hidden">
	         					<form id='pswchangeform'>
	         						<input type="password" id='oldpsw' name='oldpsw' placeholder="请输入旧密码" class="shadow"/>
	         						<input type="password" id='newpsw' name='newpsw' placeholder="请输入新密码" class="shadow"/>
	         						<input type="password" id='newpsw2' name='newpsw2' placeholder="请再输入一次新密码" class="shadow"/>
	         						<button type=button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow" onclick="javascript:$('#passwordchangediv').toggle();">
										<span class="ui-button-text">取消</span>
									</button>
	         						<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow">
										<span class="ui-button-text">提交</span>
									</button>
	         					</form>
	         				</div>
	         			</li>
	         			<li><c:out value="${self[1]}"/></li>
	         		</ul>
	         </div>
	         <div class="navigation-menu noprint">
	         				<ul id="nav" style="border:none;" class="hidden">
								    <li><a href="home"><span class="ui-icon ui-icon-home"></span>首页</a></li>
								    <c:if test="${accessRights[1] || accessRights[2] || accessRights[3] || accessRights[7]}">
								    <li><a href="#"><span class="ui-icon ui-icon-gear"></span>系统管理</a>
								        <ul>
								        	<c:if test="${accessRights[1]}">
									            <li><a href="#"><span class="ui-icon ui-icon-key"></span>安全信息管理</a>
										            <ul>
										                <li><a href="roles"><span class="ui-icon ui-icon-person"></span>用户角色管理</a></li>
										                <li><a href="groups"><span class="ui-icon ui-icon-link"></span>用户分组管理</a></li>
										                <li><a href="users"><span class="ui-icon ui-icon-contact"></span>用户信息管理</a></li>
										            </ul>
									            </li>
								            </c:if>
								            <c:if test="${accessRights[2]}">
								            <li><a href="assettype"><span class="ui-icon ui-icon-note"></span>数据字典管理</a></li>
								            </c:if>
								            <c:if test="${accessRights[3]}">
								            <li><a href="assetattributes"><span class="ui-icon ui-icon-print"></span>资产参数配置</a></li>
								            </c:if>
								            <c:if test="${accessRights[7]}">
											    <li><a href="#"><span class="ui-icon ui-icon-lightbulb"></span>服务水平管理</a>
											    	<ul>
											    		<li><a href="slasetup"><span class="ui-icon ui-icon-gear"></span>工单评分设定</a></li>
											    		<li><a href="sla"><span class="ui-icon ui-icon-search"></span>服务水平查询</a></li>
											    	</ul>
											    </li>
											</c:if>
								        </ul>
								    </li>
								    </c:if>
								    <c:if test="${accessRights[5]}">
								    <li><a href="#"><span class="ui-icon ui-icon-document"></span>工单信息管理</a>
								    	<ul>
								    	<li><a href="newtask"><span class="ui-icon ui-icon-plus"></span>新建工单</a></li>
								        <li><a href="task"><span class="ui-icon ui-icon-search"></span>工单信息检索</a></li>
								      </ul>
								    </li>
								    </c:if>
								    <c:if test="${accessRights[4]}">
								    <li><a href="#"><span class="ui-icon ui-icon-print"></span>资产信息管理</a>
								    	<ul>
								      	<li><a href="newasset"><span class="ui-icon ui-icon-plus"></span>新建资产信息</a></li>
								        <li><a href="asset"><span class="ui-icon ui-icon-search"></span>资产查询管理</a></li>
								        <li><a href="assetgroup"><span class="ui-icon ui-icon-link"></span>资产分组管理</a></li>
								        <li><a href="component"><span class="ui-icon ui-icon-cart"></span>库存配件管理</a></li>
								      </ul>
								    </li>
								    </c:if>
<%-- 								    <c:if test="${accessRights[7]}"> --%>
<!-- 									    <li><a href="#"><span class="ui-icon ui-icon-lightbulb"></span>服务水平管理</a> -->
<!-- 									    	<ul> -->
<!-- 									    		<li><a href="slasetup"><span class="ui-icon ui-icon-gear"></span>工单评分设定</a></li> -->
<!-- 									    		<li><a href="sla"><span class="ui-icon ui-icon-search"></span>服务水平查询</a></li> -->
<!-- 									    	</ul> -->
<!-- 									    </li> -->
<%-- 									</c:if> --%>
								</ul>
	         </div>
</div>
<script>
$(document).ready(function(){
	$('#pswchangeform').submit(function(){
		ajax_call_post('pswchange', $(this).serialize(), true, function(data){
			if(!validateText(data)){
				$('#passwordchangediv').hide();
				show_message('密码更改成功，请在下一次登录时使用新密码登录！');
			}
		});
		return false;
	});
});
</script>