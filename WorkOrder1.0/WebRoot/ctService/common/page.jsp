<%@ page  pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="pages">
	<ul>
		<s:if test="pages.currentPage > 1">
			<li class="i"><a href="${first}">首页</a></li>　
			<li class="i"><a href="${previous}">上一页</a></li>
		</s:if>
		<s:else>
			<li class="i">首页</li>
			<li class="i">上一页</li>
		</s:else>
			
		<s:if test="pages.currentPage < pages.lastPage">
			<li class="i"><a href="${next}">下一页</a></li>
			<li class="i"><a href="${last}">尾页</a></li>
		</s:if>
		<s:else>
			<li class="i">下一页</li>
			<li class="i">尾页</li>
		</s:else>
		<li class="i">
			每页<s:property value="pages.pageSize"/>条　[<s:property value="pages.currentPage"/>/<s:property value="pages.totalPage"/>]
		</li>
	</ul>
</div>