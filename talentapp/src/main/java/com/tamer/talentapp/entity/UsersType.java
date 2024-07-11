package com.tamer.talentapp.entity;


import jakarta.persistence.*;
import org.apache.catalina.User;

import java.util.List;

@Entity
@Table(name = "users_type")
public class UsersType {

    @Id
    private int userTypeId;

    private String userTypename;

    @ManyToMany(targetEntity = Users.class, mappedBy = "userTypeId", cascade = CascadeType.ALL)
    private List<User> users;


    public UsersType() {
    }

    public UsersType(int userTypeId, String userTypename, List<User> users) {
        this.userTypeId = userTypeId;
        this.userTypename = userTypename;
        this.users = users;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getUserTypename() {
        return userTypename;
    }

    public void setUserTypename(String userTypename) {
        this.userTypename = userTypename;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UsersType{" +
                "userTypeId=" + userTypeId +
                ", userTypename='" + userTypename + '\'' +
                '}';
    }
}
