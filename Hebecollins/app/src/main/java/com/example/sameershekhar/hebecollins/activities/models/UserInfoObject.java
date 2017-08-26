package com.example.sameershekhar.hebecollins.activities.models;

import java.io.Serializable;

/**
 * Created by sameershekhar on 19-Aug-17.
 */

public class UserInfoObject implements Serializable {

    String userName;
    String userEmailId;
    String userContactNumber;
    String userGoal;
    String userDOB;
    String userWeight;
    String userHeight;
    String userBodyfat;
    String userBMI;
    String userLeanBodyMass;
    String userTrainername;
    String userTrainerAge;
    String userTrainerNumber;
    String userTrainerCertification;
    String userTrainerAchievment;
    public UserInfoObject()
    {
    }


    public UserInfoObject(String userName, String userEmailId, String userContactNumber,
                          String userGoal, String userDOB, String userWeight, String userHeight,
                          String userBodyfat, String userBMI, String userLeanBodyMass, String userTrainername,
                          String userTrainerAge,String userTrainerNumber, String userTrainerCertification, String userTrainerAchievment) {
        this.userName = userName;
        this.userEmailId = userEmailId;
        this.userContactNumber = userContactNumber;
        this.userGoal = userGoal;
        this.userDOB = userDOB;
        this.userWeight = userWeight;
        this.userHeight = userHeight;
        this.userBodyfat = userBodyfat;
        this.userBMI = userBMI;
        this.userLeanBodyMass = userLeanBodyMass;
        this.userTrainername = userTrainername;
        this.userTrainerAge = userTrainerAge;
        this.userTrainerNumber=userTrainerNumber;
        this.userTrainerCertification = userTrainerCertification;
        this.userTrainerAchievment = userTrainerAchievment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public String getUserContactNumber() {
        return userContactNumber;
    }

    public void setUserContactNumber(String userContactNumber) {
        this.userContactNumber = userContactNumber;
    }

    public String getUserGoal() {
        return userGoal;
    }

    public void setUserGoal(String userGoal) {
        this.userGoal = userGoal;
    }

    public String getUserDOB() {
        return userDOB;
    }

    public void setUserDOB(String userDOB) {
        this.userDOB = userDOB;
    }

    public String getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(String userWeight) {
        this.userWeight = userWeight;
    }

    public String getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(String userHeight) {
        this.userHeight = userHeight;
    }

    public String getUserBodyfat() {
        return userBodyfat;
    }

    public void setUserBodyfat(String userBodyfat) {
        this.userBodyfat = userBodyfat;
    }

    public String getUserBMI() {
        return userBMI;
    }

    public void setUserBMI(String userBMI) {
        this.userBMI = userBMI;
    }

    public String getUserLeanBodyMass() {
        return userLeanBodyMass;
    }

    public void setUserLeanBodyMass(String userLeanBodyMass) {
        this.userLeanBodyMass = userLeanBodyMass;
    }

    public String getUserTrainername() {
        return userTrainername;
    }

    public String getUserTrainerNumber() {
        return userTrainerNumber;
    }

    public void setUserTrainerNumber(String userTrainerNumber) {
        this.userTrainerNumber = userTrainerNumber;
    }

    public void setUserTrainername(String userTrainername) {
        this.userTrainername = userTrainername;
    }

    public String getUserTrainerAge() {
        return userTrainerAge;
    }

    public void setUserTrainerAge(String userTrainerAge) {
        this.userTrainerAge = userTrainerAge;
    }

    public String getUserTrainerCertification() {
        return userTrainerCertification;
    }

    public void setUserTrainerCertification(String userTrainerCertification) {
        this.userTrainerCertification = userTrainerCertification;
    }

    public String getUserTrainerAchievment() {
        return userTrainerAchievment;
    }

    public void setUserTrainerAchievment(String userTrainerAchievment) {
        this.userTrainerAchievment = userTrainerAchievment;
    }
}
