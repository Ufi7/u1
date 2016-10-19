<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<spring:form commandName="groupWithRole">
    <table>
        <tbody>
            <tr>
                <td>
                    分组名称:
                </td>
                <td>
                    <spring:input path="groupName" size="20" cssClass="shadow" autocomplete="off"/>
                </td>
            </tr>
            <tr>
            	<td>
            		分组角色 :
            	</td>
            	<td>
            		<c:import url="3selecttemplate.jsp"></c:import>
            	</td>
            </tr>
            <tr>
            	<td colspan="2" align="right">
            		<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow" >
						<span class="ui-button-text">更新</span>
					</button>
            	</td>
            </tr>
        </tbody>
    </table>
</spring:form>
<script type="text/javascript">
$("#groupWithRole").submit( function() {
	var action = "groupsdetailupdate/"+<c:out value="${groupWithRole.groupId}"/>;
	ajax_call_post(action,$(this).serialize(), true, afterupdategroups);
	return false;
});
function afterupdategroups(data){
	if(!validateText(data)){
		var tds = lastobject.children();
		for(var i=0;i<tds.length;i++){
			tds.eq(i).html($("#groupWithRole input").eq(i).val());
		}
		show_message(data);
		clear_popup_content_layout();
	}
}
</script>