<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- bodytext -->
<p>当用户主动发消息给公众号的时候（包括发送信息、点击自定义菜单、订阅事件、扫描二维码事件、支付成功事件、用户维权），微信将会把消息数据推送给开发者，开发者在一段时间内（目前修改为48小时）可以调用客服消息接口，通过POST一个JSON数据包来发送消息给普通用户，在48小时内不限制发送次数。此接口主要用于客服等有人工消息处理环节的功能，方便开发者为用户提供更加优质的服务。</p>
<p><b>接口调用请求说明</b></p>
<pre>
http请求方式: POST
<a rel="nofollow" class="external free" href="http://apigatewayqa.sgmlink.com/wechat/api-wechat/cgi-bin/message/custom/send?accountId=ACCOUNT_ID" target="_blank">http://apigatewayqa.sgmlink.com/wechat/api-wechat/cgi-bin/message/custom/send?accountId=ACCOUNT_ID</a>
<b>请求Header的<font style="color:red;">Content-Type</font>请定义为<font style="color:red;">text/xml;charset=UTF-8</font></b>
</pre>
<p>各消息类型所需的JSON数据包如下。</p>
<table class="toc" id="toc">
	<tbody>
		<tr>
			<td>
				<div id="toctitle">
					<h2>目录</h2>
				</div>
				<ul>
					<li class="toclevel-1 tocsection-1">
						<a href="#fskfxx.1"><span class="tocnumber">1</span><span class="toctext">发送文本消息</span></a>
					</li>
					<li class="toclevel-1 tocsection-2">
						<a href="#fskfxx.2"><span class="tocnumber">2</span><span class="toctext">发送图片消息</span></a>
					</li>
					<li class="toclevel-1 tocsection-3">
						<a href="#fskfxx.3"><span class="tocnumber">3</span><span class="toctext">发送语音消息</span></a>
					</li>
					<li class="toclevel-1 tocsection-4">
						<a href="#fskfxx.4"><span class="tocnumber">4</span><span class="toctext">发送视频消息</span></a>
					</li>
					<li class="toclevel-1 tocsection-5">
						<a href="#fskfxx.5"><span class="tocnumber">5</span><span class="toctext">发送音乐消息</span></a>
					</li>
					<li class="toclevel-1 tocsection-6">
						<a href="#fskfxx.6"><span class="tocnumber">6</span><span class="toctext">发送图文消息</span></a>
					</li>
				</ul>
			</td>
		</tr>
	</tbody>
</table>
<h3 id="fskfxx.1">
	<span class="mw-headline"><b>发送文本消息</b></span>
</h3>
<pre>{
    "touser":"OPENID",
    "msgtype":"text",
    "text":
    {
         "content":"Hello World"
    }
}
</pre>
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
			<td>touser</td>
			<td>是</td>
			<td>普通用户openid</td>
		</tr>
		<tr>
			<td>msgtype</td>
			<td>是</td>
			<td>消息类型，text</td>
		</tr>
		<tr>
			<td>content</td>
			<td>是</td>
			<td>文本消息内容</td>
		</tr>
	</tbody>
</table>
<h3 id="fskfxx.2">
	<span class="mw-headline"><b>发送图片消息</b></span>
</h3>
<pre>{
    "touser":"OPENID",
    "msgtype":"image",
    "image":
    {
      "media_id":"MEDIA_ID"
    }
}
</pre>
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
			<td>touser</td>
			<td>是</td>
			<td>普通用户openid</td>
		</tr>
		<tr>
			<td>msgtype</td>
			<td>是</td>
			<td>消息类型，image</td>
		</tr>
		<tr>
			<td>media_id</td>
			<td>是</td>
			<td>发送的图片的媒体ID</td>
		</tr>
	</tbody>
</table>
<h3 id="fskfxx.3">
	<span class="mw-headline"><b>发送语音消息</b></span>
</h3>
<pre>{
    "touser":"OPENID",
    "msgtype":"voice",
    "voice":
    {
      "media_id":"MEDIA_ID"
    }
}
</pre>
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
			<td>touser</td>
			<td>是</td>
			<td>普通用户openid</td>
		</tr>
		<tr>
			<td>msgtype</td>
			<td>是</td>
			<td>消息类型，voice</td>
		</tr>
		<tr>
			<td>media_id</td>
			<td>是</td>
			<td>发送的语音的媒体ID</td>
		</tr>
	</tbody>
</table>
<h3 id="fskfxx.4">
	<span class="mw-headline"><b>发送视频消息</b></span>
</h3>
<pre>{
    "touser":"OPENID",
    "msgtype":"video",
    "video":
    {
      "media_id":"MEDIA_ID",
      "thumb_media_id":"MEDIA_ID",
      "title":"TITLE",
      "description":"DESCRIPTION"
    }
}
</pre>
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
			<td>touser</td>
			<td>是</td>
			<td>普通用户openid</td>
		</tr>
		<tr>
			<td>msgtype</td>
			<td>是</td>
			<td>消息类型，video</td>
		</tr>
		<tr>
			<td>media_id</td>
			<td>是</td>
			<td>发送的视频的媒体ID</td>
		</tr>
		<tr>
			<td>thumb_media_id</td>
			<td>是</td>
			<td>缩略图的媒体ID</td>
		</tr>
		<tr>
			<td>title</td>
			<td>否</td>
			<td>视频消息的标题</td>
		</tr>
		<tr>
			<td>description</td>
			<td>否</td>
			<td>视频消息的描述</td>
		</tr>
	</tbody>
</table>
<h3 id="fskfxx.5">
	<span class="mw-headline"><b>发送音乐消息</b></span>
</h3>
<pre>{
    "touser":"OPENID",
    "msgtype":"music",
    "music":
    {
      "title":"MUSIC_TITLE",
      "description":"MUSIC_DESCRIPTION",
      "musicurl":"MUSIC_URL",
      "hqmusicurl":"HQ_MUSIC_URL",
      "thumb_media_id":"THUMB_MEDIA_ID" 
    }
}
</pre>
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
			<td>touser</td>
			<td>是</td>
			<td>普通用户openid</td>
		</tr>
		<tr>
			<td>msgtype</td>
			<td>是</td>
			<td>消息类型，music</td>
		</tr>
		<tr>
			<td>title</td>
			<td>否</td>
			<td>音乐标题</td>
		</tr>
		<tr>
			<td>description</td>
			<td>否</td>
			<td>音乐描述</td>
		</tr>
		<tr>
			<td>musicurl</td>
			<td>是</td>
			<td>音乐链接</td>
		</tr>
		<tr>
			<td>hqmusicurl</td>
			<td>是</td>
			<td>高品质音乐链接，wifi环境优先使用该链接播放音乐</td>
		</tr>
		<tr>
			<td>thumb_media_id</td>
			<td>是</td>
			<td>缩略图的媒体ID</td>
		</tr>
	</tbody>
</table>
<h3 id="fskfxx.6">
	<span class="mw-headline"><b>发送图文消息</b></span>
</h3>
<p>图文消息条数限制在10条以内，注意，如果图文数超过10，则将会无响应。</p>
<pre>{
    "touser":"OPENID",
    "msgtype":"news",
    "news":{
        "articles": [
         {
             "title":"Happy Day",
             "description":"Is Really A Happy Day",
             "url":"URL",
             "picurl":"PIC_URL"
         },
         {
             "title":"Happy Day",
             "description":"Is Really A Happy Day",
             "url":"URL",
             "picurl":"PIC_URL"
         }
         ]
    }
}
</pre>
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
			<td>touser</td>
			<td>是</td>
			<td>普通用户openid</td>
		</tr>
		<tr>
			<td>msgtype</td>
			<td>是</td>
			<td>消息类型，news</td>
		</tr>
		<tr>
			<td>title</td>
			<td>否</td>
			<td>标题</td>
		</tr>
		<tr>
			<td>description</td>
			<td>否</td>
			<td>描述</td>
		</tr>
		<tr>
			<td>url</td>
			<td>否</td>
			<td>点击后跳转的链接</td>
		</tr>
		<tr>
			<td>picurl</td>
			<td>否</td>
			<td>图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80</td>
		</tr>
	</tbody>
</table>
<!-- /bodytext -->
