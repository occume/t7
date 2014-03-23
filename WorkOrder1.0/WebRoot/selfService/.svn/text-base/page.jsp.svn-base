<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	
</script>
<div id="pages">
	<ul>
		<c:if test="${pages.currentPage>1}">
			<li><a href="${first}">首页</a></li>
			<li><a href="${previous}">上一页</a></li>
		</c:if>
		<c:if test="${pages.currentPage<=1}">
			<li>首页</li>
			<li>上一页</li>
		</c:if>
		<li>${pages.currentPage}/${pages.totalPage}</li>
		<c:if test="${pages.currentPage < pages.lastPage}">
			<li><a href="${next}">下一页</a></li>
			<li><a href="${last}">尾页</a></li>
		</c:if>
		<c:if test="${pages.currentPage>=pages.lastPage}">
			<li>下一页</li>
			<li>尾页</li>
		</c:if>
	</ul>
</div>
