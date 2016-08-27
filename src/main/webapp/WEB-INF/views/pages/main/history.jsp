<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.holodniysvitanok.weatherstationwebserver.services.GlobalConfig"%>
<div id="his1">
	<form action="${pageContext.servletContext.contextPath}/history" method="POST">
		Показать значение в период с 
		<input type="text" class="in1" name="start" value="${start}"> - <input type="text" class="in1" name="end" value="${end}">
		 <input type="submit" value="Показать">
		  <br><br> 
		  Дополнительный график для сравнения 
		  <select size="1" name="loc_sel">
		  	<option>-</option>
			<c:forEach var="sensors" items="${listSensors}">
				<c:if test="${ location != sensors.id}">
					<option value="${sensors.id}">${sensors.location}</option>
				</c:if>
			</c:forEach>
		</select>
	</form>
</div>
<c:if test="${show}">
<div id="his2" >
		<div class="his3">
		<b>Температура</b>
			<img src="${pageContext.servletContext.contextPath}/getimage?sdate=${startDate}&edate=${endDate}&type=Temperature&id=${id}">
			<c:if test="${idSubLoc != -1}"><br>
				<img src="${pageContext.servletContext.contextPath}/getimage?sdate=${startDate}&edate=${endDate}&type=Temperature&id=${idSubLoc}">
			</c:if>
		</div>
		<div  class="his3">
		<b>Давление</b>
			<img src="${pageContext.servletContext.contextPath}/getimage?sdate=${startDate}&edate=${endDate}&type=Pressure&id=${id}">
			<c:if test="${idSubLoc != -1}"><br>
				<img src="${pageContext.servletContext.contextPath}/getimage?sdate=${startDate}&edate=${endDate}&type=Pressure&id=${idSubLoc}">
			</c:if>
		</div>
		<div  class="his3">
		<b>Влажность</b>
			<img src="${pageContext.servletContext.contextPath}/getimage?sdate=${startDate}&edate=${endDate}&type=Humidity&id=${id}">
			<c:if test="${idSubLoc != -1}"><br>
				<img src="${pageContext.servletContext.contextPath}/getimage?sdate=${startDate}&edate=${endDate}&type=Humidity&id=${idSubLoc}">
			</c:if>
		</div>
</div>
</c:if>