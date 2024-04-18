package com.example.product.dto;

import java.sql.Timestamp;

public class Likesdto {
	private int likeid;
	private Timestamp timestamp;
	private int userid;
	
	
	public int getLikeid() {
		return likeid;
	}
	public void setLikeid(int likeid) {
		this.likeid = likeid;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	
	

}
