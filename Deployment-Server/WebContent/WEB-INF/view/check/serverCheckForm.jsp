<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>
	<h2>Server Status</h2>


	<table>
		<tr bgcolor=#ccfffff >
			<td align=center><b>NAME</b></td>
			<td align=center><b>Content Length</b></td>
			<td align=center><b>접속 상태</b></td>
		</tr>
		<c:forEach var="item"  items= "${checkList }" >
		<tr>
			<td align=left>${item.urlName}</td>
			<td align=center>${item.buildVersion }</td>
			<td align=center>${item.getConnectStatus() }</td>
		</tr>
		</c:forEach>
	</table>
	
</body>
</html>


