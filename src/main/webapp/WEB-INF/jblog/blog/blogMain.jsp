<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@include file="../layout/header.jsp" %>

<table background="/images/kubrickbg.jpg" width="760" height="300" border="0" cellpadding="0" cellspacing="0">
	<tr valign="top"><td height="10">&nbsp;</td></tr>
	<tr valign="top"><td width="20">&nbsp;</td>
		<td width="530">
		
		
		<!-- 포스트 목록 시작 -->
		<c:forEach var="post" items="${posts }">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="420">
						<h3><font color="green">${post.title }</font></h3>
						${post.content }<br>
					</td>
					<td align="right"><a href="/post/updatePost/${blog.blogId }/${post.postId }">수정</a> / <a href="/post/deletePost/${blog.blogId }/${post.postId }">삭제</a></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<font color="gray">${post.createDate }</font><br>
					</td>
				</tr>
			</table><br>
			<br>
		</c:forEach>
		<!-- 포스트 목록 끝-->
		
		
		</td>
		<td width="20">&nbsp;</td>
		<td width="190" valign="top">
		<!-- 로그인, 관리자 메뉴, 로고, 카테고리 시작 -->
		<table cellpadding="0" cellspacing="0">
			<tr><td height="5">&nbsp;</td></tr>
			<tr><td><img height="80" src="/images/j2eelogo.jpg" border="0"></td></tr>
			<tr><td height="5">&nbsp;</td></tr>
			<tr><td><b>카테고리</b></td></tr>
			<c:forEach var="category" items="${categoryList}">
				<tr>
					<td><a href="/blog/${blog.blogId }/postByCategory/${category.categoryId }"><b>${category.categoryName }</b></a></td>
				</tr>
			</c:forEach>
			<tr><td height="5">&nbsp;</td></tr>
			<tr><td align="center"><a href="/"><img width="80" src="/images/logo.jpg" border="0"></a></td></tr>
		</table> 
		<!-- 로그인, 관리자 메뉴, 로고, 카테고리 끝 -->
		</td>
	</tr>
</table>

<%@include file="../layout/footer.jsp" %>
