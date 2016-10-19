<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="insidecontent-extend">
	<div class="u1title1"><h2>新建资产</h2></div>
	<div class="contentbox" id="asstattributes">
		<spring:form commandName="newasset">
			<table class="detailtable" id="newassettable">
				<tbody>
					<tr>
						<th colspan=8 align=right>
						基本属性&nbsp;&nbsp;
						</th>
					</tr>
					<c:if test="${accessRights[8]}">
					<tr>
						<td class="non-highlight"><img  src="img/u1/mandatory.png" class=""/>所属客户:</td>
						<td colspan=7>
							<select name="customer" id="customer" class="shadow">
								<c:choose>
									<c:when test="${newasset.customer!=null}">
										<option value="${newasset.customer.customerId}" selected=selected><c:out value="${newasset.customer.customerName}"/></option>
									</c:when>
									<c:otherwise>
										<option  value="${self[2]}"><c:out value="${self[3] }"/></option>
									</c:otherwise>
								</c:choose>
							</select>
							<script>
								$(document).ready(function(){
									buildOptionBasedOnAjaxReturn($("#newasset #customer"), 'option/customerlist');
								});
							</script>
						</td>
					</tr>
					</c:if>
					<tr>
						<td class="non-highlight"><img  src="img/u1/mandatory.png" class=""/>资产类型:</td>
						<td>
							<c:choose>
								<c:when test="${newasset.assetType!=null}">
									<span class="fixtext"><c:out value="${newasset.assetType.code}"/></span>
									<input type="hidden" name="assetType" value="${newasset.assetType.assetTypePid}">
									<script>
									function reset_new_asset_form(){
										$("#newasset")[0].reset();
									}
									</script>
								</c:when>
								<c:otherwise>
									<select name="assetType" id="assetType" class="shadow">
										<option value="">--请选择--</option>
									</select>
									<script>
									$(document).ready(function(){
										buildOptionBasedOnAjaxReturn($("#newasset #assetType"), 'option/assettypelist');
										$("#newasset #assetType").change( function() {
											$("#newasset .attachattributes").remove();
											if($(this).val()!=''){
												var link= 'addasset/'+$(this).val();
												ajax_call_get(link, true, append_asset_attributes);
											}
										});
									});
									function reset_new_asset_form(){
										$("#newasset .attachattributes").remove();
										$("#newasset")[0].reset();
									}
									</script>
								</c:otherwise>
							</c:choose>
						</td>
						<td>&nbsp;</td>
						<td class="non-highlight"><img  src="img/u1/mandatory.png" class=""/>名称:</td>
						<td>
							<spring:input path="assetName" size="20" cssClass="shadow" autocomplete="off"/>
						</td>
						<td>&nbsp;</td>
						<td class="non-highlight">自定义编号:</td>
						<td>
							<spring:input path="customNum" size="20" cssClass="shadow" autocomplete="off"/>
						</td>
					</tr>
					<tr>
						<td class="non-highlight">IP地址:</td>
						<td>
							<spring:input path="host" size="20" cssClass="shadow" autocomplete="off"/>
						</td>
						<td>&nbsp;</td>
						<td class="non-highlight">使用者:</td>
						<td>
							<spring:input path="owner" size="20" cssClass="shadow" autocomplete="off"/>
						</td>
						<td>&nbsp;</td>
						<td class="non-highlight">位置:</td>
						<td>
							<spring:input path="location" size="20" cssClass="shadow" autocomplete="off"/>
						</td>
					</tr>
					<tr>
						<td class="non-highlight">价格:</td>
						<td>
							<spring:input path="price" size="20" cssClass="shadow" autocomplete="off"/>
						</td>
						<td>&nbsp;</td>
						<td class="non-highlight"><img  src="img/u1/mandatory.png" class=""/>状态:</td>
						<td>
							<spring:select path="status" cssClass="shadow">
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
						<td>&nbsp;</td>
						<td class="non-highlight">资产用途:</td>
						<td>
							<spring:input path="assetUsage" size="20" cssClass="shadow" autocomplete="off"/>
						</td>
					</tr>
					<tr>
						<td class="non-highlight">过期时间:</td>
						<td>
							<spring:input path="expiredDate" size="20" cssClass="shadow dateinput" autocomplete="off"/>	
						</td>
						<td>&nbsp;</td>
						<td class="non-highlight">权重值:</td>
						<td>
							<spring:input path="weight" size="20" cssClass="shadow" autocomplete="off"/>	
						</td>
						<td>&nbsp;</td>
						<td class="non-highlight">操作系统:</td>
						<td>
							<spring:input path="operatingSystem" size="20" cssClass="shadow" autocomplete="off"/>
						</td>
					</tr>
					<c:if test="${newasset.assetType!=null}">
						<jsp:include page="addassetdetail.jsp"></jsp:include>
					</c:if>
				</tbody>
				<tfoot>
					<tr>
						<td colspan=8 align=right>
							<input name="remark" size="60" placeholder="你可以在这里输入备注作为日志参考" class="shadow"/>
							<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow">
								<span class="ui-button-text">提交</span>
							</button>
							<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow" onclick="javascript:reset_new_asset_form();">
							<span class="ui-button-text">重置</span>
							</button>
						</td>
					</tr>
				</tfoot>
			</table>
		</spring:form>
	</div>
</div>
<SCRIPT type="text/javascript">
	$(document).ready(function(){
		init1stdivs('addasset', 'newasset');
		$("#newasset #expiredDate").datepicker();
		$("#newasset").submit(function(){
			var link='newassetsubmit';
			ajax_call_post(link, $(this).serialize(), true, function(data){
				if(!validateText(data)){
					show_loading_message('资产创建成功，正在为你转向资产详情页面...');
					slide_to_show_detail_content(null, 'assetdetail/'+data, 'asset', '资产已成功创建', afteraddasset);
				}
			});
			return false;
		});
	});
	function append_asset_attributes(data){
		if(!validateText(data)){
			$("#newassettable").append(data);
		}
	}
	function afteraddasset(data){
		$(".attachattributes").remove();
		$('#newasset')[0].reset();
	}
</SCRIPT>