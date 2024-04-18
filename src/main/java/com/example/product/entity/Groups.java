package com.example.product.entity;
 
import java.util.List;
 
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
@Table(name="Groups")
public class Groups {
	@Id
	@Column(name="groupID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int groupID;
	@Column(name="groupName")
	private String groupName;
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="adminID")
	private Users admin;
	
	public Groups() {}
	 
	
	 
	public Groups(int groupID, String groupName, Users admin) {
		super();
		this.groupID = groupID;
		this.groupName = groupName;
		this.admin = admin;

	}


 
	public int getGroupID() {
		return groupID;
	}
 
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
 
	public String getGroupName() {
		return groupName;
	}
 
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
 
	public Users getAdmin() {
		return admin;
	}
 
	public void setAdmin(Users admin) {
		this.admin = admin;
	}
 


 
	
 
	@Override
	public String toString() {
		return "Groups [groupID=" + groupID + ", groupName=" + groupName + ", admin=" + admin + ", members=" + "]";
	}
 
	



 
}