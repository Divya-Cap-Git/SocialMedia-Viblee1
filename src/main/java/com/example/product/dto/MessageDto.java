package com.example.product.dto;

import java.sql.Timestamp;



import com.example.product.entity.Users;



public class MessageDto {
	
	private int messageID;
	
	private String message_text;
	
	private Timestamp timestamp;
	
	
	private int senderID;
	
	
	private int receiverID;


	public int getMessageID() {
		return messageID;
	}


	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}


	public String getMessage_text() {
		return message_text;
	}


	public void setMessage_text(String message_text) {
		this.message_text = message_text;
	}


	public Timestamp getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	public int getSenderID() {
		return senderID;
	}


	public void setSenderID(int senderID) {
		this.senderID = senderID;
	}


	public int getReceiverID() {
		return receiverID;
	}


	public void setReceiverID(int receiverID) {
		this.receiverID = receiverID;
	}


	

}
