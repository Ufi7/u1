<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<spring:form commandName="roleWithResource">
    <table>
        <tbody>
            <tr>
                <td>
                    角色名称:
                </td>
                <td>
                    <spring:input path="roleName" size="20" cssClass="shadow" autocomplete="off"/>
                </td>
            </tr>
            <tr>
            	<td>
            		角色可访问资源 :
            	</td>
            	<td>
            		<c:import url="3selecttemplate.jsp"></c:import>
            	</td>
            </tr>
            <tr>
            	<td colspan="2" align="right">
            		<button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow" >
						<span class="ui-button-text">更新</span>
					</button>
            	</td>
            </tr>
        </tbody>
    </table>
</spring:form>
<script type="text/javascript">
$("#roleWithResource").submit( function() {
	var action = "rolesdetailupdate/"+<c:out value="${roleWithResource.roleId}"/>;
	ajax_call_post(action,$(this).serialize(), true, afterupdaterole);
	return false;
});
function afterupdaterole(data){
	if(!validateText(data)){
		var tds = lastobject.children();
		for(var i=0;i<tds.length;i++){
			tds.eq(i).html($("#roleWithResource input").eq(i).val());
		}
		show_message(data);
		clear_popup_content_layout();
	}
}
</script>