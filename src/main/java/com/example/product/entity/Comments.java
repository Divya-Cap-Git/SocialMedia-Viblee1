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
@Table(name="Comments")
public class Comments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="commentID")
	private int commentID;
	@Column(name="comment_text")
	private String comment_text;
	@Column(name="timestamp")
	@CreationTimestamp
	private Timestamp timestamp;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="userID")
	@JsonIgnore
	private Users users;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="postID")
	@JsonIgnore
	private Posts posts;
	
	public Comments() {}
	public Comments(int commentID, String comment_text, Timestamp timestamp) {
		super();
		this.commentID = commentID;
		this.comment_text = comment_text;
		this.timestamp = timestamp;
	}
	
	
	public Comments(int commentID, String comment_text, Timestamp timestamp, Users users, Posts posts) {
		super();
		this.commentID = commentID;
		this.comment_text = comment_text;
		this.timestamp = timestamp;
		this.users = users;
		this.posts = posts;
	}
	
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public String getComment_text() {
		return comment_text;
	}
	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
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
		return "Comments [commentID=" + commentID + ", comment_text=" + comment_text + ", timestamp=" + timestamp
				+ ", users=" + users + ", posts=" + posts + "]";
	}
	
	
	
	
	
	
	
	

}
