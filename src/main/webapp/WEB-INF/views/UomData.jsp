<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Uom Data</title>

<script type="text/javascript" src="../bootstrap/js/SearchPageParam.js"></script>


</head>
<body>
	<%@include file="Menu.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-heading bg-primary text-white">
				<h2>Welcome To Uom Data Page</h2>
			</div>
			<!-- CARD HEADING END -->
			<div class="card-body">
				<div class="container">
					<form:form action="getAll" method="get" modelAttribute="uom">
						<div class="row">
							<div class="col-sm-3">
								<form:select path="uomType" cssClass="form-control">
									<form:option value="">--SELECT--</form:option>
									<form:options items="${listType}" />
								</form:select>
							</div>
							<div class="col-sm-3">
								<form:input path="uomModel" placeholder="UomModel"
									cssClass="form-control " />
							</div>
							<div class="col-sm-3">
								<form:textarea path="description" placeholder="description"
									cssClass="form-control" />
							</div>
							<div class="col-sm-3">
								<input type="submit" value="Search"
									class="btn btn-outline-success">
							</div>
						</div>
					</form:form>
				</div>
				<table class="table table-hover">
					<thead class="thead-dark ">
						<tr>
							<th>Uom Id</th>
							<th>Created On</th>
							<th>Uom Type</th>
							<th>Uom Model</th>
							<th>Description</th>
							<th>Modified On</th>
							<th>UPDATE</th>
							<th>DELETE</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="obj" items="${page.getContent()}">
							<tr>
								<td><c:out value="${obj.uomId}" /></td>
								<td><c:out value="${obj.createdOn}" /></td>
								<td><c:out value="${obj.uomType}" /></td>
								<td><c:out value="${obj.uomModel}" /></td>
								<td><c:out value="${obj.description}" /></td>
								<td><c:out value="${obj.modifiedOn}" /></td>
								<td><a href="update?uomId=${obj.uomId}"
									class="btn btn-outline-warning">Update</a></td>
								<td><a href="delete?uomId=${obj.uomId}"
									class="btn btn-outline-danger">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
				<!-- TABLE END -->
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
		</div>
		<!-- CARD END -->
	</div>
	<!-- CONTAINER END -->
</body>
</html>