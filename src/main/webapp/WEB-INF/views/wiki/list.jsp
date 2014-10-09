<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>公众平台开发者文档</title>
	<%@ include file="/common/resource.jsp"%>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/wiki/wiki.css"/>
	<!--[if IE 6]>
	<style type="text/css">
		#content{overflow-x:hidden;}
	</style>
	<![endif]-->
	<script type="text/javascript">
		var rootPath = '/anon/wiki';
	
		$(function() {
			var a = $("#mw-panel").find("div:first").find(".body a:first");
			var tag = a.data("tag");
			show_content(tag);
			
			
			$("#mw-panel").find("h5").click(function(){
				var t = $(this),b = t.next(".body"),p = t.parent();
				if (b.is(":visible")){
					b.slideUp("fast");
					p.removeClass("active");
				}else{
					b.slideDown("fast");
					p.addClass("active");
				}
				$(this).parent().siblings(".portal").removeClass("active").find(".body").slideUp("fast");
			});
			
			$("#mw-panel").find("div .body a").click(function(){
				var a = $(this),tag = a.data("tag");
				$("#firstHeading").html(a.html());
				show_content(tag);
			});
			
		});
		
		function show_content(tag){
			$.ajax({
				type : "get",
				url : global_ctxPath + rootPath + '/content',
				data : {
					"tag" : tag
				},
				success : function(result) {
					$("#bodyContent").html(result);
				}
			});
		}
	</script>
</head>
<body>
	<div class="headWrap">
		<div id="header" class="w_header">
			<div class="inner">
	            <a href="${ pageContext.request.contextPath}/anon/wiki/list" class="logo"><img alt="微信公众平台开发者文档" src="${ pageContext.request.contextPath}/images/wiki.png"></a>
			</div>
		</div>
	</div>

	<div class="mainwrapper">
		<div class="inner">
			<!-- panel -->
			<div id="mw-panel" class="noprint">
				<!-- 新手接入 -->
				<div style="margin-top: -1px;" class="portal active">
					<h5>
						<span class="portal_arrow"></span>新手接入
					</h5>
					<div style="display: block;" class="body">
						<ul>
							<li><a href="javascript:;" data-tag="jrzn">接入指南</a></li>
						</ul>
					</div>
				</div>
				<!-- /新手接入 -->

				<!-- 基础支持 -->
				<div style="margin-top: -1px;" class="portal">
					<h5>
						<span class="portal_arrow"></span>基础支持
					</h5>
					<div class="body">
						<ul>
							<li><a href="javascript:;" data-tag="token">获取access_token</a></li>
							<li><a href="javascript:;" data-tag="scxz">上传下载多媒体文件</a></li>
						</ul>
					</div>
				</div>
				<!-- /基础支持 -->

				<!-- 接收消息 -->
				<div style="margin-top: -1px;" class="portal">
					<h5>
						<span class="portal_arrow"></span>接收消息
					</h5>
					<div class="body">
						<ul>
							<li><a href="javascript:;" data-tag="ptxx">接收普通消息</a></li>
							<li><a href="javascript:;" data-tag="sjts">接收事件推送</a></li>
						</ul>
					</div>
				</div>
				<!-- /接收消息 -->

				<!-- 发送消息 -->
				<div style="margin-top: -1px;" class="portal">
					<h5>
						<span class="portal_arrow"></span>发送消息
					</h5>
					<div class="body">
						<ul>
							<li><a href="javascript:;" data-tag="bdxy">发送被动响应消息</a></li>
							<li><a href="javascript:;" data-tag="kfxx">发送客服消息</a></li>
						</ul>
					</div>
				</div>
				<!-- /发送消息 -->

				<!-- 用户管理 -->
				<div style="margin-top: -1px;" class="portal">
					<h5>
						<span class="portal_arrow"></span>用户管理
					</h5>
					<div class="body">
						<ul>
							<li><a href="javascript:;" data-tag="jbxx">获取用户基本信息</a></li>
							<li><a href="javascript:;" data-tag="oauth">网页授权获取用户基本信息</a></li>
						</ul>
					</div>
				</div>
				<!-- /用户管理 -->
				
			</div>
			<div id="content">
				<div class="content_hd">
					<h2 id="firstHeading">接入指南</h2>
				</div>
				<div id="bodyContent" class="bodyContent"></div>
			</div>
		</div>
	</div>
</body>
</html>