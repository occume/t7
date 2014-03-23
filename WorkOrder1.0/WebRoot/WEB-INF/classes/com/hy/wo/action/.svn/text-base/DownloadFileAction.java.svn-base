package com.hy.wo.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.hy.wo.util.Constants;
import com.hy.wo.util.Constants.NodeList;
import com.hy.wo.util.Constants.PromptMessage;
import com.hy.wo.util.Constants.StateList;
/**
 * 文件下载Action类
 */
public class DownloadFileAction extends BaseAction {	
	public InputStream getInputStream() throws Exception{
		//String path=inputPath+fileName;
		//System.out.println(path);
		return ServletActionContext.getServletContext().getResourceAsStream(fileName);
	}
	public InputStream getXmlStream() throws Exception{
		Document document=DocumentHelper.createDocument();
		document.setXMLEncoding( Constants.DEFAULT_CHARSET );
		//root
		Element root=DocumentHelper.createElement("hy");
		//childNode 
		document.setRootElement(root);
		
		//String outStr=DepictXmlManager.getOutputStr(DepictXmlManager.organizeBasicXML("00", "数据为空"));
		Element state=root.addElement( NodeList.STATE );
		state.setText( StateList.DATA_NOT_FOUND );
		Element stateDesc=root.addElement( NodeList.STATE_DESC );
		stateDesc.setText( PromptMessage.DATA_NOT_FOUND );
		
		StringReader strReader=new StringReader(document.asXML());

		         // 再由String转成InputStream
		InputStream inputStream = new ByteArrayInputStream(document.asXML().getBytes( Constants.DEFAULT_CHARSET ));
		
		return inputStream;
	}
	/**
	 * 提供转码后供下载用的文件名
	 * @param filename
	 * @return
	 */
	public String getDownloadFileName(){
		String downFileName = fileName;
		try {
			downFileName = new String(downFileName.getBytes(), Constants.DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			LOGGER.info("文件下载action异常: " + e.getMessage());
		}
		return downFileName;
	}
	
	public String execute()throws Exception{
		//System.out.println(fileName);
		return SUCCESS;
	}
	public String returnXml(){
		//System.out.println("An error happened!");
		return SUCCESS;
	}
	
	
	
	
	
	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	private static final long serialVersionUID = -1726048989368881701L;
	private static final Logger LOGGER = Logger.getLogger(DownloadFileAction.class);
	private String fileName;
	private String inputPath;
}
