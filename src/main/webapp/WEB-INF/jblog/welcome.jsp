<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>JBlog</title>
</head>
<body>
<center>

<!-- 검색 화면 시작 -->
<form action="/blog/search" method="get">
	<table width="720" height=200 border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%" colspan="10" align="center">
				<img src="/images/logo.jpg" border="0">
			</td>
		</tr>				
		<tr>
			<td width="70%" colspan="2" align="center">
				<c:forEach var="blog" items="${blogList }">
					<c:if test="${principal.userId == blog.blogId }">
						<c:set var="hasBlog" value="true" />
					</c:if>
				</c:forEach>
				<c:if test="${principal == null }">
					<a href="/login"><b>로그인</b></a>
				</c:if>
				<c:if test="${principal != null }">
					<a href="/logout"><b>로그아웃</b></a>
				</c:if>
				<c:if test="${principal != null && hasBlog != true }">
					<a href="/blog/insertBlog"><b>블로그등록</b></a>
				</c:if>
				<c:if test="${principal != null && hasBlog == true }">
					<a href="/blog/blogMain/${principal.userId }"><b>블로그바로가기</b></a>
				</c:if>
			</td>
		</tr>
		<tr>
			<td align="center">
				<input type="radio" name="searchCondition" value="TITLE" checked="checked"
					   <c:if test="${condition == 'TITLE' }">checked</c:if>>블로그 제목&nbsp;&nbsp;
				<input type="radio"	name="searchCondition" value="TAG"
					   <c:if test="${condition == 'TAG' }">checked</c:if>>태그
				<input type="text" name="searchKeyword" value="${keyword }" size="50">
				<button type="submit">검색</button>
			</td>
		</tr>
	</table>
</form>
<!-- 검색 화면 종료 -->

<!-- 블로그 목록 시작 -->
<br><br>
<c:if test="${!empty blogList }">
	<table width="550" border="0" cellpadding="1" cellspacing="1">
			<tr bgcolor="#9DCFFF">
				<th height="30"><font color="white">블로그 제목</font></th>
				<th width="100"><font color="white">상태</font></th>
				<c:if test="${principal.role == 'ADMIN' }">
					<th width="100"><font color="white">삭제</font></th>
				</c:if>
			</tr>
		<c:forEach var="blog" items="${blogList }">
			<tr>
				<td align="left"><a href="/blog/blogMain/${blog.blogId }">${blog.title }</a></td>
				<td align="center">${blog.status }</td>
				<c:if test="${principal.role == 'ADMIN' }">
					<td align="center"><a href="/blog/deleteBlog/${blog.blogId }"><img height="9" src="/images/delete.jpg" border="0"></a></td>
				</c:if>
			</tr>
        </c:forEach>
    </table>
</c:if>
        <!-- 블로그 목록 종료 -->

        </center>
        </body>
        </html>