package com.example.demo.controller;

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

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin()
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("")
	public String postUser(@RequestBody User data) {
		boolean flag = userRepo.findAll().stream().anyMatch(a->a.getUsername().equalsIgnoreCase(data.getUsername()));
		if(!flag){
			userRepo.save(data);
			return "USER ADDED WITH USERNAME : "+data.getUsername();
		}else{
			return "USER ALREADY IN DB";
		}

	}

	@GetMapping("/all")
	public List<User> getAllUser() {
		return userRepo.findAll();
	}

	@GetMapping("/check/{un}")
	public Boolean getAllUsernames(@PathVariable String un) {
		return userRepo.findAll().stream().map(User::getUsername).collect(Collectors.toList()).contains(un);
	}

	@GetMapping("/{un}")
	public Optional<User> getOneUser(@PathVariable String un) {
		return userRepo.findById(un);
	}

	@GetMapping("/this/{un}")
	public String getThisUser(@PathVariable String un) {

		User user = userRepo.findById(un).get();

		String cal = String.valueOf(user.getCaloriesPerDay());

		String history = user.getHistory();

		return cal+" "+history;
	}

	@DeleteMapping("/{un}")
	public String deleteOneUser(@PathVariable String un) {

		userRepo.delete(userRepo.findByUsername(un));

		return "USER WITH USERNAME "+un+" DELETED";

	}

    @PutMapping("/{un}/status/{data}")
    public String oneUserUpdateStatus(@PathVariable String un, @PathVariable String data) {

          User user = userRepo.findByUsername(un);
          user.setUserStatus(Boolean.parseBoolean(data));
          userRepo.save(user);

        return "USER STATUS UPDATED";
    }

	@PutMapping("/{un}/role/{data}")
	public String oneUserUpdateRole(@PathVariable String un, @PathVariable String data) {

		User user = userRepo.findByUsername(un);
		user.setRole(data.toUpperCase());
		userRepo.save(user);

		return "USER ROLE UPDATED";
	}



}
