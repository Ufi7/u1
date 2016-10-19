<!--login--><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" href="css/u1.css" media="screen" />
		<script src="js/jquery-1.10.2.min.js"></script>
		<title>U1 System</title>
		<style type="text/css">
			.logincontainer{
				width: 320px;
				margin: 0 auto;
				position: relative;
				content: "";
				display: table;
			}
			.message_container{
				padding:5px;
				min-height:30px;
			}
			.formlayout{
				margin:150px 0 0 0;
				background-color: #ffffff;
				padding: 0 20px 20px 20px;
				border-radius: 10px 10px 10px 10px;
				position: relative;
			}
			.loginfotter{
				color:#444444;
			}
		</style>
	</head>
	<body>
			<div class="logincontainer">
				<div class="formlayout shadow">
					<div class="message_container">
						<c:choose>
							<c:when test="${SECCODE_ERROR}">
								<font color="#ff0000"><spring:message code="seccodeerror"/></font>
							</c:when>
							<c:otherwise>
								<c:if test="${SPRING_SECURITY_LAST_EXCEPTION.message eq 'User account is locked'}">
									<font color="#FF0000"><spring:message code="authenticationlock"/></font>
								</c:if>
								<c:if test="${SPRING_SECURITY_LAST_EXCEPTION.message eq 'Bad credentials'}">
									<font color="#FF0000"><spring:message code="authenticationfail"/></font>
								</c:if>
							</c:otherwise>
						</c:choose>
					</div>
					<form method="POST" action="j_spring_security_check">
						<table>
							<tr>
								<td rowspan=3 valign=top>
									<img src="img/u1/logo_square.png" class="shadow bgcolorborder" style="padding:5px;margin:5px;"/>
								</td>
								<td>
									<input placeholder="请输入用户名"  class="shadow" size=25 id="j_username" name="j_username" tabindex="1" type="text" autocomplete="off"/>
								</td>
							</tr>
							<tr><td>
								<input placeholder="请输入用密码"  class="shadow" size=25 id="j_password" type="password" name="j_password" size="30" tabindex="2" autocomplete="off"/>
							</td></tr>
							<tr><td valign=center>
								<img id="seccodeimg" src="<c:url value='seccode'/>"/>
								<input placeholder="验证码"  class="shadow" size=5 id="seccode" name="seccode" size="30" tabindex="3" autocomplete="off" maxlength=4/>
								<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow" style="padding:5px">
									<span class="ui-button-text">登&nbsp;录</span>
								</button>
							</td></tr>
<!-- 							<tr><td> -->
								
<!-- 							</td></tr> -->
							
						</table>
					</form>
				</div>
			</div>
			<div class="footer">
				<div class="logincontainer">
				<spring:message code="copyright"/>
				</div>
			</div>
			<script>
			$(document).ready(function(){
				$("#seccodeimg").click(function(){  
	               $('#seccodeimg').hide().attr('src','<c:url value="seccode"/>'+ '?'+ Math.floor(Math.random() * 100)).fadeIn();  
	            });
			});
			</script>
		</body>
</html>