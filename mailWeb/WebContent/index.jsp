<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script type="text/javascript">
			
			function reg() {
				form1.action="<c:url value='/RegServlet' />";
				form1.submit();
			}
			function login() {
				form1.action="<c:url value='/LoginServlet' />";
				form1.submit();
			}
		</script>
		<style type="text/css">
			.error{
				color: red;
			}
		</style>
		
	</head>
	<body>
		<!-- 当用户没有登录成功时显示 -->
		<c:if test="${empty sessionScope.user}" var="boo">
			<c:if test="${!empty error}">
				<div class="error">${error}</div>
				<c:remove var="error"/>
			</c:if>
			<form id="form1" action="" method="post">
				用户名:<input type="text" name="name"/><br/>
				密&emsp;码:<input type="password" name="pwd"/><br/>
				邮&emsp;箱:<input type="email" name="email"/>&emsp;登录时不用填邮箱<br/>
				<input type="button" value="注册" onclick="reg();"> &emsp;
				<input type="button" value="登录" onclick="login();">
			</form>
		</c:if>
		<c:if test="${!boo}">
			${sessionScope.user.name}，HNCU欢迎您。
		</c:if>
	</body>
</html>