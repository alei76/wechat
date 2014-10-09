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
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dict.js"></script>
		<script type="text/javascript">
			var rootPath = '/account/service';
			change_iframe_height(650);//更改当前iframe高度为650
			$(function() {
				//定义公众号grid
				$('#account_grid').datagrid({
					view : datagrid_view,
					emptyMsg : '没有记录',
					url : global_ctxPath + rootPath + '/grid',
					method : 'post',
					fit : true,
					border : false,
					title : '公众号列表',
					fitColumns : true,
					singleSelect : true,
					pagination : true,
					rownumbers : true,
					pageSize : 10,
					pageList : [ 10, 20, 30 ],
					remoteSort : false,
					idField : 'accountId',
					loadMsg : '数据装载中......',
					columns : [ [ {
						field : 'name',
						title : '微信名称',
						width : 20,
						align : 'center',
						formatter : function(value, row, index) {
							return "<a href=\"###\" onclick=\"view_info('" + row.accountId + "')\">" + value + "</a>";
						}
					}, {
						field : 'accountNo',
						title : '微信号',
						width : 20,
						align : 'center'
					}, {
						field : 'typeCn',
						title : '账号类型',
						width : 20,
						align : 'center'
					}, {
						field : 'appId',
						title : '接口凭证',
						width : 30,
						align : 'center'
					}, {
						field : 'appSecret',
						title : '凭证密钥',
						width : 30,
						align : 'center'
					}, {
						field : 'status',
						title : '操作',
						width : 20,
						align : 'center',
						formatter : function(value, row, index) {
							var text = "";//根据状态判断是显示启用还是禁用
							if(value == '02'){
								text = "启用";
							} else {
								text = "停用";
							}
							var op = "<a href=\"###\" onclick=\"change_status('" + row.accountId + "')\">" + text + "</a>";
							var view = "<a href=\"###\" onclick=\"view_config('" + row.accountId + "')\">查看配置</a>";
							var del = "<a href=\"###\" onclick=\"del_account('" + row.accountId + "')\">删除</a>";
							return op + "&nbsp;&nbsp;" + view + "&nbsp;&nbsp;" + del;
						}
					} ] ]
				});
				//定义grid的分页
				$('#account_grid').datagrid('getPager').pagination({
					displayMsg : '当前显示从{from}到{to}共{total}记录',
					beforePageText : '第',
					afterPageText : '页 共 {pages} 页',
					onBeforeRefresh : function(pageNumber, pageSize) {
						$(this).pagination('loading');
						$(this).pagination('loaded');
					}
				});
				//定义弹出对话框
				$('#account_win').dialog({
					title : '添加微信号',
					width : 340,
					height : 240,
					cache : false,
					modal : true,
					closed : true,
					inline : false,
					buttons : [ {
						text : '保存',
						handler : save_account
					}, {
						text : '关闭',
						handler : function() {
							$('#account_win').dialog('close');
						}
					} ],
					onClose : function() {
						reset_account_win();
					},
					onOpen : function(){
						//定义打开时置屏幕中央
						$(this).dialog('move',{
							top : document.body.offsetHeight / 2 - $(this).dialog("options").height / 2,
							left : document.body.offsetWidth / 2 - $(this).dialog("options").width / 2
						});
					}
				});
				//定义查看配置弹出对话框
				$('#config_win').dialog({
					title : '配置信息',
					width : 500,
					height : 120,
					cache : false,
					modal : true,
					closed : true,
					inline : false,
					onClose : function() {
						$('#info_url').val("");
						$('#info_token').val("");
					},
					onOpen : function(){
						$(this).dialog('move',{
							top : document.body.offsetHeight / 2 - $(this).dialog("options").height / 2,
							left : document.body.offsetWidth / 2 - $(this).dialog("options").width / 2
						});
					}
				});
				//定义微信号类型下拉选择框
				$("#account_type").dict({
					dictCode : "ACCOUNT_TYPE"
				});
			});
			//打开添加微信号对话框
			function add_account() {
				$('#account_win').dialog('open');
			}
			//重置添加微信号对话框的内容
			function reset_account_win() {
				$("#account_win input[id^='account_']").val("");
				$("#account_type").combobox('reset');
				$("#account_type").combobox('readonly', false);
			}
			//保存微信号
			function save_account() {
				var account = new Object();//account对象
				$("#account_win input[id^='account_']").each(function(i, j) {
					var id = this.id.substring(8, this.id.length);
					account[id] = this.value;//循环赋account对象的属性和值
				});
				var type = $("#account_type").combobox('getValue');
				account.type = type;//微信号的类型
				//ajax提交保存微信号
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/save',
					data : account,
					dataType : "json",
					success : function(result) {
						$.messager.alert('提示', result.message, 'info', function() {
							if (result.success) {//返回成功
								$('#account_grid').datagrid('reload');//重新加载grid
								$('#account_win').dialog('close');//关闭对话框
							}
						});
					}
				});
			}
			//定义搜索框搜索事件
			function searcher_box(value, name){
				//按关键字让grid重新加载数据
				$('#account_grid').datagrid('load', {
					"keyword" : value
				});
			}
			//查看微信号信息
			function view_info(accountId){
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/viewinfo',
					data : {
						"accountId" : accountId
					},
					dataType : "json",
					success : function(result) {
						for(var key in result){
							$("#account_" + key).val(result[key]);//循环根据属性名,将值填入html
						}
						$("#account_type").combobox('setValue', result.type);//选择微信号类型
						$("#account_type").combobox('readonly', true);//类型设为只读,编辑不允许修改类型
						$('#account_win').dialog('open');//打开对话框
					}
				});
			}
			//更改状态 启用或禁用
			function change_status(accountId){
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/changestatus',
					data : {
						"accountId" : accountId
					},
					dataType : "json",
					success : function(result) {
						$.messager.alert('提示', result.message, 'info', function() {
							if (result.success) {
								$('#account_grid').datagrid('reload');//更改成功后，重新加载grid
							}
						});
					}
				});
			}
			//查看微信配置
			function view_config(accountId){
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/viewinfo',
					data : {
						"accountId" : accountId
					},
					dataType : "json",
					success : function(result) {
						$('#info_url').val(result.notifyPath);//将路径填入html
						$('#info_token').val(result.token);//将token填入html
						$('#config_win').dialog('open');//打开对话框
					}
				});
			}
			//删除公众号
			function del_account(accountId){
				$.messager.confirm('确认','该操作将删除该公众号，是否要继续？',function(r) {
					if(r){//确认删除
						//ajax提交删除请求
						$.ajax({
							type : "post",
							url : global_ctxPath + rootPath + '/delete',
							data : {
								"accountId" : accountId
							},
							dataType : "json",
							success : function(result) {
								$.messager.alert('提示', result.message, 'info', function() {
									if (result.success) {
										$('#account_grid').datagrid('reload');//删除成功后，重新加载grid
									}
								});
							}
						});
					}
				});
			}
		</script>
	</head>
	<!-- 定义为easyui的layout布局 -->
	<body class="easyui-layout">
		<!-- north -->
		<div data-options="region:'north',border:true,title:'公众号',split:false,collapsible:false" style="height: 80px; padding: 10px; overflow-y: hidden;">
			<table width="100%">
				<tr>
					<td width="350px"><input class="easyui-searchbox" style="width: 300px" data-options="prompt:'请输入微信名称',searcher:searcher_box" />
					</td>
					<td><a href="javascript:void(0);" onclick="add_account()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加微信号</a>
					</td>
				</tr>
			</table>
		</div>
		<!-- center -->
		<div data-options="region:'center',border:true,cache:false">
			<table id="account_grid"></table>
			<!-- 添加微信号对话框使用层 -->
			<div id="account_win" class="info">
				<table style="width: 100%; padding: 5px;" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td align="right" width="80px">微信名称:</td>
						<td>
							<input id="account_accountId" type="hidden" />
							<input id="account_name" type="text" />
						</td>
					</tr>
					<tr>
						<td align="right">微信号:</td>
						<td> <input id="account_accountNo" type="text" /></td>
					</tr>
					<tr>
						<td align="right">账号类型:</td>
						<td><input id="account_type"/></td>
					</tr>
					<tr>
						<td align="right">接口凭证:</td>
						<td><input id="account_appId" type="text" /></td>
					</tr>
					<tr>
						<td align="right">凭证密钥:</td>
						<td><input id="account_appSecret" type="text" /></td>
					</tr>
				</table>
			</div>
			<!-- 查看微信配置使用层 -->
			<div id="config_win" class="info">
				<table style="width: 100%; padding: 5px;" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td align="right" width="60px">URL:</td>
						<td>
							<textarea readonly="readonly" id="info_url" style="width:400px;height:35px;"></textarea>
						</td>
					</tr>
					<tr>
						<td align="right">TOKEN:</td>
						<td> <input id="info_token" type="text" readonly="readonly"/></td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>