package com.example.demo.model;

import utils.DateTimeRelatedOperations;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class UserDateModel {

    LinkedHashMap<String, ArrayList<String>> historyOneEntry = new LinkedHashMap<>();

    public String getHistoryOneEntry() {
        return historyOneEntry.toString();
    }

    public void setHistoryOneEntry(ArrayList<String> listOfMeals) {
        String key = new DateTimeRelatedOperations().getDateAndTime().split(" ")[0];
        this.historyOneEntry.put(key,listOfMeals);
    }
}
