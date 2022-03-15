<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Item Data</title>
<script type="text/javascript" src="../bootstrap/js/SearchPageParam.js"></script>
</head>
<body>
	<%@include file="Menu.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-heading bg-primary text-white">
				<h1>Welcome To Item Data Page</h1>
			</div>
			<!-- CARD HEADING END -->
			<div class="card-body">
				<div class="container">
					<form:form action="viewAll" method="get" modelAttribute="item">

						<div class="form-group">
							<form:label path="itemCode"
								cssClass="control-label col-sm-offset-4 font-weight-bold">Item Code</form:label>
							<form:input path="itemCode" cssClass="form-control col-sm-4" />
						</div>
						
						<div class="form-group">
							<form:label path="itemBaseCost"
								cssClass="control-label col-sm-offset-4 font-weight-bold">Base Cost</form:label>
							<form:input path="itemBaseCost" cssClass="form-control col-sm-4" />
						</div>
					
						<div class="form-group">
							<input type="submit" value="Search" class="btn btn-outline-primary">	
						</div>
					</form:form>
				</div><!-- INNER CONTAINER END -->
			
				<table class="table table-hover">
					<thead class="thead-dark">
						<tr>
							<th>#</th>
							<th>Item Code</th>
							<th>UOM</th>
							<th>OrderMethod(SALE)</th>
							<th>OrderMethod(PURCHASE)</th>
							<th>Item Vendors</th>
							<th>Item Customers</th>
							<th>UPDATE</th>
							<th>DELETE</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="obj" items="${page.getContent()}">
							<tr>
								<td><a href="view?itemId=${obj.itemId}"><c:out value="${obj.itemId}"/></a></td>
								<td><c:out value="${obj.itemCode}"></c:out></td>
								<td><c:out value="${obj.uom.uomModel}"></c:out></td>
								<td><c:out value="${obj.orderSale.orderCode}"></c:out></td>
								<td><c:out value="${obj.orderPurchase.orderCode}"></c:out></td>
								
								<td>
									<c:forEach var="ven" items="${obj.vendors}" varStatus="loop">
										<c:out value="${ven.whUserCode}"/>
										<c:if test="${!loop.last}">
											<c:out value=","/>
										</c:if>
									</c:forEach>
									
								</td>
								
								<td>
									<c:forEach var="cust" items="${obj.customers}" varStatus="loop">
										<c:out value="${cust.whUserCode}"/>
										<c:if test="${!loop.last}">
											<c:out value=","/>
										</c:if>
									</c:forEach>
								</td>
								
								<td><a href="edit?itemId=${obj.itemId}" class="btn btn-outline-warning">Update</a></td>
								
								<td><a href="delete?itemId=${obj.itemId}" class="btn btn-outline-danger">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- TABLE END -->
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
									onclick="setParam('page',${page.getNumber()-1})">Previous</a></li>
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
			<!-- FOOTER END -->
		</div>
		<!-- CARD END -->
	</div>
	<!-- CONTAINER END -->
</body>
</html>