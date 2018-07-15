package com.book.search.dao;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "member")
public class Member {

	@Id
	private String userID;

	@JsonIgnore
	@Column(nullable = false)
	private String userPwd;

	@Column(nullable = false)
	private Timestamp regdate;

	@Column(nullable = false)
	private String nickName;	
	
	public Member() {
		super();
	}

	public Member(String userID, String userPwd, Timestamp regdate, String nickName) {
		super();
		this.userID = userID;
		this.userPwd = userPwd;
		this.regdate = regdate;
		this.nickName = nickName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
