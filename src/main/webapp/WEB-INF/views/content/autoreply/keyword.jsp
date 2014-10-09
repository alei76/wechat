<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	$(function() {
		//定义关键字回复grid
		$('#auto_reply_grid').datagrid({
			view : datagrid_view,
			emptyMsg : '没有记录',
			url : global_ctxPath + rootPath + '/grid',
			method : 'post',
			fit : true,
			border : false,
			title : '关键字回复列表',
			fitColumns : true,
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			pageSize : 10,
			pageList : [ 10, 20, 30 ],
			remoteSort : false,
			idField : 'replyId',
			loadMsg : '数据装载中......',
			columns : [ [ {
				field : 'keyword',
				title : '关键字',
				width : 30,
				align : 'center',
				formatter : function(value, row, index) {
					return "<a href=\"###\" onclick=\"view_info('" + row.replyId + "')\">" + value + "</a>";
				}
			},{
				field : 'templateCn',
				title : '回复模板',
				width : 30,
				align : 'center'
			}, {
				field : 'checkBindCn',
				title : '验证会员绑定',
				width : 30,
				align : 'center'
			}, {
				field : 'status',
				title : '操作',
				width : 30,
				align : 'center',
				formatter : function(value, row, index) {
					return "<a href=\"###\" onclick=\"change_status('" + row.replyId + "')\">删除</a>&nbsp;&nbsp;<a href=\"###\" onclick=\"preview_msg('" + row.keyword + "','"+ row.templateId +"')\">消息预览</a>";
				}
			} ] ]
		});
		//定义grid分页配置
		$('#auto_reply_grid').datagrid('getPager').pagination({
			displayMsg : '当前显示从{from}到{to}共{total}记录',
			beforePageText : '第',
			afterPageText : '页 共 {pages} 页',
			onBeforeRefresh : function(pageNumber, pageSize) {
				$(this).pagination('loading');
				$(this).pagination('loaded');
			}
		});
		//定义弹出对话框
		$('#auto_reply_win').dialog({
			title : '创建关键字回复',
			width : 340,
			height : 210,
			cache : false,
			modal : true,
			closed : true,
			inline : false,
			buttons : [ {
				text : '保存',
				handler : save_auto_reply
			}, {
				text : '关闭',
				handler : function() {
					$('#auto_reply_win').dialog('close');
				}
			} ],
			onClose : function() {
				init_win();
			},
			onOpen : function(){
				init_options();
				$(this).dialog('move',{
					top : document.body.offsetHeight / 2 - $(this).dialog("options").height / 2,
					left : document.body.offsetWidth / 2 - $(this).dialog("options").width / 2
				});
			}
		});
		//定义回复消息模板下拉框
		$('#templateId').combobox({
			textField : 'name',
	        valueField : "templateId",
	        panelHeight : '200',
    		method : 'post',
    		editable : false,
    		multiple : false,
    		readonly : false,
    		required : false
	    });
		//定义未绑定消息模板下拉框
		$('#anonTemplateId').combobox({
			textField : 'name',
	        valueField : "templateId",
	        panelHeight : '200',
    		method : 'post',
    		editable : false,
    		multiple : false,
    		readonly : false,
    		required : false
	    });
		//加载所有的消息模板
		$.ajax({
			type : "post",
			url : global_ctxPath + rootPath + '/template',
			dataType : "json",
			success : function(result) {
				$('#templateId').combobox('loadData', result);//让下拉框加载数据
				$('#anonTemplateId').combobox('loadData', result);//让下拉框加载数据
			}
		});
		//验证绑定点击事件
		$("#checkBind").click(function(){
			if(this.checked){//如果勾选
				$('#anonTemplateId').combobox('enable');//消息模板可以选择
			} else {
				$('#anonTemplateId').combobox('disable');//消息模板禁止选择
			}
		});
	});
	//添加关键字回复
	function add_autoreply(){
		$("#auto_reply_win").dialog('open');//打开对话框
	}
	//初始化选择框状态
	function init_options(){
		var bind = document.getElementById("checkBind").checked;//验证绑定
		if(bind){//如果勾选
			$('#anonTemplateId').combobox('enable');//消息模板可以选择
		} else {
			$('#anonTemplateId').combobox('disable');//消息模板禁止选择
		}
	}
	//初始化添加对话框内的值
	function init_win(){
		$("#keyword").val("");
		$("#replyId").val("");
		$("#anonTemplateId").combobox('clear');
		$("#templateId").combobox('clear');
		document.getElementById("checkBind").checked = false;
	}
	//保存关键字消息回复
	function save_auto_reply(){
		var templateId = $('#templateId').combobox('getValue');//回复消息模板
		var anonTemplateId = $('#anonTemplateId').combobox('getValue');//未绑定消息模板
		var checkBind = '02';//默认不验证
		var bind = document.getElementById("checkBind").checked;//验证勾选状态
		if(bind){
			checkBind = '01';//需要验证
		}
		var keyword = $("#keyword").val();//关键字
		var replyId = $("#replyId").val();
		var wxReplyMsg = new Object();//消息回复对象
		wxReplyMsg.templateId = templateId;
		wxReplyMsg.anonTemplateId = anonTemplateId;
		wxReplyMsg.checkBind = checkBind;
		wxReplyMsg.keyword = keyword;
		if(replyId != ''){//有id表示修改
			wxReplyMsg.replyId = replyId;
		}
		
		$.ajax({
			type : "post",
			url : global_ctxPath + rootPath + '/savemsgreply',
			data : wxReplyMsg,
			dataType : "json",
			success : function(result) {
				$.messager.alert('提示', result.message, 'info', function() {
					if (result.success) {//保存成功
						$('#auto_reply_grid').datagrid('reload');//grid重新加载数据
						$('#auto_reply_win').dialog('close');//关闭对话框
					}
				});
			}
		});
	}
	//定义搜索框搜索事件
	function searcher_box(value, name){
		//按关键字让grid重新加载数据
		$('#auto_reply_grid').datagrid('load', {
			"search" : value
		});
	}
	//获取关键字回复的信息
	function view_info(replyId){
		$.ajax({
			type : "post",
			url : global_ctxPath + rootPath + '/viewinfo',
			data : {
				"replyId" : replyId
			},
			dataType : "json",
			success : function(result) {
				$("#replyId").val(result.replyId);//赋值
				$("#keyword").val(result.keyword);//赋值
				//循环结果集，如果存在该消息模板，则初始化选中该消息模板
				var store = $("#templateId").combobox("getData");
				for ( var i = 0; i < store.length; i++) {
					var temp = store[i];
					if(temp.templateId == result.templateId){
						$("#templateId").combobox("select", result.templateId);
						break;
					}
				}
				
				/* $("#templateId").combobox("setValue",result.templateId); */
				if(result.checkBind == '01'){//判断是否需要勾选状态
					document.getElementById("checkBind").checked = true;
				}
				
				//循环结果集，如果存在该消息模板，则初始化选中该消息模板
				var store = $("#anonTemplateId").combobox("getData");
				for ( var i = 0; i < store.length; i++) {
					var temp = store[i];
					if(temp.templateId == result.anonTemplateId){
						$("#anonTemplateId").combobox("select", result.anonTemplateId);
						break;
					}
				}
				
				/* $("#anonTemplateId").combobox("setValue",result.anonTemplateId); */
				init_options();//初始化勾选状态
				$("#auto_reply_win").dialog('open');//打开对话框
			}
		});
	}
	//更改状态
	function change_status(replyId){
		$.messager.confirm('确认','本次操作将删除该关键字的消息回复规则，是否要继续？',function(r) {
			if(r){
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/changestatus',
					data : {
						"replyId" : replyId
					},
					dataType : "json",
					success : function(result) {
						$.messager.alert('提示', result.message, 'info', function() {
							if (result.success) {
								$('#auto_reply_grid').datagrid('reload');//如果成功，重新加载grid数据
							}
						});
					}
				});
			}
		});
	}
	//消息预览
	function preview_msg(keyword, templateId){
		kernel_preview_msg(keyword,templateId);//调用核心预览JS方法
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false,title:'关键字回复设置',split:false,collapsible:false" style="height: 80px; padding: 10px; overflow-y: hidden;">
		<table width="100%">
			<tr>
				<td width="350px"><input class="easyui-searchbox" style="width: 300px" data-options="prompt:'请输入消息关键字',searcher:searcher_box" />
				</td>
				<td><a href="javascript:void(0);" onclick="add_autoreply()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">创建关键字回复</a>
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',border:false,cache:false">
		<table id="auto_reply_grid"></table>
		<!-- 创建关键字回复弹出框 -->
		<div id="auto_reply_win" class="info">
			<table style="width: 100%; padding: 5px;" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td width="100px">关键字：</td>
					<td><input type="text" id="keyword"/><input type="hidden" id="replyId"/></td>
				</tr>
				<tr>
					<td width="100px">回复消息：</td>
					<td><input type="text" id="templateId"/></td>
				</tr>
				<tr>
					<td colspan="2"><input id="checkBind" type="checkbox"/><label for="checkBind">绑定验证</label></td>
				</tr>
				<tr>
					<td>未绑定回复消息：</td>
					<td><input type="text" id="anonTemplateId"/></td>
				</tr>
			</table>
		</div>
	</div>
</div>
