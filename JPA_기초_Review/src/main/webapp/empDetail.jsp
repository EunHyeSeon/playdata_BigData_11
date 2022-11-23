<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<% 	
	String url = application.getContextPath() + "/";
	System.out.println(url);
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Emp Detail</title>
</head>
<body>
<br><br><br>
<center>

${requestScope.successMsg}

<h3>${employee.empno} 사원 정보</h3>
<hr><p> 
 
<table border="1">
	<tr>
		<th>사번</th><th>사원 이름</th><th>직무</th><th>직원 담당 상사 번호</th><th>입사일</th><th>연봉</th><th>Comm</th><th>소속 부서 번호</th>
	</tr>
 	<tr>
 		<td>${employee.empno}</td>
 		<td>${employee.ename}</td>
 		<td>${employee.job}</td>
 		<td>${employee.mgr}</td>
 		<td>${employee.hiredate}</td>
 		<td>${employee.sal}</td>
 		<td>${employee.comm}</td>
 		<td>${employee.deptno}</td>
 	</tr>
</table>

<br><br><br>
<a href="emp?command=empUpdateReq&empno=${employee.empno}">수정하기</a>

<a href="emp?command=empDelete&empno=${employee.empno}">삭제하기</a>

&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/index.html">메인 화면 이동</a>

</center>
</body>
</html>