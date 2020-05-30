package com.example.demo.model;

import java.util.ArrayList;

public class CreateMeals {

      ArrayList<String> listMealForADay = new ArrayList<>();

    public ArrayList<String> getListMealForADay() {
        return listMealForADay;
    }

    public void setListMealForADay(Meal meal) {
        this.listMealForADay.add(meal.getFoodName()+":"+meal.getCalorie());
    }
}
