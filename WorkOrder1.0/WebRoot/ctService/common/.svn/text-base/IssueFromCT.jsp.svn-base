<%@ page  pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="woDetail-box">
		<ul>
			<li>
				<span class="jKey">申请人：</span><span class="jValue"><s:property value="userInfo.mail"/></span>
			</li>
			<li>
				<span class="jKey">被祝福人：</span><span class="jValue"><s:property value="userInfo.realName"/></span>
			</li>
			<li>
				<span class="jKey">祝福时间：</span><span class="jValue"><s:date name="userInfo.happendate" format="yyyy-MM-dd  HH:mm:ss"/></span>
			</li>
			<li>
				<span class="jKey">玩家账号：</span><span class="jValue"><s:property value="userInfo.accountname"/></span>
			</li>
			<li>
				<span class="jKey">游戏名称：</span><span class="jValue">风云传奇</span>
			</li>
			<li>
				<span class="jKey">祝福类型：</span><span class="jValue"><s:property value="userInfo.tel"/></span>
			</li>
		</ul>
		<ul>
			<li>
				<span class="jKey">联系方式：</span>
				<span class="jValue"><s:property value=""/></span>
			</li>
			<li>
				<span class="jKey">QQ：</span>
				<span class="jValue"><s:property value=""/></span>
			</li>
			<li>
				<span class="jKey">Email：</span>
				<span class="jValue"><s:property value=""/></span>
			</li>
		</ul>

<!-- 问题描述 -->
<s:if test="userInfo.memo!=null">
<div id="userInfoMemo">
	<h2>祝福内容：</h2>
	<ul class="userInfoMemo">
		<li>
			<s:property value="userInfo.memo"/>
		</li>
	</ul>
</div>
</s:if>

<!-- 问题补充 -->

<s:if test="issueAddSet.{?#this.fromUser}.size>0">
<div id="additional">
<h2>问题补充：</h2>
	<ul>
		<s:iterator value="issueAddSet" status="index">
			<s:if test="fromUser">
			<li class="topic">
				<s:date name="addDate" format="yyyy-MM-dd  HH:mm:ss"/>
				<span>${index.index+1}</span>
				<span>
						<s:if test="name!+null">
							<s:property value="name"/>
						</s:if>
						<s:else>玩家</s:else>
				</span>
				<span>补充</span>
			</li>
			<li class="content"><s:property value="content"/></li>
			</s:if>
		</s:iterator>
	</ul>
</div>
</s:if>
<!-- 派转信息 -->
<!-- 处理流程 -->
<s:if test="Opers.size>0">
	<div id="additional" class="cllc">
			<h2>处理流程：<span id="openAll">展开</span></h2>
			<div>
				<s:iterator value="Opers.{?#this.operType.id<10}" status="index">
				
					<s:if test="workerParent=='客服部'">
						<s:set name="styles" value="'background-color:#99ccff;'"></s:set>
					</s:if>
					<s:elseif test="workerParent=='产品部'">
						<s:set name="styles" value="'background-color:#99cc99;'"></s:set>
					</s:elseif>
					<s:elseif test="workerParent=='运维部'">
						<s:set name="styles" value="'background-color:#ffffcc;'"></s:set>
					</s:elseif>
					<s:else>
						<s:set name="styles" value="'background-color:#f69c9f;'"></s:set>
					</s:else>
					
					<div class="topic" style="<s:property value="#styles"/>">
							<span id="lead">${index.index+1}</span>
							<span><s:property value="workerParent"/></span>
							<s:property value="operType.name"/>
							<s:if test="operType.id==1||operType.id==2||operType.id==3">
								<s:set name="offset" value="content.indexOf('|')"></s:set>
								[<s:property value="content.substring(0,#offset)"/>]
							</s:if>
							<s:date name="operTime" format="yyyy-MM-dd  HH:mm:ss"/>
							<span><s:property value="worker"/></span>
						</div>
						<div class="${(operType.name=='回复'||operType.name=='处理')?'content pthf-box step':'content step'}" ind="<s:property value="id"/>"  title="<s:property value="content"/>">
							<s:if test="operType.id==2||operType.id==2||operType.id==3">
								<s:set name="offset" value="content.indexOf('|')"></s:set>
								<s:property value="content.substring(#offset+1)"/>
							</s:if>
							<s:else>
								<s:property value="content"/>
							</s:else>
						</div>
					
				</s:iterator>
			</div>
		</div>
</s:if>
</div>

<s:if test="evaluation!=null">
	<h2>玩家评价</h2>
		<ul>
		<s:if test="evaluation != null">
				<li>
					<span class="jKey"><strong>满意程度：</strong></span>
					<span class="jValue" style="color:red;"><s:property value="evaluation"/></span>
				</li>
			</s:if>
			<s:if test="comment != null">
				<li>
					<span class="jKey"><strong>服务评语：</strong></span>
					<span class="jValue"><s:property value="comment"/></span>
				</li>
			</s:if>
		</ul>
</s:if>


<s:if test="userInfo.files.size>0">
<h2>问题截图</h2>
	<div class="edit picture-box">
		<s:iterator value="userInfo.files" status="index">
			
			<div class="picture-item">
				
					<s:if test="path.indexOf('ctExtraInfo')>0">
						
							<s:if test="path.indexOf('jpg')>0">
								<div class="edit1 picture-title">截图${index.index+1}：</div>
								<div class="editpic1 picture"><a target="blank" href="${path}"><img class="pic" src="${path}" /></a></div>
							</s:if>
							<s:else>
							<div class="edit1 picture-title">附件${index.index+1}：</div>
							<div>
								<form action="/index/download.action" method="post">
									<input type="hidden" name="fileName" value="${path}"/>
									<input  type="submit" value="下载"/>
									<div>&nbsp;&nbsp;&nbsp;</div>
								</form>
							</div>
							</s:else>
						
					</s:if>
					<s:else>
					<div class="edit1 picture-title">截图${index.index+1}：</div>
						<s:if test="path.indexOf('WorkOrder')>0">
							<div class="editpic1 picture"><a target="blank" href="${path}"><img class="pic" src="${path}" /></a></div>
						</s:if>
						<s:else>
							<div class="editpic1 picture"><a target="blank" href="http://cs.2211.com${path}"><img class="pic" src="http://cs.2211.com${path}" /></a></div>
						</s:else>
					</s:else>
				</div>
		</s:iterator>
	</div>
</s:if>