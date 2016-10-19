<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<spring:form commandName="assetTypeDetail">
	<table><tr><td>
		资产类型:<spring:input path="code" size="20" cssClass="shadow" autocomplete="off"/>&nbsp;&nbsp;
		 类型描述:<spring:input path="description" size="20" cssClass="shadow" autocomplete="off"/>&nbsp;&nbsp;
		自编号前缀:<span class="bluehighlight"><c:out value="${assetTypeDetail.definedCode}"/></span>
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
$("#assetTypeDetail").submit( function() {
	var link='assettypedetailupdate/'+<c:out value="${assetTypeDetail.assetTypePid}"/>;
	ajax_call_post(link,$(this).serialize(), true, afterupdateassettype);
	return false;
});
function afterupdateassettype(data){
	if(!validateText(data)){
		lastobject.hide();
		tds=lastobject.children();
		for(var i=0;i<2;i++){
			tds.eq(i).html($("#assetTypeDetail input").eq(i).val());
		}
		lastobject.fadeIn(2000);
		clear_popup_content_layout();
		show_message(data);
	}
}
function delete_entry(){
	if(window.confirm("确认删除？"))
	{
		var action="assettypedelete/"+<c:out value="${assetTypeDetail.assetTypePid}"/>;
		ajax_call_post(action,$(this).serialize(),true,afterdeleteassettype);
	}
}
function afterdeleteassettype(data){
	if(!validateText(data)){
		lastobject.slideUp();
		lastobject.remove();
		clear_popup_content_layout();
		show_message(data);
	}
}
function update_entry_status(currentstatus){
	if(window.confirm("确认更改状态？"))
	{
		var action='assettypestatusupdate/'+<c:out value="${assetTypeDetail.assetTypePid}"/>+'?cs='+currentstatus;
		ajax_call_post(action,$(this).serialize(),true,afterupdateassettypestatus);
	}
}
function afterupdateassettypestatus(data){
	if(!validateText(data)){
		lastobject.children().eq(2).hide();
		if(<c:out value="${assetTypeDetail.enabled == true}"/>){
			lastobject.children().eq(2).html('<img src="img/cross.png"></img>');
		}else{
			lastobject.children().eq(2).html('<img src="img/confirm.png"></img>');
		}
		lastobject.children().eq(2).fadeIn(2000);
		clear_popup_content_layout();
		show_message(data);
	}
}
</SCRIPT>