<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>HOME-PAGE</title>
<style>
.error{color:red}
</style>
</head>

<% 
String message=" ";
int listsize=(int)request.getAttribute("listsize");
if(listsize==0)
	message="oops! no more users left,try again lateer!";
%>


<body>


welcome to home page!!!!!!!!!!
<br>
${retriveduser.username}
<br>
${retriveduser.firstname}
<br>
${retriveduser.lastname}  


<br><br>

<hr><hr>


People who like you 
<table>
<tr>
<td>firstname</td>
<td>lastname</td>
</tr>

<c:forEach var="admirer" items="${admirer}">
<tr>
<td>${admirer.firstname}</td>
<td>${admirer.lastname}</td>
</tr>
</c:forEach>

</table>

<hr><hr>

People who matched with you


<table>
<tr>
<td>firstname</td>
<td>lastname</td>
</tr>

<c:forEach var="matched" items="${matchedlist}">
<tr>
<td>${matched.firstname}</td>
<td>${matched.lastname}</td>
</tr>
</c:forEach>

</table>






<br><br>
<hr><hr>


<table>
<tr>
<td>Firstname</td>
<td>Lastname</td>
<td>sex</td>
<td>Hobbies</td>
<td>Sexualpreference</td>
<td>LIKE</td>
<td>DISLIKE</td>
</tr>




<c:forEach var="PotentialCrush" items="${potentialmatchlist}">
<c:url var="Like" value="/customer/likedit">
<c:param name="likeduserbufferid" value="${PotentialCrush.id}"></c:param>
</c:url>

<c:url var="Dislike" value="/customer/disliked">
<c:param name="dislikeduserbufferid" value="${PotentialCrush.id}"></c:param>
</c:url>

<tr>
<td>${PotentialCrush.firstname}</td>
<td>${PotentialCrush.lastname}</td>
<td>${PotentialCrush.sex}</td>
<td>${PotentialCrush.hobbies}</td>
<td>${PotentialCrush.sexualorientation}</td>
<td><a href="${Like}">LIKE</a></td>
<td><a href="${Dislike}">LIKE</a></td>
</tr>

</c:forEach>

</table>

<%= message %>
<br><br>

<div>

<a href="${pageContext.request.contextPath}/customer/logout">login-page</a>
</div>


</body>

</html>