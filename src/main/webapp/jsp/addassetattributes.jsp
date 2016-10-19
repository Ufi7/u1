<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="sidebar">
	<ul class="leftmenu">
		<a href="addassetattributes"><li class="selectedli arrow-blue">新建资产属性</li></a>
		<a href='assetattributes'><li class="arrow-grey">资产属性管理</li></a>
	</ul>
</div>
<div class="insidecontent">
	<div class="u1title1"><h2>新建资产属性</h2></div>
	<div class="contentbox">
		<spring:form commandName="assetattributes">
			<table>
				<tr><td colspan="3">
				资产类型:<spring:select path="assetTypePid" cssClass="shadow">
						<c:forEach items="${assetTypeList}" var="at">
							<spring:option value="${at.assetTypePid}"><c:out value='${at.code}'/></spring:option>
						</c:forEach>
				</spring:select>
				
				属性类型:<spring:select path="attributeType" cssClass="shadow">
					<spring:option value="ENTRY"><spr:message code="ENTRY"/></spring:option>
					<spring:option value="CALENDAR"><spr:message code="CALENDAR"/></spring:option>
					<spring:option value="BOOLEAN"><spr:message code="BOOLEAN"/></spring:option>
					<spring:option value="SELECT"><spr:message code="SELECT"/></spring:option>
					<spring:option value="MULTI_SELECT"><spr:message code="MULTI_SELECT"/></spring:option>
				</spring:select>&nbsp;
				必填:<spring:checkbox path="required" cssClass="shadow"/>&nbsp;
				</td></tr>
				<tr><td colspan="3">
				<spring:input path="attributeName" placeholder="新建属性名称" size="20" cssClass="shadow" autocomplete="off"/>&nbsp;
				<spring:input path="section" placeholder="新建属性分组" size="20" cssClass="shadow" autocomplete="off"/>&nbsp;
				</td></tr>
				<tr id="attributeType_select_row" style="display:none">
					<td>选项描述:</td>
					<td>
						<table>
							<tr id="sample_option_row" style="display:none;"><td>
								<input name="sample_description" placeholder="请输入选项描述" size="20" class="shadow option-description" autocomplete="off"/></td>
								<td><button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background sample-btn shadow">
									<span class="ui-button-text">删除该选项</span>
								</button></td>
							</tr>
							<tr><td>
								<input name="selectOptionList" placeholder="请输入选项描述" size="20" class="shadow option-description" autocomplete="off"/></td>
								<td><button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow sample-btn existedoptiondelbtn">
									<span class="ui-button-text">删除该选项</span>
								</button></td>
							</tr>
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
						<span class="ui-button-text">保存</span>
					</button>
				</td></tr>
			</table>
		</spring:form>
	</div>
</div>
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
									$(this).parent('td').parent('tr').remove();
								});
								$(".addnewoptionbtn").click(function add_new_option(){
									var newrow = $("#sample_option_row").clone();
									$("#sample_option_row").parent("tbody").append(newrow);
									newrow.children().eq(0).children().eq(0).attr('name', 'selectOptionList');
									newrow.children().eq(1).children().eq(0).click(function(){
										$(this).parent('td').parent('tr').remove();
									});
									newrow.show();
								});
								////////////////////////////////////////////////////////////////////////
								$("#assetattributes").submit( function() {
									ajax_call_post($(this).action, $(this).serialize(), true, afteraddnew);
									return false;
								});
								function afteraddnew(data){
									if(!validateText(data)){
										var message = success_add_assetattributes.replace('~~0', $("#attributeName").val());
										//clean action
										$("#assetattributes input").val('');
										$("#assetattributes input:checkbox").attr('checked', false);
										var count = $(".sample-btn").length;
										for(var i=count-1;i>1;i--){
											$(".sample-btn").eq(i).click();
										}
										build_select_pulldown('strlist/section', $("#section"));//reset pulldown
										show_message(message);
									}
								}
							</SCRIPT>