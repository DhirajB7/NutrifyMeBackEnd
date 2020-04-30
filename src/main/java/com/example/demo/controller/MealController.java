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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Meal;
import com.example.demo.repository.MealRepository;

import utils.GetCal;

@RestController
@RequestMapping(value = "/meal")
public class MealController {

	@Autowired
	private MealRepository mealRepo;
	
	@PostMapping("")
	public String postMeal(@RequestBody Meal data) {
		
		if(!mealRepo.findAll().stream().anyMatch(a->a.toString().equalsIgnoreCase(data.getFoodName()))) {
			data.setCalorie(new GetCal().getCalByExternalCall(data.getFoodName()));
		}
		
		mealRepo.save(data);
		
		return "ADDED MEAL WITH ID : "+data.getMealId();
	}

	@GetMapping("/all")
	public List<Meal> getAllMeal() {
		return mealRepo.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Meal> getOneMeal(@PathVariable Long id) {

		 return mealRepo.findById(id);

	}

	@DeleteMapping("/{id}")
	public String deleteOneMeal(@PathVariable Long id) {

		mealRepo.deleteById(id); 
		
		return "MEAL With Id : "+id+" DELETED.";

	}

	@PutMapping("/{id}")
	public String  oneMealUpdate(@PathVariable Long id, @RequestBody Meal newData) {

		mealRepo.deleteById(id); 
		
		newData.setMealId(id);
		
		mealRepo.save(newData);
		
		return "MEAL UPDATED";
	}

}
