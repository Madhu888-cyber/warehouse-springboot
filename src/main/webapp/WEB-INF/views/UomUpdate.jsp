<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Uom Update Page</title>
</head>
<body>
	<%@include file="Menu.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-heading bg-primary text-white">
				<h2>Welcome To Uom Update Page</h2>
			</div>
			<div class="card-body">
				<form:form action="edit" method="POST" modelAttribute="uom">
					<div class="form-group">
						<form:label path="uomId"
							cssClass="control-label col-sm-offset-4 font-weight-bold">UomId</form:label>
						<form:input path="uomId" cssClass="form-control col-sm-4"
							readonly="true" />
					</div>

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
						<label for="createdOn" class="control-label col-sm-offset-4">Created On</label>
						<form:input path="createdOn" readonly="true"  cssClass="form-control col-sm-4" />
					</div>
					
					<div class="form-group">
						<label for="modifiedOn" class="control-label col-sm-offset-4">Modified On</label>
						<form:input path="modifiedOn" readonly="true" cssClass="form-control col-sm-4" />
					</div>
					
					<div class="form-group">
						<input type="submit" value="Update Uom"
							class="btn btn-outline-success">
					</div>

				</form:form>
				<!-- FORM END -->
			</div>
			<!-- BODY END -->
		</div>
		<!-- CARD END -->
	</div>
	<!-- CONTAINER END -->
</body>
</html>