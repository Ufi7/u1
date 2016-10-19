<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
	<c:when test="${count == 0}">
		找不到相关记录
	</c:when>
	<c:otherwise>
		<c:set var="i" value="5"></c:set>
		<c:choose>
			<c:when test="${currentPage==1}">
				<span class="navtext">&lArr;</span>
				<span class="navtext">&larr;</span>
			</c:when>
			<c:otherwise>
				<a class="navlink" href='javascript:page("1");'>&lArr;</a>
				<a class="navlink" href='javascript:page("<c:out value='${currentPage-1 }'/>");'>&larr;</a>
			</c:otherwise>
		</c:choose>
		
		<c:if test="${currentPage-i>1}">
			<a class="navlink" href='javascript:page("1");'>1</a>
		</c:if>
		<c:if test="${currentPage-i>2}">
			&nbsp;&nbsp;...&nbsp;...&nbsp;&nbsp;
		</c:if>
		<c:forEach var="x" begin="${currentPage-i>=1?currentPage-i:1}" end="${totalPage-currentPage>=i?currentPage+i:totalPage}" step="1">
			<c:choose>
				<c:when test="${x==currentPage }">
					<span class="navtext navcurrent"><c:out value='${x}'/></span>
				</c:when>
				<c:otherwise>
					<a class="navlink" href='javascript:page("<c:out value='${x}'/>");'><c:out value='${x}'/></a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${totalPage-currentPage>i+1}">
			&nbsp;&nbsp;...&nbsp;...&nbsp;&nbsp;
		</c:if>
		<c:if test="${totalPage-currentPage>i}">
			<a class="navlink" href='javascript:page("<c:out value='${totalPage }'/>");'><c:out value='${totalPage }'/></a>
		</c:if>
		<c:choose>	
			<c:when test="${currentPage==totalPage}">
				<span class="navtext">&rarr;</span>
				<span class="navtext">&rArr;</span>
			</c:when>
			<c:otherwise>
				<a class="navlink" href='javascript:page("<c:out value='${currentPage+1 }'/>");'>&rarr;</a>
				<a class="navlink" href='javascript:page("<c:out value='${totalPage }'/>");'>&rArr;</a>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>

