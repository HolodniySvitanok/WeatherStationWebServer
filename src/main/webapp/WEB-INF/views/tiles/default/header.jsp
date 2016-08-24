<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="header">
	<div id="d1">
		<div class="link1">
			<a href="${pageContext.request.contextPath}/">Начало</a>
		</div>
		<div class="link1">
			<a href="${pageContext.request.contextPath}/history">История измерений</a>
		</div>
		<div class="link1">
			<a href="${pageContext.request.contextPath}/download">Загрузить</a>
		</div>
		<div class="link1">
			<a href="${pageContext.request.contextPath}/authors">Авторы</a>
		</div>
		<div class="link1">
			<a href="${pageContext.request.contextPath}/hood/">Капот</a>
		</div>
	</div>
	<div id="d2">
		<form action="${pageContext.request.contextPath}/" method="POST">
			Метеодатчик в Днепре <select size="1" name="sel">

				<c:forEach var="sensors" items="${applicationScope.listSensors}">
					<option value="${sensors.id}" <c:if test="${sensors.id == sessionScope.location}">selected</c:if>
					>${sensors.location}</option>
				</c:forEach>

			</select><input type="submit" value="Обновить">
		</form>
	</div>
</div>