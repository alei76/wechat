<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<img height="400px" alt="${mediaAtta.name }" src="${ pageContext.request.contextPath}/anon/image/prev/${mediaAtta.attaId}?r=${covisint:timemillis()}" />
