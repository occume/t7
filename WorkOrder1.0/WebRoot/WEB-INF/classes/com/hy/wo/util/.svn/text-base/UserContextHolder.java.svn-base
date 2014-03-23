package com.hy.wo.util;

public class UserContextHolder {
	private static final ThreadLocal contextHolder=new ThreadLocal();
	public static void setUserType(String userType){
		contextHolder.set(userType);
	}
	public static String getUserType(){
		return (String)contextHolder.get();
	}
	public static void clearUserType(){
		contextHolder.remove();
	}
}
