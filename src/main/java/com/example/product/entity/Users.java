//package com.example.product.entity;
//
//import java.util.List;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotNull;
//
//@Entity
//@Table(name="users")
//public class Users {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="userID")
//	private int userID;
//	
//	@NotNull
//	@Column(name="username")
//	private String username;
//	
//	@NotNull
//	@Column(name="email")
//	private String email;
//	
//	@NotNull
//	@Column(name="password")
//	private String password;
//	
//	@Column(name="profile_picture")
//	private String profile_picture;
//	
//	@OneToMany(targetEntity = Likes.class,mappedBy = "users")
//	@JsonIgnore
//	private List<Likes> likes;
//	
//	@OneToMany(targetEntity = Comments.class,mappedBy = "users")
//	@JsonIgnore
//	private List<Comments> comments;
//  
//	@OneToMany(targetEntity = Groups.class,mappedBy = "admin")
//	@JsonIgnore
//	private List<Groups> groups;
//	
//	@OneToMany(targetEntity = Posts.class,mappedBy = "users")
//	@JsonIgnore
//	private List<Posts> posts;
//	
//	@OneToMany(targetEntity = Notifications.class,mappedBy = "users")
//	@JsonIgnore
//	private List<Notifications> notifications;
//	
//	@OneToMany(targetEntity = Friends.class,mappedBy = "userID1")
//	@JsonIgnore
//	private List<Friends> userID1;
//	
//	@OneToMany(targetEntity = Friends.class,mappedBy = "userID2")
//	@JsonIgnore
//	private List<Friends> userID2;
//	
//	@OneToMany(targetEntity = Message.class,mappedBy = "senderID")
//	
//	private List<Message> senderID;
//	
//	@OneToMany(targetEntity = Message.class,mappedBy = "receiverID")
//	
//	private List<Message> receiverID;
//	
//	
//	
//	public Users() {}
//	
//	public Users(int userID, String username, String email, String password, String profile_picture) {
//		super();
//		this.userID = userID;
//		this.username = username;
//		this.email = email;
//		this.password = password;
//		this.profile_picture = profile_picture;
//	}
//	
//	
//
//
//
//
//	public Users(int userID, @NotNull String username, @NotNull String email, @NotNull String password,
//			String profile_picture, List<Likes> likes, List<Comments> comments, List<Groups> groups, List<Posts> posts,
//			List<Notifications> notifications, List<Friends> userID1, List<Friends> userID2, List<Message> senderID,
//			List<Message> receiverID) {
//		super();
//		this.userID = userID;
//		this.username = username;
//		this.email = email;
//		this.password = password;
//		this.profile_picture = profile_picture;
//		this.likes = likes;
//		this.comments = comments;
//		this.groups = groups;
//		this.posts = posts;
//		this.notifications = notifications;
//		this.userID1 = userID1;
//		this.userID2 = userID2;
//		this.senderID = senderID;
//		this.receiverID = receiverID;
//	}
//
//	public int getUserID() {
//		return userID;
//	}
//
//	public void setUserID(int userID) {
//		this.userID = userID;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getProfile_picture() {
//		return profile_picture;
//	}
//
//	public void setProfile_picture(String profile_picture) {
//		this.profile_picture = profile_picture;
//	}
//
//	public List<Likes> getLikes() {
//		return likes;
//	}
//
//	public void setLikes(List<Likes> likes) {
//		this.likes = likes;
//	}
//	
//	
//
//	public List<Comments> getComments() {
//		return comments;
//	}
//
//	public void setComments(List<Comments> comments) {
//		this.comments = comments;
//	}
//
//	public List<Groups> getGroups() {
//		return groups;
//	}
//
//	public void setGroups(List<Groups> groups) {
//		this.groups = groups;
//	}
//
//	public List<Posts> getPosts() {
//		return posts;
//	}
//
//	public void setPosts(List<Posts> posts) {
//		this.posts = posts;
//	}
//
//	public List<Notifications> getNotifications() {
//		return notifications;
//	}
//
//	public void setNotifications(List<Notifications> notifications) {
//		this.notifications = notifications;
//	}
//
//	public List<Friends> getUserID1() {
//		return userID1;
//	}
//
//	public void setUserID1(List<Friends> userID1) {
//		this.userID1 = userID1;
//	}
//
//	public List<Friends> getUserID2() {
//		return userID2;
//	}
//
//	public void setUserID2(List<Friends> userID2) {
//		this.userID2 = userID2;
//	}
//
//	public List<Message> getSenderID() {
//		return senderID;
//	}
//
//	public void setSenderID(List<Message> senderID) {
//		this.senderID = senderID;
//	}
//
//	public List<Message> getReceiverID() {
//		return receiverID;
//	}
//
//	public void setReceiverID(List<Message> receiverID) {
//		this.receiverID = receiverID;
//	}
//
//	@Override
//	public String toString() {
//		return "Users [userID=" + userID + ", username=" + username + ", email=" + email + ", password=" + password
//				+ ", profile_picture=" + profile_picture + "]";
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//
//
//}




package com.example.product.entity;
 
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
 
import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;

import jakarta.persistence.UniqueConstraint;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Size;
 
@Entity

@Table(name="users",uniqueConstraints = { @UniqueConstraint(columnNames = "username"),

		@UniqueConstraint(columnNames = "email") })

public class Users {

	 @Id

	    @GeneratedValue(strategy = GenerationType.IDENTITY)

	    @Column(name = "userID")

	    private int userID;
 
	    @NotNull(message = "Username cannot be null")

	    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")

	    @Column(name = "username")

	    private String username;
 
	    @NotNull(message = "Email cannot be null")

	    @Email(message = "Invalid email format")

	    @Column(name = "email")

	    private String email;
 
	    @NotNull(message = "Password cannot be null")

	    @Size(min = 8, message = "Password must be at least 8 characters")

	    @Column(name = "password")

	    private String password;
 
	

	@Column(name="profile_picture")

	private String profile_picture;

	@OneToMany(targetEntity = Likes.class,mappedBy = "users")

	@JsonIgnore

	private List<Likes> likes;

	@OneToMany(targetEntity = Comments.class,mappedBy = "users")

	@JsonIgnore

	private List<Comments> comments;

	@OneToMany(targetEntity = Groups.class,mappedBy = "admin")

	@JsonIgnore

	private List<Groups> groups;

	@OneToMany(targetEntity = Posts.class,mappedBy = "users")

	@JsonIgnore

	private List<Posts> posts;

	@OneToMany(targetEntity = Notifications.class,mappedBy = "users")

	@JsonIgnore

	private List<Notifications> notifications;

	@OneToMany(targetEntity = Friends.class,mappedBy = "userID1")

	@JsonIgnore

	private List<Friends> userID1;

	@OneToMany(targetEntity = Friends.class,mappedBy = "userID2")

	@JsonIgnore

	private List<Friends> userID2;

	@OneToMany(targetEntity = Message.class,mappedBy = "senderID")

	private List<Message> senderID;

	@OneToMany(targetEntity = Message.class,mappedBy = "receiverID")

	private List<Message> receiverID;


	public Users() {}

	public Users(int userID, String username, String email, String password, String profile_picture) {

		super();

		this.userID = userID;

		this.username = username;

		this.email = email;

		this.password = password;

		this.profile_picture = profile_picture;

	}

 
 
 
	public Users(int userID, @NotNull String username, @NotNull String email, @NotNull String password,

			String profile_picture, List<Likes> likes, List<Comments> comments, List<Groups> groups, List<Posts> posts,

			List<Notifications> notifications, List<Friends> userID1, List<Friends> userID2, List<Message> senderID,

			List<Message> receiverID) {

		super();

		this.userID = userID;

		this.username = username;

		this.email = email;

		this.password = password;

		this.profile_picture = profile_picture;

		this.likes = likes;

		this.comments = comments;

		this.groups = groups;

		this.posts = posts;

		this.notifications = notifications;

		this.userID1 = userID1;

		this.userID2 = userID2;

		this.senderID = senderID;

		this.receiverID = receiverID;

	}
 
	public int getUserID() {

		return userID;

	}
 
	public void setUserID(int userID) {

		this.userID = userID;

	}
 
	public String getUsername() {

		return username;

	}
 
	public void setUsername(String username) {

		this.username = username;

	}
 
	public String getEmail() {

		return email;

	}
 
	public void setEmail(String email) {

		this.email = email;

	}
 
	public String getPassword() {

		return password;

	}
 
	public void setPassword(String password) {

		this.password = password;

	}
 
	public String getProfile_picture() {

		return profile_picture;

	}
 
	public void setProfile_picture(String profile_picture) {

		this.profile_picture = profile_picture;

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
 
	public List<Groups> getGroups() {

		return groups;

	}
 
	public void setGroups(List<Groups> groups) {

		this.groups = groups;

	}
 
	public List<Posts> getPosts() {

		return posts;

	}
 
	public void setPosts(List<Posts> posts) {

		this.posts = posts;

	}
 
	public List<Notifications> getNotifications() {

		return notifications;

	}
 
	public void setNotifications(List<Notifications> notifications) {

		this.notifications = notifications;

	}
 
	public List<Friends> getUserID1() {

		return userID1;

	}
 
	public void setUserID1(List<Friends> userID1) {

		this.userID1 = userID1;

	}
 
	public List<Friends> getUserID2() {

		return userID2;

	}
 
	public void setUserID2(List<Friends> userID2) {

		this.userID2 = userID2;

	}
 
	public List<Message> getSenderID() {

		return senderID;

	}
 
	public void setSenderID(List<Message> senderID) {

		this.senderID = senderID;

	}
 
	public List<Message> getReceiverID() {

		return receiverID;

	}
 
	public void setReceiverID(List<Message> receiverID) {

		this.receiverID = receiverID;

	}
 
	@Override

	public String toString() {

		return "Users [userID=" + userID + ", username=" + username + ", email=" + email + ", password=" + password

				+ ", profile_picture=" + profile_picture + "]";

	}




 
 
}




