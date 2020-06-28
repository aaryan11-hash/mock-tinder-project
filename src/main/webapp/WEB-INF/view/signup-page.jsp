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


<body>
<form:form action="${pageContext.request.contextPath}/customer/usersign-up-clash-check" method="GET" modelAttribute="signup-user">

	enter username <form:input  path="username"/>
	<form:errors path="username" cssClass="error"/>
	<br><br>
	enter password <form:input path="password"/>
	<form:errors path="password" cssClass="error"/>
	<input type="submit" value="sign-up">
</form:form>

</body>
</html>