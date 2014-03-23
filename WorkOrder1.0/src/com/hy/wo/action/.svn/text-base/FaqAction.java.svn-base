package com.hy.wo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.hy.wo.exception.WoCenterException;
import com.hy.wo.po.Faq;
import com.hy.wo.po.FaqAnswer;
import com.hy.wo.po.FaqRep;
import com.hy.wo.po.Staff;
import com.hy.wo.servlet.DWRHelper;
import com.hy.wo.util.CheckParamManager;
import com.hy.wo.util.Constants;
import com.hy.wo.util.DepictXmlManager;
import com.hy.wo.util.MyUtil;
import com.hy.wo.util.Pages;
import com.hy.wo.util.Constants.PAGE;
import com.hy.wo.util.Constants.ParamName;
import com.hy.wo.util.Constants.PromptMessage;
import com.hy.wo.util.Constants.SessionKey;
import com.hy.wo.util.Constants.StateList;
import com.opensymphony.xwork2.ModelDriven;

@Controller @Scope("prototype")
public class FaqAction extends BaseAction implements ModelDriven<Faq>{
	
    private static final Logger LOG         = Logger.getLogger(FaqAction.class);
	private static final long serialVersionUID = 2952944711446017872L;
	public static String state=StateList.SUCCESS;
	public static String stateDesc=PromptMessage.SUCCESS;
	/**
	 * 保存一条FAQ
	 * @return
	 */
	public String saveFaq(){
		faq.setCreateTime(new Date());
		faq.setLastEditTime(new Date());
		String touser=request.getParameter( ParamName.TO_USER );
		if(!MyUtil.isBlankOrNull(touser)&&touser.equals( ParamName.USER )){
			faq.setToUser(true);
		}
		if(faq.getSortType().equals(com.hy.wo.util.constants.Issue.NOTICE.getIname())){
			faq.setType(com.hy.wo.util.constants.Issue.NOTICE.getIname());
		}
		
		faqService.save(faq);
		Staff staff=(Staff) session.getAttribute( SessionKey.STAFF );
		faq.setGameName(staff.getNickName());
		DWRHelper.showNotice(session.getServletContext(),faq);
		setMessage(SUCCESS_MESSAGE);
		return SUCCESS;
	};
	/**
	 * FAQ编辑
	 * @return
	 */
	public String editFaq(){
		//faq.setCreateTime(createTime)
		Faq faqq=faqService.getUnique(faq.getId());
		faqq.setTitle(faq.getTitle());
		faqq.setCreateTime(faq.getCreateTime());
		faqq.setLastEditTime(new Date());
		//faqq.setVersion(faq.getVersion());
		faqq.setDescrip(faq.getDescrip());
		faqService.update(faqq);
		setMessage(EDIT_SUCCESS_MESSAGE);
		return SUCCESS;
	}
	public String toEditPage(){
		single();
		return EDITFAQPAGE;
	}
	/**
	 * FAQ回复
	 * @return
	 */
	public String replyFaq(){
		
		Staff staff = (Staff) session.getAttribute( SessionKey.STAFF ); 
 		faqService.repFaq(faq, repContent, staff.getNickName());
		
		setMessage(REPLY_SUCCESS_MESSAGE);
		return SUCCESS;
	}
	public String toReplyPage(){
		single();
		return REPLEY_FAQ_PAGE;
	}
	/**
	 * 全部查询
	 * @return
	 */
	public String getAllFaq(){
		pages=faqService.getAllFaq(currentPage, len);
		return R_FAQLIST;
	}
	/**
	 * 对玩家显示全部查询
	 * @return
	 */
	public void getAllSelfFaq(){
		//LOG.debug("get faq  from cs2211");
		String content="";
		try {
			CheckParamManager.baseValidate(request);
			pages=faqService.getAllSelfFaq(itype,currentPage, len);
		} catch (WoCenterException e) {
			state=e.getState();
			stateDesc=e.getStateDesc();
		} catch(Exception e){
			e.printStackTrace();
			state=StateList.UNKNOWN_ERROR;
			stateDesc=PromptMessage.SYSTEM_BUSY;
		}
		
		content=DepictXmlManager.makeFaqListXml(pages);
		DepictXmlManager.outputStr(DepictXmlManager.organizeBasicXML(state, stateDesc)+content, response);
		//LOG.debug("get faq  from cs2211  SUCCESS");
	}
	public String unique(){
		String content="";
		try {
			CheckParamManager.baseValidate(request);
			faq=faqService.getUnique(faq.getId());
		} catch (WoCenterException e) {
		}
		
		content=DepictXmlManager.makeFaqXml(faq);
		DepictXmlManager.outputStr(DepictXmlManager.organizeBasicXML(state, stateDesc)+content, response);
		return null;
	}
	public String single(){
		faq=faqService.getUnique(faq.getId());
		//System.out.println(faq.getReps());
		if(orient!=null){
			setCTag();
			return UNIQUE_SELF;
		}
		return UNIQUE;
	}
	/**
	 * 根据问题描述查询
	 * @return
	 */
	public String getFaqsByDescrip(){
		if(MyUtil.isBlankOrNull(faq.getDescrip())){
			pages=faqService.getAllCTFaq(currentPage, len);
			return R_FAQLIST;
		}
		pages=faqService.getFaqsByDescrip(currentPage, len, faq.getDescrip());
		return R_FAQLIST;
	}
	/**
	 * 后台管理查询
	 * @return
	 */
	public String getFaqsByDescripManage(){
		if(MyUtil.isBlankOrNull(faq.getDescrip())){
			pages=faqService.getAllFaq(currentPage, len);
			return R_FAQLIST;
		}
		pages=faqService.getFaqsByDescripManage(currentPage, len, faq.getDescrip());
		return R_FAQLIST;
	}
	public String deleteFaq(){
		String m=faqService.deleteFaq(faq.getId());
		sendAjaxMessage(m);
		return null;
	}
	//对玩家可见
	public String publishFaq(){
		//System.out.println("publish");
		String m=faqService.publishFaq(faq.getId());
		sendAjaxMessage(m);
		return null;
	}
	//取消对玩家可见
	public String inPublishFaq(){
		String m=faqService.inPublishFaq(faq.getId());
		sendAjaxMessage(m);
		return null;
	}
	/**
	 * 客服查询FAQ
	 */
	public void getFaqByKeyword(){
		String keyWord=request.getParameter( ParamName.KEY_WORD );
		String cp=request.getParameter( ParamName.CP );
		int cP=Integer.valueOf(cp);
		if(cP!=0){
			currentPage=cP;
		}
		if(!MyUtil.isBlankOrNull(keyWord)){
			faq.setDescrip(keyWord);
		}
		getFaqsByDescrip();
		sendAjaxJson(makeJsonObject());
	}
	/**
	 * 客服查询 FAQ | By 类型
	 */
	public void getFaqByType(){
//		String keyWord=request.getParameter("itype");
		String cp=request.getParameter( ParamName.CP );
		int cP=Integer.valueOf(cp);
		if(cP!=0){
			currentPage=cP;
		}
		pages=faqService.getFaqByType(itype,currentPage, len,false);
		sendAjaxJson(makeJsonObject());
	}
	/**
	 * 后台管理查询  根据类型
	 * @return
	 */
	public String getFaqByTypeManage(){
		pages=faqService.getFaqByTypeManage(itype,currentPage, len);
		return R_FAQLIST;
	}
	/**
	 * 后台管理查询  根据可见人
	 * @return
	 */
	public String getFaqByTypeToUser(){
		pages=faqService.getFaqsByDescripToUser(currentPage, len,faq.isToUser());
		return R_FAQLIST;
	}
	/**
	 * 玩家查询 FAQ | By 类型
	 */
	public String getUserFaqByType(){
		setCTag();
		
		String content="";
		try {
			CheckParamManager.baseValidate(request);
			pages=faqService.getFaqByType(itype,currentPage, len,true);
		} catch (WoCenterException e) {
			state=e.getState();
			stateDesc=e.getStateDesc();
		} catch(Exception e){
			state=StateList.UNKNOWN_ERROR;
			stateDesc=PromptMessage.SYSTEM_BUSY;
		}
		
		content=DepictXmlManager.makeFaqListXml(pages);
		DepictXmlManager.outputStr(DepictXmlManager.organizeBasicXML(state, stateDesc)+content, response);
		return null;
	}
	private void setCTag() {
			if(itype.equals("Acount")){
				currentTag=1;
			}else if(itype.equals("GAMEABNOMAL")){
				currentTag=2;
			}else if(itype.equals("GoodsLost")){
				currentTag=3;
			}else if(itype.equals("BUGSUGESTTION")){
				currentTag=4;
			}else if(itype.equals("RECHARGEISSUE")){
				currentTag=5;
			}else if(itype.equals("ROLEDATA")){
				currentTag=6;
			}else if(itype.equals("SERVERISSUE")){
				currentTag=7;
			}else if(itype.equals("MORE")){
				currentTag=8;
			}
		
	}
	/**
	 * 异步响应
	 * @return
	 */
	private JSONObject makeJsonObject(){
		
		JSONObject jobj=new JSONObject();
		//LOG.debug(pages.getResultList().size());
		for(Faq faq : pages.getResultList()){
			for(FaqRep fr : faq.getReps()){
				faq.getAnswers().add(new FaqAnswer(fr));
			}
			faq.setReps(null);
		}
		try{
			jobj.element("faqs", pages.getResultList());
		}catch(Exception e){
			e.printStackTrace();
		}
		jobj.element( PAGE.CURRENT_PAGE, pages.getCurrentPage());
		jobj.element( PAGE.PAGE_SIZE, pages.getPageSize());
		jobj.element( PAGE.TOTAL_PAGE, pages.getTotalPage());
		//System.out.println(jobj);
		return jobj;
	}
	/**
	 * 响应异步请求  json
	 * @param message
	 */
	private void sendAjaxJson(JSONObject jobj){
		response.setContentType( Constants.DEFAULT_CONTENT_TYPE );
	    response.setCharacterEncoding( Constants.DEFAULT_CHARSET );
	    
		PrintWriter out=null; 
		try
		{
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		out.print(jobj.toString());
		out.flush();
		out.close();
	}
	/**
	 * 响应异步请求 no json
	 * @param message
	 */
	private void sendAjaxMessage(String message){
		response.setContentType( Constants.DEFAULT_CONTENT_TYPE );
	    response.setCharacterEncoding( Constants.DEFAULT_CHARSET );
	    
	    JSONObject jobj=new JSONObject();
	    jobj.element("message",message);
		PrintWriter out=null; 
		try
		{
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		out.print(jobj.toString());
		out.flush();
		out.close();
	}
	
	public void initIssueSelect(){
		//Map iMap=woService.initIssueOneMap(Constants.ClassCategory.GAMEISSUE);
		Map<String,String> iMap=new HashMap<String, String>();
		iMap.put("01", "账号问题");
		iMap.put("02", "游戏异常");
		iMap.put("03", "物品丢失");
		iMap.put("04", "意见建议");
		iMap.put("11", "BUG意见");
		iMap.put("05", "游戏BUG");
		iMap.put("06", "充值问题");
		iMap.put("07", "角色数据异常");
		iMap.put("08", "服务器问题");
		iMap.put("09", "游戏知识");
		iMap.put("10", "其它问题");
		session.setAttribute("issueInfo", iMap);
	}
	
	Pages<Faq> pages=null; 
	private int currentPage=1;
	private int len=10;
	private String message;
	private String repContent;
	private String orient;
	private String itype;
	private String username;
	private int currentTag=0;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getCurrentTag() {
		return currentTag;
	}
	public void setCurrentTag(int currentTag) {
		this.currentTag = currentTag;
	}
	public String getItype() {
		return itype;
	}
	public void setItype(String itype) {
		this.itype = itype;
	}
	
	public String getRepContent() {
		return repContent;
	}
	public void setRepContent(String repContent) {
		this.repContent = repContent;
	}

	private Faq faq=new Faq();
	
	public Faq getFaq() {
		return faq;
	}
	public void setFaq(Faq faq) {
		this.faq = faq;
	}
	public Faq getModel() {
		return faq;
	}
	
	public String getOrient() {
		return orient;
	}
	public void setOrient(String orient) {
		this.orient = orient;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Pages<Faq> getPages() {
		return pages;
	}
	public void setPages(Pages<Faq> pages) {
		this.pages = pages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	private static String SUCCESS_MESSAGE="成功添加一条FAQ";
	private static String EDIT_SUCCESS_MESSAGE="修改FAQ成功！";
	private static String REPLY_SUCCESS_MESSAGE="回复FAQ成功！";
	private static String R_FAQLIST="faqList";
	private static String EDITFAQPAGE="editFaq";
	private static String REPLEY_FAQ_PAGE="replyFaq";
	private static String UNIQUE="unique";
	private static String UNIQUE_SELF="uniqueSelf";
}
