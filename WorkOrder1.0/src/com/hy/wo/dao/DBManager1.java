package com.hy.wo.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.hy.wo.action.Action4CallCenter;
import com.hy.wo.util.MyUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.you9.base.Globe;

public class DBManager1 {
	private   static   DBManager1   dbManager;      
    private   ComboPooledDataSource   dataSource;    
    private static final Logger LOGGER = Logger.getLogger(DBManager1.class);//定义日志
   
    public   DBManager1(){
            try   {
                    dataSource=new   ComboPooledDataSource();
                    dataSource.setUser("member");
                    dataSource.setPassword("nineyouzlc");
                   // dataSource.setJdbcUrl("jdbc:mysql://58.221.50.21:3306/fy_manager");
                    dataSource.setJdbcUrl("jdbc:oracle:thin:@58.221.50.85:1521:test");
                    dataSource.setDriverClass("oracle.jdbc.driver.OracleDriver");
                    dataSource.setInitialPoolSize(2);
                    dataSource.setMinPoolSize(1);
                    dataSource.setMaxPoolSize(10);
                    dataSource.setMaxStatements(50);
                    dataSource.setMaxIdleTime(60); 
            }   catch   (PropertyVetoException   e)   {
                throw   new   RuntimeException(e);
            }
    }

    public   final   static   DBManager1   getInstance(){
    		if(dbManager==null){
    			dbManager=new   DBManager1();
    		}
            return   dbManager;      
    }      

    public   final   Connection   getConnection()   {      
            try   {      
                    return   dataSource.getConnection();      
            }   catch   (SQLException   e)   {      
                    throw   new   RuntimeException( "无法从数据源获取连接 ",e);      
            }      
       
          
    }    
    /**
	 * 关闭数据库连接的方法
	 * 
	 * @param conn 数据库会话对象
	 * @param stm 通过会话对象执行sql语句返回结果的对象
	 * @param res 数据库结果集对象
	 */
	public static void closeConnection(Connection con,Statement stm,ResultSet res){
		try {
			if (res != null) {
				res.close();
			}
		} catch (SQLException e) {
			LOGGER.error("[关闭ResultSet时出错]", e);
		}
		try {
			if (stm != null) {
				stm.close();
			}
		} catch (SQLException e) {
			LOGGER.error("[关闭Statement时出错]", e);
		}
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			LOGGER.error("[关闭Connection时出错]", e);
		}
	}
   
	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		int id=0;
		Connection   con   =   null;
		try   {
			con   =   DBManager1.getInstance().getConnection();
			System.out.println(con);
			PreparedStatement ps=con.prepareStatement(
					"SELECT ID,GAMEID,GAMENAME,AREAID,AREANAME,SERVERID,SERVERNAME FROM server_info WHERE STATE=1");
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				id=rs.getInt("SERVERID");
				System.out.println(id);
			}
		}   catch   (Exception   e){
			e.printStackTrace();
		}finally{
			if   (con   !=   null)
				con.close();
		}
		try   {
			String sql="select c.username,nickname,level,class_category " +
					"from `character` a,character_attribute b,fy_login.member c,fy_manager.character_link d  " +
					"where a.id=b.char_id  and c.id=d.member_id and a.id=d.id and c.username='twtest0009'";
			Class.forName("com.mysql.jdbc.Driver");
		//	DriverManager.setLoginTimeout(5);
			//String dns=AsistDao.getGameServerUrl(11);
			//String DNS=dns.substring(0, dns.lastIndexOf("/"))+"/fy_manager";
		//	System.out.println(DNS);
//			System.out.println(url);
//			System.out.println(MyUtil.getDefaultServerUser());
//			System.out.println(MyUtil.getDefaultServerPass());
//			System.out.println(MyUtil.getSSOKey());
			con = DriverManager.getConnection("jdbc:mysql://58.221.50.21:3306/fy_manager",
																					"guanmm", 
																					"123456");//连接游戏服务器
			
			System.out.println(con);
			PreparedStatement ps=con.prepareStatement(sql);
		//	ps.setObject(1, "guozhiyuan");
//			ResultSet rs=ps.executeQuery();
//			while(rs.next()){
//				String name=rs.getString(1);
//				System.out.println(name);
//			}
		}   catch   (Exception   e){
			e.printStackTrace();
		}finally{
			if   (con   !=   null)
				con.close();
		}
		
	}

}
