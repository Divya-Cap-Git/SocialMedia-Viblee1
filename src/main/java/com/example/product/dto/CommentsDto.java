
package com.example.product.dto;
 
import java.sql.Timestamp;
 
import org.hibernate.annotations.CreationTimestamp;
 
import com.example.product.entity.Users;
 
import jakarta.persistence.Column;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
 
public class CommentsDto {

   private int commentID;

   private String comment_text;

    private Timestamp timestamp;

    private int userid;

    public CommentsDto() {}
 
	public CommentsDto(int commentID, String comment_text, Timestamp timestamp, int userid) {

		super();

		this.commentID = commentID;

		this.comment_text = comment_text;

		this.timestamp = timestamp;

		this.userid = userid;

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
 
	public int getUserid() {

		return userid;

	}
 
	public void setUserid(int userid) {

		this.userid = userid;

	}



 
}
