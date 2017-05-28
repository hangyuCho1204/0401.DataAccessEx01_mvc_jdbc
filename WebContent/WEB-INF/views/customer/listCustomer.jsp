

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>


<script type="text/javascript">
	function historyback(){
		history.back(-1);
	}
</script>
</head>
<body>
	

	결과화면<br>
	<a href="/0401.DataAccessEx01_mvc_jdbc/">메인 화면 ㄱㄱ</a><br>
	<a href="/0401.DataAccessEx01_mvc_jdbc/views/customer/insert">가입하기</a><br>
	<table border="1">
	
	
	<tr>
		<th>
			ID
		</th>
		<th>
			NAME
		</th>
		<th>
			ADDRESS
		</th>
		<th>
			E-MAIL
		</th>
		<th>
			수정
		</th>
		<th>
			삭제
		</th>
	</tr>
	
	<c:forEach var="bean" items="${lists}" >
	
	<tr>
		<td>
			${bean.id}
		</td>
		<td>
			${bean.name}
		</td>
		<td>
			${bean.address}
		</td>
		<td>
			${bean.email}
		</td>
		<td>
			<a href="/0401.DataAccessEx01_mvc_jdbc/views/customer/update?id=${bean.id}">수정</a>
			<%-- <a href="/0401.DataAccessEx01_mvc_jdbc/views/customer/update?id=${bean.id}&name=${bean.name}&address=${bean.address}&email=${bean.email}">수정</a> --%>
		</td>
		<td>
			<a href="/0401.DataAccessEx01_mvc_jdbc/views/customer/delete?id=${bean.id}">삭제</a>
		</td>
	</tr>
	</c:forEach>
	
	</table>
	<input type="button" value="뒤로가기" onclick="historyback()"/>
</body>
</html>