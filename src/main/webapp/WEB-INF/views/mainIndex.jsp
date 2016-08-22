<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
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
<security:authorize access="isAnonymous()" >
<h1><a href="/loginForm">login</a></h1>
</security:authorize>
<h1>권한 테스트</h1>

<h1><a href="/admin/result">/admin/result</a></h1>
<h1><a href="/user/result">/user/result</a></h1>

<security:authorize access="isAuthenticated()" >
<h1><a href="/logout">로그아웃</a></h1>
</security:authorize>
<security:authorize access="isAuthenticated()" >
<h1><a href="/userInfo">회원 정보</a></h1>
</security:authorize>
</body>
</html>