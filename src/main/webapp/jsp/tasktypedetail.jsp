<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<spring:form commandName="tasktypeDetail">
	<table><tr><td>
	工单类型:<spring:input path="code" size="20" cssClass="shadow" autocomplete="off"/>&nbsp;&nbsp;
	 类型描述:<spring:input path="description" size="20" cssClass="shadow" autocomplete="off"/>&nbsp;&nbsp;
	自编号前缀:<span class="bluehighlight"><c:out value="${tasktypeDetail.definedCode}"/></span>
	</td></tr><tr><td>
	权重值:<spring:input path="weight" size="10" cssClass="shadow" autocomplete="off"/>&nbsp;&nbsp;
	高优先级时限:<spring:input path="highDefaultTime" size="5" cssClass="shadow" autocomplete="off"/>&nbsp;&nbsp;
	中优先级时限:<spring:input path="mediumDefaultTime" size="5" cssClass="shadow" autocomplete="off"/>&nbsp;&nbsp;
	低优先级时限:<spring:input path="lowDefaultTime" size="5" cssClass="shadow" autocomplete="off"/>&nbsp;&nbsp;
	</td></tr><tr><td align="right">
	<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow" >
		<span class="ui-button-text">更新</span>
	</button>
	<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow"  onclick="javascript:delete_entry();">
		<span class="ui-button-text">删除</span>
	</button>
	</td></tr></table>
</spring:form>
<SCRIPT type="text/javascript">
var entityName = $("#tasktypeDetail input").eq(0).val();
$("#tasktypeDetail").submit( function() {
	var link='tasktypedetailupdate/'+<c:out value="${tasktypeDetail.taskTypePid}"/>;
	ajax_call_post(link,$(this).serialize(), true, afterupdatetasktype);
	return false;
});
function afterupdatetasktype(data){
	if(!validateText(data)){
		lastobject.hide();
		var message = success_update_tasktype.replace('~~0', entityName);
		tds=lastobject.children();
		var prefill0s = [false, false, true, true, true, true];
		var temp;
		for(var i=0;i<6;i++){
			temp = $("#tasktypeDetail input").eq(i).val();
			if(temp == '' && prefill0s[i]){
				temp='0';
			}
			tds.eq(i).html(temp);
		}
		clear_popup_content_layout();
		lastobject.fadeIn(1000);
		show_message(message);
	}
}
function delete_entry(){
	if(window.confirm(delete_alert_message.replace('~~0', entityName))){
		var action="tasktypedelete/"+<c:out value="${tasktypeDetail.taskTypePid}"/>;
		ajax_call_post(action,$(this).serialize(),true,afterdeletetasktype);
	}
}
function afterdeletetasktype(data){
	if(!validateText(data)){
		var message = success_update_tasktype.replace('~~0', entityName);
		lastobject.slideUp();
		lastobject.remove();
		clear_popup_content_layout();
		show_message(message);
	}
}
</SCRIPT>
