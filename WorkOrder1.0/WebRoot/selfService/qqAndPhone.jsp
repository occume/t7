<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


  <div class="formcontbox2">
											<div class="top"></div>
										<h3>以下2项请至少填写其中1项</h3>
										<div class="formcont">
											<div class="formlefttitle2">
												联系QQ：
											</div>
											<div class="formrigcont">
												<input type="text" id="qq" name="userInfo.qq"
													value='<s:property value="#session.member.qq"/>'
													maxlength="20" class="input180" tabindex="6" />
											</div>
											<div class="formrigcontbz">
												留下您的QQ号,以便我们在必要时能及时联系到您
											</div>
											<div class="clear"></div>
											<div class="baocuo1" id="qqErr">

											</div>
										</div>

										<div class="formcont">
											<div class="formlefttitle2">
												联系电话：
											</div>
											<div class="formrigcont">
												<input type="text" id="tel" name="userInfo.tel"
													value='<s:property value="#session.member.tel"/>'
													maxlength="20" class="input180" tabindex="7" />
											</div>
											<div class="formrigcontbz">
												填写您的真实电话，有助于您及时得到反馈。
												我们<br/>会严格保密您的个人资料。<a id="getPostCode" href="javascript:void(0)">不知道区号？</a>
											</div>
											<div class="clear"></div>
											<div class="baocuo1" id="telErr">

											</div>
										</div>

									<div class="bottom"></div>
	</div>
	<div class="formcont">
											<div class="formlefttitle2 memoLeft">
												问题描述：
											</div>
											
											<div class="formtextareacont">
												<textarea cols="" rows="20" class="textarea"
													id="memo"
													name="userInfo.memo" style="height: 200px;" tabindex="5"></textarea>
												<div id="textAreaHint">还能输入<span id="textAreaLeftCount">300</span>字</div>	
											</div>
											<div class="clear"></div>
											<div class="formnotestext2 memoBottom">
												<span>*</span>（您如果还有其它信息需要补充，可以在问题描述中说明。）
											</div>
											<div class="baocuo1" id="memoErr">
											</div>
										</div>
										<input type="hidden" name="IDC" value="<s:property value="#session.member.idCard"/>"/>