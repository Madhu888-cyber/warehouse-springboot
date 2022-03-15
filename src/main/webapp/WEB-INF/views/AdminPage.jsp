<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark ">
<ul>
	<li class="nav-item"><a class="nav-link" href="/logout">logout</a></li>
</ul>
</nav>
	<%-- <%@include file="Menu.jsp"%> --%>
	<div class="container">
		<div class="card">
			<div class="card-heading bg-primary text-white">
				<h1>Welcome to Admin Data Page!!</h1>
			</div>
			<div class="card-body">

				<table class="table table-hover">
					<thead class="thead-dark">
						<tr>
							<th>Uid</th>
							<th>Name</th>
							<th>Email</th>
							<!-- <th colspan="2">Active</th> -->
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="obj">
							<tr>
								<td>${obj.userId}</td>
								<td>${obj.userName}</td>
								<td>${obj.userEmail}</td>
								<%-- <td>${obj.active}</td> --%>

								<c:if test="${size gt 1}">

									<c:if test="${obj.active == 0}">
										<td><a href="active?userId=${obj.userId}"
											class="btn btn-outline-success"> Active </a></td>
									</c:if>

									<c:if test="${obj.active == 1}">
										<td><a href="inactive?userId=${obj.userId}"
											class="btn btn-outline-danger">In-Active</a></td>
									</c:if>

								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div>
					<a href = "/logout">Log Out</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>