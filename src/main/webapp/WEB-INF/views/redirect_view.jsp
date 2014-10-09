<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 微信菜单中转页面跳转 --%>
<input type="hidden" id="path" value="${path }" />
<script type="text/javascript">
	location.href = document.getElementById("path").value;
</script>
