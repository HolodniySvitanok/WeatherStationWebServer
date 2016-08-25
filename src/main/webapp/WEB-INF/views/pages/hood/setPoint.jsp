<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.holodniysvitanok.weatherstationwebserver.services.GlobalConfig"%>


<form action="${pageContext.servletContext.contextPath}/<%= GlobalConfig.HOOD_LINK %>/setPoint" method="POST">
	<table>
		<c:if test="${not empty point.id}">
			<tr>
				<td>id</td>
				<td><input type="text" name="id" value="${point.id}" readonly></td>
			</tr>
		</c:if>
		<tr>
			<td>Дата</td>
			<td><input type="text" name="data" value="${date}"></td>
		</tr>
		<tr>
			<td>Значение</td>
			<td><input type="text" name="value" value="${point.value}"></td>
		</tr>
		<tr>
			<td>id датчика</td>
			<td><input type="text" name="idSensor" value="${point.measuringSensor.id}"></td>
		</tr>
		<tr>
			<td>Тип</td>
			<td>
				<select size="1" name="t">
					<c:forEach var="type" items="${types}">
						<option value="${type}"	  >${type}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Сохранить"></td>
		</tr>
	</table>
</form>
<%-- <c:if test=${type}>selected</c:if>  --%>