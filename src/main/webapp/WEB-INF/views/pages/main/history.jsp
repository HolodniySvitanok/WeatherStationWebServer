<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div id="his1">
		<form>
			Показать значение в период с
			<input type="text" class="in1" name="" value="${hourStart}"> 
			<input type="text" class="in1" name="" value="${dayStart}"> 
			<input type="text" class="in1" name="" value="${monthStart}"> 
			<input type="text" class="in1" name="" value="${yearStart}"> до 
			<input type="text" class="in1" name="" value="${hourEnd}"> 
			<input type="text" class="in1" name="" value="${dayEnd}"> 
			<input type="text" class="in1" name="" value="${monthEnd}"> 
			<input type="text" class="in1" name="" value="${yearEnd}"> 
			<input type="submit" value="Показать"> 
			<br><br>
			Дополнительный график для сравнения 
			<select size="1" name="sel">
				<option>-</option>
				<c:forEach var="sensors" items="${listSensors}">
					<c:if test="${ location != sensors.id}">
						<option value="${sensors.id}">${sensors.location}</option>
					</c:if>
				</c:forEach>
			</select>
		</form>
	</div>
