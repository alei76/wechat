<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Welcome</title>
		<%@ include file="/common/resource.jsp"%>
		<style type="text/css">
			.file { position:absolute; width: 50px; height:20px; display:none;filter:alpha(opacity=0);opacity:0;}
			.span_file { padding:5px;background:#5666CC; color:#fff; }
			
			.product_ad table{ border-collapse:collapse; border-top:1px solid #ddd; border-left:1px solid #ddd; background: #fff;}
			.product_ad table td, .product_ad table th{ color:#666; line-height:18px;padding:5px 10px; border-bottom:1px solid #ddd; border-right:1px solid #ddd; height:40px;}
			.product_ad table th{ color:#333; height:30px; line-height:30px; font-weight:normal; font-size: 13px;background: #EFF5FE; border:1px solid #ddd; }
			.product_ad table td img, .product_ad table td span{ vertical-align:middle;}
		</style>
		<!-- 如果是音频，加载音频js -->
		<c:if test="${mediaAtta.type eq '02' }">
			<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsAudio/audio.min.js"></script>
			<script type="text/javascript">
				audiojs.events.ready(function() {
					audiojs.createAll();
				});
			</script>
		</c:if>
		<!-- 如果是视频，加载视频js -->
		<c:if test="${mediaAtta.type eq '03' }">
			<link href="<%=request.getContextPath()%>/css/jsVideo/video-js.min.css" rel="stylesheet" type="text/css" />
			<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsVideo/video.js"></script>
			<script type="text/javascript">
				videojs.options.flash.swf = global_ctxPath + "/scripts/jsVideo/video-js.swf";
			</script>
		</c:if>
		
		<script type="text/javascript">
			var rootPath = '/content/attachment';
			change_iframe_height(650);//更改当前iframe高度为650
			$(function(){
				//鼠标滑动至文字的dom时将目标file显示，否则在ie下，点击文字无法弹出选择文件对话框
				$(".span_file").mouseover(function(){
					var domId = $(this).attr("for");
					$("#" + domId).css("display","block");
				});
				//监听table下的file对象，当选择文件时触发
				$('#media_edit_table').on('change', '.file', function(){
					if(this.value!=''){//选择了文件
						var domId = $(this).attr("target");
						var path = this.value.split('\\');//截取路径
						var name = path[path.length-1];//截取文件名
						var regular = $(this).attr("ext");//获取file对象能支持的后缀格式
						var suffix = name.substring(name.lastIndexOf("."),name.length).toLowerCase();//获取文件后缀
						var valid = regular.indexOf(suffix) > 0;//判断是否符合需要的文件格式
						if(valid){
							$("#" + domId).html(name).attr("error", "0");//将文件名显示到html中，并设置标志'没有错误'。
							$(this).hide();//file对象隐藏
						} else {
							$.messager.alert('提示', '您选择的文件格式不正确', 'info');//弹出对话框提示
							resetFile(this);//重新file控件
						}
					}
				});
			});
			//重置file控件
			function resetFile(file) {
				var msie = /msie/.test(navigator.userAgent.toLowerCase());
				file.value = "";
				if (file.value) {
					if (msie) {
						with (file.parentNode.insertBefore(document.createElement('form'), file)) {
							appendChild(file);
							reset();
							removeNode(false);
						}
					} else {
						file.type = "text";
						file.type = "file";
					}
				}
				var domId = $(file).attr("target");
				var tip = $(file).attr("tip");
				$("#" + domId).html(tip).attr("error", "1");//将文字显示提示语，并设置标志'存在错误'
			}
			//保存多媒体文件
			function save_atta_media(){
				var error = $("span[error='1']");//如果有错误
				if(error.length > 0){
					$.messager.alert('提示', '您选择正确的文件格式', 'info');//提示有错误
				} else {
					var attaId = $("#media_attaId").val();//多媒体ID
					var name = $("#media_name").val();//多媒体名称
					$.ajaxFileUpload({
						url : global_ctxPath + rootPath + "/editmedia",
						fileElementId : [ 'image_file', 'media_audio_video'],//文件对象
						param : {
							"attaId" : attaId,
							"name" : name
						},
						success : function(data) {
							$.messager.alert('提示', data.message, 'info', function(){
								if(data.success){//保存成功
									location.href =  global_ctxPath + rootPath + '/info/' + attaId;//刷新当前页面
								}
							});
						}
					});
				}
			}
			//返回到列表页面
			function back_list(){
				location.href =  global_ctxPath + rootPath + '/list';
			}
		</script>
		
	</head>
	<body class="product_ad">
		<table id="media_edit_table" style="width: 100%; padding: 5px;" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<th align="right" width="90px">资源名称:</th>
				<td colspan="2">
					<input id="media_attaId" type="hidden" value="${mediaAtta.attaId }"/>
					<input id="media_name" style="width: 230px;" type="text" value="${mediaAtta.name }"/>
				</td>
			</tr>
			<tr>
				<th align="right">图片:</th>
				<td width="100px">
					<input type="file" target="image_tip" class="file" id="image_file" tip="*.jpg,*.png,*.gif" ext="*.jpg,*.png,*.gif"/>
					<label for="image_file" class="span_file">选择图片</label>
				</td>
				<td>
					<!-- 初始默认标志'没有错误' -->
					<span id="image_tip" error="0">
						<img height="200px" alt="${mediaAtta.name }" src="${ pageContext.request.contextPath}/anon/image/prev/${mediaAtta.attaId}?r=${covisint:timemillis()}" />
					</span>
				</td>
			</tr>
			<!-- 不是图片 -->
			<c:if test="${mediaAtta.type ne '01' }">
			<tr>
				<th align="right">媒体文件:</th>
				<!-- 音频 -->
				<c:if test="${mediaAtta.type eq '02' }">
					<td>
						<input type="file" target="audio_video_tip" class="file" id="media_audio_video" tip="*.mp3" ext="*.mp3"/>
						<label for="media_audio_video" class="span_file">选择文件</label>
					</td>
					<td>
						<!-- 初始默认标志'没有错误' -->
						<span id="audio_video_tip" error="0">
							<audio src="${ pageContext.request.contextPath}/anon/media/prev/${mediaAtta.attaId}?r=${covisint:timemillis()}" preload="auto" type="audio/mpeg"></audio>
						</span>
					</td>
				</c:if>
				<!-- 视频 -->
				<c:if test="${mediaAtta.type eq '03' }">
					<td>
						<input type="file" target="audio_video_tip" class="file" id="media_audio_video" tip="*.mp4" ext="*.mp4"/>
						<label for="media_audio_video" class="span_file">选择文件</label>
					</td>
					<td>
						<!-- 初始默认标志'没有错误' -->
						<span id="audio_video_tip" error="0">
							<video class="video-js vjs-default-skin" controls preload="none" width="320" height="200" poster="${ pageContext.request.contextPath}/anon/image/prev/${mediaAtta.attaId}?r=${covisint:timemillis()}">
								<source src="${ pageContext.request.contextPath}/anon/media/prev/${mediaAtta.attaId}?r=${covisint:timemillis()}" type="video/mp4" />
							</video>
						</span>
					</td>
				</c:if>
			</tr>
			</c:if>
			<!-- 是图片，放一个空的file对象-->
			<c:if test="${mediaAtta.type eq '01' }">
			<tr style="display:none;">
				<td colspan="3">
					<input type="file" id="media_audio_video" />
				</td>
			</tr>
			</c:if>
			<tr>
				<td colspan="3">
					<a href="javascript:void(0);" onclick="back_list()" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a>
					<a href="javascript:void(0);" onclick="save_atta_media()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存修改</a>
				</td>
			</tr>
		</table>
	</body>
</html>