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

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("")
	public String postUser(@RequestBody User data) {
		userRepo.save(data);
		return "USER ADDED WITH USERNAME : "+data.getUserName();
	}

	@GetMapping("/all")
	public List<User> getAllUser() {
		return userRepo.findAll();
	}

	@GetMapping("/{un}")
	public Optional<User> getOneUser(@PathVariable String un) {
		return userRepo.findById(un);
	}

	@DeleteMapping("/{un}")
	public String deleteOneUser(@PathVariable String un) {

		userRepo.deleteById(un);

		return "USER WITH USERNAME "+un+" DELETED";

	}

	@PutMapping("/{un}")
	public String oneUserUpdate(@PathVariable String un, @RequestBody User newData) {

		User toBeDeleated = userRepo.findAll().stream().filter(a->a.getUserName().equalsIgnoreCase(un)).findFirst().get();
		
		userRepo.deleteById(un);
		
		newData.setUserName(toBeDeleated.getUserName());
		
		userRepo.save(newData);
		
		return "USER UPDATED";
	}

	

}
