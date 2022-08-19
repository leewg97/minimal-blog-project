<%@page contentType="text/html; charset=UTF-8" %>

<%@include file="../layout/header.jsp" %>

<table background="/images/kubrickbg.jpg" width="760" height="40" border="0" cellpadding="0" cellspacing="0">
	<tr><td height="10" colspan="10">&nbsp;</td></tr>
	<tr><td height="10" width="20">&nbsp;</td>
		<td width="530" valign="top">
			<!-- 메뉴 시작 --> 
			<a href="/blog/getBlog/${blog.blogId }"><b>기본설정</b></a>&nbsp;&nbsp;
			<b>카테고리</b>&nbsp;&nbsp;
			<a href="/post/insertPost/${blog.blogId }"><b>글작성</b></a>&nbsp;&nbsp;
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
	<tr><td height="10">&nbsp;</td><td>
		<!-- 작업 화면  시작 -->
		<table width="720" border="0" cellpadding="1" cellspacing="1">
			<tr bgcolor="#9DCFFF">
				<th width="50"><font color="white">번호</font></th>
				<th width="120"><font color="white">카테고리명</font></th>
				<th width="100"><font color="white">보이기 유형</font></th>
				<th width="350"><font color="white">설명</font></th>
				<th width="100"><font color="white">삭제</font></th>
			</tr>
			<c:forEach var="category" items="${categoryList }" varStatus="status">
				<tr>
					<td align="center">${status.count }</td>
					<c:if test="${category.categoryName == '미분류'}"><td align="center">${category.categoryName }</td></c:if>
					<c:if test="${category.categoryName != '미분류'}"><td align="center">
						<a href="/category/getCategoryList/${blog.blogId }/updateCategory/${category.categoryId }">${category.categoryName }</a></td>
					</c:if>

					<c:if test="${category.displayType == 'TITLE'}"><td align="center">제목</td></c:if>
					<c:if test="${category.displayType == 'MIXED'}"><td align="center">제목 + 내용</td></c:if>

					<td align="center">${category.description }</td>

					<c:if test="${category.categoryName == '미분류'}"><td align="center">삭제불가</td></c:if>
					<c:if test="${category.categoryName != '미분류'}">
						<td align="center"><a href="/category/${blog.blogId }/deleteCategory/${category.categoryId}"><img height="9" src="/images/delete.jpg" border="0"></a></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
			
			<!-- 카테고리 등록화면 시작 -->
			<form action="/category/${blog.blogId}/insertCategory" method="post">
			<table width="720" border="0" cellpadding="1" cellspacing="1">
				<tr><td height="5">&nbsp;</td></tr>
				<tr><td height="5">&nbsp;</td></tr>
				<tr><td height="5"><b>카테고리 등록</b></td>	</tr>
				<tr><td height="5">&nbsp;</td></tr>
				<tr>
					<td width="150">카테고리명 :</td>
					<td><input type="text" size="40" name="categoryName"></td>
				</tr>
				<tr>
					<td width="150">보이기 유형 :</td>
					<td><input type="radio"	name="displayType" value="TITLE" checked>제목&nbsp;&nbsp;
						<input type="radio" name="displayType" value="MIXED">제목 + 내용&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td width="150">설명 :</td>
					<td><input type="text" size="50" name="description"></td>
				</tr>
				<tr>
					<td colspan="10" align="center">&nbsp;<input type="submit" value="카테고리 추가"></td>
				</tr>
			</table>
		</form>
		<!-- 카테고리 등록화면 종료 -->
	</td>
	</tr>
	<tr><td height="10" colspan="10">&nbsp;</td></tr>
</table>
		
<%@include file="../layout/footer.jsp" %>