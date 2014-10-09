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
		<script type="text/javascript">
			change_iframe_height(900);
			
			var rootPath = '/content/linkservice';
			$(function() {
				//定义接口grid
				$('#link_resource_grid').datagrid({
					view : datagrid_view,
					emptyMsg : '没有记录',
					url : global_ctxPath + rootPath + '/grid',
					method : 'post',
					fit : false,
					height : 350,
					border : true,
					title : '接口列表',
					fitColumns : true,
					singleSelect : false,
					pagination : true,
					rownumbers : true,
					pageSize : 10,
					pageList : [ 10, 20, 30 ],
					remoteSort : false,
					idField : 'resourceId',
					loadMsg : '数据装载中......',
					columns : [ [ {
						field : 'resourceId',
						checkbox : true
					}, {
						field : 'name',
						title : '接口名称',
						width : 20,
						align : 'center',
						formatter : function(value, row, index) {
							return "<a href=\"###\" onclick=\"view_info('" + row.resourceId + "')\">" + value + "</a>";
						}
					}, {
						field : 'resourceHref',
						title : '接口路径',
						width :50,
						align : 'center'
					}, {
						field : 'status',
						title : '操作',
						width : 15,
						align : 'center',
						formatter : function(value, row, index) {
							return "<a href=\"###\" onclick=\"change_status('" + row.resourceId + "')\">删除</a>";
						}
					} ] ],
					onBeforeLoad : function(param){
						param.type = '02';//类型02
					}
				});
				//grid分页参数配置
				$('#link_resource_grid').datagrid('getPager').pagination({
					displayMsg : '当前显示从{from}到{to}共{total}记录',
					beforePageText : '第',
					afterPageText : '页 共 {pages} 页',
					onBeforeRefresh : function(pageNumber, pageSize) {
						$(this).pagination('loading');
						$(this).pagination('loaded');
					}
				});
				//定义添加接口对话框
				$('#link_resource_win').dialog({
					title : '添加接口地址',
					width : 420,
					height : 240,
					cache : false,
					modal : true,
					closed : true,
					inline : false,
					buttons : [ {
						text : '保存',
						handler : save_linkresource
					}, {
						text : '关闭',
						handler : function() {
							$('#link_resource_win').dialog('close');
						}
					} ],
					onClose : function() {
						reset_linkresource_win();
					},
					onOpen : function(){
						$(this).dialog('move',{
							top : document.body.offsetHeight / 2 - $(this).dialog("options").height / 2,
							left : document.body.offsetWidth / 2 - $(this).dialog("options").width / 2
						});
						//初始化路径验证控件
						$('#linkresource_resourceHref').validatebox({
							validType: 'url',
							tipPosition: 'left',
							invalidMessage:'请输入合法的接口地址'
						});

					}
				});
			});
			//添加页面
			function add_linkresource(){
				$('#link_resource_win').dialog('open');//打开对话框
			}
			//重置对话框内的值
			function reset_linkresource_win(){
				$("#link_resource_win input[id^='linkresource_']").val("");
				$("#link_resource_win textarea[id^='linkresource_']").val("");
			}
			//保存接口
			function save_linkresource(){
				var valid = $('#linkresource_resourceHref').validatebox('isValid');//地址是否合法
				if(valid){
					
					var linkresource = new Object();//页面js对象
					linkresource.resourceId = $("#linkresource_resourceId").val();
					linkresource.name = $("#linkresource_name").val();
					linkresource.resourceHref = $('#linkresource_resourceHref').val();
					linkresource.type = '02';
					$.ajax({
						type : "post",
						url : global_ctxPath + rootPath + '/save',
						async : true,
						data : linkresource,
						dataType : "json",
						success : function(result) {
							$.messager.alert('提示', result.message, 'info', function() {
								if (result.success) {//保存成功
									$('#link_resource_grid').datagrid('reload');//重新加载grid数据
									$('#link_resource_win').dialog('close');//关闭对话框
								}
							});
						}
					});
				}
			}
			//定义搜索框搜索事件
			function searcher_box(value, name){
				//按关键字让grid重新加载数据
				$('#link_resource_grid').datagrid('load', {
					"keyword" : value
				});
			}
			//更改状态
			function change_status(resourceId){
				$.messager.confirm('确认','删除该接口地址，那么引用该接口的消息模板也将无法正常使用，是否继续？',function(r) {
					if(r){
						$.ajax({
							type : "post",
							url : global_ctxPath + rootPath + '/changestatus',
							async : true,
							data : {
								"resourceId" : resourceId
							},
							dataType : "json",
							success : function(result) {
								$.messager.alert('提示', result.message, 'info', function() {
									if (result.success) {
										$('#link_resource_grid').datagrid('reload');//删除成功，grid重新加载
									}
								});
							}
						});
					}
				});
			}
			//查看页面详情
			function view_info(resourceId){
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/viewinfo',
					async : true,
					data : {
						"resourceId" : resourceId
					},
					dataType : "json",
					success : function(result) {
						for(var key in result){//循环将值赋到html
							$("#linkresource_" + key).val(result[key]);
						}
						$('#link_resource_win').dialog('open');//打开对话框
					}
				});
			}
			//批量删除
			function batch_del(){
				var rows = $('#link_resource_grid').datagrid('getSelections');//获取grid中所有勾选的行
				if(rows.length > 0){//判断是否有勾选行
					$.messager.confirm('确认','删除该接口地址，那么引用该接口的消息模板也将无法正常使用，是否继续？',function(r) {
						if(r){
							var resourceIdArray = new Array();
							$.each(rows, function(i, j) {//循环选中的行
								resourceIdArray.push(j.resourceId);//将id存放在集合中
							});
							$.ajax({
								type : "post",
								url : global_ctxPath + rootPath + '/batchchange',
								async : true,
								data : {
									"resource" : $.toJSON(resourceIdArray)
								},
								dataType : "json",
								success : function(result) {
									$.messager.alert('提示', result.message, 'info', function() {
										if (result.success) {
											$('#link_resource_grid').datagrid('reload');//删除成功后，重新加载grid
										}
									});
								}
							});
						}
					});
				} else {
					$.messager.alert('提示', "请选择要删除的接口", 'info');
				}
			}
		</script>
	</head>
	<body class="easyui-layout">
		<div data-options="region:'north',border:true,title:'接口地址管理',split:false,collapsible:false" style="height: 80px; padding: 10px; overflow-y: hidden;">
			<table width="100%">
				<tr>
					<td width="350px"><input class="easyui-searchbox" style="width: 300px" data-options="prompt:'请输入接口名称或路径',searcher:searcher_box" />
					</td>
					<td>
					<a href="javascript:void(0);" onclick="add_linkresource()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加接口地址</a>
					&nbsp;&nbsp;
					<a href="javascript:void(0);" onclick="batch_del()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">删除所选接口</a>
					</td>
				</tr>
			</table>
		</div>
		<div data-options="region:'center',border:false,cache:false">
			<table id="link_resource_grid"></table>
			
			<div class="developer_info_item">
            	<div class="developer_info_hd">
            		<h4 class="developer_info_title">服务器配置</h4>
                </div>
	            <div class="developer_info_bd gray_desc">
	                <div class="popover normal_flow pos_right server_switch_tips">
	                    <div class="popover_inner">
	                        <div class="popover_content">
	                        	启用并设置服务器配置后，用户发给公众号的消息以及开发者需要的事件推送，将被微信转发到该URL中
	                        </div>
	                    </div>
	                </div>
	                <div class="frm_control_group">
	                    <label class="frm_label">URL</label>
	                    <div class="frm_controls frm_vertical_pt">
	                    	${account.notifyPath }
	                    </div>
	                </div>
	                <div class="frm_control_group">
	                    <label class="frm_label">Token</label>
	                    <div class="frm_controls frm_vertical_pt">
	                    	${account.token }
	                    </div>
	                </div>
	                <div class="frm_control_group">
	                    <label class="frm_label">ACCOUNT_ID</label>
	                    <div class="frm_controls frm_vertical_pt">
	                    	${account.accountId }
	                    </div>
	                </div>
	                <div class="frm_control_group">
	                    <label class="frm_label">授权回调页面域名</label>
	                    <div class="frm_controls frm_vertical_pt">
	                    	apigatewayqa.sgmlink.com
	                    </div>
	                </div>
	            </div>
        	</div>
        	
        	<div class="developer_info_item">
	            <div class="developer_info_hd">
	                <h4 class="developer_info_title">开发者工具</h4>
	            </div>
	            <div class="developer_info_bd gray_desc">
	                <p>开发者文档<a target="_blank" href="${ pageContext.request.contextPath}/anon/wiki/list" class="extra_link">点击进入</a></p>
	            </div>
	        </div>
			<div id="link_resource_win">
				<table style="width: 100%; padding: 5px;" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td align="right" width="80px">接口名称:</td>
						<td>
							<input id="linkresource_resourceId" type="hidden" />
							<input id="linkresource_name" style="width: 260px;" type="text" />
						</td>
					</tr>
					<tr>
						<td align="right" width="80px">接口地址:</td>
						<td>
							<textarea id="linkresource_resourceHref" style="width: 260px;height: 60px;"></textarea>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>