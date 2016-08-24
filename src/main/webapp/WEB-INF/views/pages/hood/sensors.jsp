<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="leftMain">
	<div>Все датчики</div><div><a href="${pageContext.servletContext.contextPath}/hood/setSensor?id=add">Добавить датчик</a></div>
	<table>
		<tr>
			<td><div class="txt">ID</div></td>
			<td><div class="txt">Место положения</div></td>
			<td><div class="txt">Пароль</div></td>
			<td><div class="txt">Состояние</div></td>
			<td><div class="txt">Действие</div></td>

		</tr>
		<c:forEach var="sensors" items="${allSensors}">
			<tr>
				<td>${sensors.id}</td>
				<td>${sensors.location}</td>
				<td>${sensors.password}</td>
				<td>${sensors.active}</td>
				<td>
					<a href="${pageContext.servletContext.contextPath}/hood/setSensor?id=${sensors.id}">ред. </a>
					<a href="${pageContext.servletContext.contextPath}/hood/delete?id=${sensors.id}"> удл.</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>