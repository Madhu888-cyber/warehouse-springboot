<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Data</title>
<script type="text/javascript" src="../bootstrap/js/SearchPageParam.js"></script>
</head>
<body>
	<%@include file="Menu.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-heading bg-primary text-white">
				<h2>Welcome To Order Data Page</h2>
			</div>
			<div class="card-body">
				<div class="container">
					<form:form action="viewAll" method="get"
						modelAttribute="orderMethod">
						<!-- <div class="row"> -->
						<div class="form-group">
							<label class="control-label col-sm-offset-4 font-weight-bold">Order
								Mode</label>
							<form:radiobuttons path="orderMode" items="${orderModes}" />
						</div>

						<div class="form-group">
							<form:label path="orderMethd"
								cssClass="control-label col-sm-offset-4 font-weight-bold">Order Method</form:label>
							<form:select path="orderMethd" cssClass="form-control col-sm-4">
								<form:option value="">-SELECT-</form:option>
								<form:options items="${orderMethds}" />
							</form:select>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-offset-4 font-weight-bold">Order
								Accept</label>
							<form:checkboxes items="${orderAccepts}" path="orderAccept" />
						</div>

						<div class="form-group">
							<form:label path="description"
								cssClass="control-label col-sm-offset-4 font-weight-bold">Description</form:label>
							<form:textarea path="description"
								cssClass="form-control col-sm-4" />
						</div>

						<div class="form-group">
							<input type="submit" value="Search"
								class="btn btn-outline-success">
						</div>
						<!-- </div> -->
					</form:form>
				</div>
				<table class="table table-hover">
					<thead class="thead-dark">
						<tr>
							<th>Order Id</th>
							<th>Created On</th>
							<th>Order Mode</th>
							<th>Order Code</th>
							<th>Order Method</th>
							<th>Order Accept</th>
							<th>Description</th>
							<th>Modified On</th>
							<th>UPDATE</th>
							<th>DELETE</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="obj" items="${page.getContent()}">
							<tr>
								<td><c:out value="${obj.orderId}" /></td>
								<td><c:out value="${obj.createdOn}" /></td>
								<td><c:out value="${obj.orderMode}" /></td>
								<td><c:out value="${obj.orderCode}" /></td>
								<td><c:out value="${obj.orderMethd}" /></td>
								<td><c:forEach var="val" items="${obj.orderAccept}">
										<c:out value="${val}"></c:out>
									</c:forEach></td>
								<td><c:out value="${obj.description}" /></td>
								<td><c:out value="${obj.lastModefiedOn}" /></td>
								<td><a href="update?orderId=${obj.orderId}"
									class="btn btn-outline-warning">Update</a></td>
								<td><a href="delete?orderId=${obj.orderId}"
									class="btn btn-outline-danger">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- TABLE END -->
				<div class="form-group">
					<a href="download?page=${page.getNumber()}"
						class="btn btn-outline-success"> Export Data </a>
				</div>
			</div>
			<!-- CARD BODY END -->
			<div class="card-footer">
				<ul class="pagination">
					<c:choose>
						<c:when test="${!empty page.getContent()}">


							<!-- SHOW FIRST PAGE IF NOT ON FIRST PAGE -->
							<c:if test="${!page.isFirst()}">
								<li class="page-item"><a class="page-link" href="#"
									onclick="setParam('page','0')">First</a></li>
							</c:if>

							<!-- SHOW IF PREVIOUS -->
							<c:if test="${page.hasPrevious()}">
								<li class="page-item"><a class="page-link" href="#"
									onclick="setParam('page',${uoms.getNumber()-1})">Previous</a></li>
							</c:if>



							<!-- TO SHOW PAGINATION -->
							<c:forEach begin="0" end="${page.getTotalPages()-1}" var="i">

								<c:choose>
									<c:when test="${page.getNumber() eq i }">
										<li class="page-item active"><a class="page-link"
											href="#" onclick="setParam('page',${i})">${i+1}</a></li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a class="page-link" href="#"
											onclick="setParam('page',${i})">${i+1}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<!-- SHOW IF NEXT -->
							<c:if test="${page.hasNext()}">
								<li class="page-item"><a class="page-link" href="#"
									onclick="setParam('page',${page.getNumber()+1})">Next</a></li>
							</c:if>

							<!-- SHOW LAST PAGE IF NOT ON LAST PAGE -->
							<c:if test="${!page.isLast()}">
								<li class="page-item"><a class="page-link" href="#"
									onclick="setParam('page',${page.getTotalPages()-1})">Last</a></li>
							</c:if>
						</c:when>
						<c:otherwise>
							<h2>No Records Found</h2>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
		<!-- CARD END -->
	</div>
	<!-- CONTAINER END -->
</body>
</html>