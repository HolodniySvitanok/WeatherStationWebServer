<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.holodniysvitanok.weatherstationwebserver.services.GlobalConfig" %>
	<div id="body">
		<div id="table1">
			<table>
				<tr>
					<td colspan="2"><h3>Погода на ${time}</h3></td>
				</tr>
				<c:if test="${not empty temperature}">
					<tr>
						<td>Температура воздуха</td>
						<td><b>${temperature} С</b></td>
						
					</tr>
				</c:if>
				<c:if test="${not empty pressure}">
					<tr>
						<td>Атмосферное давление</td>
						<td><b>${pressure} мм.рт.ст</b></td>
						
					</tr>
				</c:if>
				<c:if test="${not empty humidity}">
					<tr>
						<td>Влажность воздуха</td>
						<td><b>${humidity} %</b></td>
						
					</tr>
				</c:if>
			</table>
		</div>
		<div id="image">
			<c:if test="${not empty temperature}">
				<div class="text">Температура</div>
					<br>
					<img src="${pageContext.request.contextPath}/getimage?sdate=${sdate}&edate=${edate}&type=Temperature&id=${location}">
			</c:if>
			<c:if test="${not empty pressure}">
				<br>
				<div class="text">Давление</div>
				<br>
				<img src="${pageContext.request.contextPath}/getimage?sdate=${sdate}&edate=${edate}&type=Pressure&id=${location}">
			</c:if>
			<c:if test="${not empty humidity}">
				<br>
				<div class="text">Влажность</div>
				<br>
				<img src="${pageContext.request.contextPath}/getimage?sdate=${sdate}&edate=${edate}&type=Humidity&id=${location}">
				<br>
			</c:if>	
		</div>
	</div>

