<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<spring:form commandName="simpleUserWithGroup">
    <table>
        <tbody>
            <tr>
                <td>
                    	用户名称:
                </td>
                <td>
                    <spring:input path="username" size="20" cssClass="shadow" autocomplete="off"/>
                </td>
                <td>
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
           		<td>
                    	用户姓名:
                </td>
                <td>
                    <spring:input path="givenName" size="20" cssClass="shadow" autocomplete="off"/>
                </td>
            </tr>
            <tr>
                <td>
                    	用户姓名:
                </td>
                <td>
                    <spring:input path="telephone" size="20" cssClass="shadow" autocomplete="off"/>
                </td>
                <td>
                </td>
                <td>
                    	邮件地址:
                </td>
                <td>
                    <spring:input path="email" size="20" cssClass="shadow" autocomplete="off"/>
                </td>
            </tr>
            <tr>
            	<td>
            		服务客户：
            	</td>
            	<td colspan=3>
            		<select name="customer" id="customer" class="shadow">
            			<c:if test="${simpleUserWithGroup.customer!=null}">
            				<option value="${simpleUserWithGroup.customer.customerId}" selected=selected><c:out value="${simpleUserWithGroup.customer.customerName}"></c:out></option>
            			</c:if>
            		</select>
            	</td>
            </tr>
            <tr>
            	<td>
            		用户分组 :
            	</td>
            	<td colspan="4">
            		<c:import url="3selecttemplate.jsp"></c:import>
            	</td>
            </tr>
            <tr>
            	<td colspan="5" align="right">
            		<button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow" >
						<span class="ui-button-text">更新</span>
					</button>
            	</td>
            </tr>
        </tbody>
    </table>
</spring:form>
<script type="text/javascript">
$(document).ready(function(){
	buildOptionBasedOnAjaxReturn($("#simpleUserWithGroup #customer"),'option/customerlist');
	$("#simpleUserWithGroup").submit( function() {
		var link = 'usersdetailupdate/'+<c:out value="${simpleUserWithGroup.userId}"/>;
		ajax_call_post(link,$(this).serialize(), true, afterupdateuser);
		return false;
	});
});
function afterupdateuser(data){
	if(!validateText(data)){
		var tds = lastobject.children();
		for(var i=0;i<tds.length;i++){
			tds.eq(i).html($("#simpleUserWithGroup input").eq(i).val());
		}
		show_message(data);
		clear_popup_content_layout();
	}
}
</script>