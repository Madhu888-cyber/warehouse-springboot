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
				<h2>Welcome To OrderMethod Updation Page</h2>
			</div>
			<div class="card-body">
				<form:form action="edit" method="POST" modelAttribute="orderMethod">
					<div class="form-group">
						<form:label path="orderId"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Order Id</form:label>
						<form:input path="orderId" cssClass="form-control col-sm-4" readonly="true"/>
					</div>
					
					<div class="form-group">
						<form:label path="orderCode"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Order Code</form:label>
						<form:input path="orderCode" cssClass="form-control col-sm-4" />
						<form:errors path="orderCode"
							cssClass="text-danger font-weight-bold"></form:errors>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-offset-4 font-weight-bold">Order
							Mode</label>
						<form:radiobuttons path="orderMode" items="${orderModes}"/>
					</div>

					<div class="form-group">
						<form:label path="orderMethd"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Order Method</form:label>
						<form:select path="orderMethd" cssClass="form-control col-sm-4">
							<form:option value="">-SELECT-</form:option>
							<form:options items="${orderMethds}"/>
						</form:select>
						<form:errors path="orderMethd" cssClass="text-danger font-weight-bold"/>
					</div>

					<div class="form-group">
						<form:label path="orderAccept"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Order Accept</form:label>
						<form:checkboxes items="${orderAccepts}" path="orderAccept"/>
						<form:errors path="orderAccept" cssClass="text-danger font-weight-bold"/>
					</div>

					<div class="form-group">
						<form:label path="description"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Description</form:label>
						<form:textarea path="description" cssClass="form-control col-sm-4" />
						<form:errors path="description" cssClass="text-danger font-weight-bold"/>
					</div>
					
					<div class="form-group">
						<label for="createdOn" class="control-label col-sm-offset-4">Created On</label>
						<form:input path="createdOn" readonly="true"  cssClass="form-control col-sm-4" />
					</div>
					
					<div class="form-group">
						<label for="modifiedOn" class="control-label col-sm-offset-4">Modified On</label>
						<form:input path="lastModefiedOn" readonly="true" cssClass="form-control col-sm-4" />
					</div>

					<div class="form-group">
						<input type="submit" value="Update OrderMethod"
							class="btn btn-outline-success">
					</div>
				</form:form>
				<!-- FORM END -->
			</div>
			<!-- CARD BODY END -->
		</div>
		<!-- CARD END -->
	</div>
	<!-- CONTAINER END -->
</body>
</html>