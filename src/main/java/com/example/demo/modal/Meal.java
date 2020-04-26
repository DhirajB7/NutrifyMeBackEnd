package com.example.demo.modal;

public class Meal {

	Long mealId;
	String foodName;
	String foodDescription;
	int calorie;
	String date;
	String time;
	boolean isInDayLimits;
	
	public Long getMealId() {
		return mealId;
	}
	public void setMealId(Long mealId) {
		this.mealId = mealId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getFoodDescription() {
		return foodDescription;
	}
	public void setFoodDescription(String foodDescription) {
		this.foodDescription = foodDescription;
	}
	public int getCalorie() {
		return calorie;
	}
	public void setCalorie(int calorie) {
		this.calorie = calorie;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public boolean isInDayLimits() {
		return isInDayLimits;
	}
	public void setInDayLimits(boolean isInDayLimits) {
		this.isInDayLimits = isInDayLimits;
	}
	
	
}
