<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.holodniysvitanok.weatherstationwebserver.services.GlobalConfig" %>
	<div id="body">
		<div id="table1">
			<table>
				<tr>
					<td colspan="3"><h3>Погода на время ${time}</h3></td>
				</tr>
				<tr>
					<td>Температура воздуха</td>
					<td>${temperature}</td>
					<td>С</td>
				</tr>
				<tr>
					<td>Атмосферное давление</td>
					<td>${pressure}</td>
					<td>мм.рт.ст</td>
				</tr>
				<tr>
					<td>Влажность воздуха</td>
					<td>${humidity}</td>
					<td>%</td>
				</tr>
			</table>
		</div>
		<div id="image">
		<c:if test="${ image }">
			<div class="text">Температура</div>
			<br>
			<img alt="график"
				src="${pageContext.request.contextPath}/getimage?sdate=${sdate}&edate=${edate}&type=Temperature&id=${location}">
			<br>
			<div class="text">Давление</div>
			<br>
			<img alt="график"
				src="${pageContext.request.contextPath}/getimage?sdate=${sdate}&edate=${edate}&type=Pressure&id=${location}">
			<br>
			<div class="text">Влажность</div>
			<br>
			<img alt="график"
				src="${pageContext.request.contextPath}/getimage?sdate=${sdate}&edate=${edate}&type=Humidity&id=${location}">
			<br>
		</c:if>
		<c:if test="${not image }">
			<h2>Нет данных измерений</h2>
		</c:if>
		</div>
	</div>

