package com.example.product.dto;

import java.sql.Timestamp;

import com.example.product.entity.Users;

public class PostsDto2 {
	private int postId;
	private String content;
	private Timestamp timestamp;
	private String post_picture;
	private int userId;
	private String profile_picture;
	private String username;
	
	public PostsDto2() {}
	
	
	
	public PostsDto2(int postId, String content, Timestamp timestamp, String post_picture, int userId,
			String profile_picture, String username) {
		super();
		this.postId = postId;
		this.content = content;
		this.timestamp = timestamp;
		this.post_picture = post_picture;
		this.userId = userId;
		this.profile_picture = profile_picture;
		this.username = username;
	}



	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
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
	public String getPost_picture() {
		return post_picture;
	}
	public void setPost_picture(String post_picture) {
		this.post_picture = post_picture;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getProfile_picture() {
		return profile_picture;
	}
	public void setProfile_picture(String profile_picture) {
		this.profile_picture = profile_picture;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
