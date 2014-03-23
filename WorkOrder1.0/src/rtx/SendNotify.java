package rtx;
import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.hy.wo.util.Constants.RTX;
import com.you9.base.Globe;
  
public class SendNotify
 {
	/**
	 * 派单提示
	 * @param receivers
	 * @param id
	 * @throws IOException
	 */
    public static void notify( String receivers, String id ) throws IOException {
    	
    	String title = RTX.MSG_TITLE;
    	
    	NameValuePair[] data=new NameValuePair[]{
    			new NameValuePair(RTX.TITLE,title),
    			new NameValuePair(RTX.MSG,id),
    			new NameValuePair(RTX.RECEIVER,receivers)
    	};
    	
    	sendNotify(RTX.SERVER_URL, data);
    	
    }
    /**
     * 处理回复提示
     * @param receivers
     * @param id
     * @throws IOException
     */
    public static void procesNotify( String receivers, String id ) throws IOException {
    	
    	String title = RTX.PROCESS_TITLE;
    	String msg = RTX.PROCESS_TITLE_PRE_URL + id + RTX.PROCESS_TITLE_SUF_URL;
    	
    	NameValuePair[] data=new NameValuePair[]{
    			new NameValuePair(RTX.TITLE,title),
    			new NameValuePair(RTX.MSG,id),
    			new NameValuePair(RTX.RECEIVER,receivers)
    	};
    	
    	sendNotify(RTX.SERVER_URL, data);
    	
    }
    /**
     * 报警提示
     * @throws IOException
     */
    public static void alert() throws IOException {
    	
    	String title = RTX.PROCESS_TITLE;
    	String msg = RTX.PROCESS_TITLE_PRE_URL + RTX.PROCESS_TITLE_SUF_URL;
    	
    	NameValuePair[] data = new NameValuePair[]{
    			new NameValuePair(RTX.TITLE, title),
    			new NameValuePair(RTX.MSG, msg),
    			new NameValuePair(RTX.RECEIVER, "jindong"),
    			new NameValuePair(RTX.DELAY, "3000")
    	};
    	
    	multiSendNotify(RTX.SERVER_URL_TEST, data, 1000);
    	
    }
    private static String notifySwitch = "open";
    public static void setNotifySwitch(String data){
    	notifySwitch = data;
    }
    /**
     * 重复发送IM消息
     * @param url
     * @param data
     */
    private static void multiSendNotify(String url, NameValuePair[] data, int times){
    	PostMethod postMethod = null;
    	
    	try {
    		postMethod = new PostMethod(url);
    		//postMethod.
    		postMethod.addRequestHeader( "Connection", "close");
    		postMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gb2312");
    		postMethod.setRequestBody(data);
    		HttpClient httpclient = new HttpClient();
    		int result = 0;
    		for(int i = 0; i < times; i++){
    			if(notifySwitch.equals("open")){
	    			result = httpclient.executeMethod(postMethod);
	    			Thread.sleep(1000);
    			}else{
    				notifySwitch = "open";
    				break;
    			}
    		}
    		//int result = 
    		if (result == 200) {
    			//String re = postMethod.getResponseBodyAsString();
    		}
    		else {
    			
    			//System.out.println("at Date: "+new Date());
    			
    		}
    	} catch (HttpException e) {
		}  catch (Exception e) {
		}
    	finally {
    		postMethod.releaseConnection();
    	}
    	
    }
    /**
     * 发送IM消息
     * @param url
     * @param data
     */
    private static void sendNotify(String url, NameValuePair[] data){
    	PostMethod postMethod = null;
    	
    	try {
    		postMethod = new PostMethod(url);
    		//postMethod.
    		postMethod.addRequestHeader( "Connection", "close");
    		postMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gb2312");
    		postMethod.setRequestBody(data);
    		HttpClient httpclient = new HttpClient();
    		int result = httpclient.executeMethod(postMethod);
    		if (result == 200) {
    			//String re = postMethod.getResponseBodyAsString();
    		}
    		else {
    			
    			//System.out.println("at Date: "+new Date());
    			
    		}
    	} catch (HttpException e) {
		}  catch (Exception e) {
		}
    	finally {
    		postMethod.releaseConnection();
    	}
    	
    }
    public static void main(String...strings) throws IOException{
    	String name = Globe.getProperty("charset");
    	System.out.println(name);
    }
}