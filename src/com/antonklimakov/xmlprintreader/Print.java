package com.antonklimakov.xmlprintreader;

/**
 * Created by Anton Klimakov on 17.06.2016.
 */
public class Print {
    private String deviceAddress;
    private String UserLogin;
    private String UserType;
    private String UserGroup;
    private String JobName;
    private String EndTime;
    private int Amount;

    public Print() {
    }

    public Print(String deviceAddress, String userLogin, String userType, String userGroup, String jobName, String endTime, int amount) {
        this.deviceAddress = deviceAddress;
        UserLogin = userLogin;
        UserType = userType;
        UserGroup = userGroup;
        JobName = jobName;
        EndTime = endTime;
        Amount = amount;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public String getUserLogin() {
        return UserLogin;
    }

    public void setUserLogin(String userLogin) {
        UserLogin = userLogin;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public String getUserGroup() {
        return UserGroup;
    }

    public void setUserGroup(String userGroup) {
        UserGroup = userGroup;
    }

    public String getJobName() {
        return JobName;
    }

    public void setJobName(String jobName) {
        JobName = jobName;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    @Override
    public String toString() {
        return "Print:: deviceAddress="+this.deviceAddress+" UserLogin=" + this.UserLogin + " Amount=" + this.Amount;
    }
}
