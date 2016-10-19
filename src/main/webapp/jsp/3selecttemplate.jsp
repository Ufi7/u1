<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
    <table>
    	<tr>
    		<th class="u1background shadow">可选选项</th>
    		<th></th>
    		<th class="u1background shadow">已选选项</th>
    	</tr>
    	<tr>
    		<td>
    			<select id="select_1" size="8" multiple length="50" class="shadow">
    				<c:forEach items="${sel_full_list}" var="o">
    					<c:if test="${!sel_selected_list.contains(o)}">
    						<option value='<c:out value="${o.key }"/>'><c:out value="${o.value }"/></option>
    					</c:if>
    				</c:forEach>
				</select>
    		</td>
    		<td>
    			<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only u1background shadow" onclick="javascript:select($('#select_1 option'));">
    				<span class="ui-icon-arrowthick-1-e ui-icon"></span><span class="ui-button-text">Button with icon only</span>
    			</button>
    			<br>
    			<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only u1background shadow" onclick="javascript:select($('#select_1 option:selected'));">
    				<span class="ui-icon ui-icon-arrow-1-e"></span><span class="ui-button-text">Button with icon only</span>
    			</button>
    			<br>
    			<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only u1background shadow" onclick="javascript:select($('#select_2 option:selected'));">
    				<span class="ui-icon ui-icon-arrow-1-w"></span><span class="ui-button-text">Button with icon only</span>
    			</button>
    			<br>
    			<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only u1background shadow" onclick="javascript:select($('#select_2 option'));">
    				<span class="ui-icon ui-icon-arrowthick-1-w"></span><span class="ui-button-text">Button with icon only</span>
    			</button>
    		<!-- <a class="circlelink" href="javascript:select($('#select_1 option'));">&rArr;</a><br>
    		<a class="circlelink" href="javascript:select($('#select_1 option:selected'));">&rarr;</a><br>
    		<a class="circlelink" href="javascript:select($('#select_2 option:selected'));">&larr;</a><br>
    		<a class="circlelink" href="javascript:select($('#select_2 option'));">&lArr;</a> -->
    		</td>
    		<td>
    			<select id="select_2" size="8" multiple class="shadow">
					<c:forEach items="${sel_full_list}" var="o2">
    					<c:if test="${sel_selected_list.contains(o2)}">
    						<option value='<c:out value="${o2.key }"/>'><c:out value="${o2.value }"/></option>
    					</c:if>
    				</c:forEach>
				</select>
				<div style="display:none">
					<select name="<c:out value='${select_property_name}'/>" id="select_3" size="6" multiple="true">
						<c:forEach items="${sel_full_list}" var="o3">
							<c:choose>
								<c:when test="${sel_selected_list.contains(o3)}">
									<option value='<c:out value="${o3.key }"/>' selected="selected"><c:out value="${o3.value }"/></option>
								</c:when>
								<c:otherwise>
									<option value='<c:out value="${o3.key }"/>'><c:out value="${o3.value }"/></option>
								</c:otherwise>
							</c:choose>
					   	</c:forEach>
					</select>
				</div>
				<script type="text/javascript">
				$("option").dblclick( function() {
				  	select($(this));
				});
				function select(target){
					if(target == null)
						return false;
					multi_select_switch(target,$("#select_1"),$("#select_2"),$("#select_3"));
				}
				</script>
			</td>
    	</tr>
    </table>