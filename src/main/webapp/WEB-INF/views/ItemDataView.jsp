<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Item Data View Page</title>
</head>
<body>
	<%@include file="Menu.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-heading bg-primary text-white"><h1>Item Data View Page</h1></div>
			<div class="card-body">
				<table class="table table-hover" rules="all" border="1">
					<tr>
						<td class="font-weight-bold">Item Id</td>
						<td><c:out value="${item.itemId}" /></td>
					</tr>

					<tr>
						<td class="font-weight-bold">Item Code</td>
						<td><c:out value="${item.itemCode}" /></td>
					</tr>

					<tr>
						<td class="font-weight-bold">Width</td>
						<td><c:out value="${item.itemWidth}" /></td>
					</tr>

					<tr>
						<td class="font-weight-bold">Length</td>
						<td><c:out value="${item.itemLength}" /></td>
					</tr>

					<tr>
						<td class="font-weight-bold">Height</td>
						<td><c:out value="${item.itemHeight}" /></td>
					</tr>

					<tr>
						<td class="font-weight-bold">Base Cost</td>
						<td><c:out value="${item.itemBaseCost}" /></td>
					</tr>

					<tr>
						<td class="font-weight-bold">Base Currency</td>
						<td><c:out value="${item.itemBaseCurrency}" /></td>
					</tr>

					<tr>
						<td class="font-weight-bold">Item Uom</td>
						<td><c:out value="${item.uom.uomModel}" /></td>
					</tr>

					<tr>
						<td class="font-weight-bold">OrderMode Sale</td>
						<td><c:out value="${item.orderSale.orderCode}"/></td>
					</tr>
					
					<tr>
						<td class="font-weight-bold">OrderMode Purchase</td>
						<td><c:out value="${item.orderPurchase.orderCode}" /></td>
					</tr>
					
					<tr>
						<td class="font-weight-bold">WHUser Vendor</td>
						<td>
							<c:forEach var="ven" items="${item.vendors}" varStatus="loop">
								<c:out value="${ven.whUserCode}"/>
								<c:if test="${!loop.last}">
									<c:out value=","/>
								</c:if>
							</c:forEach>
							
						</td>
					</tr>
					
					<tr>
						<td class="font-weight-bold">WHUser Customer</td>
						<td>
							<c:forEach var="cust" items="${item.customers}" varStatus="loop">
									<c:out value="${cust.whUserCode}"/>
									<c:if test="${!loop.last}">
										<c:out value=","/>
									</c:if>
							</c:forEach>
						</td>
					</tr>
					
					<tr>
						<td class="font-weight-bold">Description</td>
						<td><c:out value="${item.description}" /></td>
					</tr>
					

					<tr>
						<td class="font-weight-bold">Created On</td>
						<td><c:out value="${item.createdOn}" /></td>
					</tr>

					<tr>
						<td class="font-weight-bold">Modified On</td>
						<td><c:out value="${item.modifiedOn}" /></td>
					</tr>
				</table>
			</div><!-- BODY END -->
		</div><!-- CARD END -->
	</div><!-- CONTAINER END -->
</body>
</html>