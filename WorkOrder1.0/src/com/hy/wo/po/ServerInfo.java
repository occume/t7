package com.hy.wo.po;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerInfo  implements java.io.Serializable {

	private static final long serialVersionUID = 6935311329726011419L;

	Integer id;
	
	Integer gameId;
	
	String gameName;
	
	Integer areaId;
	
	String areaName;
	
	Integer serverId;
	
	String serverName;
	
	
	public ServerInfo(){}
	
	public ServerInfo(ResultSet rs) throws SQLException
	{
		try{
			//rs.
		//setId(rs.getInt("id"));
		//setGameId(rs.getInt("gameId"));
		//setGameName(rs.getString("gameName"));
		//setAreaId(rs.getInt("areaId"));
		//setAreaName(rs.getString("areaName"));
		setServerId(rs.getInt("serverid"));
		setServerName(rs.getString("servername"));
	}catch(Exception e){
		e.printStackTrace();
	}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getServerId() {
		return serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
}
