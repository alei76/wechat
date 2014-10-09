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
			.current-account {background: #3A9BD2;color: #ffffff;}
		</style>
		<script type="text/javascript">
			var rootPath = '/admin/user';
			var current_edit_account = null;//当前编辑的微信号---为用户设置角色使用
			change_iframe_height(650);//更改当前iframe高度为650
			$(function() {
				//定义用户grid控件
				$('#user_grid').datagrid( {
					view : datagrid_view,
					emptyMsg : '没有记录',
					url : global_ctxPath + rootPath + '/grid',
					method : 'post',
					fit : true,
					border : false,
					title : '维护人员列表',
					fitColumns : true,
					singleSelect : true,
					pagination : true,
					rownumbers : true,
					pageSize : 10,
					pageList : [ 10, 20, 30 ],
					remoteSort : false,
					idField : 'userId',
					loadMsg : '数据装载中......',
					columns : [ [ {
						field : 'userName',
						title : '用户名',
						width : 30,
						align : 'center'
					}, {
						field : 'fullName',
						title : '姓名',
						width : 30,
						align : 'center'
					}, {
						field : 'userId',
						title : '查看权限',
						width : 30,
						align : 'center',
						formatter : function(value, row, index) {
							return "<a href=\"javascript:void(0);\" onclick=\"edit_auth('" + value + "')\">编辑</a>";
						}
					}, {
						field : 'status',
						title : '操作',
						width : 30,
						align : 'center',
						formatter : function(value, row, index) {
							var text = "";//根据状态显示启用或者停用
							if(value == '02'){
								text = "启用";
							} else {
								text = "停用";
							}
							return "<a href=\"###\" onclick=\"change_status('" + row.userId + "')\">" + text + "</a>";
						}
					} ] ]
				});
				//定义grid的分页配置
				$('#user_grid').datagrid('getPager').pagination({
					displayMsg : '当前显示从{from}到{to}共{total}记录',
					beforePageText : '第',
					afterPageText : '页 共 {pages} 页',
					onBeforeRefresh : function(pageNumber, pageSize) {
						$(this).pagination('loading');
						$(this).pagination('loaded');
					}
				});
				//定义权限分配的对话框
				$('#user_role_win').dialog({
					title : '权限分配',
					width : 380,
					height : 340,
					cache : false,
					modal : true,
					closed : true,
					inline : false,
					buttons : [ {
						text : '保存',
						handler : save_user_role
					}, {
						text : '关闭',
						handler : function() {
							$('#user_role_win').dialog('close');
						}
					} ],
					onClose : function() {
						$("#current_user_id").val("");//清空当前编辑的用户id
						$("#role_div").hide();//角色列表div隐藏
						$("label[id^='label_']").removeClass("current-account");//将选中的角色样式去除
					},
					onOpen : function(){
						//定义打开时置屏幕中央
						$(this).dialog('move',{
							top : document.body.offsetHeight / 2 - $(this).dialog("options").height / 2,
							left : document.body.offsetWidth / 2 - $(this).dialog("options").width / 2
						});
					}
				});
				
				//账户名称点击事件
				$("label[id^='label_']").click(function() {
					var accountId = this.id.substring(6, this.id.length);
					$("label[id^='label_']").removeClass("current-account");
					$(this).addClass("current-account");//更改样式，设置为选中样式
					$("#role_div").show();//显示所有角色的div
					current_edit_account = accountId;//更改当前编辑的微信号
					var accountDom = $("#account_" + current_edit_account).get(0);//当前编辑账户状态，勾选状态
					$("input[id^='role_']").prop("checked", false);//先全部取消勾选
					if (!accountDom.checked) {//如果勾选状态是未选中
						$("input[id^='role_']").prop("disabled", true);//全部禁用，不允许勾选角色
					} else {
						$("input[id^='role_']").prop("disabled", false);//全部启用
						var roleArray = $("#account_" + current_edit_account).data("roleArray");//当前勾选数据，存放在data里。
						var array = roleArray.split(",");//用逗号拆分
						$.each(array, function(i, j) {
							$("#role_" + j).prop("checked", true);//勾选之前选中的角色
						});
					}
				});
		
				//选择角色时的点击事件
				$("input[id^='role_']").click(function() {
					var accountDom = $("#account_" + current_edit_account).get(0);//当前编辑账户状态
					if (accountDom.checked) {//如果是勾选状态
						var roleArray = new Array();
						$("input[id^='role_']:checked").each(function(i, j) {//循环遍历勾选的角色
							roleArray.push(this.value);//将角色的id存入集合
						});
						$(accountDom).data("roleArray", roleArray.join(","));//把勾选的角色临时赋给data，用逗号分隔的字符串
					}
				});
				
				//账户名称勾选事件
				$("input[id^='account_']").click(function() {
					var accountId = this.id.substring(8, this.id.length);
					$("label[id^='label_']").removeClass("current-account");
					$("#label_"+accountId).addClass("current-account");//更改样式，设置为选中样式
					$("#role_div").show();//显示所有角色的div
					current_edit_account = accountId;//更改当前编辑账户ID
					if (this.checked) {//如果是勾选状态
						$("input[id^='role_']").prop("disabled", false);//全部启用
						$("input[id^='role_']").prop("checked", false);//全部取消勾选
					} else {//禁用
						$("input[id^='role_']").prop("checked", false);//全部取消勾选
						$("input[id^='role_']").prop("disabled", true);//全部禁用
						$(this).data("roleArray", "");//清空数据
					}
				});
			});
			//编辑用户权限
			function edit_auth(userId) {
				$("#current_user_id").val(userId);//当前修改的用户id
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/listrole',
					async : true,
					data : {
						"userId" : userId
					},
					dataType : "json",
					success : function(result) {
						$("input[id^='account_']").prop("checked", false);//先所有微信号取消勾选
						$.each(result, function(i, j) {
							$("#account_" + j.accountId).prop("checked", true);//勾选该用户可使用的微信号
							$("#account_" + j.accountId).data("roleArray", j.roleList.join(","));//将该微信号所拥有的角色集合用逗号分隔，存入data属性
						});
						$('#user_role_win').dialog('open');//打开对话框
					}
				});
			}
			//保存用户的权限
			function save_user_role() {
				var roleArray = new Array();//微信号角色集合
				$("input[id^='account_']:checked").each(function(i, j) {//循环所有勾选的微信账号
					var roleList = $(this).data("roleArray");//取出存放在data中的角色集合
					if (roleList !== undefined) {
						if (roleList != "") {
							var roleObj = new Object();
							var accountId = this.id.substring(8, this.id.length);
							roleObj.accountId = accountId;//微信号ID
							var array = roleList.split(",");//将角色集合字符串变成数组对象
							roleObj.roleList = array;//该微信号所勾选的角色
							roleArray.push(roleObj);//存入集合
						}
					}
				});
				var json = $.toJSON(roleArray);//转换为json字符串
				var userId = $("#current_user_id").val();//当前用户id
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/save',
					async : true,
					data : {
						"json" : json,
						"userId" : userId
					},
					dataType : "json",
					success : function(result) {
						$.messager.alert('提示', result.message, 'info', function() {
							if (result.success) {
								$('#user_role_win').dialog('close');//保存成功，关闭对话框
							}
						});
					}
				});
			}
			//更改用户状态
			function change_status(userId){
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/changestatus',
					async : true,
					data : {
						"userId" : userId
					},
					dataType : "json",
					success : function(result) {
						$.messager.alert('提示', result.message, 'info', function() {
							if (result.success) {
								$('#user_grid').datagrid('reload');//更改成功，grid重新加载
							}
						});
					}
				});
			}
			//定义搜索框搜索事件
			function searcher_box(value, name){
				//按关键字让grid重新加载数据
				$('#user_grid').datagrid('load', {
					"keyword" : value
				});
			}
		</script>
	</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:true,title:'维护人员管理',split:false,collapsible:false" style="height: 80px; padding: 10px; overflow-y: hidden;">
		<table width="100%">
			<tr>
				<td><input class="easyui-searchbox" style="width: 300px" data-options="prompt:'请输入用户姓名',searcher:searcher_box" /></td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',border:true,cache:false">
		<table id="user_grid"></table>
		
		<div id="user_role_win">
			<div class="easyui-layout" data-options="fit:true,border:false">
				<div data-options="region:'west',split:false" style="width: 150px; padding: 10px;">
					<input id="current_user_id" type="hidden" />
					<!-- 页面加载完成时，将所有微信号写入页面 -->
					<c:forEach var="account" items="${accountList }">
						<input name="account-check" id="account_${account.accountId }" value="${account.accountId }" type="checkbox" />
						<label id="label_${account.accountId }">${account.name }</label>
						<br />
					</c:forEach>
				</div>
				<div data-options="region:'center'" style="padding: 10px;">
					<div id="role_div" style="display: none;">
						<!-- 页面加载完时，将所有角色写入页面 -->
						<c:forEach var="role" items="${roleList }">
							<input name="role-check" id="role_${role.roleId }" value="${role.roleId }" type="checkbox" />
							<label for="role_${role.roleId }">${role.roleName } </label>
							<br />
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>