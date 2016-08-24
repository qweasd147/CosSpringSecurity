<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/loginCheck" method="post">
<table>
<tr>
	<td>아이디 : </td><td><input type="text" name="userid"/></td>
</tr>
<tr>
	<td>비밀번호 : </td><td><input type="text" name="userpw"/>
	원래는 히든으로 넣어야하는 redirect 주소<input type="text" name="loginRedirect" value="${loginRedirect}" readonly="readonly" />
	</td>
</tr>
<c:if test="${not empty securityexceptionmsg}">
        <tr>
            <td colspan="2">
                <p>Your login attempt was not successful, try again.</p>
                <p>${securityexceptionmsg}</p>
            </td>
        </tr>
        </c:if>
<tr>
	<td colspan="2"><button type="submit" >로그인</button></td>
</tr>
</table>
</form>
</body>
</html>