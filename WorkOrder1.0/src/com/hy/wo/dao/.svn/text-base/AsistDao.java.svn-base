package com.hy.wo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;
import com.hy.wo.exception.WoCenterException;
import com.hy.wo.po.ServerInfo;
import com.hy.wo.util.Constants;
import com.hy.wo.util.Constants.PromptMessage;
import com.hy.wo.util.Constants.SQL;
import com.hy.wo.util.Constants.SystemConst;


public class AsistDao {
	private static final Logger LOGGER = Logger.getLogger(AsistDao.class);//定义日志
	 /**
	    * 获取游戏服务器信息
	    * @return
	    * @throws SQLException
	    * @throws Exception
	    */
	   public static List<ServerInfo> getGameServers() throws WoCenterException
	   {
		   LOGGER.debug("AsistDao ---> A request from CallCenter get Servers");
		    Connection con = null;
		   	PreparedStatement stm = null;
		   	ResultSet res = null;
		   	String sql = null;
		   	List<ServerInfo> list = new ArrayList<ServerInfo>();
		   	try {
					con = DBManager.getConnection("oracle");
				//	 LOGGER.debug(con);
					sql = SQL.GET_GAME_SERVER;
					stm = con.prepareStatement(sql);
				//	 LOGGER.debug(SystemConst.DEFAULT_WAIT_SEC);
				//	stm.setQueryTimeout(SystemConst.DEFAULT_WAIT_SEC);
					res = stm.executeQuery();
					LOGGER.debug(res);
		            while (res.next()) {
		            	list.add(new ServerInfo(res));
		            }
		   	} catch (SQLException e){
					LOGGER.error("查询server_info表失败:SQL["+sql+"]"+e.getMessage());
					throw new WoCenterException(Constants.StateList.DATABASE_ERR, PromptMessage.DATABASE_ERROR);
			} catch (Exception e){
				LOGGER.error("查询server_info表失败：数据源为空.[错误信息]"+e.getMessage());
				throw new WoCenterException(Constants.StateList.UNKNOWN_ERROR, PromptMessage.SYSTEM_BUSY);
			}finally{
				DBManager.closeConnection(con, stm, res);
			}
			return list;
	   }
	   /**
	    * 获取游戏服务器URL
	    * @param gameid
	    * @return
	    * @throws ChargingCenterException
	    */
	   public static NameValuePair getGameServerUrl(final int gameid) throws WoCenterException
	   {
		   Connection con = null;
		   PreparedStatement stm = null;
		   ResultSet res = null;
		   String sql = null;
		   NameValuePair url = new NameValuePair();//游戏服务器链接
		   try {
			   con = DBManager.getConnection("oracle");
				sql = SQL.GET_GAME_SERVER_URL;
				stm = con.prepareStatement(sql);
			//	stm.setQueryTimeout(5);
				stm.setInt(1, gameid);
				res = stm.executeQuery();
				LOGGER.info("res.next?：[" + res+":::"+"]");
				if(res.next()){
					url.setName(res.getString(Constants.TableFieldName.SERVER_URL));
					url.setValue(res.getString(Constants.TableFieldName.SERVER_NAME));
				}else{//服务器不存在或已关闭
					LOGGER.info("ResultSet为空：[" + gameid+"]");
					throw new WoCenterException(Constants.StateList.DATABASE_ERR,PromptMessage.DATABASE_ERROR);
				}
		   } catch (SQLException e){
			   LOGGER.info("查找的游戏服务器已关闭或不存在：[" + gameid+"]");
				throw new WoCenterException(Constants.StateList.DATABASE_ERR, PromptMessage.DATABASE_ERROR);
		  } catch (Exception e){
			  LOGGER.info("查找的游戏服务器已关闭或不存在：[" + gameid+"]");
				throw new WoCenterException(Constants.StateList.DATABASE_ERR, PromptMessage.DATABASE_ERROR);
		  }finally{
			   DBManager.closeConnection(con, stm, res);
		  }
		  LOGGER.info("url=：[" + url+"]");
		  return url;
	   }
	   /**
	    * 获取游戏服务器名称
	    * @return
	    * @throws SQLException
	    * @throws Exception
	    */
	   public static ServerInfo getServerInfo(int id) throws WoCenterException
	   {
		  // LOGGER.debug("A request from CallCenter get Servers");
		    Connection con = null;
		   	PreparedStatement stm = null;
		   	ResultSet res = null;
		   	String sql = null;
		   	ServerInfo serverInfo=null;
		   	try {
					con = DBManager.getConnection("oracle");
					 LOGGER.debug(con);
					sql = SQL.GET_GAME_SERVER_NAME;
					stm = con.prepareStatement(sql);
					 LOGGER.debug(SystemConst.DEFAULT_WAIT_SEC);
				//	stm.setQueryTimeout(SystemConst.DEFAULT_WAIT_SEC);
					res = stm.executeQuery();
					LOGGER.debug(res);
		            while (res.next()) {
		            	serverInfo=new ServerInfo(res);
		            }
		   	} catch (SQLException e){
					LOGGER.error("查询server_info表失败:SQL["+sql+"]"+e.getMessage());
					throw new WoCenterException(Constants.StateList.DATABASE_ERR, PromptMessage.DATABASE_ERROR);
			} catch (Exception e){
				LOGGER.error("查询server_info表失败：数据源为空.[错误信息]"+e.getMessage());
				throw new WoCenterException(Constants.StateList.UNKNOWN_ERROR, PromptMessage.SYSTEM_BUSY);
			}finally{
				DBManager.closeConnection(con, stm, res);
			}
			return serverInfo;
	   }
}
