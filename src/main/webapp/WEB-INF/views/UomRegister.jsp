<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>

</head>
<body>
	<%@include file="Menu.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-heading bg-primary text-white">
				<h2>Welcome To Registration Page</h2>
			</div>
			<!-- CARD HEADING END -->
			<div class="card-body">
				<form:form action="register" method="POST" modelAttribute="uom">
					<div class="form-group">
						<form:label path="uomType"
							cssClass="control-label col-sm-offset-4 font-weight-bold">UomType</form:label>
						<form:select path="uomType" cssClass="form-control col-sm-4">
							<form:option value="">--SELECT--</form:option>
							<form:options items="${listType}" />
						</form:select>
						<form:errors path="uomType" cssClass="text-danger"></form:errors>
					</div>

					<div class="form-group">
						<form:label path="uomModel"
							cssClass="control-label col-sm-offset-4 font-weight-bold">UomModel</form:label>
						<form:input path="uomModel" cssClass="form-control col-sm-4" />
						<form:errors path="uomModel" cssClass="text-danger"></form:errors>
					</div>

					<div class="form-group">
						<form:label path="description"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Description</form:label>
						<form:textarea path="description" cssClass="form-control col-sm-4" />
						<form:errors path="description" cssClass="text-danger"></form:errors>
					</div>

					<div class="form-group">
						<input type="submit" value="Create Uom"
							class="btn btn-outline-success">
					</div>
				</form:form>
				<!-- FORM END -->
				<div class="form-group">
					<a href="getAll" class="font-weight-bold">Show Data</a>
				</div>
			</div>
			<!-- BODY END -->
			<c:if test="${message != null}">
				<div class="card-footer bg-success">
					<h3 class="text-white">${message}</h3>
				</div>
			</c:if>
		</div>
		<!-- CARD END -->
	</div>
	<!-- CONTAINER END -->
</body>
</html>