package com.example.spe_majorproject.controller;

import com.example.spe_majorproject.repository.UserCredentialsRepository;
//import com.example.spe_majorproject.services.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.spe_majorproject.bean.*;

import java.util.List;


@RestController
@RequestMapping("/home")
public class MainController {

	@Autowired
	private UserCredentialsRepository usercredrepo;

	@PostMapping("/login")
	public ResponseEntity validateUser(@RequestBody UserCredentials cred)
	{

		if(!cred.getEmail().isEmpty()) {
			System.out.println("In Service : " + cred.getEmail());
			UserCredentials usr = usercredrepo.findById(cred.getEmail()).orElse(new UserCredentials());
			System.out.println("Here:" + usr);
			if (usr.getPassword() != null && usr.getPassword().equals(cred.getPassword())) {
				return ResponseEntity.ok(HttpStatus.OK);
			}
		}
		return ResponseEntity.badRequest();
	}
	@PostMapping("/register")
	public Boolean registerUser(@RequestBody UserCredentials newUser)
	{
		if(usercredrepo.existsById(newUser.getEmail()))
		{
			return false;
		}
		usercredrepo.save(newUser);
		return true;
	}


}
