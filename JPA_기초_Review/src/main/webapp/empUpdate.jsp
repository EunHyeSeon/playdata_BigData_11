<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>사원 정보 수정</title>
</head>
<body>
<br><br><br>
<center>

<h3>사원 정보 수정하기 - 직무 수정</h3>
<hr><p>

<form action="emp?command=empUpdate" method="post">
<table border="1">
	<thead>
		<tr>
			<th>사번</th><th>사원명</th><th>직무</th><th>담당 상사 번호</th><th>입사일</th>
		</tr>
 	<tr>
 		<td><input type="text" name="empno"  value="${employee.empno}" readonly></td>
 		<td>${employee.ename}</td>
 		<td><input type="text" name="job" value="${employee.job}"></td>
 		<td>${employee.mgr}</td>
 		<td>${employee.hiredate}</td>
 	</tr>
 	
 	<tr>
 		<td colspan="4">
 			<input type="submit" value="수정">
 			&nbsp;&nbsp;&nbsp;
 			<input type="reset" value="취소">
 		</td>
 	</tr>
</table>
</form>

</center>

</body>
</html>