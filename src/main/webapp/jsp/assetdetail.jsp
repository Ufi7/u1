<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="insidecontent-extend">
<div class="u1title1"><h2>查看资产详情</h2></div>
<div class="copybtn noprint">
	<form id="copyasset" action="addasset" method="POST">
		<input name="id" type="hidden" value="<c:out value='${assetDetail.assetPid}'/>"/>
		<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow"><img src="img/u1/copyasset.png"></button>
	</form>
</div>
<div class="copybtn noprint">
	<form id="raisetask" method="POST">
		<input name="id" type="hidden" value="<c:out value='${assetDetail.assetPid}'/>"/>
		<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only shadow u1background"><img src="img/u1/raisetask.png"></button>
	</form>
</div>
<div id="assetdetailtabs">
	<ul class="u1list2" id="assetdetailtaps">
	  <li class="selected"><a>资产详细</a></li>
	  <li><a>相关附件</a></li>
	  <li><a>相关工单</a></li>
	  <li><a>所属分组</a></li>
	  <li><a>资产日志</a></li>
	</ul>
</div>
<div id="assetDetailDiv">
	<spring:form commandName="assetDetail">
	<table class="detailtable" id="assetdetailtable">
		<tr>
			<th colspan=8 align=right>
			基本属性&nbsp;&nbsp;
			</th>
		</tr>
		<tr>
			<td class="non-highlight">所属客户:</td>
			<td colspan=7>
				<span class="fixtext"><c:out value="${assetDetail.customer.customerName}" /></span>
			</td>
		</tr>
		<tr>
			<td class="non-highlight">资产类型:</td>
			<td>
				<span class="fixtext"><c:out value="${assetDetail.assetType.code}"/></span>
			</td>
			<td>&nbsp;</td>
			<td class="non-highlight">编号:</td>
			<td>
				<span class="fixtext"><c:out value="${assetDetail.assetNum}"/></span>
			</td>
			<td>&nbsp;</td>
			<td class="non-highlight"><img  src="img/u1/mandatory.png" class="hidden"/>名称:</td>
			<td>
				<span class="text"><c:out value="${assetDetail.assetName}" /></span>
				<spring:input path="assetName" size="20" cssClass="shadow hidden" autocomplete="off"/>
			</td>
		</tr>
		<tr>
			<td class="non-highlight">名自定义编号:</td>
			<td>
				<span class="text"><c:out value="${assetDetail.customNum}" /></span>
				<spring:input path="customNum" size="20" cssClass="shadow hidden" autocomplete="off"/>
			</td>
			<td>&nbsp;</td>
			<td class="non-highlight">IP地址:</td>
			<td>
				<span class="text"><c:out value="${assetDetail.host}" /></span>
				<spring:input path="host" size="20" cssClass="shadow hidden" autocomplete="off"/>
			</td>
			<td>&nbsp;</td>
			<td class="non-highlight">使用者:</td>
			<td>
				<span class="text"><c:out value="${assetDetail.owner}" /></span>
				<spring:input path="owner" size="20" cssClass="shadow hidden" autocomplete="off"/>
			</td>
		</tr>
		<tr>
			<td class="non-highlight">位置:</td>
			<td>
				<span class="text"><c:out value="${assetDetail.location}" /></span>
				<spring:input path="location" size="20" cssClass="shadow hidden" autocomplete="off"/>
			</td>
			<td>&nbsp;</td>
			<td class="non-highlight">价格:</td>
			<td>
				<span class="text"><c:out value="${assetDetail.price}" /></span>
				<spring:input path="price" size="20" cssClass="shadow hidden" autocomplete="off"/>
			</td>
			<td>&nbsp;</td>
			<td class="non-highlight"><img  src="img/u1/mandatory.png" class="hidden"/>状态:</td>
			<td>
				<span class="text"><spr:message code="${assetDetail.status}"/></span>
				<spring:select path="status" cssClass="shadow hidden">
					<spring:option value="">--请选择--</spring:option>
					<spring:option value="breakdown">故障中</spring:option>
					<spring:option value="expired">已过期</spring:option>
					<spring:option value="fixing">维修中</spring:option>
					<spring:option value="idle">闲置</spring:option>
					<spring:option value="in_use">使用中</spring:option>
					<spring:option value="orderring">购买中</spring:option>
					<spring:option value="suspended">暂停使用</spring:option>
					<spring:option value="wait_for_fix">等待维修</spring:option>
				</spring:select>
			</td>
		</tr>
		<tr>
			<td class="non-highlight">资产用途:</td>
			<td>
				<span class="text"><c:out value="${assetDetail.assetUsage}" /></span>
				<spring:input path="assetUsage" size="20" cssClass="shadow hidden" autocomplete="off"/>
			</td>
			<td>&nbsp;</td>
			<td class="non-highlight">过期时间:</td>
			<td>
				<span class="text"><c:out value="${assetDetail.expiredDate}" /></span>
				<spring:input path="expiredDate" size="20" cssClass="shadow hidden dateinput" autocomplete="off"/>	
			</td>
			<td>&nbsp;</td>
			<td class="non-highlight">权重值:</td>
			<td>
				<span class="text"><c:out value="${assetDetail.weight}" /></span>
				<spring:input path="weight" size="20" cssClass="shadow hidden" autocomplete="off"/>	
			</td>
		</tr>
		<tr>
			<td class="non-highlight">操作系统:</td>
			<td>
				<span class="text"><c:out value="${assetDetail.operatingSystem}" /></span>
				<spring:input path="operatingSystem" size="20" cssClass="shadow hidden" autocomplete="off"/>
			</td>
			<td colspan=6>&nbsp;</td>
		</tr>
		<c:set var="index">0</c:set>
		<c:set var="offset">0</c:set>
		<c:set var="lastsection"></c:set>
		<tr>
			<c:forEach items="${assetDetail.assetType.templateList}" var="attr">
				<c:choose>
					<c:when test="${attr.enabled}">
						<c:if test="${index==0 || attr.section != lastsection}">
							<c:if test="${(index+offset)%3 != 0 }">
								<td colspan=<c:out value="${(index+offset)%3*3}"/>>&nbsp;</td></tr><tr>
								<c:set var="offset"><c:out value="${offset+3-(index+offset)%3 }"/></c:set>
							</c:if>
							<th colspan=8 align=right>自定义属性-<c:out value="${attr.section }"/>&nbsp;&nbsp;</th></tr><tr>
						</c:if>
						<c:set var="lastsection"><c:out value="${attr.section }"/></c:set>
						
						<c:choose>
							<c:when test="${attr.attributeType == 'MULTI_SELECT'}">
								<c:if test="${(index+offset)%3 != 0 }">
									<td colspan=<c:out value="${(index+offset)%3*3}"/>>&nbsp;</td></tr><tr>
									<c:set var="offset"><c:out value="${offset+3-(index+offset)%3 }"/></c:set>
								</c:if>
								<td class="non-highlight">
									<c:if test="${attr.required == true}">
										<img  src="img/u1/mandatory.png" class="hidden"/>
									</c:if>
									<c:out value="${attr.attributeName}"/>:&nbsp;
								</td>
								<td colspan=7>
									<span class="text">
										<c:set var="optionindex">0</c:set>
										<c:forEach items="${attr.selectOptionList}" var="option">
											<c:if test="${assetDetail.assetType.existingAttributeIdValues[1][index][optionindex]==true}">
												<u><c:out value="${option.description }"/></u>&nbsp;&nbsp;
											</c:if>
											<c:set var="optionindex"><c:out value="${optionindex+1}"/></c:set>
										</c:forEach>
									</span>
									<span class="hidden">
										<c:set var="optionindex">0</c:set>
										<c:forEach items="${attr.selectOptionList}" var="option">
											<c:choose>
												<c:when test="${assetDetail.assetType.existingAttributeIdValues[1][index][optionindex]==true}">
													<input name="u1teamplate_<c:out value='${attr.assetAttrTemplatePid}'/>" type="checkbox" value="${option.attrSelectOptionPid}" class="shadow" checked="checked"/>
												</c:when>
												<c:otherwise>
													<input name="u1teamplate_<c:out value='${attr.assetAttrTemplatePid}'/>" type="checkbox" value="${option.attrSelectOptionPid}" class="shadow"/>
												</c:otherwise>
											</c:choose>
											&nbsp;<u><c:out value="${option.description }"/></u>&nbsp;&nbsp;&nbsp;&nbsp;
											<c:set var="optionindex"><c:out value="${optionindex+1}"/></c:set>
										</c:forEach>
									</span>
								</td>
								<c:set var="offset"><c:out value="${offset+2 }"/></c:set>
							</c:when>
							<c:otherwise>
								<c:if test="${(index+offset)%3 != 0 }">
									<td>&nbsp;</td>
								</c:if>
								<td class="non-highlight">
									<c:if test="${attr.required == true}">
										<img  src="img/u1/mandatory.png" class="hidden"/>
									</c:if>
									<c:out value="${attr.attributeName}"/>:&nbsp;
								</td>
								<td>
									<span class="text">
										<c:if test="${attr.attributeType == 'ENTRY' || attr.attributeType == 'CALENDAR'}">
											<c:out value="${assetDetail.assetType.existingAttributeIdValues[1][index]}"/>
										</c:if>
										<c:if test="${attr.attributeType == 'BOOLEAN'}">
											<c:if test="${assetDetail.assetType.existingAttributeIdValues[1][index]=='true'}">是</c:if>
											<c:if test="${assetDetail.assetType.existingAttributeIdValues[1][index]=='false'}">否</c:if>
										</c:if>
										<c:if test="${attr.attributeType == 'SELECT'}">
											<c:forEach items="${attr.selectOptionList}" var="option">
												<c:if test="${assetDetail.assetType.existingAttributeIdValues[1][index] == option.attrSelectOptionPid}">
													<c:out value="${option.description}"></c:out>
												</c:if>
											</c:forEach>
										</c:if>
									</span>
									<span class="hidden">
										<c:if test="${attr.attributeType == 'ENTRY'}">
											<input name="u1teamplate_<c:out value='${attr.assetAttrTemplatePid}'/>" value="${assetDetail.assetType.existingAttributeIdValues[1][index]}" size=20 class="shadow"/>
										</c:if>
										<c:if test="${attr.attributeType == 'CALENDAR'}">
											<input name="u1teamplate_<c:out value='${attr.assetAttrTemplatePid}'/>" value="${assetDetail.assetType.existingAttributeIdValues[1][index]}" size=20 maxlength=10 class="shadow dateinput"/>
										</c:if>
										<c:if test="${attr.attributeType == 'BOOLEAN'}">
											<select name="u1teamplate_<c:out value='${attr.assetAttrTemplatePid}'/>" value="${assetDetail.assetType.existingAttributeIdValues[1][index]}" class="shadow">
												<option value="">--请选择--</option>
												<c:choose>
													<c:when test="${assetDetail.assetType.existingAttributeIdValues[1][index]=='true'}">
														<option value="true" selected=selected>是</option>
													</c:when>
													<c:otherwise>
														<option value="true">是</option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${assetDetail.assetType.existingAttributeIdValues[1][index]=='false'}">
														<option value="false" selected=selected>否</option>
														
													</c:when>
													<c:otherwise>
														<option value="false">否</option>
													</c:otherwise>
												</c:choose>
											</select>
										</c:if>
										<c:if test="${attr.attributeType == 'SELECT'}">
											<select name="u1teamplate_<c:out value='${attr.assetAttrTemplatePid}'/>" value="${assetDetail.assetType.existingAttributeIdValues[1][index]}" class="shadow">
												<option value="">--请选择--</option>
												<c:forEach items="${attr.selectOptionList}" var="option">
													<c:choose>
														<c:when test="${assetDetail.assetType.existingAttributeIdValues[1][index] == option.attrSelectOptionPid}">
															<option value="${option.attrSelectOptionPid}" selected=selected><c:out value="${option.description }"/></option>
														</c:when>
														<c:otherwise>
															<option value="${option.attrSelectOptionPid}"><c:out value="${option.description }"/></option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</c:if>
									</span>
								</td>
							</c:otherwise>
						</c:choose>
						
						<c:if test="${(index+offset)%3 == 2 }">
							</tr><tr>
						</c:if>
					</c:when>
					<c:otherwise>
						<c:set var="offset"><c:out value="${offset-1 }"/></c:set>
					</c:otherwise>
				</c:choose>
				<c:set var="index"><c:out value="${index+1 }"/></c:set>
			</c:forEach>
		</tr>
		<c:if test="${accessRights[4]}">
		<tr class="noprint">
			<td colspan=8 align=right>
				<div id="viewbutton">
					<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow" onclick="javascript:edit_asset();">
						<span class="ui-button-text">编辑</span>
					</button>
				</div>
				<div id="editbutton" class="hidden">
					<input name="remark" size="60" placeholder="你可以在这里输入备注作为日志参考备注" class="shadow"/>
					<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow">
						<span class="ui-button-text">提交</span>
					</button>
					<button type="reset" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow">
						<span class="ui-button-text">重置</span>
					</button>
				</div>
			</td>
		</tr>
		</c:if>
	</table>
	</spring:form>
</div>
</div>
<script>
$(document).ready(function(){
	tabsbindurl($("#assetdetailtaps li"),['','assetattachment/<c:out value="${assetDetail.assetPid}"/>','assettask/<c:out value="${assetDetail.assetPid}"/>','assetingroup/<c:out value="${assetDetail.assetPid}"/>','assetlog/<c:out value="${assetDetail.assetPid}"/>'], [$("#assetDetailDiv")],"selected");
	$.each($(".text"), function(i, val){      
	    if($(".text").eq(i).html().trim()==''){
	    	$(".text").eq(i).html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');
	    }    
	});
	$('#copyasset').submit(function(){
		slide_to_show_detail_content(null, 'addasset','newasset', null, null, true, $(this).serialize());
		return false;	
	});
	$('#raisetask').submit(function(){
		slide_to_show_detail_content(null, 'raisetaskforasset','newtask', null, null, true, $(this).serialize());
		return false;
	});
});
function edit_asset(){
	$("#assetdetailtable .text").hide();
	$("#assetdetailtable .hidden").fadeIn();
	$("#assetdetailtable img").fadeIn();
	$("#assetdetailtable .dateinput").datepicker();
	$("#assetdetailtable #viewbutton").hide();
	$("#assetdetailtable #editbutton").fadeIn();
}
$("#assetDetail").submit(function(){
	var link='updateasset/'+ <c:out value="${assetDetail.assetPid}"/>;
	ajax_call_post(link, $(this).serialize(), true, afterupdateasset);
	return false;
});
function afterupdateasset(data){
	if(!validateText(data)){
		var message=success_update_asset.replace('~~0', $("#assetDetail #assetName").val());
		var lastrow = divs[divs.length-1][0];
		if(lastrow != undefined){
			var tdlist = lastrow.children('td');
			if(tdlist.length>0){
				var tds=[tdlist.eq(0), tdlist.eq(3), tdlist.eq(4), tdlist.eq(5)];
				var values=[$("#assetDetail #assetName").val(),$("#assetDetail #location").val(), $("#assetDetail #expiredDate").val(),$("#assetDetail #status").find("option:selected").text()];
				var prefill0s=[false, false, false, false];
				prefillvalue(tds, values, prefill0s);
			}
		}
		refresh_current_content(message);
	}
}
</script>