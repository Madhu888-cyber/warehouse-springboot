<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload File</title>
</head>
<body>
<%@include file="Menu.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-heading bg-primary text-white">
				<h2>Welcome To File Upload Page</h2>
			</div>
			<!-- CARD HEADING END -->
			<div class="card-body">
				<form action="save" method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label for="shipmentFile" class="control-label col-sm-offset-4">File
							input</label> <input type="file" class="form-control col-sm-4"
							id="shipmentFile" name="shipmentFile">
					</div>
					<div class="form-group">
						<input type="submit" value="Import Excel File"
							class="btn btn-outline-success">
						<a href="download" class="btn btn-outline-primary"> Download </a>
					</div>
				</form>
			</div>
			<!-- CARD BODY END -->
			<div class="card-footer">
				<c:if test="${!empty message}">
					<h3>${message}</h3>
				</c:if>
				
				<c:if test="${!empty errorMap}">
					<table class="table table-hover">
						<thead class="thead-dark">
							<tr>
								<th>Error Row</th>
								<th>Value</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${errorMap}" var="obj">
								<tr>
									<td><c:out value="${obj.key}"></c:out></td>
									<td><c:out value="${obj.value}"></c:out></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div><!-- FOOTER END -->
		</div>
		<!-- CARD END -->
	</div>
	<!-- CONTAINER END -->
</body>
</html>