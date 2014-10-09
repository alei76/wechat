<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- bodytext -->
<p>公众号在使用接口时，对多媒体文件、多媒体消息的获取和调用等操作，是通过media_id来进行的。通过本接口，公众号可以上传或下载多媒体文件。但请注意，每个多媒体文件（media_id）会在上传、用户发送到微信服务器3天后自动删除，以节省服务器资源。</p>
<h3><span class="mw-headline"><b>上传多媒体文件</b></span></h3>
<p>公众号可调用本接口来上传图片、语音、视频等文件到微信服务器，上传后服务器会返回对应的media_id，公众号此后可根据该media_id来获取多媒体。<b>请注意，media_id是可复用的。</b></p>
<p><b>接口调用请求说明</b></p>
<pre>
http请求方式: POST/FORM
<a rel="nofollow" class="external free" href="http://apigatewayqa.sgmlink.com/wechat/api-wechat/cgi-bin/media/upload?accountId=ACCOUNT_ID&amp;type=TYPE" target="_blank">http://apigatewayqa.sgmlink.com/wechat/api-wechat/cgi-bin/media/upload?accountId=ACCOUNT_ID&amp;type=TYPE</a>
</pre>
<p><b>参数说明</b></p>
<table width="640px" border="1" align="center" cellspacing="0" cellpadding="4">
	<tbody>
		<tr>
			<th style="width: 120px">参数</th>
			<th style="width: 120px">是否必须</th>
			<th>说明</th>
		</tr>
		<tr>
			<td>accountId</td>
			<td>是</td>
			<td>SGM微信平台账户唯一凭证</td>
		</tr>
		<tr>
			<td>type</td>
			<td>是</td>
			<td>媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）</td>
		</tr>
		<tr>
			<td>media</td>
			<td>是</td>
			<td>form-data中媒体文件标识，有filename、filelength、content-type等信息</td>
		</tr>
	</tbody>
</table>
<p><b>返回说明</b></p>
<p>正确情况下的返回JSON数据包结果如下：</p>
<pre>{"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}</pre>
<table width="640px" border="1" align="center" cellspacing="0" cellpadding="4">
	<tbody>
		<tr>
			<th style="width: 240px">参数</th>
			<th>描述</th>
		</tr>
		<tr>
			<td>type</td>
			<td>
				媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb，主要用于视频与音乐格式的缩略图）</td>
		</tr>
		<tr>
			<td>media_id</td>
			<td>媒体文件上传后，获取时的唯一标识</td>
		</tr>
		<tr>
			<td>created_at</td>
			<td>媒体文件上传时间戳</td>
		</tr>
	</tbody>
</table>
<p>错误情况下的返回JSON数据包示例如下（示例为无效媒体类型错误）：</p>
<pre>{"errcode":40004,"errmsg":"invalid media type"}</pre>
<p><b>注意事项</b></p>
<p>上传的多媒体文件有格式和大小限制，如下：</p>
<ul>
	<li>图片（image）: 1M，支持JPG格式</li>
	<li>语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式</li>
	<li>视频（video）：10MB，支持MP4格式</li>
	<li>缩略图（thumb）：64KB，支持JPG格式</li>
</ul>
<p><b>媒体文件在后台保存时间为3天，即3天后media_id失效。</b></p>
<h3><span class="mw-headline"><b>下载多媒体文件</b></span></h3>
<p>公众号可调用本接口来获取多媒体文件。<b>请注意，视频文件不支持下载，调用该接口需http协议。</b></p>
<p><b>接口调用请求说明</b></p>
<pre>
http请求方式: GET
<a rel="nofollow" class="external free" href="http://apigatewayqa.sgmlink.com/wechat/api-wechat/cgi-bin/media/get?accountId=ACCOUNT_ID&amp;media_id=MEDIA_ID" target="_blank">http://apigatewayqa.sgmlink.com/wechat/api-wechat/cgi-bin/media/get?accountId=ACCOUNT_ID&amp;media_id=MEDIA_ID</a>
</pre>
<p><b>参数说明</b></p>
<table width="640px" border="1" align="center" cellspacing="0" cellpadding="4">
	<tbody>
		<tr>
			<th style="width: 120px">参数</th>
			<th style="width: 120px">是否必须</th>
			<th>说明</th>
		</tr>
		<tr>
			<td>accountId</td>
			<td>是</td>
			<td>SGM微信平台账户唯一凭证</td>
		</tr>
		<tr>
			<td>media_id</td>
			<td>是</td>
			<td>媒体文件ID</td>
		</tr>
	</tbody>
</table>
<p><b>返回说明</b></p>
<p>正确情况下的返回HTTP头如下：</p>
<pre>
HTTP/1.1 200 OK
Connection: close
Content-Type: image/jpeg 
Content-disposition: attachment; filename="MEDIA_ID.jpg"
Date: Sun, 06 Jan 2013 10:20:18 GMT
Cache-Control: no-cache, must-revalidate
Content-Length: 339721
</pre>
<p>错误情况下的返回JSON数据包示例如下（示例为无效媒体ID错误）：</p>
<pre>{"errcode":40007,"errmsg":"invalid media_id"}</pre>
<!-- /bodytext -->