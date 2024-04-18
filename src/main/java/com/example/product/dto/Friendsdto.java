
package com.example.product.dto;

public class Friendsdto {
  private int friendshipid;
  private statustype status;
  private int userid1;
  private int userid2;
  private String user1Profile;
  private String user2Profile;
  private String user2name;
  private String user1name;
  
  
  
  
  public String getUser1name() {
	return user1name;
}
public void setUser1name(String user1name) {
	this.user1name = user1name;
}
public int getFriendshipid() {
	return friendshipid;
}
  public void setFriendshipid(int friendshipid) {
	this.friendshipid = friendshipid;
}
  public statustype getStatus() {
	return status;
}
  public void setStatus(statustype status) {
	this.status = status;
}
  public int getUserid1() {
	return userid1;
}
  public void setUserid1(int userid1) {
	this.userid1 = userid1;
}
  public int getUserid2() {
	return userid2;
}
  public void setUserid2(int userid2) {
	this.userid2 = userid2;
}
public String getUser1Profile() {
	return user1Profile;
}
public void setUser1Profile(String user1Profile) {
	this.user1Profile = user1Profile;
}
public String getUser2Profile() {
	return user2Profile;
}
public void setUser2Profile(String user2Profile) {
	this.user2Profile = user2Profile;
}
public String getUser2name() {
	return user2name;
}
public void setUser2name(String user2name) {
	this.user2name = user2name;
}
  
  
}
