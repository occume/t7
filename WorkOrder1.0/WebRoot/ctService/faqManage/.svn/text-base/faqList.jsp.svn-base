<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>\
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="context_path" value="#request.get('javax.servlet.forward.context_path')"></s:set>

<div id="com-faq-list">
		<ul>
				<li class="yi">序号</li>
				<li class="san">类型</li>
				<li class="wu">问题描述</li>
				<li class="san">可见者</li>
				<li class="liu">创建时间</li>
				<li class="qi">操作</li>
				<s:iterator value="pages.resultList">
					
						<li class="yi"><s:property value="id"/></li>
						<li class="san"><a href="/faq/getFaqByTypeManage?itype=<s:property value="@com.hy.wo.util.constants.Issue@getClassNameByIname(type)"/>"><s:property value="type"/></a></li>
						<li class="wu">
								<a href="/faq/single?id=<s:property value="id"/>">
										<s:if test="title.length()>20">
												<s:property value="title.substring(0,16)"/>...
										</s:if>
										<s:else>
												<s:property value="title"/>
										</s:else>
								</a>
						</li>
						<li class="san"><s:if test="toUser==true">
																<a href="/faq/getFaqByTypeToUser?toUser=true">玩家</a>
														</s:if>
														<s:else>
															<a href="/faq/getFaqByTypeToUser?toUser=false">客服</a>
															</s:else> </li>
						<li class="liu"><s:date name="createTime" format="yyyy-MM-dd hh:mm:ss"/></li>
						<li class="qi">
								<a id="editFaq" href="javascript:void(0)" onclick="dialog.openUrl('/faq/toEditPage?id=<s:property value="id"/>','450','450');">编辑</a>|
								<a id="deleteFaq" title="<s:property value="id"/>" href="javascript:void(0)" >删除</a>|
								<s:if test="visible">
										<a id="inPublishFaq" title="<s:property value="id"/>" href="javascript:void(0)">取消发布</a>
								</s:if>
								<s:else>
									<s:if test="toUser==true">	
										<a id="publishFaq" title="<s:property value="id"/>" href="javascript:void(0)">发布</a>
									</s:if>
								</s:else>
						</li>
					
				</s:iterator>
		</ul>
		<s:url var="first">
				<s:param name="currentPage" value="1"></s:param>
				<s:param name="toUser" value="toUser"></s:param>
				<s:param name="itype" value="@com.hy.wo.util.constants.Issue@getClassNameByIname(type)"></s:param>
			</s:url>
			<s:url var="previous">
				<s:param name="currentPage" value="pages.currentPage-1"></s:param>
				<s:param name="toUser" value="toUser"></s:param>
				<s:param name="itype" value="@com.hy.wo.util.constants.Issue@getClassNameByIname(type)"></s:param>
			</s:url>
			<s:url var="last">
				<s:param name="currentPage" value="pages.lastPage"></s:param>
				<s:param name="toUser" value="toUser"></s:param>
				<s:param name="itype" value="@com.hy.wo.util.constants.Issue@getClassNameByIname(type)"></s:param>
			</s:url>
			<s:url var="next">
				<s:param name="currentPage" value="pages.currentPage+1"></s:param>
				<s:param name="toUser" value="toUser"></s:param>
				<s:param name="itype" value="@com.hy.wo.util.constants.Issue@getClassNameByIname(type)"></s:param>
			</s:url>
			
</div>
<%@ include file="../common/page.jsp" %>