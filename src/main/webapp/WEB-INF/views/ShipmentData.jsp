<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ShipmentType Data Page</title>
<script type="text/javascript" src="../bootstrap/js/SearchPageParam.js"></script>

</head>
<body>
	<%@include file="Menu.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-heading bg-primary text-white">
				<h2>Welcome To Data Page</h2>
			</div>
			<!-- CARD HEADING END -->
			<form:form action="viewAll" method="GET"
				modelAttribute="shipmentType">
				<!-- <div class="row"> -->
				<div class="form-group col-sm-5">
					<form:label path="shipmentMode"
						cssClass="control-label col-sm-offset-4 font-weight-bold">Shipment Mode</form:label>
					<form:select path="shipmentMode" cssClass="form-control 8">
						<form:option value="">--SELECT--</form:option>
						<form:options items="${shipmentModes}" />
					</form:select>
				</div>

				<div class="form-group col-sm-5">
					<label class="control-label col-sm-offset-4 font-weight-bold">ShipmentGrade</label>
					<form:radiobuttons path="shipmentGrade" items="${shipmentGrades}"
						cssStyle="margin:10px;" />
				</div>

				<div class="col-sm-2">
					<input type="submit" value="Search" class="btn btn-outline-success">
				</div>
				<!-- </div> -->
				<!-- ROW END -->
			</form:form>

			<div class="card-body">
				<table class="table table-hover">
					<thead class="thead-dark">
						<tr>
							<th>Shipment Id</th>
							<th>Shipment Mode</th>
							<th>Created On</th>
							<th>Shipment Code</th>
							<th>Enable Shipment</th>
							<th>Shipment Grade</th>
							<th>Description</th>
							<th>Modified On</th>
							<th>UPDATE</th>
							<th>DELETE</th>
						</tr>
					</thead>
					<c:forEach items="${page.getContent()}" var="obj">
						<tr>
							<td><c:out value="${obj.shipmentId}"></c:out></td>
							<td><c:out value="${obj.shipmentMode}"></c:out></td>
							<td><c:out value="${obj.createdOn}"></c:out></td>
							<td><c:out value="${obj.shipmentCode}"></c:out></td>
							<td><c:out value="${obj.enableShipment}"></c:out></td>
							<td><c:out value="${obj.shipmentGrade}"></c:out></td>
							<td><c:out value="${obj.description}"></c:out></td>
							<td><c:out value="${obj.modifiedOn}"></c:out></td>
							<td><a href="update?shipmentId=${obj.shipmentId}"
								class="btn btn-outline-warning">UPDATE</a></td>
							<td><a href="delete?shipmentId=${obj.shipmentId}"
								class="btn btn-outline-danger">DELETE</a></td>
						</tr>
					</c:forEach>
				</table>
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
	<!-- CONTAINER END -->
</body>
</html>