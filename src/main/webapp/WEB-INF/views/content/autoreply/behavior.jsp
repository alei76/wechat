<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	$(function() {
		//定义回复消息模板下拉框
		$('#templateId').combobox({
	        width : 206,
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
	        width : 206,
	        textField : 'name',
	        valueField : "templateId",
	        panelHeight : '200',
    		method : 'post',
    		editable : false,
    		multiple : false,
    		readonly : false,
    		required : false
	    });
		
		var s = $("#s").val();//状态
		if(s == '01'){//01为启用
			document.getElementById("status").checked = true;
		} else {
			document.getElementById("status").checked = false;
		}
		var c = $("#c").val();//验证绑定
		if(c == '01'){//01需要验证
			document.getElementById("checkBind").checked = true;
		} else {
			document.getElementById("checkBind").checked = false;
		}
		//加载所有的消息模板
		$.ajax({
			type : "post",
			url : global_ctxPath + rootPath + '/template',
			dataType : "json",
			success : function(result) {
				$('#templateId').combobox('loadData', result);//让下拉框加载数据
				$('#anonTemplateId').combobox('loadData', result);//让下拉框加载数据
				var t = $("#t").val();//回复消息的ID
				var a = $("#a").val();//未绑定回复消息ID
				$('#templateId').combobox('clear');//清空选中状态
				$('#anonTemplateId').combobox('clear');//清空选中状态
				//循环结果集，如果存在该消息模板，则初始化选中该消息模板
				for ( var i = 0; i < result.length; i++) {
					var temp = result[i];
					if(temp.templateId == t){
						$('#templateId').combobox('setValue', t);
						break;
					}
				}
				//循环结果集，如果存在该消息模板，则初始化选中该消息模板
				for ( var i = 0; i < result.length; i++) {
					var temp = result[i];
					if(temp.templateId == a){
						$('#anonTemplateId').combobox('setValue', a);
						break;
					}
				}
			}
		});
		//初始化控件状态
		init_options();
		//勾选验证绑定触发事件
		$("#checkBind").click(function(){
			init_options();
		});
		//勾选启用状态触发事件
		$("#status").click(function(){
			init_options();
		});
	});
	
	function save_behavior(){
		var templateId = $('#templateId').combobox('getValue');
		var anonTemplateId = $('#anonTemplateId').combobox('getValue');
		var tiggerType = $("#tiggerType").val();
		var status = '02';
		var enable = document.getElementById("status").checked;
		if(enable){
			status = '01';
		}
		var checkBind = '02';
		var bind = document.getElementById("checkBind").checked;
		if(bind){
			checkBind = '01';
		}
		var wxReplyMsg = new Object();
		wxReplyMsg.templateId = templateId;
		wxReplyMsg.anonTemplateId = anonTemplateId;
		wxReplyMsg.status = status;
		wxReplyMsg.checkBind = checkBind;
		wxReplyMsg.tiggerType = tiggerType;

		$.ajax({
			type : "post",
			url : global_ctxPath + rootPath + '/savebehavior',
			data : wxReplyMsg,
			dataType : "json",
			success : function(result) {
				$.messager.alert('提示', result.message, 'info');
			}
		});
		
	}
	//初始化控件状态
	function init_options(){
		var enable = document.getElementById("status").checked;//启用状态
		if(enable){//如果启用
			$('#templateId').combobox('enable');//消息模板可以选择
			document.getElementById("checkBind").disabled = false;//验证绑定可以选择
		} else {//如果不启用
			document.getElementById("checkBind").checked = false;//取消验证绑定勾选
			document.getElementById("checkBind").disabled = true;//验证绑定不可以选择
			$('#templateId').combobox('disable');//消息模板禁止选择
		}
		var bind = document.getElementById("checkBind").checked;//验证绑定状态
		if(bind){//如果启用
			$('#anonTemplateId').combobox('enable');//消息模板可以选择
		} else {//如果不启用
			$('#anonTemplateId').combobox('disable');//消息模板禁止选择
		}
	}
	//消息预览
	function preview_message(){
		var type = $("#tiggerType").val();//当前的事件触发类型
		 if(type == '04'){//04为默认消息回复，需要输入关键字
			$.messager.prompt('提示', '请输入消息关键字', function(r){
				if (r){//填入关键字确认预览
					var templateId = $('#templateId').combobox('getValue');//获取模板ID
					kernel_preview_msg(r, templateId);//调用核心预览JS方法
				}
			});
		} else {
			if(type == '02'){//地址位置
				if (window.navigator.geolocation) {
					var options = {
						enableHighAccuracy: true
					};
					window.navigator.geolocation.getCurrentPosition(handleSuccess, handleError, options);//通过浏览器获取当前地理位置
				} else {
					alert("浏览器不支持html5来获取地理位置信息");
				}
			} else if (type= "01"){//添加关注时的回复
				var keyword = "欢迎词";//如果是小I，设置默认关键字
				var templateId = $('#templateId').combobox('getValue');//获取模板ID
				kernel_preview_msg(keyword, templateId);//调用核心预览JS方法
			} else if (type= "05"){//添加关注时的回复
				var keyword = "再见";//如果是小I，设置默认关键字
				var templateId = $('#templateId').combobox('getValue');//获取模板ID
				kernel_preview_msg(keyword, templateId);//调用核心预览JS方法
			}
		}
	}
	//成功获取经纬度事件
	function handleSuccess(position) {
		var lng = position.coords.longitude;
		var lat = position.coords.latitude;
		var keyword = lat + "," + lng;//设置关键字
		var templateId = $('#templateId').combobox('getValue');//获取模板ID
		kernel_preview_msg(keyword, templateId);//调用核心预览JS方法
	}
	//不能获取经纬度的事件
	function handleError(error){
		alert(error);
	}
</script>
<div class="info">
<!-- 模板ID -->
<input type="hidden" id="t" value="${domain.templateId }" />
<!-- 未绑定回复消息模板ID -->
<input type="hidden" id="a" value="${domain.anonTemplateId }" />
<!-- 状态 -->
<input type="hidden" id="s" value="${domain.status }" />
<!-- 验证绑定 -->
<input type="hidden" id="c" value="${domain.checkBind }" />

<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td colspan="2"><input id="status" type="checkbox"/><label for="status">启用</label></td>
		<td rowspan="5">
			&nbsp;<!-- 如果是地理位置事件回复 -->
			<c:if test="${tiggerType eq '02'}">地理位置关键词为：纬度,经度</c:if>
		</td>
	</tr>
	<tr>
		<td width="120px">回复消息：</td>
		<td width="250px"><input type="text" id="templateId"/><!-- 触发类型，添加关注，地理位置，默认回复。。 --><input id="tiggerType" type="hidden" value="${tiggerType }"/></td>
	</tr>
	<tr>
		<td colspan="2"><input id="checkBind" value="01" type="checkbox"/><label for="checkBind">绑定验证</label></td>
	</tr>
	<tr>
		<td>未绑定回复消息：</td>
		<td><input type="text" id="anonTemplateId"/></td>
	</tr>
	<tr>
		<td colspan="2"><a href="javascript:void(0);" onclick="save_behavior()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">保存</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="preview_message()" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">消息预览</a></td>
	</tr>
</table>
</div>