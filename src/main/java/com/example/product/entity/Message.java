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
@Table(name="Message")
public class Message {
	@Id
	@Column(name="messageID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int messageID;
	@Column(name="message_text")
	private String message_text;
	@Column(name="timestamp")
	@CreationTimestamp
	private Timestamp timestamp;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="senderID",referencedColumnName = "userID")
	
	private Users senderID;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="receiverID",referencedColumnName = "userID")
	@JsonIgnore
	private Users receiverID;
	
	public Message() {}


	

	public Message(int messageID, String message_text, Timestamp timestamp, Users senderID, Users receiverID) {
		super();
		this.messageID = messageID;
		this.message_text = message_text;
		this.timestamp = timestamp;
		this.senderID = senderID;
		this.receiverID = receiverID;
	}

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

	public Users getSenderID() {
		return senderID;
	}

	public void setSenderID(Users senderID) {
		this.senderID = senderID;
	}

	public Users getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(Users receiverID) {
		this.receiverID = receiverID;
	}

	@Override
	public String toString() {
		return "Message [messageID=" + messageID + ", message_text=" + message_text + ", timestamp=" + timestamp
				+ ", senderID=" + senderID + ", receiverID=" + receiverID + "]";
	}
	
	

}
