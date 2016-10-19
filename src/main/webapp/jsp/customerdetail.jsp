<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<spring:form commandName="customerDetail">
	<table><tr><td>
	客户名称:<spring:input path="customerName" size="20" cssClass="shadow right10margin" autocomplete="off"/>
	 客户描述:<spring:input path="customerDesc" size="20" cssClass="shadow right10margin" autocomplete="off"/>
	</td><td align="right">
	<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow" >
		<span class="ui-button-text">更新</span>
	</button>
	<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow"  onclick="javascript:delete_entry();">
		<span class="ui-button-text">删除</span>
	</button>
	</td></tr></table>
</spring:form>
<SCRIPT type="text/javascript">
var entityName = $("#customerDetail #customerName").val();
$("#customerDetail").submit( function() {
	var link='customerdetailupdate/'+<c:out value="${customerDetail.customerId}"/>;
	ajax_call_post(link,$(this).serialize(), true, function(data){
		if(!validateText(data)){
			var message = success_update_customer.replace('~~0', $("#customerDetail #customerName").val());
			lastobject.hide();
			tds=lastobject.children();
			tds.eq(0).html($("#customerDetail #customerName").val());
			tds.eq(1).html($("#customerDetail #customerDesc").val());
			lastobject.fadeIn(2000);
			clear_popup_content_layout();
			show_message(message);
		}
	});
	return false;
});
function delete_entry(){
	if(window.confirm("确认删除？"))
	{
		var action="customerdelete/"+<c:out value="${customerDetail.customerId}"/>;
		ajax_call_post(action,$(this).serialize(),true,function(data){
			if(!validateText(data)){
				var message = success_delete_customer.replace('~~0', entityName);
				lastobject.remove();
				clear_popup_content_layout();
				show_message(message);
			}
		});
	}
}
</SCRIPT>