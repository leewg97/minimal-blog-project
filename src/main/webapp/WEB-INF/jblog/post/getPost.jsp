<%@page contentType="text/html; charset=UTF-8" %>

<%@include file="../layout/header.jsp" %>

<table background="/images/kubrickbg.jpg" width="760" height="40" border="0" cellpadding="0" cellspacing="0">
	<tr><td height="10" colspan="10">&nbsp;</td></tr>
	<tr>
		<td height="10" width="20">&nbsp;</td>
		<td width="530" valign="top">
		<!-- 메뉴 시작 -->
		<a href="/blog/getBlog/${blog.blogId }"><b>기본설정</b></a>&nbsp;&nbsp;
		<a href="/category/getCategoryList/${blog.blogId }"><b>카테고리</b></a>&nbsp;&nbsp;
		<b>글작성</b>&nbsp;&nbsp;
			<c:if test="${principal.role == 'ADMIN' }">
				<a href="/blog/deleteBlog/${blog.blogId }"><b>블로그삭제</b></a>&nbsp;&nbsp;
			</c:if>
			<c:if test="${principal.role == 'USER' }">
				<a href="/blog/deleteRequest/${blog.blogId }"><b>블로그삭제요청</b></a>&nbsp;&nbsp;
			</c:if>
		<!-- 메뉴 끝 -->
		</td>
	</tr>
	<tr><td height="5">&nbsp;</td></tr>
	<tr><td height="10">&nbsp;</td>
		<td>
		
			<!-- 포스트 수정화면 시작 -->
			<form action="/post/updatePost/${blog.blogId }/${post.postId }" method="post">
				<table width="720" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<td>제목 :</td>
						<td>
							<input type="text" size="50" name="title" value="${post.title }">
							<select name="categoryId">
								<c:forEach var="categoryList" items="${categoryList }">
									<option value="${categoryList.categoryId }"
											<c:if test="${categoryList.categoryId == post.categoryId }">selected</c:if>>${categoryList.categoryName }
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>내용 :</td>
						<td colspan="10"><textarea name="content" rows="10" cols="80">${post.content }</textarea></td>
					</tr>
					<tr><td height="5">&nbsp;</td></tr>
					<tr><td colspan="10" align="center">&nbsp;<input type="submit" value="수정하기"></td></tr>
				</table>
			</form> 
			<!-- 포스트 수정화면 종료 -->
			
		</td>
	</tr>
	<tr><td height="10" colspan="10">&nbsp;</td></tr>
</table>

<%@include file="../layout/footer.jsp" %>
