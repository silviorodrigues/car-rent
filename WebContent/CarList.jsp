<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="new">Adicionar novo veículo</a>
	<ul>
		<c:forEach var="car" items="${listCar}">
			<li>
				<c:out value="${car.id}" /> - 
				<c:out value="${car.model}" /> -
				<c:out value="${car.vehicle_assembler}" /> - 
				<c:out value="${car.year}" /> - 
				<c:out value="${car.price}" />
			</li>
			<a href="edit?id=<c:out value='${car.id}' />">Alterar</a>
            <a href="delete?id=<c:out value='${car.id}' />">Remover</a> 
		</c:forEach>
	</ul>
</body>
</html>