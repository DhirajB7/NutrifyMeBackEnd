package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.model.CreateMeals;
import com.example.demo.model.User;
import com.example.demo.model.UserDateModel;
import com.example.demo.repository.UserRepository;
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

	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("/add/{un}")
	public String postMeal(@RequestBody Meal data,@PathVariable String un) {

		try {
			
		boolean flag = mealRepo.findAll().stream().anyMatch(a->a.getFoodName().equalsIgnoreCase(data.getFoodName()));
		
		if(!flag) {
			data.setCalorie(new GetCal().getCalByExternalCall(data.getFoodName()));
			mealRepo.save(data);
			getDataReady(data,un);
			return "ADDED MEAL WITH NAME : "+data.getFoodName();
		}else{
			getDataReady(mealRepo.findByFoodName(data.getFoodName()),un);
			return "MEAL ALREADY IN DB.";
		}
		}catch (Exception e) {
			return "CALAROIES CANNOT BE FETCHED FOR ENTERED FOOD NAME.";
		}

	}

	private void getDataReady(Meal meal,String userName){


		CreateMeals createMeals = new CreateMeals();
		UserDateModel userDataModel = new UserDateModel();


		createMeals.setListMealForADay(meal);

		userDataModel.setHistoryOneEntry(createMeals.getListMealForADay());

		User user = userRepo.findByUsername(userName);

		String fromDb = user.getHistory();

		String toDb = userDataModel.getHistoryOneEntry()+",";

         user.setHistory(oneString(fromDb,toDb));

         userRepo.save(user);

	}

	private String oneString(String fromDb,String toDb){



		if(fromDb.split("]},").length==1 && fromDb.substring(1,11).equalsIgnoreCase(toDb.substring(1,11))){

			int startA = fromDb.indexOf("[");
			int endA = fromDb.indexOf("]");

			int startB = toDb.indexOf("[");
			int endB = toDb.indexOf("]");

			String A = fromDb.substring(startA+1,endA);

			String B = toDb.substring(startB+1,endB);

			return toDb.substring(0,startB+1)+A+","+B+toDb.substring(endB);

		}else {

			int requiredIndex = fromDb.split("},").length-1;

			String A = fromDb.split("},")[requiredIndex];

			String dateA = A.substring(1,11);

			String B = toDb;

			String dateB = B.substring(1,11);

			if(dateA.equalsIgnoreCase(dateB)){

				int startA = A.indexOf("[");
				int endA = A.indexOf("]");

				int startB = B.indexOf("[");
				int endB = B.indexOf("]");

				String AA = A.substring(startA+1,endA);

				String BB = B.substring(startB+1,endB);

				String newToDb = toDb.substring(0,startB+1)+AA+","+BB+toDb.substring(endB);

				return fromDb.replace(A+"},",newToDb);

			}else {
				return fromDb + "" + toDb;
			}


		}




	}

	@GetMapping("/all")
	public List<Meal> getAllMeal() {

		return mealRepo.findAll();

	}

	@GetMapping("/all/{name}")
	public List<Meal> getAllMealByOneUSer(@PathVariable String name) {

		return mealRepo.findAll();

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
