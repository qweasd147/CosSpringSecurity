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
<script src="/resources/js/jquery-1.12.2.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#btnlogin').on('click',function(){
			
			var inputID = $('#userid').val();
			var inputPW = $('#userpw').val();
			
			
			$.ajax({
				url : "${ctx}/write",
				data : {userid : inputID, userpw : inputPW},
				type : "POST",
				beforeSend: function (xhr) {
	            	xhr.setRequestHeader("X-Ajax-call", "true");
	          	},
	          	success:function(data, textStatus, jqXHR){
	          		
	          		var jsonObj = JSON.parse(data);
	          		
	          		if(jsonObj.result == "success"){
	          			if(jsonObj.rtnURL)
	          				location.href=jsonObj.rtnURL;
	          			else
	          				location.href="${pageContext.request.contextPath}";
	          		}else if(jsonObj.result == "fail"){
	          			//alert(data.failMsg);
	          			alert("아이디 또는 비밀번호 확인");
	          		}
	        	},
	        	error:function(jqXHR, textStatus, errorThrown){
	        		alert("로그인을 수행할 수 없습니다");
	        	}
			});
		});
	});
	
</script>
</head>
<body>
<form action="/loginCheck" method="post" name="loginform">
<table>
<tr>
	<td>아이디 : </td><td><input type="text" name="userid" id="userid"/></td>
</tr>
<tr>
	<td>비밀번호 : </td><td><input type="text" name="userpw" id="userpw"/>
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
	<td colspan="2"><button type="button" id="btnlogin">로그인</button></td>
</tr>
</table>
</form>
</body>
</html>