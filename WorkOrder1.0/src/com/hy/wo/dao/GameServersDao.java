package com.hy.wo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hy.wo.po.WorkOrderUserInfo;
import com.hy.wo.util.Constants.SQL;


/**
 * 动态获取游戏服务器操作数据库
 * @author jiyin_chen
 *
 */
public class GameServersDao {

	String url;
	String username;
	String password;
	
	String driver="com.mysql.jdbc.Driver";
	private static final Logger LOGGER = Logger.getLogger(GameServersDao.class);
	
	
	static GameServersDao instance;
	
	private GameServersDao()
	{
		try{
			Class.forName(driver);
			
		}catch(ClassNotFoundException e){
			LOGGER.error("找不到驱动程序类,加载失败");
			e.printStackTrace();
		}
	}
	
	
    public static GameServersDao getInstance() {
        synchronized (GameServersDao.class) {
            if (instance == null) {
                instance = new GameServersDao();
            }
        }
        return instance;
    }
	
	


	/**
	 * 
	 * @param DNS
	 * @param USER
	 * @param PASS
	 * @param userInfo
	 * @return
	 */
	public List<WorkOrderUserInfo> doQuery(final String DNS, final String USER, final String PASS,final String account){
		List<WorkOrderUserInfo> list=null;
		Connection con =null;
		PreparedStatement psm =null;
		ResultSet rs=null;
		long startTime = System.currentTimeMillis();
		try{
			//DriverManager.setLoginTimeout(Integer.valueOf(Globe.getProperty("default-waitTime")));
			con = DriverManager.getConnection(DNS, USER, PASS);//连接游戏服务器
		}catch(SQLException se){
			LOGGER.debug("连接游戏服务器失败的时间："+(System.currentTimeMillis()-startTime));
			LOGGER.error("数据库连接失败:["+DNS+"]");
			se.printStackTrace();
			return null;
		}		
		try{
			LOGGER.debug("成功连接游戏服务器的时间："+(System.currentTimeMillis()-startTime));
			psm = con.prepareStatement(SQL.GET_ROLE_NAME);
			LOGGER.debug("查询角色SQL："+SQL.GET_ROLE_NAME+"["+account+"]");
			psm.setString(1, account);
			 rs=psm.executeQuery();
			LOGGER.debug(rs);
			list=new ArrayList<WorkOrderUserInfo>();
			while(rs.next()){
				list.add(new WorkOrderUserInfo(rs));
			}
			LOGGER.debug("size:"+list.size());
		}catch(SQLException se){
			
		}finally{
			if (rs !=null)
			{
				try{
					rs.close();
					}catch(SQLException se){
						se.printStackTrace();
				}
			}
			if (psm !=null)
			{
				try{
					psm.close();
					}catch(SQLException se){
						se.printStackTrace();
				}
			}
			if (con!=null)
			{
				try{
					con.close();
				}catch(SQLException se)
				{
					se.printStackTrace();
				}
			}
		}
		LOGGER.debug("处理充值游戏数据库总的时间为："+(System.currentTimeMillis() - startTime));
		return list;	
	}
	
	/**
	 * 查找角色名
	 * @param DNS
	 * @param USER
	 * @param PASS
	 * @param userInfo
	 * @return
	 */
	public String doUserNameQuery(final String DNS, final String USER, final String PASS,final String account){
		String username=null;
		Connection con =null;
		PreparedStatement psm =null;
		ResultSet rs=null;
		long startTime = System.currentTimeMillis();
		try{
			//DriverManager.setLoginTimeout(Integer.valueOf(Globe.getProperty("default-waitTime")));
			con = DriverManager.getConnection(DNS, USER, PASS);//连接游戏服务器
		}catch(SQLException se){
			LOGGER.debug("连接游戏服务器失败的时间："+(System.currentTimeMillis()-startTime));
			LOGGER.error("数据库连接失败:["+DNS+"]");
			se.printStackTrace();
			return null;
		}		
		try{
			LOGGER.debug("成功连接游戏服务器的时间："+(System.currentTimeMillis()-startTime));
			psm = con.prepareStatement(SQL.GET_ROLE_NAME_BY_ID);
			//LOGGER.debug("查询角色SQL："+SQL.GET_ROLE_NAME+"["+account+"]");
			psm.setString(1, account);
			 rs=psm.executeQuery();
			LOGGER.debug(rs);
			while(rs.next()){
				username = rs.getString(1);
			}
			LOGGER.debug("size:"+username);
		}catch(SQLException se){
			
		}finally{
			if (rs !=null)
			{
				try{
					rs.close();
					}catch(SQLException se){
						se.printStackTrace();
				}
			}
			if (psm !=null)
			{
				try{
					psm.close();
					}catch(SQLException se){
						se.printStackTrace();
				}
			}
			if (con!=null)
			{
				try{
					con.close();
				}catch(SQLException se)
				{
					se.printStackTrace();
				}
			}
		}
		LOGGER.debug("处理充值游戏数据库总的时间为："+(System.currentTimeMillis() - startTime));
		return username;	
	}
	
}
