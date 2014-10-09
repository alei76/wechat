package com.covisint.wechat.weixin.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class InMessage {
	@XStreamAlias("ToUserName")
	private String toUserName;// 开发者微信号
	@XStreamAlias("FromUserName")
	private String fromUserName;// 发送方帐号（一个OpenID）
	@XStreamAlias("CreateTime")
	private Long createTime;// 消息创建时间 （整型）

	@XStreamAlias("MsgType")
	private String msgType = "text";// 消息类型

	@XStreamAlias("MsgId")
	private Long msgId;// 消息id，64位整型
	// 文本消息
	@XStreamAlias("Content")
	private String content;// 文本消息内容
	// 图片消息
	@XStreamAlias("PicUrl")
	private String picUrl;// 图片链接
	// 位置消息
	@XStreamAlias("Location_X")
	private String locationX;// 地理位置纬度
	@XStreamAlias("Location_Y")
	private String locationY;// 地理位置经度
	@XStreamAlias("Scale")
	private Long scale;// 地图缩放大小
	@XStreamAlias("Label")
	private String label;// 地理位置信息
	@XStreamAlias("Latitude")
	private String latitude;// 地理位置纬度
	@XStreamAlias("Longitude")
	private String longitude;// 地理位置经度
	@XStreamAlias("Precision")
	private String precision;// 地理位置精度
	// 链接消息
	@XStreamAlias("Title")
	private String title;// 消息标题
	@XStreamAlias("Description")
	private String description;// 消息描述
	@XStreamAlias("Url")
	private String url;// 消息链接
	// 语音信息
	@XStreamAlias("MediaId")
	private String mediaId;// 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
	@XStreamAlias("ThumbMediaId")
	private String thumbMediaId;// 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	@XStreamAlias("Format")
	private String format;// 语音格式，如amr，speex等
	@XStreamAlias("Recognition")
	private String recognition;

	// 事件
	@XStreamAlias("Event")
	private String event;
	@XStreamAlias("EventKey")
	private String eventKey;// 未关注:事件KEY值，qrscene_为前缀，后面为二维码的参数值.已关注:事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id.事件KEY值，菜单click事件:与自定义菜单接口中KEY值对应
	@XStreamAlias("Ticket")
	private String ticket;// 二维码的ticket，可用来换取二维码图片
	@XStreamAlias("accountId")
	private String accountId;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getLocationX() {
		return locationX;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	public String getLocationY() {
		return locationY;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	public Long getScale() {
		return scale;
	}

	public void setScale(Long scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getRecognition() {
		return recognition;
	}

	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

}
