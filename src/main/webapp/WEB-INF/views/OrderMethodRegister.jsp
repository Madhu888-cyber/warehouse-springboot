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
			<div class="card-body">
				<form:form action="register" method="POST"
					modelAttribute="orderMethod">
					<div class="form-group">
						<label class="control-label col-sm-offset-4 font-weight-bold">Order
							Mode</label>

						<form:radiobuttons path="orderMode" items="${orderModes}" />
						<div>
							<form:errors path="orderMode"
								cssClass="text-danger font-weight-bold"></form:errors>
						</div>
					</div>

					<div class="form-group">
						<form:label path="orderCode"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Order Code</form:label>
						<form:input path="orderCode" cssClass="form-control col-sm-4" />
						<form:errors path="orderCode"
							cssClass="text-danger font-weight-bold"></form:errors>
					</div>

					<div class="form-group">
						<form:label path="orderMethd"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Order Method</form:label>
						<form:select path="orderMethd" cssClass="form-control col-sm-4">
							<form:option value="">-SELECT-</form:option>
							<form:options items="${orderMethds}" />
						</form:select>
						<form:errors path="orderMethd"
							cssClass="text-danger font-weight-bold"></form:errors>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-offset-4 font-weight-bold">Order
							Accept</label>
						<form:checkboxes items="${orderAccepts}" path="orderAccept" />

						<form:errors path="orderAccept"
							cssClass="text-danger font-weight-bold"></form:errors>
					</div>

					<div class="form-group">
						<form:label path="description"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Description</form:label>
						<form:textarea path="description" cssClass="form-control col-sm-4" />
						<form:errors path="description"
							cssClass="text-danger font-weight-bold"></form:errors>
					</div>

					<div class="form-group">
						<input type="submit" value="Create orderMethd"
							class="btn btn-outline-success">
					</div>
				</form:form>
				<!-- FORM END -->
				<div class="gorm-group">
					<a href="viewAll" class="font-weight-bold">Show Data</a>
				</div>
			</div>
			<!-- CARD BODY END -->
			<c:if test="${message != null}">
				<div class="card-footer bg-success text-white">
					<h3>${message}</h3>
				</div>
				<!-- FOOTER END -->
			</c:if>
		</div>
		<!-- CARD END -->
	</div>
	<!-- CONTAINER END -->
</body>
</html>

<%-- <form:radiobutton path="orderMode" value="SALE" id="sale"
							cssClass="radio-inline" />
						<label class="control-label" for="sale">SALE</label>
						<form:radiobutton path="orderMode" value="PURCHASE" id="purchase"
							cssClass="radio-inline" />
						<label class="control-label" for="purchase">PURCHASE</label> --%>


<%-- <div class="form-group">
						<form:label path="orderCode"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Order Code</form:label>
						<form:input path="orderCode" cssClass="form-control col-sm-4" />
					</div> --%>


<%-- <form:option value="FIFO">FIFO</form:option>
							<form:option value="LIFO">LIFO</form:option>
							<form:option value="FCFO">FCFO</form:option>
							<form:option value="FEFO">FEFO</form:option> --%>


<%-- <div class="form-check">
							<form:checkbox path="orderAccept" value="MULTI_MODEL"
								cssClass="form-check-input" id="mulmodel" />
							<label class="form-check-label" for="mulmodel">Multi-Model</label>
						</div>
						<div class="form-check">
							<form:checkbox path="orderAccept" value="ACCEPT_RETURN"
								cssClass="form-check-input" id="return" />
							<label class="form-check-label" for="return">Accept
								Return</label>
						</div> --%>