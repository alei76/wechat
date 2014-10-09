$(function() {
	$(".js_title").focusout(js_title).blur(js_title);//图文项 “标题”失去焦点的触发事件
	
	$(".js_desc").focusout(js_desc).blur(js_desc);//图文项 “摘要”失去焦点的触发事件
	
	$(".js_url").focusout(js_url).blur(js_url);//图文项 “原文链接”失去焦点的触发事件
	
	//鼠标滑动至上传文字的dom时将目标file显示，否则在ie下，点击文字无法弹出选择文件对话框
	$(".span_file").mouseover(function(){
		var domId = $(this).attr("for");
		$("#" + domId).css("display","block");
	});
	//监听upload_area下的file对象，当选择文件时触发，将文件自动上传至后台
	$('.upload_area').on('change', '.file', function(){
		if(this.value!=''){
			var path = this.value.split('\\');
			var name = path[path.length-1];
			var regular = $(this).attr("ext");//合法的后缀
			var suffix = name.substring(name.indexOf("."),name.length);//选择文件的后缀
			var valid = regular.indexOf(suffix) > 0;//判断是否合法
			if(valid){
				file_upload();//上传文件，并显示图片预览
			} else {
				$.messager.alert('提示', '您选择的文件格式不正确', 'info');
				resetFile(this);
			}
		}
	});
	//多图文消息中，编辑按钮的事件
	$("#js_appmsg_preview").on('click', '.js_edit', function(){
		var id = $(this).data("id");//获取id
		load_edit(id);//加载对应的数据并显示到右边的编辑框中
	});
	//多图文消息中，删除按钮的事件
	$("#js_appmsg_preview").on('click', '.js_del', function(){
		var items = $("#js_appmsg_preview").children(".js_appmsg_item");//获取图文消息项的数量
		if(items.length > 2){//如果大于2项，可以删除
			var id = $(this).data("id");//获取id
			
			var newsMessageId = $("#appmsgItem" + id).data("newsMessageId");//有这个ID，表示该项已在数据库中保存过，需要删除该ID对应的数据
			if(newsMessageId !== undefined){
				push_del_item(newsMessageId);//将该ID记录
			}
			$("#appmsgItem" + id).remove();//页面删除此项
			refresh_iframe_height();//刷新iframe的高度
			load_edit("1");//重新指定到第一个项
		} else {//不允许删除
			$.messager.alert('提示', '无法删除，多条图文至少需要2条消息。', 'info');
		}
	});
	//右侧编辑出图片预览旁，删除按钮的事件
	$(".js_removeCover").click(function(){
		$(".js_cover").hide();//预览图片所在html隐藏
		$(".js_cover").find("img").attr("src", "");//预览图片img标签的属性置空
		var target = getCurrentItem();//获取当前编辑的项
		target.find(".js_appmsg_thumb").attr("src", "").removeData("fileName").hide();//将该项中的图片预览设为空白，并删除隐藏属性
		target.find(".default").show();//显示默认图片
	});
	
	$("#js_add_appmsg").click(add_item);//添加一条图文项的事件
});
//将要删除的图文项ID存入html中
function push_del_item(newsMessageId){
	var str = $("#del_item_id").val();
	var array = new Array();
	if(str != ""){
		array = str.split(",");
	}
	array.push(newsMessageId);
	$("#del_item_id").val(array.join(","));
}

//初始化页面
function init_page(templateId){
	//根据模板ID，将图文项加载到页面中
	$.ajax({
		type : "post",
		url : global_ctxPath + rootPath + '/info/' + templateId,
		async : true,
		dataType : "json",
		success : function(result) {
			var count = result.itemsCount;
			var f = count > 1 ? 2 : 1;//项的第一个，第二个必须id是 1跟2
			for ( var i = 0; i < f; i++) {
				var item = result.items[i];//获取数据
				init_item(item, (i + 1));//填充数据，显示预览的html
			}
			for ( var i = 0; i < count - 2; i++) {
				var id = add_item();//动态添加一个项
				var item = result.items[i + 2];//获取数据
				init_item(item, id);//填充数据，显示预览的html
			}
			load_edit("1");//指定到第一个项
		}
	});
}
//初始化 图文项的预览页面
function init_item(item, id){
	var target = $("div.js_appmsg_item[data-id='" + id + "']");
	target.data("newsMessageId", item.newsMessageId);//把id存入data中
	var image_path = global_ctxPath + "/anon/image/prev/" + item.attaId;//图片路径
	target.find(".js_appmsg_thumb").attr("src", image_path).data("fileName", item.attaId).show();//显示图片
	target.find(".default").hide();//隐藏默认图片
	target.find(".appmsg_title").children("a").html(item.title);//标题
	target.find(".appmsg_title").children("a").attr("href", item.targetHref);//原文路径
	target.find(".appmsg_desc").html(item.description);//摘要(多图文是空字符串)
}
//生成一个项，并返回这个项的临时id
function add_item(){
	var items = $("#js_appmsg_preview").children(".js_appmsg_item");//获取图文消息项的数量
	if(items.length < 8){//小于8个时可以创建，
		var id = Math.uuid(15);//随机生成一个id
		var html = "<div class=\"appmsg_item js_appmsg_item \" data-id=\"" + id + "\" id=\"appmsgItem" + id + "\"><img src=\"\" class=\"js_appmsg_thumb appmsg_thumb\"/><i class=\"appmsg_thumb default\">缩略图</i><h4 class=\"appmsg_title\"><a target=\"_blank\" href=\"\" onclick=\"return false;\"></a></h4><p class=\"appmsg_desc\"></p><div class=\"appmsg_edit_mask\"><a href=\"javascript:void(0);\" onclick=\"return false;\" data-id=\"" + id + "\" class=\"icon18_common edit_gray js_edit\">编辑</a><a href=\"javascript:void(0);\" onclick=\"return false;\" data-id=\"" + id + "\" class=\"icon18_common del_gray js_del\">删除</a></div></div>";
		$(html).appendTo("#js_appmsg_preview");//拼凑一个项的html，并加载到页面
		refresh_iframe_height();//刷新iframe的高度
		return id;
	} else {
		$.messager.alert('提示', '你最多只可以加入8条图文消息', 'info');
	}
	return null;
}
//图文项 “原文链接”失去焦点的触发事件
function js_url(){
	var target = getCurrentItem();//获取当前编辑的项
	var dom = target.find(".appmsg_title").children("a");
	var href = this.value;
	if(href == ""){
		dom.attr("onclick", "return false");
	} else {
		dom.removeAttr("onclick");
	}
	dom.attr("href", href);//将路径写到标题的href属性中
}
//将id对应的项的数据加载到右边初始化（编辑使用）
function load_edit(id){
	var dom = $("#appmsgItem" + id);
	var index = $('#js_appmsg_preview .js_appmsg_item').index(dom);//获取本次编辑的项的索引位
	$("#js_appmsg_editor").data("target", id);//记录当前编辑的图文id，之后获取的当前编辑项id为该属性
	var top = (index - 1) * 102 + 190;//根据索引位计算编辑框的高度
	if(index ==0 ){
		top = 0;
	}
	$(".appmsg_editor").css("margin-top", top + "px");//将编辑框移动到该项的位置
	var fileName = dom.find(".js_appmsg_thumb").data("fileName");//图片的名称（即为图片的id）
	var title = dom.find(".appmsg_title").children("a").html();//获取标题
	var url = dom.find(".appmsg_title").children("a").attr("href");//获取原文路径
	var desc = dom.find(".appmsg_desc").html();//获取摘要
	$(".js_desc").val(desc);//赋值
	$(".js_title").val(title);//赋值
	$(".js_url").val(url);//赋值
	if(fileName === undefined){
		$(".js_cover").hide();//隐藏图片
		$(".js_cover").find("img").attr("src", "");
	} else {
		var image_path = global_ctxPath + "/anon/image/prev/" + fileName;//拼凑图片预览路径
		$(".js_cover").find("img").attr("src", image_path);//显示图片
		$(".js_cover").show();//显示图片对应的html
	}
}
//图文项 “摘要”失去焦点的触发事件
function js_desc(){
	var target = getCurrentItem();//获取当前编辑的项
	target.find(".appmsg_desc").html(this.value);//将摘要框中的内容显示到左边预览
}
//图文项 “标题”失去焦点的触发事件
function js_title(){
	var target = getCurrentItem();//获取当前编辑的项
	target.find(".appmsg_title").children("a").html(this.value);//将标题框中的内容显示到左边预览
}

//上传图片到后台，成功后将图片显示html中
function file_upload(){
	$.messager.progress(); 
	//上传图片
	$.ajaxFileUpload({
		url : global_ctxPath + "/content/attachment/tempimage",
		fileElementId : [ 'image_file'],
		success : function(data) {
			$.messager.progress('close');
			if(data.success){//上传成功
				var image_path = global_ctxPath + "/anon/image/prev/" + data.message;//拼凑图片路径
				var target = getCurrentItem();//获取当前编辑的项
				target.find(".js_appmsg_thumb").attr("src", image_path).data("fileName", data.message).show();//左边预览该图片，把图片id存入data属性(data.message为图片id)
				target.find(".default").hide();//默认图片隐藏
				$(".js_cover").find("img").attr("src", image_path);//编辑框内图片预览显示
				$(".js_cover").show();//显示图片对应的html
			}else{
				$.messager.alert('提示', data.message, 'info');
			}
		}
	});
}
//新建 调 保存图文消息模板
function save_message_template(){
	var items = new Array();
	$(".js_appmsg_item").each(function(i, j) {
		var item = new Object();
		var fileName = $(j).find(".js_appmsg_thumb").data("fileName");
		var title = $(j).find(".appmsg_title").children("a").html();
		var url = $(j).find(".appmsg_title").children("a").attr("href");
		var desc = $(j).find(".appmsg_desc").html();
		item.title = title;
		item.description = desc;
		item.targetHref = url;
		item.msgIndex = i;
		item.attaId = fileName;
		items.push(item);
	});
	
	var name = $("#template_name").val();
	var type = $("#type").val();
	var templateId = $("#template_templateId").val();
	var template = new Object();
	template.name = name;
	template.type = type;
	if(templateId != ''){
		template.templateId = templateId;
	}
	
	$.ajax({
		type : "post",
		url : global_ctxPath + "/content/newsmessage/savenews",
		async : true,
		data : {
			"template" : $.toJSON(template),
			"items" : $.toJSON(items)
		},
		dataType : "json",
		success : function(result) {
			$.messager.alert('提示', result.message, 'info', function(){
				if(result.success){
					back_list();
				}
			});
		}
	});
}
//编辑 调 保存图文消息模板
function edit_message_template() {
	var delItems = $("#del_item_id").val();
	var items = new Array();
	$(".js_appmsg_item").each(function(i, j) {
		var item = new Object();
		var fileName = $(j).find(".js_appmsg_thumb").data("fileName");
		var title = $(j).find(".appmsg_title").children("a").html();
		var url = $(j).find(".appmsg_title").children("a").attr("href");
		var desc = $(j).find(".appmsg_desc").html();
		var newsMessageId = $(j).data("newsMessageId");
		item.newsMessageId = newsMessageId;
		item.title = title;
		item.description = desc;
		item.targetHref = url;
		item.msgIndex = i;
		item.attaId = fileName;
		items.push(item);
	});
	
	var name = $("#template_name").val();
	var type = $("#type").val();
	var templateId = $("#template_templateId").val();
	var template = new Object();
	template.name = name;
	template.type = type;
	if(templateId != ''){
		template.templateId = templateId;
	}
	
	$.ajax({
		type : "post",
		url : global_ctxPath + "/content/newsmessage/editnews",
		async : true,
		data : {
			"delItems" : delItems,
			"template" : $.toJSON(template),
			"items" : $.toJSON(items)
		},
		dataType : "json",
		success : function(result) {
			$.messager.alert('提示', result.message, 'info', function(){
				if(result.success){
					back_list();
				}
			});
		}
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
}
//获取当前编辑的项
function getCurrentItem() {
	var index = $("#js_appmsg_editor").data('target');
	var target = $("div.js_appmsg_item[data-id='" + index + "']");
	return target;
}
//返回选择创建图文消息类型的页面（单或多）
function choose_multi() {
	$("#t_body").layout('panel', 'center').panel("refresh", global_ctxPath + rootPath + '/content/06');//将当前页面跳转到创建图文消息页面
}

//动态修改当前iframe的高度，根据项的数量
function refresh_iframe_height(){
	var items = $("#js_appmsg_preview").children(".js_appmsg_item");//获取图文消息项的数量
	var count = items.length;
	if(count > 3){
		var h = count - 3;
		var height = h * 104 + 660;
		change_iframe_height(height);
	} else {
		change_iframe_height(660);
	}
}