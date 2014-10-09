<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsAudio/audio.min.js"></script>
<script type="text/javascript">
	audiojs.events.ready(function() {
		audiojs.createAll();
	});
</script>
<audio src="${ pageContext.request.contextPath}/anon/media/prev/${mediaAtta.attaId}?r=${covisint:timemillis()}" preload="auto" type="audio/mp3"></audio>
