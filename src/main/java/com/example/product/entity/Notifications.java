package com.example.product.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Notifications")
public class Notifications {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="notificationID")
	private int notificationID;
	@Column(name="content")
	private String content;
	@Column(name="timestamp")
	@CreationTimestamp
	private Timestamp timestamp;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="userID")
	@JsonIgnore
	private Users users;
	
	public Notifications() {}

	public Notifications(int notificationID, String content, Timestamp timestamp) {
		super();
		this.notificationID = notificationID;
		this.content = content;
		this.timestamp = timestamp;
	}
	
	

	public Notifications(int notificationID, String content, Timestamp timestamp, Users users) {
		super();
		this.notificationID = notificationID;
		this.content = content;
		this.timestamp = timestamp;
		this.users = users;
	}

	public int getNotificationID() {
		return notificationID;
	}

	public void setNotificationID(int notificationID) {
		this.notificationID = notificationID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Notifications [notificationID=" + notificationID + ", content=" + content + ", timestamp=" + timestamp
				+ ", users=" + users + "]";
	}
	
	
	

}
