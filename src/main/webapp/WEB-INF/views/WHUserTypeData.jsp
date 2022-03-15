<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Data page</title>
<script type="text/javascript" src="../bootstrap/js/SearchPageParam.js"></script>
</head>
<body>
<%@include file="Menu.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-heading bg-primary text-white">
				<h1>WHUserType Data Table</h1>
			</div>
			<div class="card-body">
				<form:form action="viewAll" method="GET"
					modelAttribute="whUserType">
					<div class="form-group">
						<form:label path="userType"
							cssClass="control-label col-sm-offset-4 font-weight-bold">UserCode</form:label>
						<form:radiobuttons path="userType" items="${userTypes}" cssStyle="margin:10px;"/>
					</div>

					<div class="form-group">
						<form:label path="whUserIdType"
							cssClass="control-label col-sm-offset-4 font-weight-bold">UserIdType</form:label>
						<form:select path="whUserIdType" cssClass="form-control col-sm-4">
							<form:option value="">--SELECT--</form:option>
							<form:options items="${whUserIdTypes}" />
						</form:select>
					</div>

					<div class="form-group">
						<input type="submit" value="Search"
							class="btn btn-outline-primary">
					</div>
				</form:form>

				
				<table class="table table-hover">
					<thead class="thead-dark">
						<tr>
							<th>#</th>
							<th>User Type</th>
							<th>UserId Type</th>
							<th>UPDATE</th>
							<th>DELETE</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.getContent()}" var="user">
							<tr>
								<td><a href="view?whUserId=${user.whUserId}"><c:out
											value="${user.whUserId}" /></a></td>
								<td><c:out value="${user.userType}" /></td>
								<td><c:out value="${user.whUserIdType}" /></td>
								<td><a href="edit?whUserId=${user.whUserId}"
									class="btn btn-outline-warning">Update</a></td>
								<td><a href="delete?whUserId=${user.whUserId}"
									class="btn btn-outline-danger">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="form-group">
						<a href="download?page=${page.getNumber()}" class="btn btn-outline-primary">Download</a>
						
					</div>
			</div>
			<!-- CARD BODY END -->

			<div class="card-footer">
				<div style="padding-left: 27%; padding-right: 25%">
					<ul class="pagination">
						<c:choose>
							<c:when test="${page.getContent() ne []}">

								<!--If Not on First Page Show First Page Link-->
								<c:if test="${!page.isFirst()}">
									<li class="page-item"><a class="page-link" href="#"
										onclick="setParam('page','0')">First</a></li>
								</c:if>

								<!--To Show Previous Page-->
								<c:if test="${page.hasPrevious()}">
									<li class="page-item"><a class="page-link" href="#"
										onclick="setParam('page',${page.getNumber()-1})">Previous</a></li>
								</c:if>

								<!--Show Total Pages-->
								<c:forEach begin="0" end="${page.getTotalPages()-1}" var="i">
									<%-- <li class="page-item"><a class="page-link" href="?page=${i}">${i+1}</a></li> --%>
									<!--To Show Active Page-->
									<c:choose>
										<c:when test="${page.getNumber() eq i}">
											<li class="page-item active"><a class="page-link"
												href="#" onclick="setParam('page',${i})">${i+1}</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link" href="#"
												onclick="setParam('page',${i})">${i+1}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<!--To Show Next Page-->
								<c:if test="${page.hasNext()}">
									<li class="page-item"><a class="page-link" href="#"
										onclick="setParam('page',${page.getNumber()+1})">Next</a></li>
								</c:if>

								<!--If Not on Last Page Show Last Page Link-->
								<c:if test="${!page.isLast()}">
									<li class="page-item"><a class="page-link" href="#"
										onclick="setParam('page',${page.getTotalPages()-1})">Last</a></li>
								</c:if>
							</c:when>
							<c:otherwise>
								<h3 class="text-danger">No Records Found</h3>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
			<!-- CARD FOOTER END -->
		</div>
		<!-- CARD END -->
	</div>
</body>
</html>