package com.example.product.dto;

import java.sql.Timestamp;

import com.example.product.entity.Users;

public class PostsDto {
	private int postId;
	private String content;
	private Timestamp timestamp;
	private String post_picture;
	private Users users;
	
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getPost_picture() {
		return post_picture;
	}

	public void setPost_picture(String post_picture) {
		this.post_picture = post_picture;
	}

	public PostsDto() {}

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
	
	

}
