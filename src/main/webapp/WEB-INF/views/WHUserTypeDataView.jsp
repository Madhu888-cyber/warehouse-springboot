<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Page</title>
</head>
<body>
	<%@include file="Menu.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-heading bg-primary text-white">
				<h2>Welcome To User View Page</h2>
			</div>
			<div class="card-body">
				<table class="table table-hover" rules="all" border="1">
					<tr>
						<td class="font-weight-bold">User Id</td>
						<td><c:out value="${whUserType.whUserId}" /></td>
					</tr>

					<tr>
						<td class="font-weight-bold">User Type</td>
						<td><c:out value="${whUserType.userType}" /></td>
					</tr>

					<tr>
						<td class="font-weight-bold">User Code</td>
						<td><c:out value="${whUserType.whUserCode}" /></td>
					</tr>

					<tr>
						<td class="font-weight-bold">User For</td>
						<td><c:out value="${whUserType.whUserFor}" /></td>
					</tr>

					<tr>
						<td class="font-weight-bold">User Email</td>
						<td><c:out value="${whUserType.whUserEmail}" /></td>
					</tr>

					<tr>
						<td class="font-weight-bold">User Contact</td>
						<td><c:out value="${whUserType.whUserContact}" /></td>
					</tr>

					<tr>
						<td class="font-weight-bold">User Id Type</td>
						<td><c:out value="${whUserType.whUserIdType}" /></td>
					</tr>

					<tr>
						<td class="font-weight-bold">If Other</td>
						<td><c:out value="${whUserType.whIfOther}" /></td>
					</tr>

					<tr>
						<td class="font-weight-bold">Id Number</td>
						<td><c:out value="${whUserType.whUserIdNumber}" /></td>
					</tr>

					<tr>
						<td class="font-weight-bold">Created On</td>
						<td><c:out value="${whUserType.createdOn}" /></td>
					</tr>

					<tr>
						<td class="font-weight-bold">Modified On</td>
						<td><c:out value="${whUserType.modeifiedOn}" /></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>