<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="${pageContext.servletContext.contextPath}/hood/setSensor" method="POST">
	<table>
		<c:if test="${not empty sensor.id}">
			<tr>
				<td>ID</td>
				<td><input type="text" name="id" value="${sensor.id}" readonly></td>
			</tr>
		</c:if>
		<tr>
			<td>Место положения</td>
			<td><input type="text" name="location" value="${sensor.location}"></td>
		</tr>
		<tr>
			<td>Пароль</td>
			<td><input type="text" name="password" value="${sensor.password}"></td>
		</tr>
		<tr>
			<td>Состояние</td>
			<td><select size="1" name="active">
					<option <c:if test="${sensor.active}" >selected</c:if> value="true">true</option>
					<option <c:if test="${not sensor.active}"  >selected</c:if> value="false">false</option>
			</select></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Сохранить"></td>
		</tr>
	</table>
</form>