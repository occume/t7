package com.hy.wo.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hy.wo.dao.BaseDaoSupport;
import com.hy.wo.po.Faq;
import com.hy.wo.po.FaqRep;
import com.hy.wo.service.FaqService;
import com.hy.wo.util.Constants;
import com.hy.wo.util.MyUtil;
import com.hy.wo.util.Pages;
import com.hy.wo.util.constants.Issue;

@Service @Transactional
public class FaqServiceImpl extends BaseDaoSupport<Faq> implements FaqService{
	private static final Logger LOG = Logger.getLogger(FaqServiceImpl.class);//定义日志

	public Pages<Faq> getAllFaq(int start,int len) {
		String hql=" o.isdelete=false ";
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String, String>();
		orderby.put("lastEditTime", "desc");
		Pages<Faq> pages=getPageData(start, len, hql, null, orderby);
		return pages;
	}
	public Pages<Faq> getAllCTFaq(int start,int len) {
		String hql=" o.toUser=false and o.isdelete=false ";
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String, String>();
		orderby.put("lastEditTime", "desc");
		Pages<Faq> pages=getPageData(start, len, hql, null, orderby);
		return pages;
	}
	public Pages<Faq> getAllSelfFaq(String type,int start, int len) {
		Issue is=getIssueType(type);
		String ttype="";
		Query query=null;
		if(type.equals("MORE")){
			String hql=" o.visible=true and o.isdelete=false and o.toUser=true";
			LinkedHashMap<String,String> orderby=new LinkedHashMap<String, String>();
			orderby.put("lastEditTime", "desc");
			Pages<Faq> pages=getPageData(start, len, hql, null, orderby);
			return pages;
		}else{
			String hql="select o from Faq o where o.type=? and  o.visible=true and o.toUser=true and o.isdelete=false order by o.createTime desc";
			ttype=is.getIname();
			query=getSession().createQuery(hql).setParameter(0, ttype);
			// Important
			query.setMaxResults(10);
			List<Faq> list=query.list();
			Pages<Faq> pages=new Pages<Faq>();
			pages.setResultList(list);
			LOG.debug("Query faq  SUCCESS");
			return pages;
		}
		
	}
	private Issue getIssueType(String type) {
		Issue[] issue=Issue.values();
		for(Issue is:issue){
			if(is.name().equals(type))
				return is;
		}
		return null;
	}
	public Pages<Faq> getFaqsByDescrip(int start, int len, String keyWord) {
		//System.out.println("keyword====="+keyWord);
		String temp="";
		try {
			temp=URLDecoder.decode(keyWord, Constants.DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {}
		//System.out.println("temp="+temp);
		
		String hql="( upper(o.descrip) like ? or  upper(o.title) like ? ) and o.toUser=false and o.isdelete=false";
		Object[] param=new Object[]{"%"+temp.toUpperCase()+"%","%"+temp.toUpperCase()+"%"};
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String, String>();
		orderby.put("lastEditTime", "asc");
		Pages<Faq> pages=getPageData(start, len, hql, param, orderby);
		return pages;
	}
	/**
	 * 后台管理查询
	 */
	public Pages<Faq> getFaqsByDescripManage(int start, int len, String keyWord) {
		//System.out.println("keyword====="+keyWord);
		String temp=keyWord;
//		try {
//			temp=URLDecoder.decode(keyWord, "utf-8");
//		} catch (UnsupportedEncodingException e) {}
		
		String hql=" upper(o.descrip) like ? and o.isdelete=false";
	    Object[] param=new Object[]{"%"+temp.toUpperCase()+"%"};
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String, String>();
		orderby.put("lastEditTime", "desc");
		Pages<Faq> pages=getPageData(start, len, hql, param, orderby);
		return pages;
	}
	/**
	 * 后台管理查询 根据可见人
	 */
	public Pages<Faq> getFaqsByDescripToUser(int start, int len, boolean toUser) {
		String hql="";
		if(toUser){
			 hql=" o.toUser=true and o.isdelete=false";
		}else{
			hql=" o.toUser=false and o.isdelete=false";
		}
	
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String, String>();
		orderby.put("lastEditTime", "desc");
		Pages<Faq> pages=getPageData(start, len, hql, null, orderby);
		return pages;
	}
	
	public Pages<Faq> getFaqByType(String itype, int currentPage, int len,boolean toUser) {
		Issue is=getIssueType(itype);
		String ttype=is.getIname();
		String hql="";
		if(toUser){
			hql="o.type = ? and o.toUser=true and  o.isdelete=false";
		}else{
				hql="o.type = ? and o.toUser=false and  o.isdelete=false";
				if(itype.equals("NOTICE")){
					hql="o.sortType=? and o.toUser=false and o.isdelete=false";
				}
		}
		Object[] param=new Object[]{ttype};
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String, String>();
		orderby.put("lastEditTime", "desc");
		Pages<Faq> pages=getPageData(currentPage, len, hql, param, orderby);
		return pages;
	}
	/**
	 * 后台管理查询 
	 */
	public Pages<Faq> getFaqByTypeManage(String itype, int currentPage, int len) {
		Issue is=getIssueType(itype);
		String ttype=is.getIname();
		String hql="";
		
		hql="o.type = ? and  o.isdelete=false";
				
		Object[] param=new Object[]{ttype};
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String, String>();
		orderby.put("lastEditTime", "desc");
		Pages<Faq> pages=getPageData(currentPage, len, hql, param, orderby);
		return pages;
	}
	
	public String deleteFaq(int id) {
		Faq faq=(Faq) getSession().get(Faq.class, id);
		faq.setIsdelete(true);
		return "OK";
	}

	public String publishFaq(int id) {
		Faq faq=(Faq) getSession().get(Faq.class, id);
		//System.out.println(faq.getTitle());
		faq.setVisible(true);
		return "OK";
	}
	public String inPublishFaq(int id) {
		Faq faq=(Faq) getSession().get(Faq.class, id);
		faq.setVisible(false);
		return "OK";
	}
	public Faq getUnique(int id) {
		Faq faq=(Faq) getSession().get(Faq.class, id);
		return faq;
	}
	/**
	 * 回复FAQ
	 */
	public void repFaq(Faq faq, String content, String guy) {
		Faq faqq = getUnique( faq.getId() );
		String status = faq.getStatus();
		if( !MyUtil.isBlankOrNull(status) )
			faqq.setStatus( status );
		FaqRep fr = new FaqRep();
		fr.setGuy(guy);
		fr.setCdate(new Date());
		fr.setDesc(content);
		fr.setParent(faqq);
		faqq.addRep(fr);
	}
	
		
}
