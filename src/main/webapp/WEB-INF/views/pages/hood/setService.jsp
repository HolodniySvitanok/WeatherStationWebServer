<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.holodniysvitanok.weatherstationwebserver.services.GlobalConfig"%>

<form action="${pageContext.servletContext.contextPath}/<%= GlobalConfig.HOOD_LINK %>/setting" method="GET">
	<table>
		<tr>
			<td>id сенсора (Симулятор):</td>
			<td><input type="text" name="sensorId" value="${sensorId}"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Сохранить"></td>
		</tr>
	</table>
</form>
<br>
Симуляция через парсинг странички "Метеопост" :
<a
	href="${pageContext.servletContext.contextPath}/<%= GlobalConfig.HOOD_LINK %>/setting?v1=
																		<c:if test="${not  active}">on</c:if>
																		<c:if test="${active}">off</c:if> ">
	<c:if test="${active}">Вкл.</c:if> <c:if test="${not active}">Откл.</c:if>
</a>
