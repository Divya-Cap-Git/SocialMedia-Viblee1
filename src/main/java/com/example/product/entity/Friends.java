package com.example.product.entity;

import com.example.product.dto.statustype;
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
import jakarta.validation.constraints.NotNull;



@Entity
@Table(name = "Friends")
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friendshipID")
    private int friendshipID;

    @NotNull
    @Column(name = "status")
    private statustype status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userID1", referencedColumnName = "userID")
     private Users userID1;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userID2", referencedColumnName = "userID")
    private Users userID2;

    // Default constructor
    public Friends() {
    }

    // Constructor with parameters
    public Friends(statustype status, Users userID1, Users userID2) {
        this.status = status;
        this.userID1 = userID1;
        this.userID2 = userID2;
    }

    // Getters and setters
    public int getFriendshipID() {
        return friendshipID;
    }

    public void setFriendshipID(int friendshipID) {
        this.friendshipID = friendshipID;
    }

    public statustype getStatus() {
        return status;
    }

    public void setStatus(statustype status) {
        this.status = status;
    }

    public Users getUserID1() {
        return userID1;
    }

    public void setUserID1(Users userID1) {
        this.userID1 = userID1;
    }

    public Users getUserID2() {
        return userID2;
    }

    public void setUserID2(Users userID2) {
        this.userID2 = userID2;
    }

    @Override
    public String toString() {
        return "Friends [friendshipID=" + friendshipID + ", status=" + status + ", userID1=" + userID1 + ", userID2="
                + userID2 + "]";
    }
}
