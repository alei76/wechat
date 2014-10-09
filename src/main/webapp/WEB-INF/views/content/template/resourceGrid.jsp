<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- 富媒体资源选择页面，分页面 --%>
<%-- template_02.jsp，template_03.jsp，template_04.jsp为总页面 --%>
<c:if test="${fn:length(page.rows) gt 0 }">
	<div class="kernel_menus">
		<ul>
			<c:forEach var="resource" items="${page.rows }" varStatus="index">
				<li id="li_resource_${resource.attaId }">
					<div class="item">
						<img src="${pageContext.request.contextPath}/anon/image/prev/${resource.attaId}" />
						<p>
							<input type="radio" path="${resource.attaId}" data-options="${resource.name}" name="resource" id="resource_${resource.attaId }" value="${resource.attaId }" />
							<label for="resource_${resource.attaId }">${fn:substring(resource.name,0,10) }</label>
						</p>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</c:if>
<input type="hidden" id="resouces_total" value="${page.total }"/>
<input type="hidden" id="resouces_pagesize" value="${page.pageSize }"/>