package com.hy.wo.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.hy.wo.dao.BaseDao;
import com.hy.wo.exception.WoCenterException;
import com.hy.wo.po.Department;
import com.hy.wo.po.Group;
import com.hy.wo.po.Role;
import com.hy.wo.po.Servers;
import com.hy.wo.po.Staff;
import com.hy.wo.util.Pages;

public interface StaffService extends BaseDao<Staff> {
	/**
	 * 客服登录
	 */
	public Staff logon(Staff staff);
	/**
	 * 查看部门员工信息
	 * @param id
	 * @return
	 */
	public Pages<Staff> findStaffByDepartmentId(int id,int start,int len);
	/**
	 * 获取职位，部门，小组信息
	 * @param clazz
	 * @return
	 */
	public Map<Integer, String> getRoleMap();
	public Map<Integer, String> getDepartmentMap();
	public Map<Integer, String> getGroupMap();
	/**
	 * 添加员工
	 * @param staff
	 * @return
	 */
	public Staff addStaff(Staff staff);
	public Staff addStaffToDepartment(Staff staff,int departmentId);
	/**
	 * 修改员工信息
	 * @param staff
	 */
	public Staff editStaffInfo(Staff staff);
	/**
	 * 删除员工信息
	 */
	public Staff deleteStaffOfDepartment(Staff staff);
	public Staff deleteStaff(Staff staff);
	/**
	 * 根据工号查找员工
	 * @param accName
	 * @return
	 */
	public Staff getStaffByAccName(String accName);
	public Pages<Staff> findStaffByAccName(String accName);
	/**
	 * 根据部门名称查找员工
	 * @param departname
	 * @return
	 */
	public Pages<Staff> findStaffByDepartmentName(String departname,int start,int len);
	public Pages<Staff> searchStaff(int start,int len,Staff staff,Role role);
	
	public Pages<Servers> getAllServers(int start ,int len);
	/**
	 * 获取所有小组
	 * @param start
	 * @param len
	 * @return
	 */
	public List<Group> getAllGroups();
	/******************小组操作*********************/
	public Pages<Group> getGroupPages(Role role,Department depart,int start ,int len);
	public boolean editGroupName(int id,String groupName,int departId);//编辑
	public void createGroup(String groupName,int departId);//创建
	public void deleteGroup(int departId);//删除
	public Group searchGoupById(int groupId);
	public Map<Integer, String> getCallCenterGroups(String accName) throws WoCenterException;
	public Map<Integer, String> getCallCenterStaffs(int gId) throws WoCenterException;
	public Staff logon_wo(Staff staff);
	public List<Long> getStaffOfGroup(int groupId);
}
