package com.hy.wo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hy.wo.dao.BaseDaoSupport;
import com.hy.wo.exception.WoCenterException;
import com.hy.wo.po.Department;
import com.hy.wo.po.Group;
import com.hy.wo.po.Role;
import com.hy.wo.po.Servers;
import com.hy.wo.po.Staff;
import com.hy.wo.service.StaffService;
import com.hy.wo.util.Constants;
import com.hy.wo.util.MyUtil;
import com.hy.wo.util.Pages;
import com.hy.wo.util.Constants.PromptMessage;
import com.hy.wo.util.Constants.RoleLevel;
import com.hy.wo.util.Constants.StateList;

import static com.hy.wo.util.Constants.DefaultValue.*;
import static com.hy.wo.util.Constants.DepartmentId.CTDEPARTMENT;
import static com.hy.wo.util.Constants.RoleLevel.GENARA_ROLE;
import static com.hy.wo.util.Constants.RoleLevel.GM_ROLE;
import static com.hy.wo.util.Constants.RoleLevel.ZUZHANG_ROLE;

@Service @Transactional
public class StaffServiceImpl extends BaseDaoSupport<Staff> implements StaffService{
	private static final Logger LOG = Logger.getLogger(StaffServiceImpl.class);//定义日志
	/**
	 * 员工登录
	 */
	@SuppressWarnings("unchecked")
	public Staff logon(Staff staff) {
		String hql="from Staff o where o.zuoXi=? and o.password=? and o.isdelete=false";
		//System.out.println(staff.getZuoXi()+":"+staff.getPassword());
		Query query=getSession().createQuery(hql);
		query.setParameter(0, staff.getAccName());
		query.setParameter(1, MyUtil.hash(staff.getPassword()));
		List<Staff> list=query.list();
		Staff staf=null;
		if( list != null && list.size() > 0 ){
			staf = (Staff) list .get( 0 );
		}
		LOG.debug("List<Staff>.size = "+list.size());
		//System.out.println(staf.getAccName());
		return staf;
	}
	/**
	 * 员工登录
	 */
	@SuppressWarnings("unchecked")
	public Staff logon_wo(Staff staff) {
		String hql="from Staff o where o.accName=? and o.password=? and o.isdelete=false";
		Query query=getSession().createQuery(hql);
		query.setParameter(0, staff.getAccName());
		query.setParameter(1, MyUtil.hash(staff.getPassword()));

		List list=null;
		try{
			list=query.list();
		}catch(HibernateException e){
			e.printStackTrace();
		}
		Staff staf=null;
		if(list.size()>0){
			staf=(Staff) list .get(0);
		}
		return staf;
	}

	/**************************** Staff CURD*********************************/
	/**
	 * 添加员工 ￥客服主管以上
	 */
	public Staff addStaff(Staff staff) {
		Session s=getSession();
		Role role=(Role) s.get(Role.class, staff.getRole().getId());
		Group group=(Group) s.get(Group.class, staff.getGroup().getId());
		Department department=(Department) s.get(Department.class, staff.getDepartment().getId());
		
		staff.setRole(role);
		staff.setDepartment(department);
		staff.setGroup(group);
		staff.setIsdelete(false);
		staff.setPassword(MyUtil.hash(String.valueOf(DEFAULT_PSW)));
		staff.setIsdelete(false);
		
		
		save(staff);
		return staff;
	}
	/****************/
	public Staff addStaffToDepartment(Staff staff,int departmentId){
		Staff staf=get(staff.getId());
		Department department=(Department)getSession().get(Department.class, departmentId);
		staf.setDepartment(department);
		update(staf);
		return staf;
	}
	/*** 修改员工信息***/
	public Staff editStaffInfo(Staff staff) {
		
		Session s=getSession();
		Role role=(Role) s.get(Role.class, staff.getRole().getId());
		Group group=(Group) s.get(Group.class, staff.getGroup().getId());
		Department department=(Department) s.get(Department.class, staff.getDepartment().getId());
		
		Staff staf=get(staff.getId());
		LOG.debug("修改Staff"+staf);
		staf.setRole(role);
		staf.setDepartment(department);
		staf.setGroup(group);
		staf.setZuoXi(staff.getZuoXi());
		//update(staf);
		return staf;
	}
	/*** 删除本部门员工****/
	public Staff deleteStaffOfDepartment(Staff staff) {
		Staff staf=get(staff.getId());
		Department department=(Department)getSession().get(Department.class, 1);
		staf.setDepartment(department);
		//注意这里  为什么不能用 delete(Object object)方法？？？
		update(staf);
		//System.out.println("success");
		return staf;
	}
	/**
	 * 删除员工
	 */
	public Staff deleteStaff(Staff staff) {
		Staff staf=get(staff.getId());
		if(staf.getRole().getLevel()<3){
			staf.setIsdelete(true);
		}
		return staf;
	}
	/************************ Staff Search ****************************/
	/**
	 * 根据工号查找员工
	 */
	public Pages<Staff> findStaffByAccName(String accName) {
		String hql="( upper(o.accName) like ? or  upper(o.nickName) like ? ) and o.isdelete=false";
		Object[] param=new Object[]{"%"+accName.toUpperCase()+"%","%"+accName.toUpperCase()+"%"};
		Pages<Staff> pages=getPageData(1, 10, hql, param, null);
		return pages;
	}
	public Staff getStaffByAccName(String accName){
		String hql="select o from Staff o where o.accName=? and o.isdelete=false";
		Query q=getSession().createQuery(hql)
													.setParameter(0, accName);
		Staff staff=(Staff) q.uniqueResult();
		return staff;
	}
	/**
	 * 根据部门Id查找员工
	 */
	public Pages<Staff> findStaffByDepartmentId(int id,int start,int len) {
		String hql="o.department.id=? and o.isdelete=false";
		Object[] param=new Object[]{id};
		LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
		orderby.put("accName", "desc");
		Pages<Staff> pages=getPageData(start, len, hql, param, orderby);
		return pages;
	}
	/**
	 * 根据部门名称查找员工
	 * @param departmentName
	 * @param start
	 * @param len
	 * @return
	 */
	public Pages<Staff> findStaffByDepartmentName(String departmentname,int start,int len) {
		String hql="o.department.name=? and o.isdelete=false";
		Object[] param=new Object[]{departmentname};
		Pages<Staff> pages=getPageData(start, len, hql, param, null);
		return pages;
	}
	/**
	 * 查找员工
	 */
	public Pages<Staff> searchStaff(int start ,int len,Staff staff,Role role) {
		int roleId=role.getId();
		String hql="";
		Object[] param=null;
		LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
		orderby.put("accName", "desc");
		Pages<Staff> pages=null;
		
		if(roleId==RoleLevel.ADMIN_ROLE){//管理员
				hql=createHQL(staff);
				param=createParams(staff);
				if(hql==null)
					hql="";
				else
					hql+=" and ";
				//pages=getPageData(start, len, hql, param, orderby);
		}
		else{
			hql=createHQL(staff);
			param=createParams(staff);
			if(hql==null)
				hql=" o.department.id=2 and";
			else
				hql+=" and o.department.id=2 and";
			//Pages pages=getPageData(start, len, hql, param, orderby);
			//List<Staff> list=page.getResultList();
		}
		hql+=" o.isdelete=false";
		pages=getPageData(start, len, hql, param, orderby);
		return pages;
	}
//	private Pages convertPages(Pages<Staff> pages){
//		Pages page=new Pages();
//		page.setCurrentPage(pages.getCurrentPage());
//		page.setLastPage(pages.getLastPage());
//		page.setPageSize(pages.getPageSize());
//		page.setTotalCount(pages.getTotalCount());
//		page.setTotalPage(pages.getTotalPage());
//		for(Staff staff:pages.getResultList()){
//			
//		}
//		
//		return page;
//	}
	
	private String createHQL(Staff staff){
		//System.out.println("creatHQL");
		String hql="";
		hql+=staff.getDepartment()==null?"":" o.department.id=? ";
		hql+=staff.getGroup()==null?"":" o.group.id=? ";
		hql+=staff.getRole()==null?"":" o.role.id=? ";
		
		//System.out.println("---"+hql);
		return hql.equals("")?null:hql;
	}
	private Object[] createParams(Staff staff){
		
		List<Object> list=new ArrayList<Object>();
		list.add(staff.getDepartment()==null?null:staff.getDepartment().getId());
		list.add(staff.getGroup()==null?null:staff.getGroup().getId());
		list.add(staff.getRole()==null?null:staff.getRole().getId());
		
		List<Object> list1=new ArrayList<Object>();
		for(Object o:list){
			if(o!=null){
				list1.add(o);
			}
		}
		Object[] para=new Object[list1.size()];
		for(int i=0;i<para.length;i++){
			para[i]=list1.get(i);
		}
		//System.out.println(para.length);
	//	LOGGER.debug("Params完毕！--"+Arrays.asList(para));
		//System.out.println("length"+para.length);
		return para.length==0?null:para;
	}
//	private boolean isEmpty(String str){
//		if(StringUtil.isBlankOrNull(str)||"".equals(str)||"0".equals(str)){
//			return true;
//		}
//		return false;
//	}
	/******************************* Select Manage **************************************/
	public Pages<Servers> getAllServers(int start, int len) {
		
		return null;
	}
	/****************************** Initial Information *********************************/
	@SuppressWarnings("unchecked")
	public Map<Integer, String> getRoleMap() {
		Map<Integer, String> map=new HashMap<Integer, String>();
		String hql="from Role";
		Query query=getSession().createQuery(hql);
		List<Role> list=query.list();
		for(int i=1;i<=list.size();i++){
			map.put(list.get(i-1).getId(), list.get(i-1).getName());
		}
		return map;
	}
	@SuppressWarnings("unchecked")
	public Map<Integer, String> getDepartmentMap() {
		Map<Integer, String> map=new HashMap<Integer, String>();
		String hql="from Department";
		Query query=getSession().createQuery(hql);
		List<Department> list=query.list();
		for(int i=1;i<=list.size();i++){
			map.put(list.get(i-1).getId(), list.get(i-1).getName());
		}
		return map;
	}
	@SuppressWarnings("unchecked")
	public Map<Integer, String> getGroupMap() {
		Map<Integer, String> map=new HashMap<Integer, String>();
		String hql="from Group";
		Query query=getSession().createQuery(hql);
		List<Group> list=query.list();
		for(int i=1;i<=list.size();i++){
			map.put(list.get(i-1).getId(), list.get(i-1).getName());
		}
		return map;
	}
	/******************组操作方法****************************/
	//获取所有小组
	@SuppressWarnings("unchecked")
	public List<Group> getAllGroups() {
		String hql="from Group";
		Query query=getSession().createQuery(hql);
		List<Group> list=query.list();
		return list;
	}
	/**
	 *  编辑小组名称
	 */
	public boolean editGroupName(int id,String groupName,int departId) {
		Session session=getSession();
		Group group=(Group)session .get(Group.class, id);
		group.setName(groupName);
		group.setDepartId(departId);
		return true;
	}
	/**
	 * 创建一个小组
	 */
	public void createGroup(String groupName,int departId) {
		Group group=new Group();
		group.setName(groupName);
		group.setDepartId(departId);
		group.setCreateDate(new Date());
		getSession().save(group);
	}
	/**
	 * 获取小组分页对象
	 */
	@SuppressWarnings("unchecked")
	public Pages<Group> getGroupPages(Role role,Department depart,int start, int len) {
		Pages<Group> groupPage=new Pages<Group>();
		groupPage.setCurrentPage(start);
		groupPage.setPageSize(len);
		
		List<Group> list=null;
		String hql="";
		Object[] param=null;
		if(role.getLevel()==Constants.PermissionLevel.MANAGE){
			hql="from Group o";
		}else if(role.getLevel()==Constants.PermissionLevel.ZHUGUAN){
			hql="from Group o where o.departId="+depart.getId();
			param=new Object[]{depart.getId()};
			LOG.debug("PARAM = "+param);
		}
		
		Query query=getSession().createQuery("select o "+hql);
		if(start<0&&len<0){
			list=query.list();
		}else{
			query.setFirstResult(getFirstResult(start,len));
			query.setMaxResults(len);
			list=query.list();
		}
		
		query=getSession().createQuery("select count(o) "+hql);
		int total=((Long)query.uniqueResult()).intValue();
		groupPage.setTotalCount(total);
		int totalPage=(total+len-1)/len;
		groupPage.setTotalPage(totalPage);
		int lastPage=(totalPage==0?1:totalPage);
		groupPage.setLastPage(lastPage);
		
		groupPage.setResultList(list);
		return groupPage;
	}
	private int getFirstResult(int index, int length) {
		int r=(index-1)*length;
		return r<=0?0:r;
	}
	@SuppressWarnings("unchecked")
	public Group searchGoupById(int groupId) {
		String hql="select o from Group o where o.id=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0, groupId);
		List<Group> list=query.list();
		
		return list.size()>0?list.get(0):null;
	}
	public void deleteGroup(int departId) {
	//	String hql="delete o from Group o where o.id=?";
		Group group=(Group) getSession().get(Group.class, departId);
		getSession().delete(group);
		//getSession().createQuery(hql).setParameter(0, departId).e
	}
	/**
	 * 查询CallCenter派单小组
	 * @throws WoCenterException 
	 */
	public Map<Integer, String> getCallCenterGroups(String accName) throws WoCenterException {
		Map<Integer, String> groupMap=null;
		try{
		groupMap=new HashMap<Integer, String>();
		Staff staf=getStaffByAccName(accName);
		LOG.debug("assign staff="+staf.getNickName());
		int level=staf.getRole().getLevel();
		int roleId=staf.getRole().getId();
		String hql="";
		Query query=null;
		if(level>1){
			hql="from Group";
			query=getSession().createQuery(hql);
		}else if(roleId==GENARA_ROLE){
			hql="select o from Group o where o.departId = ?  and o.departId!=1";
			query=getSession().createQuery(hql).setParameter(0,CTDEPARTMENT);
		}else if(roleId==ZUZHANG_ROLE || roleId==GM_ROLE){
			hql="select o from Group o where o.departId !=?  and o.departId!=1";
			query=getSession().createQuery(hql).setParameter(0,CTDEPARTMENT);
		}
		
		List<Group> groupList=query.list();
		
		for(Group group:groupList){
			groupMap.put(group.getId(), group.getName());
		}
		if(roleId==ZUZHANG_ROLE||roleId==GENARA_ROLE){
			groupMap.put(staf.getGroup().getId(), staf.getGroup().getName());
		}
	}catch(Exception e){
		e.printStackTrace();
		throw new WoCenterException(StateList.DATABASE_ERR,PromptMessage.DATABASE_ERROR);
	}
		return groupMap;
	}
	/**
	 * 查询CallCenter派单员工
	 * @throws WoCenterException 
	 */
	public Map<Integer, String> getCallCenterStaffs(int gId) throws WoCenterException{
		Map<Integer, String> map=new HashMap<Integer, String>();
		try{
		Group group=(Group) getSession().get(Group.class, gId);
		
		String hql="select o from Staff o where o.group.id=? and o.isdelete=false" ;
		Query query=getSession().createQuery(hql);
		//System.out.println(gId);
		List<Staff> list=query.setParameter(0, gId).list();
		//System.out.println(list.size());
		if(list.size()>0){
			if(group.getDepartId()==2){//if 派单到客服
				
				for(Staff staff:list){
					if(staff.getRole().getId()==5||staff.getRole().getId()==3){
						map.put(Integer.parseInt(String.valueOf(staff.getId())), staff.getNickName());
					}
				}
			}else{
				for(Staff staff:list){
					map.put(Integer.parseInt(String.valueOf(staff.getId())), staff.getNickName());
				}
			}
			if(map.size()<=0){
				map=null;
			}
		}else{
			map=null;
		}
		}catch(Exception e){
			throw new WoCenterException(StateList.DATABASE_ERR,PromptMessage.DATABASE_ERROR);
		}
		return map;
	}
	/**
	 * 获取小组所有员工
	 * @param groupId
	 * @return
	 */
	public List<Long> getStaffOfGroup(int groupId){
		List<Long> idList=new ArrayList<Long>();
		String hql="select o from Staff o where o.group.id = ?";
		Query q=getSession().createQuery(hql);
		q.setParameter(0, groupId);
		List<Staff> list=q.list();
		for(Staff staf:list){
			idList.add(staf.getId());
		}
		return idList;
	}
}
