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

import com.example.demo.modal.User;

@RestController
public class UserController {

	List<User> arrayListUser = new ArrayList<User>();
	AtomicLong atomicLongUser = new AtomicLong();

	@PostMapping("/user")
	User postUser(@RequestBody User data) {
		data.setId(atomicLongUser.incrementAndGet());
		arrayListUser.add(data);
		return data;
	}

	@GetMapping("/user/all")
	List<User> getAllUser() {
		return arrayListUser;
	}

	@GetMapping("/user/{userName}")
	User getOneUser(@PathVariable("userName") String un) {

		return arrayListUser.stream().filter(a -> a.getUserName().equalsIgnoreCase(un)).findFirst().get();

	}

	@DeleteMapping("/user/{userName}")
	User deleteOneUser(@PathVariable("userName") String un) {

		User toBeDeleated = arrayListUser.stream().filter(a -> a.getUserName().equalsIgnoreCase(un)).findFirst().get();

		arrayListUser.remove(toBeDeleated);

		return toBeDeleated;

	}

	@PutMapping("/user/{userName}")
	User oneUserUpdate(@PathVariable("userName") String un, @RequestBody User newData) {

		User toBeDeleated = arrayListUser.stream().filter(a -> a.getUserName().equalsIgnoreCase(un)).findFirst().get();

		arrayListUser.remove(toBeDeleated);
		
		newData.setId(toBeDeleated.getId());
		
		arrayListUser.add(newData);
		
		return newData;
	}

	

}
