<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>\
<%@ taglib prefix="s" uri="/struts-tags"%>


<DIV class="faqcont">
	<s:if test="pages.resultList.size>0">
				<ul>
					<s:if test="pages.resultList.size<10">
						<s:iterator value="pages.resultList">
								<li>
									<a href="/faq/unique?id=<s:property value="id"/>&orient=self&itype=${itype}" target="blank">
										<s:if test="title.length()>30">
												<s:property value="title.substring(0,30)"/>
										</s:if>
										<s:else>
												<s:property value="title"/>
										</s:else>
									</a>
								</li>
						</s:iterator>
					</s:if>
					<s:else>
						<s:iterator value="pages.resultList.subList(0,10)">
								<li>
									<a href="/faq/unique?id=<s:property value="id"/>&orient=self&itype=${itype}" target="blank">
										<s:if test="title.length()>30">
												<s:property value="title.substring(0,30)"/>...
										</s:if>
										<s:else>
												<s:property value="title"/>
										</s:else>
									</a>
								</li>
							</s:iterator>	
							<li><a href="/faq/getUserFaqByType?itype=${itype}">更多>>></a></li>
					</s:else>
				</ul>
		</s:if>
</DIV>





