package com.hy.wo.action;

import java.io.IOException;

import org.apache.commons.httpclient.NameValuePair;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import rtx.SendNotify;

import com.hy.wo.servlet.DWRHelper;
/**
 * IM
 */
@Controller @Scope("prototype")
public class ImsgAction extends BaseAction {	
	
	public void sendIM(){
		//System.out.println("im");
		//System.out.println(message);
		DWRHelper.sendIM(session.getServletContext(), new NameValuePair(username,message));
	}
	
	public void alert(){
		try {
			SendNotify.alert();
		} catch (IOException e) {
		}
	}
	public void closeAlert(){
		SendNotify.setNotifySwitch("close");
	}
	
	private String message;
	private String username;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
