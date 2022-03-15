<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
<%@include file="Menu.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-heading bg-primary text-white"><h2>Welcome To Update ShipmentType</h2></div><!-- CARD HEAD END -->
			<div class="card-body">
				<form:form action="edit" method="POST" modelAttribute="shipmentType">
				
					<div class="form-group">
						<form:label path="shipmentId"
							cssClass="control-label col-sm-offset-4 font-weight-bold">ShipmentId</form:label>
						<form:input path="shipmentId" cssClass="form-control col-sm-4" readonly="true"/>
					</div>
					
					<div class="form-group">
						<form:label path="shipmentMode"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Shipment Mode</form:label>
						<form:select path="shipmentMode" cssClass="form-control col-sm-4">
							<form:option value="">-SELECT-</form:option>
							<form:options items="${listShipMode}"/>
						</form:select>
						<form:errors path="shipmentMode" cssClass="text-danger font-weight-bold"/>
					</div>

					<div class="form-group">
						<form:label path="shipmentCode"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Shipment Code</form:label>
						<form:input path="shipmentCode" cssClass="form-control col-sm-4" />
					</div>

					<div class="form-group ">
						<span><label
							class="control-label col-sm-offset-4 font-weight-bold">Enable
								Shipment</label></span>
						<form:checkbox path="enableShipment"
							cssClass="form-check form-check-inline" value="YES" id="yes" />
						<label for="yes" class="radio-inline">YES</label>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-offset-4 font-weight-bold">ShipmentGrade</label> 
						<form:radiobuttons path="shipmentGrade" items="${listGrade}" cssStyle="margin:10px;"/>
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
						<form:input path="modifiedOn" readonly="true" cssClass="form-control col-sm-4" />
					</div>

					<div class="form-group">
						<input type="submit" value="Update"
							class="btn btn-outline-success">
					</div>
				</form:form>
			</div><!-- CARD BODY END -->
		</div><!-- CARD END -->
	</div><!-- CONTAINER END -->
</body>
</html>