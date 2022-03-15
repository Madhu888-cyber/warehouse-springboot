<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Upload/Download</title>
</head>
<body>
	<%@include file="Menu.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-heading bg-primary text-white">
				<h1>File Upload/Download</h1>
			</div>
			<div class="card-body">
				<form action="upload" method="POST" enctype="multipart/form-data">
					<table>

						<tr>
							<td><label class="col-sm-offset-4 font-weight-bold">Choose
									File</label></td>
							<td><input type="file" name="userFile" class="form-control"></td>
						</tr>

						<tr>
							<td></td>
							<td><input type="submit" value="Upload File"
								class="btn btn-outline-success"> <a href="download"
								class="btn btn-outline-info"> Download </a></td>
						</tr>
					</table>
				</form>
			</div>
			<c:if test="${message != null}">
				<div class="card-footer bg-success text-white">
					<h3>
						<c:out value="${message}"></c:out>
					</h3>
				</div>
			</c:if>

			<c:if test="${errorMap != null}">
				<div class="card-footer bg-danger text-white">
					<table class="table table-hover">
						<thead class="thead-dark">
							<tr>
								<th>Row Number</th>
								<th>Error Details</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${errorMap}" var="err">
								<tr>
									<td><c:out value="${err.key}" /></td>
									<td><c:out value="${err.value}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
		</div>

	</div>
</body>
</html>