package com.example.demo.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin()

public class MealController {

	@Autowired
	private MealRepository mealRepo;
	
	@PostMapping("")
	public String postMeal(@RequestBody Meal data) {

		boolean flag = mealRepo.findAll().stream().anyMatch(a->a.getFoodName().equalsIgnoreCase(data.getFoodName()));
		
		if(!flag) {
			data.setCalorie(new GetCal().getCalByExternalCall(data.getFoodName()));
			mealRepo.save(data);
			return "ADDED MEAL WITH NAME : "+data.getFoodName();
		}else{

			return "MEAL ALREADY IN DB.";

		}

	}

	@GetMapping("/all")
	public List<Meal> getAllMeal() {

		return mealRepo.findAll();

	}

	@GetMapping("/all/{name}")
	public List<Meal> getAllMealByOneUSer(@PathVariable String name) {

		return mealRepo.findAll().stream().filter(a->a.getAddedByUserName().equalsIgnoreCase(name)).collect(Collectors.toList());

	}

	@GetMapping("/{id}")
	public Meal getOneMeal(@PathVariable String id) {

		 return mealRepo.findByFoodName(id);

	}

	@DeleteMapping("/{id}")
	public String deleteOneMeal(@PathVariable String id) {

		mealRepo.delete(mealRepo.findByFoodName(id));
		
		return "MEAL With Id : "+id+" DELETED.";

	}

	//Better Put Mapping may follow
	@PutMapping("/{id}")
	public String  oneMealUpdate(@PathVariable String id, @RequestBody Meal newData) {

		mealRepo.deleteById(id);
		
		mealRepo.save(newData);
		
		return "MEAL UPDATED";
	}

	@PutMapping("/{id}/desc/{desc}")
	public String oneMealDescriptionUpdate(@PathVariable String id,@PathVariable String desc){


		Meal meal = mealRepo.findByFoodName(id);

		meal.setFoodDescription(desc);

		mealRepo.save(meal);

		return "MEAL UPDATED with desc "+ desc;
	}

}
