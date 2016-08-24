<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<a href="${pageContext.servletContext.contextPath}/hood/setUser?id=add" >Добавить пользователя</a>
<table>
	<tr>
		<td>id</td>
		<td>Логин</td>
		<td>Пароль</td>
		<td>Действия</td>
	</tr>
	<c:forEach var="user" items="${users}">
		<tr>
			<td>${user.id}</td>
			<td>${user.login}</td>
			<td>${user.password}</td>
			<td>
				<a href="${pageContext.servletContext.contextPath}/hood/setUser?id=${user.id}">ред. </a>
				<a href="${pageContext.servletContext.contextPath}/hood/deleteUser?id=${user.id}"> удл.</a>
			</td>
		</tr>
	</c:forEach>
</table>