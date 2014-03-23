package com.hy.wo.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hy.wo.util.CheckCodeUtils;
import com.opensymphony.xwork2.ActionContext;
import com.you9.base.Globe;
@Controller @Scope("prototype")
public class VerificationCodeAction extends BaseAction {
	private static final Logger LOGGER = Logger.getLogger(VerificationCodeAction.class);
	private static final long serialVersionUID = -4533733918564942165L;
	
	public String makeCheckCode(){
		try {
			LOGGER.debug("====makeCheckCode===");
			ActionContext ctx = ActionContext.getContext();//获取ActionContext实例
			HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);   
			setPageNotCache(response);// 设置页面不缓存			
			String codeValue = CheckCodeUtils.makeCheckCode(response);
			LOGGER.debug("codeValue=" + codeValue);
			// 生成验证码并显示			
			String checkCodeName = Globe.getProperty(CheckCodeUtils.GlobalNodeName.SESSION_CHECKCODE_KEY);//session里验证码名			
			ctx.getSession().remove(checkCodeName);
			ctx.getSession().put(checkCodeName, codeValue.toLowerCase());
			LOGGER.debug("写入SESSION的验证码："+ctx.getSession().get(checkCodeName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 设置页面不缓存
	 * 
	 * @param response
	 */
	private static void setPageNotCache(HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	}
}
