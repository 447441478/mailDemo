<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>激活页面</title>
		<script type="text/javascript">
			var i = 3;
			var tm;
			onload= function(){
				tm = setInterval(function(){
					if( i<= 0 ){
						clearInterval(tm);
						location.href="<c:url value='/'/>";
					}
					span1.innerHTML=i+"秒后自动前往登录页面。";
					i--;
				},1000);
			}
		</script>
	</head>
	<body>
		<c:if test="${boo}">
			激活成功，请前往登录页面进行登录。<br/>
			<span id="span1"></span>
		</c:if>
		<c:if test="${!boo}">
			该用户已经激活，请不要重复激活。
		</c:if>
		
	</body>
</html>