<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%-- 消息预览页面 --%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/wechat/wechat.css" />

<c:if test="${message.msgType eq '05' }">
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsAudio/audio.min.js"></script>
<style type="text/css">
.audiojs{width: 340px;}
.audiojs .scrubber{width: 170px;}
</style>
</c:if>

<c:choose>
	<%-- 文本消息 --%>
	<c:when test="${message.msgType eq '01' }">
		<div class="tab_content">
			<div class="appmsg">
				<div class="appmsg_info">
					${covisint:replace(covisint:replaceLink(message.content))}
				</div>
			</div>
		</div>
	</c:when>
	<%-- 图片消息 --%>
	<c:when test="${message.msgType eq '02' }">
		<c:set var="image" value="${covisint:toMap(message.content) }"/>
		${image.mediaId }
	</c:when>
	<%-- 语音消息 --%>
	<c:when test="${message.msgType eq '03' }">
		<c:set var="voice" value="${covisint:toMap(message.content) }"/>
		${voice.mediaId }
	</c:when>
	<%-- 视频消息 --%>
	<c:when test="${message.msgType eq '04' }">
		<c:set var="video" value="${covisint:toMap(message.content) }"/>
		${video.mediaId },${video.title },${video.description }
	</c:when>
	<%-- 音频消息 --%>
	<c:when test="${message.msgType eq '05' }">
		<c:set var="music" value="${covisint:toMap(message.content) }"/>
		<audio src="${music.musicUrl }?r=${covisint:timemillis()}" preload="auto" type="audio/mp3"></audio>
	</c:when>
	<%-- 图文消息 --%>
	<c:when test="${message.msgType eq '06' }">
		<c:set var="items" value="${covisint:toList(message.content) }"/>
		<div class="tab_content">
			<div class="appmsg <c:if test="${fn:length(items) gt 1 }">multi</c:if>">
				<c:if test="${fn:length(items) eq 1 }">
					<c:set var="item" value="${items[0] }"/>
					<div class="appmsg_content">
		                <h4 class="appmsg_title"><a target="_blank" href="${item.url }">${item.title }</a></h4>
		                <div class="appmsg_thumb_wrp"><img class="appmsg_thumb" src="${item.picurl }"></div>
		                <p class="appmsg_desc">${item.description }</p>
					</div>
				</c:if>
				<c:if test="${fn:length(items) gt 1 }">
					<div class="appmsg_content">
						<c:forEach items="${items }" var="item" varStatus="index">
							<c:if test="${index.first }">
								<div class="cover_appmsg_item">
					                <h4 class="appmsg_title"><a target="_blank" href="${item.url }">${item.title }</a></h4>
					                <div class="appmsg_thumb_wrp"><img class="appmsg_thumb" src="${item.picurl }"></div>
					            </div>
							</c:if>
							<c:if test="${not index.first }">
								 <div class="appmsg_item">
					                <img class="appmsg_thumb" alt="" src="${item.picurl }">
					                <h4 class="appmsg_title"><a target="_blank" href="${item.url }">${item.title }</a></h4>
					            </div>
							</c:if>
						</c:forEach>
					</div>
				</c:if>
			</div>
		</div>
	</c:when>
</c:choose>

<c:if test="${message.msgType eq '05' }">
<script type="text/javascript">
	audiojs.events.ready(function() {
		audiojs.createAll();
	});
</script>
</c:if>
