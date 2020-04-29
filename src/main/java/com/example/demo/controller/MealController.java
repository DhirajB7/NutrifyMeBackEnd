package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Meal;
import com.example.demo.repository.MealRepository;

@RestController
public class MealController {

	@Autowired
	private MealRepository mealRepo;
	
	@PostMapping("/meal")
	public String postMeal(@RequestBody Meal data) {
		System.out.println(data.isDayLimit());
		mealRepo.save(data);
		return "ADDED MEAL WITH ID : "+data.getMealId();
	}

	@GetMapping("/meal/all")
	public List<Meal> getAllMeal() {
		return mealRepo.findAll();
	}

	@GetMapping("/meal/{mealId}")
	public Optional<Meal> getOneMeal(@PathVariable("mealId") Long id) {

		 return mealRepo.findById(id);

	}

	@DeleteMapping("/meal/{mealId}")
	public String deleteOneMeal(@PathVariable("mealId") Long id) {

		mealRepo.deleteById(id); 
		
		return "MEAL With Id : "+id+" DELETED.";

	}

	@PutMapping("/meal/{mealId}")
	public String  oneMealUpdate(@PathVariable("mealId") Long id, @RequestBody Meal newData) {

		mealRepo.deleteById(id); 
		
		newData.setMealId(id);
		
		mealRepo.save(newData);
		
		return "MEAL UPDATED";
	}

}
