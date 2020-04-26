package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modal.Meal;

@RestController
public class MealController {

	List<Meal> arrayListMeal = new ArrayList<Meal>();
	AtomicLong atomicLong = new AtomicLong();

	@PostMapping("/meal")
	Meal postMeal(@RequestBody Meal data) {
		data.setMealId(atomicLong.incrementAndGet());
		arrayListMeal.add(data);
		return data;
	}

	@GetMapping("/meal/all")
	List<Meal> getAllMeal() {
		return arrayListMeal;
	}

	@GetMapping("/meal/{mealId}")
	Meal getOneMeal(@PathVariable("mealId") Long id) {

		return arrayListMeal.stream().filter(a -> a.getMealId() == id).findFirst().get();

	}

	@DeleteMapping("/meal/{mealId}")
	Meal deleteOneMeal(@PathVariable("mealId") Long id) {

		Meal toBeDeleated = arrayListMeal.stream().filter(a -> a.getMealId() == id).findFirst().get();

		arrayListMeal.remove(toBeDeleated);

		return toBeDeleated;

	}

	@PutMapping("/meal/{mealId}")
	Meal oneMealUpdate(@PathVariable("mealId") Long id, @RequestBody Meal newData) {

		Meal toBeDeleated = arrayListMeal.stream().filter(a -> a.getMealId() == id).findFirst().get();

		arrayListMeal.remove(toBeDeleated);
		
		newData.setMealId(id);
		
		arrayListMeal.add(newData);
		
		return newData;
	}

}
