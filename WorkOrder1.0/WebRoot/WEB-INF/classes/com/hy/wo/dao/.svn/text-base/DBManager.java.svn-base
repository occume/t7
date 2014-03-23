package com.hy.wo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Random;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import com.you9.base.Globe;

/**
 * DBManager 该类通过加载globe_config.xml中database节点下的数据源配置信息来获得
 * 数据源和连接.如果配置文件中配置了多个数据源类型节点,只需传入指定的节点名称及
 * 配置对应的数据源名称位置,就可得到相应的数据源及连接.					
 *
 * DBManager 类包括的方法可用于获得数据源DataSrouce，直接获得Connection连接,关闭
 * 数据库会话对象.
 * 
 * globe_config.xml文件中database节点下配置信息错误或者将database节点下不存在的节
 * 点类型传递给类中的getDataSource,getConnection方法,将导致返回的数据源及连接为null.
 * @author 杨果
 * @version 1.2 创建时间 2010-11-03
 */
public class DBManager {

	private static Logger LOGGER = Logger.getLogger(DBManager.class);
	/** 数据源上下文环境 */
	private static Context CTX;
	/** 产生随机数的对象 */
	private static final Random RANDOM = new Random();
	static {
		try {
			// 获得数据源上下文环境
			CTX = (Context) new InitialContext().lookup("java:comp/env");
		} catch (javax.naming.NamingException e) {
			LOGGER.error("获得数据源上下文环境失败", e);
		}
	}

	/**
	 * 通过传入数据源节点名称来获得数据源,该参数必须是globe_config.xml中database节
	 * 点下已经配置好的数据源的名称。
	 * 
	 * @param dbType 需要连接的数据源节点名称，配置文件中database子节点的名称
	 * @return DataSource 数据源
	 */
	private static DataSource getDataSource(String dbType) {
		// 配置文件中数据源列表
		List<String> dsources = null;
		// 数据源对象
		DataSource datasource = null;
		// 配置文件中数据源的个数
		int sNum =0;
		try {
			// 得到配置文件中数据源列表
			dsources = (List<String>) Globe.getProperties("database/" + dbType+
					"/pool-name");
			// 得到配置文件中数据源的个数
			sNum = dsources.size();
		} catch (NullPointerException e) {
			LOGGER.error("配置文件中<database>下没有该参数名的子节点，传入的参数为：*" 
					+ dbType +"*");
		}
		// 如果数据源个数为0,记录错误
		if (sNum == 0) {
			LOGGER.error("[golbe_config.xml中节点 database/" + dbType
					+ "/pool-name 未配置]");
		} else { // 如果数据源个数不为0,通过switch节点数据获得数据源
			// 获得switch节点的数据
			int flag = Integer.parseInt(Globe.getProperty("database/" + dbType
					+ "/switch"));
			// 配置文件中数据源的名称
			String name = null;
			// 如果switch节点中的数据为0，则随机获得一个数据源名称
			if (flag == 0) {
				name = dsources.get(RANDOM.nextInt(sNum));
			} else { // 如果switch节点中数据不为0,则获得对象数据的数据源名称
				// 如果switch节点中的数据小于节点总数，获得相应的数据源集合下标
				if (flag <= sNum) {
					name = dsources.get(flag - 1);
				} else { // 如果switch节点数据大于节点总数，记录错误日志
					LOGGER.error("golbe_config.xml中 database/" + dbType
						+ "/switch 节点配置错误[switch=*" + flag + "*]," +
									"switch中的值不能大于pool-name的个数");
				}
			}
			// 如果name不为Null，获得相应数据源
			if (name != null) {
				try {
					// 根据数据源名称获得链接
					datasource = (DataSource) CTX.lookup(name);
				} catch (NamingException e) {
					LOGGER.error("获取数据时发生错误[poolname:*" + name + "*]," +
							"错误的原因可能是web服务器的配置文件中没有配置该名称的数据源信息", e);
				}
			} else {// 如果name为null，记录错误日志
				LOGGER.error("golbe_config.xml中 database/" + dbType
						+ "/switch 节点配置错误[switch=*" + flag + "*]," +
						"switch中的值不能大于pool-name的个数");
			}
		}
		return datasource;
	}

	/**
	 * 通过传入数据源节点名称直接获得connection,该参数必须是globe_config.xml中database节
	 * 点下已经配置好的数据源的名称。
	 * 
	 * @param dbType 需要获得链接的数据源名称,配置文件中database子节点的名称
	 * @return Connection 特定数据库的连接
	 */
	public static Connection getConnection(String dbType) {
		Connection conn = null;
		try {
			conn = getDataSource(dbType).getConnection();
		} catch (SQLException e) {
			LOGGER.error("获取数据库连接时发生错误[dbType:" + dbType + "]", e);
		}
		return conn;
	}

	 /**
     * 获取事务管理器
     * @return
     */
//    public synchronized  static TranManager getTranManager(Connection conn){
//    	return new TranManager(conn);
//    }
	/**
	 * 关闭数据库连接的方法
	 * 
	 * @param conn 数据库会话对象
	 * @param stm 通过会话对象执行sql语句返回结果的对象
	 * @param res 数据库结果集对象
	 */
	public static void closeConnection(Connection con, Statement stm,
			ResultSet res) {
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
     * 关闭数据库连接
     * @param conn
     * @throws DAOException 
     */
    public static void close(Connection con, Statement stm) {		  
    	closeConnection(con,stm,null);
	  }	
    
    /**
     * 关闭数据库连接
     * @param conn
     * @throws DAOException 
     */
    public static void close(Connection conn){
    	close(conn,null);
    }
}