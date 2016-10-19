<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<tr class=attachattributes>
			<c:set var="index">0</c:set>
			<c:set var="offset">0</c:set>
			<c:set var="lastsection"></c:set>
			<c:forEach items="${newasset.assetType.templateList}" var="attr">
				<c:choose>
					<c:when test="${attr.enabled}">
						<c:if test="${index==0 || attr.section != lastsection}">
							<c:if test="${(index+offset)%3 != 0 }">
								<td colspan=<c:out value="${(index+offset)%3*3}"/>>&nbsp;</td></tr><tr class=attachattributes>
								<c:set var="offset"><c:out value="${offset+3-(index+offset)%3 }"/></c:set>
							</c:if>
							<th colspan=8 align=right>自定义属性-<c:out value="${attr.section }"/>&nbsp;&nbsp;</th></tr><tr class=attachattributes>
						</c:if>
						<c:set var="lastsection"><c:out value="${attr.section }"/></c:set>
						
						<c:choose>
							<c:when test="${attr.attributeType == 'MULTI_SELECT'}">
								<c:if test="${(index+offset)%3 != 0 }">
									<td colspan=<c:out value="${(index+offset)%3*3}"/>>&nbsp;</td></tr><tr class=attachattributes>
									<c:set var="offset"><c:out value="${offset+3-(index+offset)%3 }"/></c:set>
								</c:if>
								<td class="non-highlight">
									<c:if test="${attr.required == true}">
										<img  src="img/u1/mandatory.png" class=""/>
									</c:if>
									<c:out value="${attr.attributeName}"/>:&nbsp;
								</td>
								<td colspan=7>
									<span class="">
										<c:set var="optionindex">0</c:set>
										<c:forEach items="${attr.selectOptionList}" var="option">
											<c:choose>
												<c:when test="${newasset.assetType.existingAttributeIdValues[1][index][optionindex]==true}">
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
										<img  src="img/u1/mandatory.png" class=""/>
									</c:if>
									<c:out value="${attr.attributeName}"/>:&nbsp;
								</td>
								<td>
									<span class="">
										<c:if test="${attr.attributeType == 'ENTRY'}">
											<input name="u1teamplate_<c:out value='${attr.assetAttrTemplatePid}'/>" value="${newasset.assetType.existingAttributeIdValues[1][index]}" size=20 class="shadow"/>
										</c:if>
										<c:if test="${attr.attributeType == 'CALENDAR'}">
											<input name="u1teamplate_<c:out value='${attr.assetAttrTemplatePid}'/>" value="${newasset.assetType.existingAttributeIdValues[1][index]}" size=20 maxlength=10 class="shadow dateinput"/>
										</c:if>
										<c:if test="${attr.attributeType == 'BOOLEAN'}">
											<select name="u1teamplate_<c:out value='${attr.assetAttrTemplatePid}'/>" value="${newasset.assetType.existingAttributeIdValues[1][index]}" class="shadow">
												<option value="">--请选择--</option>
												<c:choose>
													<c:when test="${newasset.assetType.existingAttributeIdValues[1][index]=='true'}">
														<option value="true" selected=selected>是</option>
													</c:when>
													<c:otherwise>
														<option value="true">是</option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${newasset.assetType.existingAttributeIdValues[1][index]=='false'}">
														<option value="false" selected=selected>否</option>
														
													</c:when>
													<c:otherwise>
														<option value="false">否</option>
													</c:otherwise>
												</c:choose>
											</select>
										</c:if>
										<c:if test="${attr.attributeType == 'SELECT'}">
											<select name="u1teamplate_<c:out value='${attr.assetAttrTemplatePid}'/>" value="${newasset.assetType.existingAttributeIdValues[1][index]}" class="shadow">
												<option value="">--请选择--</option>
												<c:forEach items="${attr.selectOptionList}" var="option">
													<c:choose>
														<c:when test="${newasset.assetType.existingAttributeIdValues[1][index] == option.attrSelectOptionPid}">
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
							</tr><tr class=attachattributes>
						</c:if>
					</c:when>
					<c:otherwise>
						<c:set var="offset"><c:out value="${offset-1 }"/></c:set>
					</c:otherwise>
				</c:choose>
				<c:set var="index"><c:out value="${index+1 }"/></c:set>
			</c:forEach>
		</tr>
		<script>
		$(document).ready(function(){
			$(".dateinput").datepicker();
		});
		</script>