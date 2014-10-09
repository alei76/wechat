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
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/fancybox/jquery.fancybox.css" media="screen" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dict.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.fancybox.pack.js"></script>
		<script type="text/javascript">
			var rootPath = '/content/attachment';
			change_iframe_height(650);//更改当前iframe高度为650
			
			$(function() {
				//定义媒体grid
				$('#media_atta_grid').datagrid({
					view : datagrid_view,
					emptyMsg : '没有记录',
					url : global_ctxPath + rootPath + '/grid',
					method : 'post',
					fit : true,
					border : false,
					title : '文件列表',
					fitColumns : true,
					singleSelect : true,
					pagination : true,
					rownumbers : true,
					pageSize : 10,
					pageList : [ 10, 20, 30 ],
					remoteSort : false,
					idField : 'attaId',
					loadMsg : '数据装载中......',
					columns : [ [ {
						field : 'name',
						title : '文件名称',
						width : 30,
						align : 'center'
					}, {
						field : 'typeCn',
						title : '媒体类型',
						width : 30,
						align : 'center'
					}, {
						field : 'createTime',
						title : '上传时间',
						width : 30,
						align : 'center'
					}, {
						field : 'status',
						title : '操作',
						width : 30,
						align : 'center',
						formatter : function(value, row, index) {
							var attaId = row.attaId;
							return "<a onclick=\"edit_media('" + attaId + "')\" href=\"javascript:void(0)\">编辑</a>&nbsp;&nbsp;<a onclick=\"view_media('" + attaId + "')\" href=\"javascript:void(0)\">预览</a>";
						}
					} ] ]
				});
				//定义grid分页配置
				$('#media_atta_grid').datagrid('getPager').pagination({
					displayMsg : '当前显示从{from}到{to}共{total}记录',
					beforePageText : '第',
					afterPageText : '页 共 {pages} 页',
					onBeforeRefresh : function(pageNumber, pageSize) {
						$(this).pagination('loading');
						$(this).pagination('loaded');
					}
				});
				//媒体类型下拉框
				$("#media_type").dict({
					dictCode : "MEDIA_TYPE",
					//当更改媒体类型时触发
					onChange : function(newValue, oldValue){
						if(newValue == '01' ){//图片类型
							$("#audio_video_tr").hide();//选择媒体文件行隐藏
						} else {
							if(newValue == '02'){//音频
								$("#media_audio_video").attr("ext","*.mp3");//更改限制文件后缀类型
								$("#media_audio_video").attr("tip","*.mp3");//更改提示文字
							} else {//视频
								$("#media_audio_video").attr("ext","*.mp4");//更改限制文件后缀类型
								$("#media_audio_video").attr("tip","*.mp4");//更改提示文字
							}
							$("#audio_video_tr").show();//选择媒体文件行显示
						}
						reset_file_input();//重置选择文件控件
					}
				});
				//鼠标滑动至文字的dom时将目标file显示，否则在ie下，点击文字无法弹出选择文件对话框
				$(".span_file").mouseover(function(){
					var domId = $(this).attr("for");
					$("#" + domId).css("display","block");
				});
				//监听table下的file对象，当选择文件时触发
				$('#media_atta_table').on('change', '.file', function(){
					if(this.value!=''){//选择了文件
						var domId = $(this).attr("target");
						var path = this.value.split('\\');//截取路径
						var name = path[path.length-1];//截取文件名
						var regular = $(this).attr("ext");//获取file对象能支持的后缀格式
						var suffix = name.substring(name.lastIndexOf("."),name.length).toLowerCase();//获取文件后缀
						var valid = regular.indexOf(suffix) > 0;//判断是否符合需要的文件格式
						if(valid){
							$("#" + domId).html(name);//将文件名显示到html中
							$(this).hide();//file对象隐藏
						} else {//不符合
							$.messager.alert('提示', '您选择的文件格式不正确', 'info');//弹出对话框提示
							resetFile(this);//重新file控件
						}
					}
				});
				//定义上传媒体文件对话框
				$('#media_atta_win').dialog({
					title : '上传媒体文件',
					width : 560,
					height : 220,
					cache : false,
					modal : true,
					closed : true,
					inline : false,
					buttons : [ {
						text : '保存',
						handler : upload_media_source
					}, {
						text : '关闭',
						handler : function() {
							$('#media_atta_win').dialog('close');
						}
					} ],
					onClose : function() {
						$("#media_name").val("");//媒体名称设置为空
						reset_file_input();//重置所有file对象
					},
					onOpen : function(){
						//定义打开时置屏幕中央
						$(this).dialog('move',{
							top : document.body.offsetHeight / 2 - $(this).dialog("options").height / 2,
							left : document.body.offsetWidth / 2 - $(this).dialog("options").width / 2
						});
					}
				});
			});
			//上传多媒体文件
			function upload_media_source(){
				var name = $("#media_name").val();//媒体名称
				var type = $("#media_type").combobox("getValue");//媒体类型
				$.ajaxFileUpload({
					url : global_ctxPath + rootPath + "/savemedia",
					fileElementId : [ 'image_file', 'media_audio_video'],//文件对象
					param : {
						"name" : name,
						"type" : type
					},
					success : function(data) {
						$.messager.alert('提示', data.message, 'info', function(){
							if(data.success){
								$('#media_atta_win').dialog('close');//如果成功关闭对话框
								$('#media_atta_grid').datagrid("reload");//grid重新加载
							}
						});
					}
				});
			}
			//添加媒体文件
			function add_media_atta(){
				$('#media_atta_win').dialog('open');//打开对话框
			}
			//定义搜索框搜索事件
			function searcher_box(value, name){
				//按关键字让grid重新加载数据
				$('#media_atta_grid').datagrid('load', {
					"keyword" : value
				});
			}
			//重置所有file控件
			function reset_file_input() {
				$(".file").each(function(i, j) {//循环所有file控件
					resetFile(j);//重置
				});
			}
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
				$("#" + domId).html(tip);//将文字显示提示语
			}
			//预览媒体文件
			function view_media(attaId){
				$.fancybox.open({
					autoSize : true,
					href : global_ctxPath + rootPath + "/preview/" + attaId,
					type : 'ajax',
					padding : 5,
					scrollOutside : false,
					scrolling : 'no',
					helpers:  {
				        overlay : null
				    }
				});
			}
			//编辑多媒体文件
			function edit_media(attaId){
				location.href =  global_ctxPath + rootPath + '/info/' + attaId;//将当前路径跳转至编辑路径
			}
		</script>
		<style type="text/css">
			.file { position:absolute; width: 50px; height:20px; display:none;filter:alpha(opacity=0);opacity:0;}
			.span_file { padding:5px;background:#5666CC; color:#fff; }  
		</style>
	</head>
	<body class="easyui-layout">
		<div data-options="region:'north',border:true,title:'素材管理',split:false,collapsible:false" style="height: 80px; padding: 10px; overflow-y: hidden;">
			<table width="100%">
				<tr>
					<td width="350px"><input class="easyui-searchbox" style="width: 300px" data-options="prompt:'请输入文件名称',searcher:searcher_box" />
					</td>
					<td><a href="javascript:void(0);" onclick="add_media_atta()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加媒体文件</a>
					</td>
				</tr>
			</table>
		</div>
		<div data-options="region:'center',border:true,cache:false">
			<table id="media_atta_grid"></table>
			<!-- 添加资源对话框 -->
			<div id="media_atta_win" class="info">
				<table style="width: 100%; padding: 5px;" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="320px">
							<table id="media_atta_table" style="width: 100%; padding: 5px;" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td align="right" width="80px">资源名称:</td>
									<td>
										<input id="media_name" style="width: 150px;" type="text" />
									</td>
								</tr>
								<tr>
									<td align="right" width="80px">媒体类型:</td>
									<td><input id="media_type"/></td>
								</tr>
								<tr>
									<td align="right" width="80px">图片:</td>
									<td>
										<input type="file" target="image_tip" class="file" id="image_file" tip="*.jpg,*.png,*.gif" ext="*.jpg,*.png,*.gif"/>
										<label for="image_file" class="span_file">选择图片</label>
										<span id="image_tip"></span>
									</td>
								</tr>
								<tr id="audio_video_tr">
									<td align="right" width="80px">媒体文件:</td>
									<td>
										<input type="file" target="audio_video_tip" class="file" id="media_audio_video" tip="*.mp4" ext="*.mp4"/>
										<label for="media_audio_video" class="span_file">选择文件</label>
										<span id="audio_video_tip"></span>
									</td>
								</tr>
							</table>
						</td>
						<td>
							图片支持*.jpg,*.png,*.gif格式<br/>
							音频支持*.mp3格式<br/>
							视频支持*.mp4格式
						</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>