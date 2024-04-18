package com.example.product.entity;

import java.sql.Timestamp;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Posts")
public class Posts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="postID")
	private int postID;
	
	@Column(name="content")
	private String content;
	
	@Column(name="timestamp")
	@CreationTimestamp
	private Timestamp timestamp;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name="userID")
	@JsonIgnore
	private Users users;
	
	@OneToMany(targetEntity = Likes.class,mappedBy = "posts")
	
	private List<Likes> likes;
	
	@OneToMany(targetEntity = Comments.class,mappedBy = "posts")
	private List<Comments> comments;
	
	@Column(name="post_picture")
	private String post_picture;
	
	public Posts() {}

	public Posts(int postID, String content, Timestamp timestamp) {
		super();
		this.postID = postID;
		this.content = content;
		this.timestamp = timestamp;
	}
	
	

	public Posts(int postID, String content, Timestamp timestamp, Users users, List<Likes> likes,
			List<Comments> comments) {
		super();
		this.postID = postID;
		this.content = content;
		this.timestamp = timestamp;
		this.users = users;
		this.likes = likes;
		this.comments = comments;
	}

	
	public Posts(int postID, String content, Timestamp timestamp, Users users, List<Likes> likes,
			List<Comments> comments, String post_picture) {
		super();
		this.postID = postID;
		this.content = content;
		this.timestamp = timestamp;
		this.users = users;
		this.likes = likes;
		this.comments = comments;
		this.post_picture = post_picture;
	}

	public String getPost_picture() {
		return post_picture;
	}

	public void setPost_picture(String post_picture) {
		this.post_picture = post_picture;
	}

	public int getPostID() {
		return postID;
	}

	public void setPostID(int postID) {
		this.postID = postID;
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

	public List<Likes> getLikes() {
		return likes;
	}

	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Posts [postID=" + postID + ", content=" + content + ", timestamp=" + timestamp + ", users=" + users
				+ ", likes=" + likes + ", comments=" + comments + "]";
	}
	
	
	

}
