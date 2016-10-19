<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<spring:form commandName="assetattributesDetail">
	<table>
		<tr><td colspan="3">
		属性类型:<spring:select path="attributeType" cssClass="shadow">
			<spring:option value="ENTRY"><spr:message code="ENTRY"/></spring:option>
			<spring:option value="CALENDAR"><spr:message code="CALENDAR"/></spring:option>
			<spring:option value="BOOLEAN"><spr:message code="BOOLEAN"/></spring:option>
			<spring:option value="SELECT"><spr:message code="SELECT"/></spring:option>
			<spring:option value="MULTI_SELECT"><spr:message code="MULTI_SELECT"/></spring:option>
		</spring:select>&nbsp;
		属性名:<spring:input path="attributeName" size="20" cssClass="shadow" autocomplete="off"/>&nbsp;
		显示分组:<spring:input path="section" size="20" cssClass="shadow" autocomplete="off"/>&nbsp;
		必填:<spring:checkbox path="required" cssClass="shadow"/>&nbsp;
		状态<c:choose>
			<c:when test="${assetattributesDetail.enabled == true}">
	        	<img src="img/confirm.png"></img>
	        	<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow" onclick="javascript:update_entry_status(true);">
					<span class="ui-button-text">停用</span>
				</button>
	        </c:when>
	        <c:otherwise>
	           	<img src="img/cross.png"></img>
	           	<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow" onclick="javascript:update_entry_status(false);">
					<span class="ui-button-text">启用</span>
				</button>
	        </c:otherwise>
	    </c:choose>
		</td></tr>
		<c:set var="show_hidden_option_indicator" value="display:none;"></c:set>
		<c:if test="${assetattributesDetail.attributeType == 'SELECT' || assetattributesDetail.attributeType == 'MULTI_SELECT'}">
			<c:set var="show_hidden_option_indicator" value=""></c:set>
		</c:if>
		<tr id="attributeType_select_row" style="<c:out value='${show_hidden_option_indicator}'/>">
			<td>选项描述:</td>
			<td>
				<table>
					<tr id="sample_option_row" style="display:none;"><td>
						选项描述:<input name="sample_description" size="20" 出lass="shadow option-description" autocomplete="off"/></td>
						<td><button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow">
							<span class="ui-button-text">删除该选项</span>
						</button></td>
					</tr>
					<c:forEach items="${assetattributesDetail.selectOptionList}" var="o">
						<tr id="existed_option_row<c:out value='${o.attrSelectOptionPid}'/>"><td>
							选项描述:<input name="selectOptionList" value="${o.description}" size="20" class="shadow option-description" autocomplete="off"/></td>
							<td><button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow existedoptiondelbtn">
								<span class="ui-button-text">删除该选项</span>
							</button></td>
						</tr>
					</c:forEach>
				</table>
			</td>
			<td>
				<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow addnewoptionbtn">
					<span class="ui-button-text">增加新选项</span>
				</button>
				<select name="hiddenOptionList" size="4" multiple style="display:none;">
					<c:forEach items="${assetattributesDetail.selectOptionList}" var="option">
						<option id="hidden_existed_option_<c:out value='${option.attrSelectOptionPid}'/>" selected="selected" value="<c:out value='${option.attrSelectOptionPid}'/>"><c:out value='${option.attrSelectOptionPid}'/></option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr><td colspan="3" align="right">
			<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow" >
				<span class="ui-button-text">更新</span>
			</button>
<!-- 			<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow"  onclick="javascript:delete_entry();"> -->
<!-- 				<span class="ui-button-text">删除</span> -->
<!-- 			</button> -->
		</td></tr>
	</table>
</spring:form>
							
							<SCRIPT type="text/javascript">
								//init the select pull down for section input
								$(document).ready(function(){
									build_select_pulldown('strlist/section', $("#section"));
								});

								$("#attributeType").change(function(){
									if($("#attributeType").val() == 'SELECT' || $("#attributeType").val()=='MULTI_SELECT' ){
										$("#attributeType_select_row").fadeIn();
										$(".option-description").removeAttr('disabled');
									}else{
										$("#attributeType_select_row").fadeOut();
										$(".option-description").attr('disabled', 'disabled');
									}
								});
								$(".existedoptiondelbtn").click(function(){
									var rowid = $(this).parent('td').parent('tr').attr('id');
									var deleteid = rowid.substr(18);
									$("#hidden_existed_option_"+deleteid).attr('selected', false);
									$(this).parent('td').parent('tr').remove();
								});
								$(".addnewoptionbtn").click(function add_new_option(){
									var newrow = $("#sample_option_row").clone();
									$("#sample_option_row").parent("tbody").append(newrow);
									newrow.children().eq(0).children().eq(0).attr('name', 'selectOptionList');
									newrow.children().eq(1).children().eq(0).click(function(){
										$(this).parent('td').parent('tr').remove();
									});
									newrow.fadeIn(1000);
								});
								////////////////////////////////////////////////////////////////////////
								var entityName = $("#assetattributesDetail input").eq(0).val();
								$("#assetattributesDetail").submit( function() {
									var action="assetattributesdetail/"+<c:out value="${assetattributesDetail.assetAttrTemplatePid}"/>;
									ajax_call_post(action, $(this).serialize(), true, afterupdateassetattribute);
									return false;
								});
								function afterupdateassetattribute(data){
									if(!validateText(data)){
										var message = success_update_assetattributes.replace('~~0', $("#attributeName").val());
										var row=lastobject.children();
										var tds=[row.eq(0),row.eq(1), row.eq(2), row.eq(3)];
										var values=[$("#attributeName").val(),$("#attributeType").find("option:selected").text(),$("#section").val(),$("#required1").is(":checked")?'是':'否'];
										var prefill0s=[false, false, false, false];
										prefillvalue(tds, values, prefill0s);
										clear_popup_content_layout();
										show_message(message);
									}
								}
								function update_entry_status(currentstatus){
									if(window.confirm(update_status_alert_message.replace('~~0', entityName)))
									{
										var action='assetattributesstatusupdate/'+<c:out value="${assetattributesDetail.assetAttrTemplatePid}"/>+'?cs='+currentstatus;
										ajax_call_post(action,$(this).serialize(), true, afterupdatestatus);
									}
								}
								function afterupdatestatus(data){
									if(!validateText(data)){
										lastobject.children().eq(4).hide();
										var message = success_updatestatus_assetattributes.replace('~~0', entityName);
										if(<c:out value="${assetattributesDetail.enabled == true}"/>){
											lastobject.children().eq(4).html('<img src="img/cross.png"></img>');
										}else{
											lastobject.children().eq(4).html('<img src="img/confirm.png"></img>');
										}
										clear_popup_content_layout();
										lastobject.children().eq(4).fadeIn(2000);
										show_message(message);
									}
								}
								function delete_entry(){
									if(window.confirm(delete_alert_message.replace('~~0', entityName)))
									{
										var action="assetattributesdelete/"+<c:out value="${assetattributesDetail.assetAttrTemplatePid}"/>;
										ajax_call_post(action,$(this).serialize(), true, afterdelete);
									}
								}
								function afterdelete(data){
									if(!validateText(data)){
										var message = success_delete_assetattributes.replace('~~0', entityName);
										lastobject.slideUp();
										lastobject.remove();
										clear_popup_content_layout();
										show_message(message);
									}
								}
							</SCRIPT>