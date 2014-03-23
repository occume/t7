package com.hy.wo.service;

import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.jdom.Element;

import com.hy.wo.po.User;

public interface PrivateCenterService {

	/**
	 * 请求PrivateCenter取得用户的扩展信息
	 * @param username
	 * @param ip
	 */
	public abstract User getUserInfo(String username, String ip)
			throws Exception;

	/**
	 * 专为查询用户信息的准备
	 * @param targetURL String 远程链接的目标url地址
	 * @param data NameValuePair[] httpclient的post方法的参数
	 * @throws Exception
	 * @return Element 包含远程服务器返回的xml的信息
	 */
	public abstract Element doUserInfoPost(String targetURL,
			NameValuePair[] data) throws Exception;

	/**
	 * 以httpclient的post的方式向远程服务请求服务，并把远程服务器返回的xml信息包装在element中返回
	 * @param data NameValuePair[] httpclient的post方法的参数
	 * @param targetURL 远程链接的目标url地址
	 * @throws Exception
	 * @return Element 包含远程服务器返回的xml的信息
	 */
	public abstract Element privateuserPost(String targetURL,
			NameValuePair[] data) throws Exception;
	 /**
	  * 更新个人资料
	  * @param map 用户信息
	  * 
	  */
	 public String UpdateUserInfo(Map<String, String> map);
}