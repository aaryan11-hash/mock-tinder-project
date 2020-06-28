<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>SIGN-UP-FORM</title>
<style>
.error{color:red}

</style>

</head>

<form:form action="${pageContext.request.contextPath}/customer/aftersign-up" method="GET" modelAttribute="validuser">

Welcome!!!
<br>
your username: ${username} 


	enter firstname<form:input  path="firstname"/>
	<form:errors path="firstname" cssClass="error"></form:errors>
	<br><br>
	enter lastname<form:input  path="lastname"/>
	<form:errors path="lastname" cssClass="error"></form:errors>
	<br><br>
	Select your Gender <form:select path="sex">
						<form:option value="MALE" name="male"></form:option>
						<form:option value="FEMALE" name="female"></form:option>
						<form:option value="TRANSGENDER" name="transgender"></form:option>
						<form:option value="OTHER" name="other"></form:option>
						</form:select>
	<br><br>
	
	<br><br>
	Select your sexual preference:
	<form:select path="sexualpreference">
						<form:option value="STRAIGHT" name="straight"></form:option>
						<form:option value="GAY" name="gay"></form:option>
						<form:option value="PAN-SEXUAL" name="pansexual"></form:option>
						<form:option value="BI-SEXUAL" name="bisexual"></form:option>
						</form:select>
	<br><br>
	
	What are your hobbies? <form:textarea path="hobbies" rows="6" cols="6"/>
	
	<br><br>
	What's your age?<form:input  path="age"/>
	<form:errors path="age" cssClass="error"></form:errors>
	
	<input type="submit" value="sign-up">
</form:form>




















</html>