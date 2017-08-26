package com.example.sameershekhar.hebecollins.activities.models;

import java.io.Serializable;

/**
 * Created by sameershekhar on 19-Aug-17.
 */

public class UpcommingBirthday implements Serializable {
    String name;
    String photo;
    String DOB;
    String userType;

    public UpcommingBirthday(String name, String photo, String DOB, String userType) {
        this.name = name;
        this.photo = photo;
        this.DOB = DOB;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
