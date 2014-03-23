package com.hy.wo.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.hy.wo.exception.PasswordReqErrorException;
import com.hy.wo.util.Constants;
import com.hy.wo.util.MyUtil;
import com.hy.wo.util.Constants.PromptMessage;
import com.hy.wo.util.Constants.StateList;
import com.hy.wo.util.Constants.States;
import com.hy.wo.util.Constants.ViewNode;

@Controller @Scope("prototype")
public class Action4Password extends BaseAction{

	private static final long serialVersionUID = -2002772844202406633L;
	private static final Logger LOG = Logger.getLogger(Action4Password.class);//定义日志
	
		/**
		 * 获取工单信息
		 */
		public void getWorkOrderInfoOfUser() {
			//LOG.debug("request from passport");
				try {
					checkParam(request);
					//LOG.debug("params is correct");
					makeDealInfo(request.getParameter("username"));
				} catch (PasswordReqErrorException e) {
					String m=e.getMessage();
					
					if(m.equals(NAMENULL)){//用户名为空
						makeErrorInfo( StateList.USER_NOTFOUND_ERR );
					}else if(m.equals(ILLEGALCLIENT)){//不合法的客户端
						makeErrorInfo( StateList.Illegal_OPERATION_ERROR);
					}else if(m.equals(ILLEGALTYPE)){//不合法的请求信息
						makeErrorInfo( StateList.PASSWORD_ERR);
					}
				}
		}
	
		private void checkParam(HttpServletRequest reqquest) throws PasswordReqErrorException{
				String accName=reqquest.getParameter("username");
				String id = reqquest.getParameter(Constants.ParamName.ID);
		        String s = reqquest.getParameter(Constants.ParamName.S);
		        String r = reqquest.getParameter("req");
		        String type = reqquest.getParameter("type");
		       
		        
		        if(MyUtil.isBlankOrNull(accName)){
		        	throw new PasswordReqErrorException(NAMENULL);//用户名为空
		        }
		       
		        if(MyUtil.isBlankOrNull(id)||MyUtil.isBlankOrNull(s)||!MyUtil.isValidateClient(s,accName,id)){
		        	throw new PasswordReqErrorException(ILLEGALCLIENT);//不合法的客户
		        }
		        
		        if(MyUtil.isBlankOrNull(r)||!r.equals(REQ)){
		        	throw new PasswordReqErrorException(ILLEGALREQ);//不合法的请求
		        }
		        if(MyUtil.isBlankOrNull(type)||!type.equals(CURRENTSTATE)){
		        	throw new PasswordReqErrorException(ILLEGALTYPE);//不合法的请求信息
		        }
		}

		public void makeDealInfo(String accName){
			
			int alldata = woService.getAllWorkOrderCountOfUserByStatus(accName, States.DEALED);
			int dealingdata = woService.getAllWorkOrderCountOfUserByStatus(accName, States.DEALING);
			int responseddata = woService.getAllWorkOrderCountOfUserByStatus(accName, States.RESPONSED);
			int undealdata = woService.getAllWorkOrderCountOfUserByStatus(accName, States.UNDEAL);
			int lackinfodata = woService.getAllWorkOrderCountOfUserByStatus(accName, States.LACKINFO);
			
			Document document=DocumentHelper.createDocument();
			//root
			Element root=DocumentHelper.createElement( ViewNode.WO_DATA );
			//childNode 
			document.setRootElement(root);
			Element state=root.addElement( ViewNode.STATE );
			state.setText( StateList.SUCCESS );
			Element stateDesc=root.addElement( ViewNode.STATE_DESC );
			stateDesc.setText( PromptMessage.SUCCESS );
			
			Element count=root.addElement( ViewNode.COUNT );
			//all workOrder count
			Element allCount=count.addElement( ViewNode.ALL_COUNT );
			allCount.setText(String.valueOf(alldata));
			//dealing workOrder count
			Element dealingCount=count.addElement( ViewNode.DEALING_COUNT );
			dealingCount.setText(String.valueOf(dealingdata));
			//responsed workOrder count
			Element responsedCount=count.addElement( ViewNode.RESPONSED_COUNT );
			responsedCount.setText(String.valueOf(responseddata));
			//undeal workOrder count
			Element undealCount=count.addElement( ViewNode.UNDEAL_COUNT );
			undealCount.setText(String.valueOf(undealdata));
			//lackInfo workOrder count
			Element lackInfoCount=count.addElement( ViewNode.LACKINFO_COUNT );
			lackInfoCount.setText(String.valueOf(lackinfodata));
			//LOG.debug("element is constructed");
			sendInfo(document);
		}
		public void makeErrorInfo(String reason) {
			Document document=DocumentHelper.createDocument();
			//root
			Element root=DocumentHelper.createElement( ViewNode.WO_DATA );
			//childNode 
			document.setRootElement(root);
			Element state=root.addElement( ViewNode.STATE );
			state.setText(reason);
			Element stateDesc=root.addElement( ViewNode.STATE_DESC );
			String desc="";
			if(reason.equals( StateList.USER_NOTFOUND_ERR )){
				desc = PromptMessage.USERNAME_IS_NULL;
			}else if(reason.equals( StateList.Illegal_OPERATION_ERROR )){
				desc = PromptMessage.Illegal_OPERATION_ERROR;
			}else if(reason.equals( StateList.PARAM_ERR )){
				desc = PromptMessage.PARAM_IS_NULL;
			}
			stateDesc.setText(desc);
			sendInfo(document);
		}
		//发送响应请求
		public void sendInfo(Document document){
			//LOG.debug("send nomal info");
			response.setContentType( Constants.DEFAULT_CONTENT_TYPE );
		    response.setCharacterEncoding( Constants.DEFAULT_CHARSET );
		    PrintWriter out=null;
		    XMLWriter xmlWriter=null;
			try {
				out = response.getWriter();
				OutputFormat outPut=new OutputFormat();
			    outPut.setEncoding( Constants.DEFAULT_CHARSET );
			    xmlWriter=new XMLWriter(out, outPut);
			    xmlWriter.write(document);
			    xmlWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
			}
		    
		    
		}
		
		private static final String REQ="getCurrentDealInfo";
		private static final String CURRENTSTATE="currentDealInfo";
		
		private static final String NAMENULL="nameIsNull";
		private static final String ILLEGALCLIENT="illegalClient";
		private static final String ILLEGALREQ="illegalReq";
		private static final String ILLEGALTYPE="illegalType";
}
