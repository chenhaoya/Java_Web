package com.ch.pojo;


import java.util.Date;

public class User {

  private int id;
  private String userCode;
  private String userName;
  private String userPassword;
  private long gender;
  private java.util.Date birthday;
  private String phone;
  private String address;
  private long userRole;
  private long createdBy;
  private Date creationDate;
  private long modifyBy;
  private java.sql.Timestamp modifyDate;

  private Integer age;// 年龄

  public Integer getAge() {
    /*
     * long time = System.currentTimeMillis()-birthday.getTime(); Integer age =
     * Long.valueOf(time/365/24/60/60/1000).IntegerValue();
     */
    Date date = new Date();
    Integer age = date.getYear() - birthday.getYear();
    return age;
  }


  public String getUserRoleName() {
    return userRoleName;
  }


  public void setUserRoleName(String userRoleName) {
    this.userRoleName = userRoleName;
  }

  private String userRoleName; // 用户角色名称



  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getUserCode() {
    return userCode;
  }

  public void setUserCode(String userCode) {
    this.userCode = userCode;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }


  public long getGender() {
    return gender;
  }

  public void setGender(int gender) {
    this.gender = gender;
  }


  public java.util.Date getBirthday() {
    return birthday;
  }

  public void setBirthday(java.util.Date birthday) {
    this.birthday = birthday;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public long getUserRole() {
    return userRole;
  }

  public void setUserRole(int userRole) {
    this.userRole = userRole;
  }


  public long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(long createdBy) {
    this.createdBy = createdBy;
  }


  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }


  public long getModifyBy() {
    return modifyBy;
  }

  public void setModifyBy(long modifyBy) {
    this.modifyBy = modifyBy;
  }


  public java.sql.Timestamp getModifyDate() {
    return modifyDate;
  }

  public void setModifyDate(java.sql.Timestamp modifyDate) {
    this.modifyDate = modifyDate;
  }

}
