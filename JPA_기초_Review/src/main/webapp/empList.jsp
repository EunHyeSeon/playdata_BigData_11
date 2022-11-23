<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>모든 사원 정보 페이지</title>
</head>
<body>
<br><br><br>
<center>  
<h3>사원 list</h3>
<hr><p>

<table border="1">
	<thead>
		<tr>
			<th>사번</th><th>사원 이름</th><th>직무</th><th>직원 담당 상사 번호</th><th>입사일</th><th>소속 부서 번호</th>
		</tr>
	</thead>
	
	<%--???
	 	모든 기부자 정보 보기 : 기부자 id를 클릭하면 "재능 기부자"상세 보기 로직이 실행되어야 함 --%>
	<c:forEach items="${requestScope.getEmpAll}" var="dataAll"> 
 		<tr>
 			<td><a href='${pageContext.request.contextPath}/emp?command=employee&empno=${dataAll.empno}'>${dataAll.empno}</a></td>
 			<td>${dataAll.ename}</td>
 			<td>${dataAll.job}</td>
 			<td>${dataAll.mgr}</td>
 			<td>${dataAll.hiredate}</td>
 			<td>${dataAll.deptno}</td> 
 		</tr>
 	</c:forEach> 
</table>

<br><br><br>
<font color="blue">사번을 클릭하면 상세 정보 확인이 가능합니다</font>

&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/index.html">메인 화면 이동</a>

</center>
</body>
</html>