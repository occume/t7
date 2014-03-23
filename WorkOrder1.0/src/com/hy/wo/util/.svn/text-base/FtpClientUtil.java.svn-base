package com.hy.wo.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.hy.wo.action.WorkOrderAction;
import com.hy.wo.util.CheckCodeUtils.GlobalNodeName;
import com.you9.base.Globe;

import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;

public class FtpClientUtil { 
	FtpClient ftpClient; 
	private String server=Globe.getProperty(GlobalNodeName.FTP_SERVER);
	private int port= Integer.parseInt(Globe.getProperty(GlobalNodeName.FTP_PORT)); 
	private String userName=Globe.getProperty(GlobalNodeName.FTP_USERNAME); 
	private String userPassword=Globe.getProperty(GlobalNodeName.FTP_USERPASSWORD); 
	private static final Logger Logs = Logger.getLogger(FtpClientUtil.class);
	
//	public static final String ftpServer=Globe.getProperty(GlobalNodeName.FTP_SERVER);
//	  public static final int ftpPort= Integer.parseInt(Globe.getProperty(GlobalNodeName.FTP_PORT));
//	  public static final String ftpUsername=Globe.getProperty(GlobalNodeName.FTP_USERNAME);
//	  public static final String ftpPassword=Globe.getProperty(GlobalNodeName.FTP_USERPASSWORD);
//	  public static final String ftpDirectory=Globe.getProperty(GlobalNodeName.FTP_DIRECTORY);
//	  public static final String ftpDomain=Globe.getProperty(GlobalNodeName.FTP_DOMAIN);
	public FtpClientUtil(String server,int port,String userName,String userPassword)
	 { 
	  this.server=server; 
	  this.port=port; 
	  this.userName=userName; 
	  this.userPassword=userPassword; 
	} 
	private static final FtpClientUtil ftpcu = new FtpClientUtil();
	
	private FtpClientUtil()
	{
//		  this.server=ftpServer; 
//		  this.port=ftpPort; 
//		  this.userName=ftpUsername; 
//		  this.userPassword=ftpPassword; 
		  
		  System.out.println("*****************ftpServer:"+server+"*********");
		  System.out.println("*****************ftpPort:"+port+"*********");
		  System.out.println("*****************ftpUsername:"+userName+"*********");
		  System.out.println("*****************ftpPassword:"+userPassword+"*********");
		  
	}
	
	
	public static FtpClientUtil getInstance(){
		return ftpcu;
	}
	/** 
	  * 链接到服务器 
	  * @return 
	  */ 
	public boolean open() 
	{ 
		
		System.out.println("*****************before open********");
	  if(ftpClient!=null&&ftpClient.serverIsOpen()) 
	   return true; 
	  try 
	  { 
	      ftpClient= new FtpClient();
	      System.out.println("*****************openServer("+server+","+port+")********");
	      ftpClient.openServer(server,port); 
	      System.out.println("*****************login("+userName+","+userPassword+")********");
	      ftpClient.login(userName, userPassword); 
	      System.out.println("*****************FTP已经登录********");
	      ftpClient.binary(); 
	      System.out.println("*****************FTP打开********");
	      
	      return true; 
	  } 
	  catch(Exception e) 
	  { 
	   e.printStackTrace(); 
	   ftpClient=null; 
	   return false; 
	  } 
	} 

	public boolean cd(String dir){ 
	  boolean f = false; 
	  try { 
	   ftpClient.cd(dir); 
	  } catch (IOException e) { 
	   Logs.error(e.toString()); 
	   return f; 
	  } 
	  return true; 
	} 

	/** 
	  * 上传文件到FTP服务器 
	  * @param localPathAndFileName 本地文件目录和文件名 
	  * @param ftpFileName  上传后的文件名 
	  * @param ftpDirectory FTP目录如:/path1/pathb2/,如果目录不存在回自动创建目录 
	  * @throws Exception 
	  */ 
	public boolean upload(String localDirectoryAndFileName,String ftpFileName,String ftpDirectory)throws Exception {
		
		System.out.println("*****************ftpfilename:"+ftpFileName+"*********");
		System.out.println("*****************ftpDirectory:"+ftpDirectory+"*********");
	   if(!open()) 
	   return false; 
	  FileInputStream is=null; 
	  TelnetOutputStream os=null; 
	  try 
	  { 
	   char ch = ' '; 
	   if (ftpDirectory.length() > 0) 
	    ch = ftpDirectory.charAt(ftpDirectory.length() - 1); 
	   for (; ch == '/' || ch == '\\'; ch = ftpDirectory.charAt(ftpDirectory.length() - 1))
	     ftpDirectory = ftpDirectory.substring(0, ftpDirectory.length() - 1); 

	   int slashIndex = ftpDirectory.indexOf(47); 
	   int backslashIndex = ftpDirectory.indexOf(92); 
	   int index = slashIndex; 
	   String dirall = ftpDirectory; 
	   if (backslashIndex != -1 && (index == -1 || index > backslashIndex)) 
	    index = backslashIndex; 
	   String directory = ""; 
	   while (index != -1) { 
	    if (index > 0) { 
	     String dir = dirall.substring(0, index); 
	     directory = directory + "/" + dir; 
	     ftpClient.sendServer("XMKD " + directory + "\r\n"); 
	     ftpClient.sendServer("quote PASV"); //修改传输模式
	     ftpClient.readServerResponse(); 
	    } 
	    dirall = dirall.substring(index + 1); 
	    slashIndex = dirall.indexOf(47); 
	    backslashIndex = dirall.indexOf(92); 
	    index = slashIndex; 
	    if (backslashIndex != -1 && (index == -1 || index > backslashIndex)) 
	     index = backslashIndex; 
	   } 
	   
	   System.out.println("*****************XMKD   ftpDirectory:"+ftpDirectory+"*********");
	   ftpClient.sendServer("XMKD " + ftpDirectory + "\r\n"); 
	   ftpClient.readServerResponse(); 

	   os = ftpClient.put(ftpDirectory + "/"+ ftpFileName); 
	   File file_in = new File(localDirectoryAndFileName); 
	   is = new FileInputStream(file_in); 
	   byte bytes[] = new byte[1024]; 
	   int i; 
	   while ((i = is.read(bytes)) != -1) 
	    os.write(bytes, 0, i); 
	   //清理垃圾 
	   
	   System.out.println("*****************uploadcomplete");
	   return true; 
	  } 
	  catch(Exception e) 
	  { 
	   e.printStackTrace(); 
	   return false; 
	  } 
	  finally 
	  { 
	   if (is != null) 
	      is.close(); 
	   if (os != null) 
	      os.close(); 
	  } 
	} 
	
	/**
	 * 上传文件
	 * @param localDirectoryAndFileName
	 * @return
	 * @throws Exception
	 */
	public String upload(String localDirectoryAndFileName,String vPath)throws Exception {
		File tempFile =new File(localDirectoryAndFileName.trim());  
		System.out.println("***********localDirectoryAndFileName:"+localDirectoryAndFileName+"**************");
		String tempFileName=tempFile.getName();
		boolean re= upload(localDirectoryAndFileName,tempFileName,ftpDirectory+vPath);
		
		if (re)
		{	
			//删除本地文件
			try{
				tempFile.delete();
			}catch(Exception e)
			{
				System.out.println("*************tempFileName:"+vPath+"/"+tempFileName+"*************");
			}
			return ftpDomain+vPath+"/"+tempFileName;
		}
		else
			return "";
	}
	
	
	/** 
	  * 从FTP服务器上下载文件并返回下载文件长度 
	  * @param ftpDirectoryAndFileName 
	  * @param localDirectoryAndFileName 
	  * @return 
	  * @throws Exception 
	  */ 
	public long download(String ftpDirectoryAndFileName,String localDirectoryAndFileName)throws Exception
	 { 
	  long result = 0; 
	  if(!open()) 
	   return result; 
	  TelnetInputStream is = null; 
	  FileOutputStream os = null; 
	  try  
	  { 
	        is = ftpClient.get(ftpDirectoryAndFileName);        
	        java.io.File outfile = new java.io.File(localDirectoryAndFileName); 
	        os = new FileOutputStream(outfile); 
	        byte[] bytes = new byte[1024]; 
	        int c; 
	        while ((c = is.read(bytes)) != -1) 
	        { 
	            os.write(bytes, 0, c); 
	            result = result + c; 
	        } 
	     } 
	  catch (Exception e)  
	  { 
	         throw e; 
	  } 
	  finally 
	  { 
	      if (is != null) 
	        is.close(); 
	      if (os != null) 
	        os.close(); 
	      
	   } 
	      return result; 
	} 
	/** 
	  * 返回FTP目录下的文件列表 
	  * @param ftpDirectory 
	  * @return 
	  */ 
	  public List<String> getFileNameList(String ftpDirectory) 
	  { 
	     List<String> list = new ArrayList<String>(); 
	     if(!open()) 
	   return list; 
	     try  
	     { 
	        DataInputStream dis = new  DataInputStream(ftpClient.nameList(ftpDirectory));
	         String filename = ""; 
	        while((filename=dis.readLine())!=null)   
	        { 
	          list.add(filename);         
	        }   
	     } catch (Exception e)  
	     { 
	        e.printStackTrace(); 
	     } 
	     return list; 
	  } 
	  /** 
	   * 删除FTP上的文件 
	   * @param ftpDirAndFileName 
	   */ 
	  public boolean deleteFile(String ftpDirAndFileName) 
	  { 
	   if(!open()) 
	   return false; 
	   ftpClient.sendServer("DELE "+ftpDirAndFileName+"\r\n"); 
	   return true; 
	  } 
	  /** 
	   * 删除FTP目录 
	   * @param ftpDirectory 
	   */ 
	  public boolean deleteDirectory(String ftpDirectory) 
	  { 
	   if(!open()) 
	   return false; 
	   ftpClient.sendServer("XRMD "+ftpDirectory+"\r\n"); 
	   return true; 
	  } 
	  /** 
	   * 关闭链接 
	   */ 
	  public void close() 
	  { 
	   try 
	   { 
	       if(ftpClient!=null&&ftpClient.serverIsOpen()) 
	        ftpClient.closeServer(); 
	   }catch(Exception e) 
	   { 
	    
	   } 
	  } 
	  
	  
	  
	  public static final String ftpServer=Globe.getProperty(GlobalNodeName.FTP_SERVER);
	  public static final int ftpPort= Integer.parseInt(Globe.getProperty(GlobalNodeName.FTP_PORT));
	  public static final String ftpUsername=Globe.getProperty(GlobalNodeName.FTP_USERNAME);
	  public static final String ftpPassword=Globe.getProperty(GlobalNodeName.FTP_USERPASSWORD);
	  public static final String ftpDirectory=Globe.getProperty(GlobalNodeName.FTP_DIRECTORY);
	  public static final String ftpDomain=Globe.getProperty(GlobalNodeName.FTP_DOMAIN);
	  
	  public class GlobalNodeName{
		  public static final String FTP_SERVER="ftp/server";
		  public static final String FTP_PORT="ftp/port";
		  public static final String FTP_USERNAME="ftp/userName";
		  public static final String FTP_USERPASSWORD="ftp/userPassword";
		  public static final String FTP_DIRECTORY="ftp/ftpDirectory";
		  public static final String FTP_DOMAIN="ftp/ftpdomain";
	  }
	  
	  
	} 

