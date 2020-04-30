package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {
	
	@Id
	String userName;
	
	String firstName;
	String lastName;
	String email;
	String password;
	String phone;
	double caloriesPerDay;
	boolean userStatus;
	
	public double getCaloriesPerDay() {
		return caloriesPerDay;
	}
	
	public void setCaloriesPerDay(double caloriesPerDay) {
		this.caloriesPerDay = caloriesPerDay;
	}
	
	public boolean isUserStatus() {
		return userStatus;
	}
	
	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
