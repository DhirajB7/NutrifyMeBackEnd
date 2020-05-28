package com.example.demo.model;

import com.example.demo.repository.MealRepository;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import utils.DateTimeRelatedOperations;

@Document(collection = "Meal")
public class Meal {



    @Id
    Long mealId;

    String foodName;
    String foodDescription;
    double calorie;
    String date;
    String time;


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

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = new DateTimeRelatedOperations().getDateAndTime().split(" ")[0];
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = new DateTimeRelatedOperations().getDateAndTime().split(" ")[1];
    }


}
