package com.hy.wo.po;
/**
 * 游戏大区类
 * @author dong_jin
 *
 */
public class Areas {
	private int id;// 游戏大区id
	private String name;//大区名称
	private Games game;//所属游戏
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Games getGame() {
		return game;
	}
	public void setGame(Games game) {
		this.game = game;
	}
}
