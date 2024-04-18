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
@Table(name="Likes")
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="likeID")
	private int likeID;
	
	@Column(name="timestamp")
	@CreationTimestamp
	private Timestamp timestamp;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="userID")
	@JsonIgnore
	private Users users;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
     @JoinColumn(name="postID")
	
	private Posts posts;
	
	public Likes() {}

	public Likes(int likeID, Timestamp timestamp) {
		super();
		this.likeID = likeID;
		this.timestamp = timestamp;
	}
	
	

	
	public Likes(int likeID, Timestamp timestamp, Users users, Posts posts) {
		super();
		this.likeID = likeID;
		this.timestamp = timestamp;
		this.users = users;
		this.posts = posts;
	}

	public int getLikeID() {
		return likeID;
	}

	public void setLikeID(int likeID) {
		this.likeID = likeID;
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

	public Posts getPosts() {
		return posts;
	}

	public void setPosts(Posts posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "Likes [likeID=" + likeID + ", timestamp=" + timestamp + ", user=" + users + "]";
	}
	
	
	

}
