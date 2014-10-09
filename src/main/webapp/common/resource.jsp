<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 全局JS资源引用页面 --%>
<link href="<%=request.getContextPath()%>/images/favicon.ico" rel="Shortcut Icon"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common/common.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/easyui/easyui.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/easyui/icon.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/uuid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
	//全局上下文路径
	var global_ctxPath = '<%=request.getContextPath()%>';
	//全局配置ajax请求，增加自定义参数_ajax=true标志
	jQuery.ajaxSettings.data = jQuery.extend({}, jQuery.ajaxSettings.data, {
		"_ajax" : true
	});
	//全局配置ajax请求，错误触发方法
	$(document).ajaxError(function(event, xhr, options, exc) {
		var response = xhr.responseJSON;
		if(response){
			if (response.redirect) {//一般是指session过期，或无session，跳转至登陆页面，路径由后台提供
				if (self != top) {
					window.parent.location.href = response.path;
				} else {
					location.href = response.path;
				}
			}
		}
		
	});
	//easyui全局配置layout收缩时增加箭头，显示标题。
	$.extend($.fn.layout.paneldefaults, {
		onCollapse : function () {
			var layout = $(this).parents("body.layout");
			var opts = $(this).panel("options");
			var expandKey = "expand" + opts.region.substring(0, 1).toUpperCase() + opts.region.substring(1);
			var expandPanel = layout.data("layout").panels[expandKey];
			if (opts.region == "west" || opts.region == "east") {
				var split = [];
				for (var i = 0; i < opts.title.length; i++) {
					split.push(opts.title.substring(i, i + 1));
				}
				expandPanel.panel("body").addClass("panel-title").css("text-align", "center").html(split.join("<br/>"));
			} else {
				expandPanel.panel("setTitle", opts.title);
			}
		}
	});
	//自定义easyui的grid视图，当grid中没有数据时，显示emptyMsg配置的文字内容。
	var datagrid_view = $.extend({},$.fn.datagrid.defaults.view,{
		onAfterRender:function(target){
			$.fn.datagrid.defaults.view.onAfterRender.call(this,target);
			var opts = $(target).datagrid('options');
			var vc = $(target).datagrid('getPanel').children('div.datagrid-view');
			vc.children('div.datagrid-empty').remove();
			if (!$(target).datagrid('getRows').length){
				var d = $('<div class="datagrid-empty"></div>').html(opts.emptyMsg || 'no records').appendTo(vc);
				d.css({
					position:'absolute',
					left:0,
					top:50,
					width:'100%',
					textAlign:'center'
				});
			}
		}
	});
	//拓展easyui的combobox组件，使其支持添加请求参数
	$.extend($.fn.combobox.defaults, {
	    loader : function (param, success, error) {
	        var opts = $(this).combobox("options");
	        if (!opts.url) {
	            return false;
	        }
	        if(opts.queryParams){
	            param = $.extend({},opts.queryParams,param);
	        }
	        $.ajax({
	            type : opts.method,
	            url : opts.url,
	            data : param,
	            dataType : "json",
	            success : function (data) {
	                success(data);
	            },
	            error : function () {
	                error.apply(this, arguments);
	            }
	        });
	    },
	    queryParams:{}
	});
	//拓展easyui的combobox组件，增加setQueryParams方法，更改请求参数
	$.extend($.fn.combobox.methods, {
	    setQueryParams : function (jq, params) {
	        return jq.each(function () {
	            $(this).combobox("options").queryParams = params;
	        });
	    }
	});
	//定义easyui中panel，dialog，window组件拖动时，不超过父容器的边界。
	function easyuiPanelOnMove(left, top) {
		var l = left;
		var t = top;
		if (l < 1) {
			l = 1;
		}
		if (t < 1) {
			t = 1;
		}
		var width = parseInt($(this).parent().css('width')) + 14;
		var height = parseInt($(this).parent().css('height')) + 14;
		var right = l + width;
		var buttom = t + height;
		var browserWidth = $(window).width();
		var browserHeight = $(window).height();
		if (right > browserWidth) {
			l = browserWidth - width;
		}
		if (buttom > browserHeight) {
			t = browserHeight - height;
		}
		$(this).parent().css({
			left : l,
			top : t
		});
	};
	$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
	$.fn.window.defaults.onMove = easyuiPanelOnMove;
	$.fn.panel.defaults.onMove = easyuiPanelOnMove;
	$.messager.defaults = { ok: "确定", cancel: "取消" };//全局定义easyui的对话框按钮文字
	//消息预览核心方法
	function kernel_preview_msg(keyword, templateId){
		$.ajax({
			type : "post",
			url : global_ctxPath + '/kernel/message/view',
			data : {
				"keyword" : keyword,
				"templateId" : templateId
			},
			dataType : "html",
			success : function(result) {
				$('<div style="padding:20px;"/>').dialog({
					title : '消息预览',
					cache : false,
					content : "<div id=\"message_view_div\">fff</div>",
					width : 390,
					height : 320,
					modal : true,
					inline : false,
					closed : false,
					onClose : function() {
						$(this).dialog('destroy');
					},
					onOpen : function(){
						$("#message_view_div").html(result);
					}
				});
			}
		});
	}
	//去除空格（左右包括全角半角）
	function newtrim(text) {
		var strTrim = text.replace(/(^\s*)|(\s*$)/g, "");
		strTrim = strTrim.replace(/^[\s　\t]+|[\s　\t]+$/, "");
		var strf = strTrim;
		strf = strf.replace(/(^\s*)|(\s*$)/g, "");
		strf = strf.replace(/^[\s　\t]+|[\s　\t]+$/, "");
		return strf;
	}
	//更改iframe的高度
	function change_iframe_height(height){
		if ($("#mainframe", window.parent.document).is("iframe")) {
	        $("#mainframe", window.parent.document).height(height);
	    }
	}
	//比较两个时间
	function checkEndTime(startTime, endTime) {
		var start = new Date(startTime.replace("-", "/").replace("-", "/"));
		var end = new Date(endTime.replace("-", "/").replace("-", "/"));
		if (end < start) {
			return -1;
		} else if(end > start){
			return 1;
		}else{
			return 0;
		}
	}
	
	//扩展Date的format方法 
	Date.prototype.format = function (format) {  
        var o = {  
            "M+": this.getMonth() + 1,  
            "d+": this.getDate(),  
            "H+": this.getHours(),  
            "m+": this.getMinutes(),  
            "s+": this.getSeconds(),  
            "q+": Math.floor((this.getMonth() + 3) / 3),  
            "S": this.getMilliseconds()  
        };
        if (/(y+)/.test(format)) {  
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
        }  
        for (var k in o) {  
            if (new RegExp("(" + k + ")").test(format)) {  
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
            }  
        }  
        return format;  
    };
</script>
