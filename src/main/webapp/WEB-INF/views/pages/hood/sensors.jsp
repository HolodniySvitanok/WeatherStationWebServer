<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.holodniysvitanok.weatherstationwebserver.services.GlobalConfig"%>
<div id="leftMain">
	<div>
		<a href="${pageContext.servletContext.contextPath}/<%= GlobalConfig.HOOD_LINK %>/setSensor?id=add">Добавить
			датчик</a>
	</div>
	<table>
		<tr>
			<td><div class="txt">id</div></td>
			<td><div class="txt">Место положения</div></td>
			<td><div class="txt">Пароль</div></td>
			<td><div class="txt">Состояние</div></td>
			<td><div class="txt">Действия</div></td>
		</tr>
		<c:forEach var="sensors" items="${allSensors}">
			<tr>
				<td>${sensors.id}</td>
				<td>${sensors.location}</td>
				<td>${sensors.password}</td>
				<td>${sensors.active}</td>
				<td><a
					href="${pageContext.servletContext.contextPath}/<%= GlobalConfig.HOOD_LINK %>/setSensor?id=${sensors.id}">ред.
				</a> <a
					href="${pageContext.servletContext.contextPath}/<%= GlobalConfig.HOOD_LINK %>/delete?id=${sensors.id}">
						удл.</a></td>
			</tr>
		</c:forEach>
	</table>
</div>