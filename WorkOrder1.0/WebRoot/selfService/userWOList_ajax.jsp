<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.hy.wo.util.MyUtil"%>
<s:iterator value="userwolist_ajax.workorderlist.resultList">
	<div class="wtlistcont">
		<div class="wtlistcont1">
			<a
				href='/wo/get_workOrderDetail?woId=<s:property value="@com.hy.wo.util.MyUtil@paramEncode(id)"/>'
				title='<s:property value="userInfo.memo"/>'>
				(工单号:<s:property value="id" />)<span style="color:red;">
				(类型：<s:property value="userInfo.lvlOne.name"/>)</span>
				<s:property
					value="@com.hy.wo.util.MyUtil@SubString(userInfo.memo,16,'...','无问题描述')" />
		</a>
		</div>
		<div class="wtlistcont2">
			<s:date name="createTime" format="yyyy-MM-dd hh:mm:ss" />
		</div>
		<s:set var="s" value="userwolist_ajax.states"></s:set>
		<s:if test="#s=='未处理'"><div class="wtlistcontwcl"></div></s:if>
		<s:if test="#s=='已反馈'"><div class="wtlistcontyhf"></div></s:if>
		<s:if test="#s=='处理中"><div class="wtlistcontclz"></div></s:if>
		<s:if test="#s=='已处理'"><div class="wtlistcontwb"></div></s:if>
		<s:if test="#s=='待补充资料'"><div class="wtlistcontbczl"></div></s:if>
		<div class="clear"></div>
	</div>
</s:iterator>