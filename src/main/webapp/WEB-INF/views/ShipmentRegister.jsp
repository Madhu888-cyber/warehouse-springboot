<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ShipmentType Registration Page</title>
</head>
<body>
<%@include file="Menu.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-heading bg-primary text-white">
				<h2>Welcome To ShipmentType Registration</h2>
			</div>
			<!-- CARD HEADING END -->
			<div class="card-body">
				<form:form action="register" method="POST"
					modelAttribute="shipmentType">
					<div class="form-group">
						<form:label path="shipmentMode"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Shipment Mode</form:label>
						<form:select path="shipmentMode" cssClass="form-control col-sm-4">
							<form:option value="">-SELECT-</form:option>
							<form:options items="${listShipMode}" />
						</form:select>
						<form:errors path="shipmentMode"
							cssClass="text-danger font-weight-bold" />
					</div>
					
					<div class="form-group">
						<form:label path="shipmentCode"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Shipment Code</form:label>
						<form:input path="shipmentCode" cssClass="form-control col-sm-4" />
						<form:errors path="shipmentCode"
							cssClass="text-danger font-weight-bold" />
					</div>

					<div class="form-group ">
						<label class="control-label col-sm-offset-4 font-weight-bold">Enable
							Shipment</label>
						<form:checkbox path="enableShipment"
							cssClass="form-check form-check-inline" value="YES" id="yes" cssStyle="margin:10px;"/>
						<label for="yes" class="radio-inline">YES</label>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-offset-4 font-weight-bold">ShipmentGrade</label> 
						<form:radiobuttons path="shipmentGrade" items="${listGrade}" cssStyle="margin:10px;"/>	
						<form:errors path="shipmentGrade"
							cssClass="text-danger font-weight-bold" />
					</div>

					<div class="form-group">
						<form:label path="description"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Description</form:label>
						<form:textarea path="description" cssClass="form-control col-sm-4" />
						<form:errors path="description"
							cssClass="text-danger font-weight-bold" />
					</div>

					<div class="form-group">
						<input type="submit" value="Create ShipmentType"
							class="btn btn-outline-success">
					</div>

				</form:form>
				<div class="form-group">
					<a href="viewAll" class="font-weight-bold">Show Data</a>
				</div>
			</div>
			<!-- CARD BODY END -->
			<c:if test="${shipmentId != null}">
				<div class="card-footer bg-success text-white">
					<h2>${shipmentId}</h2>
				</div>
				<!-- FOOTER END -->
			</c:if>
		</div>
		<!-- CARD END -->
	</div>
	<!-- CONTAINER END -->
</body>
</html>

						<%-- <span> <form:radiobutton path="shipmentGrade"
								value="A" id="a" /> <label for="a" class="radio-inline">A</label>
						</span> <span> <form:radiobutton path="shipmentGrade" value="B"
								id="b" /> <label for="b" class="radio-inline">B</label>
						</span> <span> <form:radiobutton path="shipmentGrade" value="C"
								id="c" /> <label for="c" class="radio-inline">C</label>
						</span> --%>
						
						<%-- <div class="form-group">
						<form:label path="shipmentCode"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Shipment Code</form:label>
						<form:input path="shipmentCode" cssClass="form-control col-sm-4" />
						<form:errors path="shipmentCode" cssClass="text-danger font-weight-bold"/>
						</div>--%>
						
						<%-- <form:option value="AIR">AIR</form:option>
							<form:option value="TRUCK">TRUCK</form:option>
							<form:option value="SHIP">SHIP</form:option>
							<form:option value="TRAIN">TRAIN</form:option> --%>