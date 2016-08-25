<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.holodniysvitanok.weatherstationwebserver.services.GlobalConfig" %>

<div id="leftMainDiv">
	<div class="leftDiv1">
		<a href="${pageContext.request.contextPath}/<%= GlobalConfig.HOOD_LINK %>/sensors">Датчики</a>
	</div>
	<div class="leftDiv1">
		<a href="${pageContext.request.contextPath}/<%= GlobalConfig.HOOD_LINK %>/pointers?id=100">Точки
			измерений</a>
	</div>
	<div class="leftDiv1">
		<a href="${pageContext.request.contextPath}/<%= GlobalConfig.HOOD_LINK %>/users">Пользователи</a>
	</div>
	<div class="leftDiv1">
		<a href="${pageContext.request.contextPath}/<%= GlobalConfig.HOOD_LINK %>/#">Логи</a>
	</div>
</div>

