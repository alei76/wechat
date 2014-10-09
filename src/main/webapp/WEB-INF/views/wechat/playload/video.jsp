<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>${mediaAtta.name }</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<meta name="format-detection" content="telephone=no" />
		<link href="<%=request.getContextPath()%>/css/jsVideo/video-js.min.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsVideo/video.js"></script>
		<script type="text/javascript">
			var global_ctxPath = '<%=request.getContextPath()%>';
			videojs.options.flash.swf = global_ctxPath + "/scripts/jsVideo/video-js.swf";
		</script>
	</head>
	<body id="activity-detail">
		<div class="rich_media">
			<div class="rich_media_inner">
				<h2 class="rich_media_title" id="activity-name">${mediaAtta.name }</h2>
				<div id="page-content">
					<div id="img-content">
						<div class="rich_media_content" id="js_content">
							<p>建议在WiFi下观看</p>
							<p>
								<video class="video-js vjs-default-skin" controls preload="none" width="320" height="200" poster="${ pageContext.request.contextPath}/anon/image/prev/${mediaAtta.attaId}?r=${covisint:timemillis()}">
									<source src="${ pageContext.request.contextPath}/anon/media/prev/${mediaAtta.attaId}?r=${covisint:timemillis()}" type="video/mp4" />
								</video>
								<br />
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>

