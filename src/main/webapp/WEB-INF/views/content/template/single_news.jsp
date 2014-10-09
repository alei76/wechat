<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%-- 单图文消息模板分页面 --%>
<%-- 添加时addstatic.jsp为总页面，编辑时edit-static.jsp为总页面 --%>
<style type="text/css">
	.file { position:absolute; cursor:pointer; width: 510px; height:30px;line-height:30px; display:none;filter:alpha(opacity=0);opacity:0;}
</style>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/wechat/appmsg_edit.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/newsmessage_editor.js"></script>

<script type="text/javascript">
change_iframe_height(700);
$(function(){
	var templateId = $("#template_templateId").val();
	if(templateId != ''){
		$(".page_nav a").hide();
		init_page(templateId);//初始化数据，方法在newsmessage_editor.js中
	}
});
</script>


<input type="hidden" id="del_item_id" value="" />
<input type="hidden" id="type" value="${type }" />
<div class="page_nav">
	<a class="icon_goback" href="javascript:void(0)" onclick="choose_multi()">返回上一层</a>
	<a href="javascript:void(0)" onclick="choose_multi()">图文消息</a>
</div>
<div class="col_main">
	<div class="main_bd">
		<div class="media_preview_area">
			<div class="appmsg editing">
				<div class="appmsg_content">
					<div class="js_appmsg_item" data-id="1" id="appmsgItem1">
						<h4 class="appmsg_title">
							<a target="_blank" href="" onclick="return false;"></a>
						</h4>
						<div class="appmsg_info">
							<em class="appmsg_date"></em>
						</div>
						<div class="appmsg_thumb_wrp">
							<img src="" class="js_appmsg_thumb appmsg_thumb"/>
							<i class="appmsg_thumb default">封面图片</i>
						</div>
						<p class="appmsg_desc"></p>
					</div>
				</div>
			</div>
		</div>

		<div class="media_edit_area">
			<div id="js_appmsg_editor" data-target="1">
				<div class="appmsg_editor" style="margin-top: 0px;">
					<div class="inner">
						<div class="appmsg_edit_item">
							<label class="frm_label">标题</label>
							<span class="frm_input_box"><input type="text" class="frm_input js_title"/></span>
						</div>
						<div class="appmsg_edit_item">
							<label class="frm_label">
								<strong class="title">封面</strong>
								<p class="js_cover_tip tips">（大图片建议尺寸：360像素 * 200像素）</p>
							</label>
							<div class="frm_input_box">
								<div class="upload_box">
									<div class="upload_area span_file" for="image_file">
										<input type="file" class="file" id="image_file" ext="*.jpg,*.png,*.gif"/>
										<label for="image_file"><i class="icon18_common upload_gray"></i>上传</label>
									</div>
									<p class="js_cover upload_preview" style="display: none;">
										<img src=""/><a onclick="return false;" href="javascript:void(0);" class="js_removeCover">删除</a>
									</p>
								</div>
							</div>
						</div>
						<div class="appmsg_edit_item">
							<label class="frm_label">摘要</label>
							<span class="frm_textarea_box"><textarea class="js_desc frm_textarea"></textarea></span>
						</div>
						<div class="appmsg_edit_item">
							<label class="frm_label">
								<strong class="title">原文链接</strong>
								<p class="js_cover_tip tips">（请输入http或https开头的链接地址）</p>
							</label>
							<span class="frm_input_box"><input type="text" class="frm_input js_url easyui-validatebox" data-options="invalidMessage:'请输入合法的链接地址',required:false,tipPosition:'left',validType:'url'"/></span>
						</div>
					</div>
					<i class="arrow arrow_out" style="margin-top: 0px;"></i>
					<i class="arrow arrow_in" style="margin-top: 0px;"></i>
				</div>
			</div>
		</div>
	</div>
</div>