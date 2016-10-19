<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<spring:form commandName="departmentDetail">
	<table><tr><td>
	<input type="hidden" name="customer" id="customer" value="${departmentDetail.customer.customerId}"/>
	部门名称:<spring:input path="departmentName" size="20" cssClass="shadow" autocomplete="off"/>&nbsp;&nbsp;
	 部门描述:<spring:input path="departmentDesc" size="20" cssClass="shadow" autocomplete="off"/>&nbsp;&nbsp;
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
$("#departmentDetail").submit( function() {
	var action="departmentdetailupdate/"+<c:out value="${departmentDetail.departmentPid}"/>;
	ajax_call_post(action,$(this).serialize(), true, afterupdatedepartment);
	return false;
});
function afterupdatedepartment(data){
	if(!validateText(data)){
		lastobject.hide();
		tds=lastobject.children();
		for(var i=0;i<2;i++){
			tds.eq(i).html($("#departmentDetail input[type!='hidden']").eq(i).val());
		}
		lastobject.fadeIn(2000);
		clear_popup_content_layout();
		show_message(data);
	}
}
function delete_entry(){
	if(window.confirm("确认删除？"))
	{
		var action="departmentdelete/"+<c:out value="${departmentDetail.departmentPid}"/>;
		ajax_call_post(action,$(this).serialize(),true,afterdeletedepartment);
	}
}
function afterdeletedepartment(data){
	if(!validateText(data)){
		lastobject.slideUp();
		lastobject.remove();
		clear_popup_content_layout();
		show_message(data);
	}
}
</SCRIPT>