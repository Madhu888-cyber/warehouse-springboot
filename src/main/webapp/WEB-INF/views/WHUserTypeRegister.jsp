<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WHUserTypeRegister</title>
<script type="text/javascript">
	
	function selectUserType() {
		var user = $('input[name=userType][type=radio]:checked').val();
		if(user == "CUSTOMER"){
			$('input[name=whUserFor]').val("SALE");
		}
		else{
			$('input[name=whUserFor]').val("PURCHASE")
		}
	}
	
	function selectwhUserIdType() {
		var whUserIdType = $('select[name=whUserIdType]').val();
		if(whUserIdType != "OTHER"){
			$('input[name=whIfOther]').attr("readonly",true).val("NO");
			
		}
		else{
			$('input[name=whIfOther]').attr("readonly",false).val("");
		}
	}
</script>

</head>
<body>
<%@include file="Menu.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-heading bg-primary text-white">
				<h2>Welcome To User Registration</h2>
			</div>
			<div class="card-body">
				<form:form action="register" method="POST" modelAttribute="whUserType">
					<div class="form-group">
					
						<form:label path="userType"
							cssClass="control-label col-sm-offset-4 font-weight-bold">UserType</form:label>
						<form:radiobuttons path="userType" items="${userTypes}"
							onchange="selectUserType()" cssStyle="margin:10px"/>
						<form:errors path="userType" cssClass="text-danger"/>
					</div>

					<div class="form-group">
						<form:label path="whUserCode"
							cssClass="control-label col-sm-offset-4 font-weight-bold">UserCode</form:label>
						<form:input path="whUserCode" cssClass="form-control col-sm-4" value="${whUserCodes}" readonly="true"/>
						<form:errors path="whUserCode" cssClass="text-danger"/>
					</div>

					<div class="form-group">
						<form:label path="whUserFor"
							cssClass="control-label col-sm-offset-4 font-weight-bold">UserFor</form:label>
						<form:input path="whUserFor" cssClass="form-control col-sm-4"
							id="whUserFor" readonly="true" />
						<form:errors path="whUserFor" cssClass="text-danger"/>
					</div>

					<div class="form-group">
						<form:label path="whUserEmail"
							cssClass="control-label col-sm-offset-4 font-weight-bold">UserEmail</form:label>
						<form:input path="whUserEmail" cssClass="form-control col-sm-4" />
						<form:errors path="whUserEmail" cssClass="text-danger"/>
					</div>

					<div class="form-group">
						<form:label path="whUserContact"
							cssClass="control-label col-sm-offset-4 font-weight-bold">UserContact</form:label>
						<form:input path="whUserContact" cssClass="form-control col-sm-4" />
						<form:errors path="whUserContact" cssClass="text-danger"/>
					</div>

					<div class="form-group">
						<form:label path="whUserIdType"
							cssClass="control-label col-sm-offset-4 font-weight-bold">UserIdType</form:label>
						<form:select path="whUserIdType" cssClass="form-control col-sm-4"
							onchange="selectwhUserIdType()">
							<form:option value="">--SELECT--</form:option>
							<form:options items="${whUserIdTypes}" />
						</form:select>
						<form:errors path="whUserIdType" cssClass="text-danger"/>
					</div>

					<div class="form-group">
						<form:label path="whIfOther"
							cssClass="control-label col-sm-offset-4 font-weight-bold">IfOther</form:label>
						<form:input path="whIfOther" cssClass="form-control col-sm-4"
							readonly="true" value="NO"/>
						<form:errors path="whIfOther" cssClass="text-danger"/>
					</div>

					<div class="form-group">
						<form:label path="whUserIdNumber"
							cssClass="control-label col-sm-offset-4 font-weight-bold">UserIdNumber</form:label>
						<form:input path="whUserIdNumber" cssClass="form-control col-sm-4" />
						<form:errors path="whUserIdNumber" cssClass="text-danger"/>
					</div>

					<div class="form-group">
						<input type="submit" value="Create User"
							class="btn btn-outline-success">
					</div>
				</form:form>
				<!-- FORM END -->
			</div>
			<!-- CARD BODY END -->
			<c:if test="${message != null}">
				<div class="card-footer bg-success text-white">
					<h3>${message}</h3>
				</div>
				<!-- FOOTER END -->
			</c:if>
		</div>
		<!-- CARD END -->
	</div>
	<!-- CONTAINER END -->
</body>
</html>