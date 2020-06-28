<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>LOGIN-FORM</title>
</head>

<form:form action="${pageContext.request.contextPath}/customer/afterlogin" method="GET" modelAttribute="loginuser">

	enter username <form:input  path="username"/>
	<br><br>
	enter password <form:input  path="password"/>
	<input type="submit" value="login">
</form:form>


</html>