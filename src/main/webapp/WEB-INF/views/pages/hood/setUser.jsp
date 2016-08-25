<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.holodniysvitanok.weatherstationwebserver.services.GlobalConfig" %>

<form action="${pageContext.servletContext.contextPath}/<%= GlobalConfig.HOOD_LINK %>/setUser" method="POST">
	<table>
		<c:if test="${not empty user.id}">
			<tr>
				<td>id</td>
				<td><input type="text" value="${user.id}" readonly name="id"></td>
			</tr>
		</c:if>
		<tr>
			<td>Логин</td>
			<td><input type="text" value="${user.login}" name="login"></td>
		</tr>
		<tr>
			<td>Пароль</td>
			<td><input type="text" value="${user.password}" name="password"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Сохранить"></td>
		</tr>
	</table>
</form>