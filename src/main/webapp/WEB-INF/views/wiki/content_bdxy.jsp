<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- bodytext -->
<p>对于每一个POST请求，开发者在响应包（Get）中返回特定XML结构，对该消息进行响应（现支持回复文本、图片、图文、语音、视频、音乐）。请注意，回复图片等多媒体消息时需要预先上传多媒体文件到微信服务器，只支持认证服务号。</p>
<p><b>消息创建时间 （整型）CreateTime字段请精确到秒</b></p>
<p><b>微信服务器在五秒内收不到响应会断掉连接，并且重新发起请求，总共重试三次</b>，如果在调试中，发现用户无法收到响应的消息，可以检查是否消息处理超时。</p>
<p>关于重试的消息排重，有msgid的消息推荐使用msgid排重。事件类型消息推荐使用FromUserName + CreateTime 排重。</p>
<p><b>假如服务器无法保证在五秒内处理并回复，可以直接回复空串，微信服务器不会对此作任何处理，并且不会发起重试。</b>这种情况下，可以使用客服消息接口进行异步回复。</p>
<p>各消息类型需要的XML数据包结构如下。</p>
<table class="toc" id="toc">
	<tbody>
		<tr>
			<td>
				<div id="toctitle">
					<h2>目录</h2>
				</div>
				<ul>
					<li class="toclevel-1 tocsection-1">
						<a href="javascript:;"><span class="tocnumber">1</span><span class="toctext">回复文本消息</span></a>
					</li>
					<li class="toclevel-1 tocsection-2">
						<a href="javascript:;"><span class="tocnumber">2</span><span class="toctext">回复图片消息</span></a>
					</li>
					<li class="toclevel-1 tocsection-3">
						<a href="javascript:;"><span class="tocnumber">3</span><span class="toctext">回复语音消息</span></a>
					</li>
					<li class="toclevel-1 tocsection-4">
						<a href="javascript:;"><span class="tocnumber">4</span><span class="toctext">回复视频消息</span></a>
					</li>
					<li class="toclevel-1 tocsection-5">
						<a href="javascript:;"><span class="tocnumber">5</span><span class="toctext">回复音乐消息</span></a>
					</li>
					<li class="toclevel-1 tocsection-6">
						<a href="javascript:;"><span class="tocnumber">6</span><span class="toctext">回复图文消息</span></a>
					</li>
				</ul>
			</td>
		</tr>
	</tbody>
</table>
<h3>
	<span class="mw-headline"><b>回复文本消息</b></span>
</h3>
<pre>
&lt;xml&gt;
	&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
	&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;
	&lt;CreateTime&gt;12345678&lt;/CreateTime&gt;
	&lt;MsgType&gt;&lt;![CDATA[text]]&gt;&lt;/MsgType&gt;
	&lt;Content&gt;&lt;![CDATA[你好]]&gt;&lt;/Content&gt;
&lt;/xml&gt;
</pre>
<table border="1" align="center" cellspacing="0" cellpadding="4">
	<tbody>
		<tr>
			<th style="width: 120px">参数</th>
			<th style="width: 120px">是否必须</th>
			<th style="width: 470px">描述</th>
		</tr>
		<tr>
			<td>ToUserName</td>
			<td>是</td>
			<td>接收方帐号（收到的OpenID）</td>
		</tr>
		<tr>
			<td>FromUserName</td>
			<td>是</td>
			<td><b>开发者</b>微信号</td>
		</tr>
		<tr>
			<td>CreateTime</td>
			<td>是</td>
			<td>消息创建时间 （整型）</td>
		</tr>
		<tr>
			<td>MsgType</td>
			<td>是</td>
			<td>text</td>
		</tr>
		<tr>
			<td>Content</td>
			<td>是</td>
			<td>回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）</td>
		</tr>
	</tbody>
</table>
<h3>
	<span class="mw-headline"><b>回复图片消息</b></span>
</h3>
<pre>
&lt;xml&gt;
	&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
	&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;
	&lt;CreateTime&gt;12345678&lt;/CreateTime&gt;
	&lt;MsgType&gt;&lt;![CDATA[image]]&gt;&lt;/MsgType&gt;
	&lt;Image&gt;
		&lt;MediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/MediaId&gt;
	&lt;/Image&gt;
&lt;/xml&gt;
</pre>
<table width="640px" border="1" align="center" cellspacing="0" cellpadding="4">
	<tbody>
		<tr>
			<th style="width: 120px">参数</th>
			<th style="width: 120px">是否必须</th>
			<th>说明</th>
		</tr>
		<tr>
			<td>ToUserName</td>
			<td>是</td>
			<td>接收方帐号（收到的OpenID）</td>
		</tr>
		<tr>
			<td>FromUserName</td>
			<td>是</td>
			<td><b>开发者</b>微信号</td>
		</tr>
		<tr>
			<td>CreateTime</td>
			<td>是</td>
			<td>消息创建时间 （整型）</td>
		</tr>
		<tr>
			<td>MsgType</td>
			<td>是</td>
			<td>image</td>
		</tr>
		<tr>
			<td>MediaId</td>
			<td>是</td>
			<td>通过上传多媒体文件，得到的id。</td>
		</tr>
	</tbody>
</table>
<h3>
	<span class="mw-headline"><b>回复语音消息</b></span>
</h3>
<pre>
&lt;xml&gt;
	&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
	&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;
	&lt;CreateTime&gt;12345678&lt;/CreateTime&gt;
	&lt;MsgType&gt;&lt;![CDATA[voice]]&gt;&lt;/MsgType&gt;
	&lt;Voice&gt;
		&lt;MediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/MediaId&gt;
	&lt;/Voice&gt;
&lt;/xml&gt;
</pre>
<table width="640px" border="1" align="center" cellspacing="0" cellpadding="4">
	<tbody>
		<tr>
			<th style="width: 120px">参数</th>
			<th style="width: 120px">是否必须</th>
			<th>说明</th>
		</tr>
		<tr>
			<td>ToUserName</td>
			<td>是</td>
			<td>接收方帐号（收到的OpenID）</td>
		</tr>
		<tr>
			<td>FromUserName</td>
			<td>是</td>
			<td><b>开发者</b>微信号</td>
		</tr>
		<tr>
			<td>CreateTime</td>
			<td>是</td>
			<td>消息创建时间戳 （整型）</td>
		</tr>
		<tr>
			<td>MsgType</td>
			<td>是</td>
			<td>语音，voice</td>
		</tr>
		<tr>
			<td>MediaId</td>
			<td>是</td>
			<td>通过上传多媒体文件，得到的id</td>
		</tr>
	</tbody>
</table>
<h3>
	<span class="mw-headline"><b>回复视频消息</b></span>
</h3>
<pre>
&lt;xml&gt;
	&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
	&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;
	&lt;CreateTime&gt;12345678&lt;/CreateTime&gt;
	&lt;MsgType&gt;&lt;![CDATA[video]]&gt;&lt;/MsgType&gt;
	&lt;Video&gt;
		&lt;MediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/MediaId&gt;
		&lt;Title&gt;&lt;![CDATA[title]]&gt;&lt;/Title&gt;
		&lt;Description&gt;&lt;![CDATA[description]]&gt;&lt;/Description&gt;
	&lt;/Video&gt; 
&lt;/xml&gt;
</pre>
<table width="640px" border="1" align="center" cellspacing="0" cellpadding="4">
	<tbody>
		<tr>
			<th style="width: 120px">参数</th>
			<th style="width: 120px">是否必须</th>
			<th>说明</th>
		</tr>
		<tr>
			<td>ToUserName</td>
			<td>是</td>
			<td>接收方帐号（收到的OpenID）</td>
		</tr>
		<tr>
			<td>FromUserName</td>
			<td>是</td>
			<td><b>开发者</b>微信号</td>
		</tr>
		<tr>
			<td>CreateTime</td>
			<td>是</td>
			<td>消息创建时间 （整型）</td>
		</tr>
		<tr>
			<td>MsgType</td>
			<td>是</td>
			<td>video</td>
		</tr>
		<tr>
			<td>MediaId</td>
			<td>是</td>
			<td>通过上传多媒体文件，得到的id</td>
		</tr>
		<tr>
			<td>Title</td>
			<td>否</td>
			<td>视频消息的标题</td>
		</tr>
		<tr>
			<td>Description</td>
			<td>否</td>
			<td>视频消息的描述</td>
		</tr>
	</tbody>
</table>
<h3>
	<span class="mw-headline"><b>回复音乐消息</b></span>
</h3>
<pre>
&lt;xml&gt;
	&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
	&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;
	&lt;CreateTime&gt;12345678&lt;/CreateTime&gt;
	&lt;MsgType&gt;&lt;![CDATA[music]]&gt;&lt;/MsgType&gt;
	&lt;Music&gt;
		&lt;Title&gt;&lt;![CDATA[TITLE]]&gt;&lt;/Title&gt;
		&lt;Description&gt;&lt;![CDATA[DESCRIPTION]]&gt;&lt;/Description&gt;
		&lt;MusicUrl&gt;&lt;![CDATA[MUSIC_Url]]&gt;&lt;/MusicUrl&gt;
		&lt;HQMusicUrl&gt;&lt;![CDATA[HQ_MUSIC_Url]]&gt;&lt;/HQMusicUrl&gt;
		&lt;ThumbMediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/ThumbMediaId&gt;
	&lt;/Music&gt;
&lt;/xml&gt;
</pre>
<table width="640px" border="1" align="center" cellspacing="0" cellpadding="4">
	<tbody>
		<tr>
			<th style="width: 120px">参数</th>
			<th style="width: 120px">是否必须</th>
			<th>说明</th>
		</tr>
		<tr>
			<td>ToUserName</td>
			<td>是</td>
			<td>接收方帐号（收到的OpenID）</td>
		</tr>
		<tr>
			<td>FromUserName</td>
			<td>是</td>
			<td><b>开发者</b>微信号</td>
		</tr>
		<tr>
			<td>CreateTime</td>
			<td>是</td>
			<td>消息创建时间 （整型）</td>
		</tr>
		<tr>
			<td>MsgType</td>
			<td>是</td>
			<td>music</td>
		</tr>
		<tr>
			<td>Title</td>
			<td>否</td>
			<td>音乐标题</td>
		</tr>
		<tr>
			<td>Description</td>
			<td>否</td>
			<td>音乐描述</td>
		</tr>
		<tr>
			<td>MusicURL</td>
			<td>否</td>
			<td>音乐链接</td>
		</tr>
		<tr>
			<td>HQMusicUrl</td>
			<td>否</td>
			<td>高质量音乐链接，WIFI环境优先使用该链接播放音乐</td>
		</tr>
		<tr>
			<td>ThumbMediaId</td>
			<td>是</td>
			<td>缩略图的媒体id，通过上传多媒体文件，得到的id</td>
		</tr>
	</tbody>
</table>
<h3>
	<span class="mw-headline"><b>回复图文消息</b></span>
</h3>
<pre>
&lt;xml&gt;
	&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
	&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;
	&lt;CreateTime&gt;12345678&lt;/CreateTime&gt;
	&lt;MsgType&gt;&lt;![CDATA[news]]&gt;&lt;/MsgType&gt;
	&lt;ArticleCount&gt;2&lt;/ArticleCount&gt;
	&lt;Articles&gt;
		&lt;item&gt;
			&lt;Title&gt;&lt;![CDATA[title1]]&gt;&lt;/Title&gt; 
			&lt;Description&gt;&lt;![CDATA[description1]]&gt;&lt;/Description&gt;
			&lt;PicUrl&gt;&lt;![CDATA[picurl]]&gt;&lt;/PicUrl&gt;
			&lt;Url&gt;&lt;![CDATA[url]]&gt;&lt;/Url&gt;
		&lt;/item&gt;
		&lt;item&gt;
			&lt;Title&gt;&lt;![CDATA[title]]&gt;&lt;/Title&gt;
			&lt;Description&gt;&lt;![CDATA[description]]&gt;&lt;/Description&gt;
			&lt;PicUrl&gt;&lt;![CDATA[picurl]]&gt;&lt;/PicUrl&gt;
			&lt;Url&gt;&lt;![CDATA[url]]&gt;&lt;/Url&gt;
		&lt;/item&gt;
	&lt;/Articles&gt;
&lt;/xml&gt; 
</pre>
<table width="640px" border="1" align="center" cellspacing="0" cellpadding="4">
	<tbody>
		<tr>
			<th style="width: 120px">参数</th>
			<th style="width: 120px">是否必须</th>
			<th>说明</th>
		</tr>
		<tr>
			<td>ToUserName</td>
			<td>是</td>
			<td>接收方帐号（收到的OpenID）</td>
		</tr>
		<tr>
			<td>FromUserName</td>
			<td>是</td>
			<td><b>开发者</b>微信号</td>
		</tr>
		<tr>
			<td>CreateTime</td>
			<td>是</td>
			<td>消息创建时间 （整型）</td>
		</tr>
		<tr>
			<td>MsgType</td>
			<td>是</td>
			<td>news</td>
		</tr>
		<tr>
			<td>ArticleCount</td>
			<td>是</td>
			<td>图文消息个数，限制为10条以内</td>
		</tr>
		<tr>
			<td>Articles</td>
			<td>是</td>
			<td>多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应</td>
		</tr>
		<tr>
			<td>Title</td>
			<td>否</td>
			<td>图文消息标题</td>
		</tr>
		<tr>
			<td>Description</td>
			<td>否</td>
			<td>图文消息描述</td>
		</tr>
		<tr>
			<td>PicUrl</td>
			<td>否</td>
			<td>图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200</td>
		</tr>
		<tr>
			<td>Url</td>
			<td>否</td>
			<td>点击图文消息跳转链接</td>
		</tr>
	</tbody>
</table>
<!-- /bodytext -->
