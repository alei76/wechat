<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- bodytext -->
<p>当普通微信用户向公众账号发消息时，微信服务器将POST消息的XML数据包到开发者填写的URL上。各消息类型的推送XML数据包结构如下：</p>
<p><b>微信服务器在五秒内收不到响应会断掉连接，并且重新发起请求，总共重试三次</b></p>
<p>关于重试的消息排重，推荐使用msgid排重。</p>
<p><b>假如服务器无法保证在五秒内处理并回复，可以直接回复空串，微信服务器不会对此作任何处理，并且不会发起重试。</b></p>
<table class="toc" id="toc">
	<tbody>
		<tr>
			<td>
				<div id="toctitle">
					<h2>目录</h2>
				</div>
				<ul style="display: block;">
					<li class="toclevel-1 tocsection-1">
						<a href="javascript:;"><span class="tocnumber">1</span><span class="toctext">文本消息</span></a>
					</li>
					<li class="toclevel-1 tocsection-2">
						<a href="javascript:;"><span class="tocnumber">2</span><span class="toctext">地理位置消息</span></a>
					</li>
				</ul>
			</td>
		</tr>
	</tbody>
</table>
<h3>
	<span class="mw-headline"><b>文本消息</b></span>
</h3>
<pre>
&lt;xml&gt;
	&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
	&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt; 
	&lt;CreateTime&gt;1348831860&lt;/CreateTime&gt;
	&lt;MsgType&gt;&lt;![CDATA[text]]&gt;&lt;/MsgType&gt;
	&lt;Content&gt;&lt;![CDATA[this is a test]]&gt;&lt;/Content&gt;
	&lt;MsgId&gt;1234567890123456&lt;/MsgId&gt;
&lt;/xml&gt;
</pre>
<table border="1" align="center" cellspacing="0" cellpadding="4">
	<tbody>
		<tr>
			<th style="width: 180px">参数</th>
			<th style="width: 470px">描述</th>
		</tr>
		<tr>
			<td>ToUserName</td>
			<td><b>开发者</b>微信号</td>
		</tr>
		<tr>
			<td>FromUserName</td>
			<td>发送方帐号（一个OpenID）</td>
		</tr>
		<tr>
			<td>CreateTime</td>
			<td>消息创建时间 （整型）</td>
		</tr>
		<tr>
			<td>MsgType</td>
			<td>text</td>
		</tr>
		<tr>
			<td>Content</td>
			<td>文本消息内容</td>
		</tr>
		<tr>
			<td>MsgId</td>
			<td>消息id，64位整型</td>
		</tr>
	</tbody>
</table>
<h3>
	<span class="mw-headline"><b>地理位置消息</b></span>
</h3>
<pre>
&lt;xml&gt;
	&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
	&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;
	&lt;CreateTime&gt;1351776360&lt;/CreateTime&gt;
	&lt;MsgType&gt;&lt;![CDATA[location]]&gt;&lt;/MsgType&gt;
	&lt;Location_X&gt;23.134521&lt;/Location_X&gt;
	&lt;Location_Y&gt;113.358803&lt;/Location_Y&gt;
	&lt;Scale&gt;20&lt;/Scale&gt;
	&lt;Label&gt;&lt;![CDATA[位置信息]]&gt;&lt;/Label&gt;
	&lt;MsgId&gt;1234567890123456&lt;/MsgId&gt;
&lt;/xml&gt; 
</pre>
<table border="1" align="center" cellspacing="0" cellpadding="4">
	<tbody>
		<tr>
			<th style="width: 180px">参数</th>
			<th style="width: 470px">描述</th>
		</tr>
		<tr>
			<td>ToUserName</td>
			<td><b>开发者</b>微信号</td>
		</tr>
		<tr>
			<td>FromUserName</td>
			<td>发送方帐号（一个OpenID）</td>
		</tr>
		<tr>
			<td>CreateTime</td>
			<td>消息创建时间 （整型）</td>
		</tr>
		<tr>
			<td>MsgType</td>
			<td>location</td>
		</tr>
		<tr>
			<td>Location_X</td>
			<td>地理位置维度</td>
		</tr>
		<tr>
			<td>Location_Y</td>
			<td>地理位置经度</td>
		</tr>
		<tr>
			<td>Scale</td>
			<td>地图缩放大小</td>
		</tr>
		<tr>
			<td>Label</td>
			<td>地理位置信息</td>
		</tr>
		<tr>
			<td>MsgId</td>
			<td>消息id，64位整型</td>
		</tr>
	</tbody>
</table>
<!-- /bodytext -->
