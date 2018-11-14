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
	<c:if test="${car != null}">
    	Alterar veículo
    </c:if>
    <c:if test="${car == null}">
    	Adicionar veículo
    </c:if>

	<c:if test="${car != null}">
		<form action="update" method="post">
   	</c:if>

    <c:if test="${car == null}">
		<form action="insert" method="post">
    </c:if>
    
	    <c:if test="${car != null}">
	    	<input type="hidden" name="id" value="<c:out value='${car.id}' />" />
	    </c:if>
    
	    <input type="text" name="model" size="128" value="<c:out value='${car.model}' />"/>
	    <input type="text" name="vehicle_assembler" size="45" value="<c:out value='${car.vehicle_assembler}' />"/>
		<input type="text" name="year" size="4" value="<c:out value='${car.year}' />"/>
		<input type="text" name="price" size="5" value="<c:out value='${car.price}' />"/>
		<input type="submit" value="Salvar" />
	</form>
</body>
</html>