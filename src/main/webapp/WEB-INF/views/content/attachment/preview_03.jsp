<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<link href="<%=request.getContextPath()%>/css/jsVideo/video-js.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsVideo/video.js"></script>
<script type="text/javascript">
	var global_ctxPath = '<%=request.getContextPath()%>';
	videojs.options.flash.swf = global_ctxPath + "/scripts/jsVideo/video-js.swf";
</script>
<video class="video-js vjs-default-skin" controls preload="none" width="640" height="320" poster="${ pageContext.request.contextPath}/anon/image/prev/${mediaAtta.attaId}?r=${covisint:timemillis()}">
	<source src="${ pageContext.request.contextPath}/anon/media/prev/${mediaAtta.attaId}?r=${covisint:timemillis()}" type="video/mp4" />
</video>
