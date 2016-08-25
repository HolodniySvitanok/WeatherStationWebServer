<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.holodniysvitanok.weatherstationwebserver.services.GlobalConfig" %>

<form action="${pageContext.request.contextPath}/authorization" method="POST">
	<table>
		<tr>
			<td colspan="2">${errorMessage}</td>
		</tr>
		<tr>
			<td>Логин :</td>
			<td><input type="text" name="lgn"></td>
		</tr>
		<tr>
			<td>Пароль :</td>
			<td><input type="password" name="pswd"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="войти"></td>
		</tr>
	</table>
</form>