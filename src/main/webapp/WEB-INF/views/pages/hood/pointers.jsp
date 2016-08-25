<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.holodniysvitanok.weatherstationwebserver.services.GlobalConfig"%>
<a href="${pageContext.servletContext.contextPath}/<%= GlobalConfig.HOOD_LINK %>/setPoint?id=add">Добавить
	точку в ручную</a>
<table>
	<tr>
		<td>id</td>
		<td>Дата</td>
		<td>Тип</td>
		<td>Значение</td>
		<td>id датчика</td>
		<td>Действия</td>
	</tr>
	<c:forEach var="point" items="${pointers}">
		<tr>
			<td>${point.id}</td>
			<td>${point.datePoint}</td>
			<td>${point.typeMeasurement}</td>
			<td>${point.value}</td>
			<td>${point.measuringSensor.id}</td>
			<td><a
				href="${pageContext.servletContext.contextPath}/<%= GlobalConfig.HOOD_LINK %>/setPoint?id=${point.id}">ред.
			</a> <a
				href="${pageContext.servletContext.contextPath}/<%= GlobalConfig.HOOD_LINK %>/deletePoint?id=${point.id}">
					удл.</a></td>
		</tr>
	</c:forEach>
</table>
