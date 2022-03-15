<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Item Edit Page</title>
</head>
<body>
	<%@include file="Menu.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-heading bg-primary text-white"><h1>Welcome To Item Edit</h1></div>
			<div class="card-body">
				<form:form action="update" method="POST" modelAttribute="item">
					
					<div class="form-group">
						<form:label path="itemId"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Item Id</form:label>
						<form:input path="itemId" cssClass="form-control col-sm-4" readonly="true"/>
					</div>
					
					<div class="form-group">
						<form:label path="itemCode"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Item Code</form:label>
						<form:input path="itemCode" cssClass="form-control col-sm-4" />
						<form:errors path="itemCode" cssClass="text-danger"/>
					</div>

					<label class="control-label col-sm-offset-4 font-weight-bold">Item
						Dimensions:</label>
					<div class="form-group">
						<form:label path="itemWidth" cssClass="control-label">Width</form:label>
						<form:input path="itemWidth" cssClass="form-control col-sm-2" />

						<form:label path="itemLength" cssClass="control-label">Length</form:label>
						<form:input path="itemLength" cssClass="form-control col-sm-2" />

						<form:label path="itemHeight" cssClass="control-label">Height</form:label>
						<form:input path="itemHeight" cssClass="form-control col-sm-2" />
					</div>

					<div class="form-group">
						<form:label path="itemBaseCost"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Base Cost</form:label>
						<form:input path="itemBaseCost" cssClass="form-control col-sm-4" />
						<form:errors path="itemBaseCost" cssClass="text-danger"/>
					</div>

					<div class="form-group">
						<form:label path="itemBaseCurrency"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Base Currency</form:label>
						<form:select path="itemBaseCurrency"
							cssClass="form-control col-sm-4">
							<form:option value="">--SELECT--</form:option>
							<form:options items="${baseCurrencies}" />
						</form:select>
						<form:errors path="itemBaseCurrency" cssClass="text-danger"/>
					</div>

					<div class="form-group">
						<form:label path="itemBaseCurrency"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Item Uom</form:label>
						<form:select path="uom" cssClass="form-control col-sm-4">
							<form:option value="">--SELECT--</form:option>
							<form:options items="${uoms}" itemLabel="uomModel"
								itemValue="uomId" />
						</form:select>
						<form:errors path="uom" cssClass="text-danger"/>
					</div>

					<label class="control-label col-sm-offset-4 font-weight-bold">Order
						Method Modes</label>
					<div class="form-group">
						<form:label path="orderSale"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Order Mode SALE</form:label>
						<form:select path="orderSale" cssClass="form-control col-sm-4">
							<form:option value="">--SELECT--</form:option>
							<form:options items="${orderSales}" itemLabel="orderCode"
								itemValue="orderId" />
						</form:select>
						<form:errors path="orderSale" cssClass="text-danger"/>

					</div>

					<div class="form-group">
						<form:label path="orderPurchase"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Order Mode Purchase</form:label>
						<form:select path="orderPurchase" cssClass="form-control col-sm-4">
							<form:option value="">--SELECT--</form:option>
							<form:options items="${orderPurchases}" itemLabel="orderCode"
								itemValue="orderId" />
						</form:select>
						<form:errors path="orderPurchase" cssClass="text-danger"/>

					</div>

					<div class="form-group">
						<form:label path="vendors"
							cssClass="control-label col-sm-offset-4 font-weight-bold">WhUser Vendor</form:label>
						<form:select path="vendors" cssClass="form-control col-sm-4"
							multiple="true">
							<form:options items="${whUserVendors}" itemLabel="whUserCode"
								itemValue="whUserId" />
						</form:select>
						<form:errors path="vendors" cssClass="text-danger"/>

					</div>

					<div class="form-group">
						<form:label path="customers"
							cssClass="control-label col-sm-offset-4 font-weight-bold">WhUser Customer</form:label>
						<form:select path="customers" cssClass="form-control col-sm-4"
							multiple="true">
							<form:options items="${whUserCustomers}" itemLabel="whUserCode"
								itemValue="whUserId" />
						</form:select>
						<form:errors path="customers" cssClass="text-danger"/>
					</div>
					
					<div class="form-group">
						<form:label path="description"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Description</form:label>
						<form:textarea path="description" cssClass="form-control col-sm-4" />
						<form:errors path="description" cssClass="text-danger"/>
					</div>
					
					<div class="form-group">
						<form:label path="createdOn"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Created On</form:label>
						<form:input path="createdOn" cssClass="form-control col-sm-4" />
					</div>
					
					<div class="form-group">
						<form:label path="modifiedOn"
							cssClass="control-label col-sm-offset-4 font-weight-bold">Modified On</form:label>
						<form:input path="modifiedOn" cssClass="form-control col-sm-4" />
					</div>

					<div class="form-group">
						<input type="submit" value="Update"
							class="btn btn-outline-success">
					</div>
				</form:form>
			</div><!-- CARD BODY END -->
			<div class="card-footer">
				
			</div><!-- FOOTER END -->
		</div><!-- CARD END -->
	</div><!-- CONTAINER END -->
</body>
</html>