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

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("/user")
	public String postUser(@RequestBody User data) {
		System.out.println(data.isUserSattus());
		userRepo.save(data);
		return "USER ADDED WITH USERNAME : "+data.getUserName();
	}

	@GetMapping("/user/all")
	public List<User> getAllUser() {
		return userRepo.findAll();
	}

	@GetMapping("/user/{userName}")
	public Optional<User> getOneUser(@PathVariable("userName") String un) {
		return userRepo.findById(un);
	}

	@DeleteMapping("/user/{userName}")
	public String deleteOneUser(@PathVariable("userName") String un) {

		userRepo.deleteById(un);

		return "USER WITH USERNAME "+un+" DELETED";

	}

	@PutMapping("/user/{userName}")
	public String oneUserUpdate(@PathVariable("userName") String un, @RequestBody User newData) {

		User toBeDeleated = userRepo.findAll().stream().filter(a->a.getUserName().equalsIgnoreCase(un)).findFirst().get();
		
		userRepo.deleteById(un);
		
		newData.setUserName(toBeDeleated.getUserName());
		
		userRepo.save(newData);
		
		return "USER UPDATED";
	}

	

}
