package com.hy.wo.service;

import com.hy.wo.dao.BaseDao;
import com.hy.wo.po.Faq;
import com.hy.wo.util.Pages;

public interface FaqService extends BaseDao<Faq> {
		public Pages<Faq> getAllFaq(int start,int len);
		public Pages<Faq> getFaqsByDescrip(int start,int len,String keyWord);
		public String deleteFaq(int id);
		public String publishFaq(int id);
		public Faq getUnique(int id);
		public Pages<Faq> getAllSelfFaq(String type,int currentPage, int len);
		public String inPublishFaq(int id);
		public Pages<Faq> getFaqByType(String itype, int currentPage, int len,boolean toUser);
		public Pages<Faq> getAllCTFaq(int start,int len) ;
		public Pages<Faq> getFaqByTypeManage(String itype, int currentPage, int len) ;
		public Pages<Faq> getFaqsByDescripManage(int start, int len, String keyWord);
		public Pages<Faq> getFaqsByDescripToUser(int start, int len, boolean toUser);
		public void repFaq(Faq faq, String content, String staff);
}
