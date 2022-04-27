package com.example.spe_majorproject.controller;
import com.example.spe_majorproject.repository.FacultyRepository;
import com.example.spe_majorproject.repository.StudentRepository;
import com.example.spe_majorproject.repository.UserCredentialsRepository;
//import com.example.spe_majorproject.services.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.spe_majorproject.bean.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/home")
public class MainController {

	@Autowired
	private UserCredentialsRepository usercredrepo;

	@Autowired
	private FacultyRepository facultyRepository;

	@Autowired
	private StudentRepository studentRepository;

	BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

	@PostMapping("/login")
	public ResponseEntity<Response> validateUser(@RequestBody UserCredentials cred)
	{
		Response response=new Response();

		if(!cred.getEmail().isEmpty()) {
			UserCredentials usr = usercredrepo.findById(cred.getEmail()).orElse(new UserCredentials());

			if (usr.getPassword() != null && bCryptPasswordEncoder.matches(cred.getPassword(), usr.getPassword())){
				response.setStatus("Success");
				response.setMessage("User Credentials Verified");

				return ResponseEntity.ok().header("Content-Type", "application/json")
						.body(response);
			}
		}
		response.setStatus("Failed");
		response.setMessage("Invalid User Credentials");
		return ResponseEntity.badRequest().header("Content-Type", "application/json")
				.body(response);
	}

	@PostMapping("/register_student")
	public ResponseEntity<Response> registerStudent(@RequestBody Student newStudent)
	{
		Response response=new Response();
		if(usercredrepo.existsById(newStudent.getEmail()))
		{
			response.setStatus("Failed");
			response.setMessage("User already exists");
			return ResponseEntity.badRequest().header("Content-Type", "application/json")
					.body(response);
		}
		UserCredentials userCredentials=new UserCredentials();
		userCredentials.setEmail(newStudent.getEmail());
		userCredentials.setPassword(bCryptPasswordEncoder.encode(newStudent.getPassword()));
		userCredentials.setName(newStudent.getName());
		userCredentials.setUserType("Student");
		usercredrepo.save(userCredentials);
		newStudent.setPassword(null);
		studentRepository.save(newStudent);
		response.setStatus("Success");
		response.setMessage("User registered successfully");
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body(response);
	}
	@PostMapping("/register_faculty")
	public ResponseEntity<Response> registerFaculty(@RequestBody Faculty newFaculty)
	{
		Response response=new Response();
		if(usercredrepo.existsById(newFaculty.getEmail()))
		{
			response.setStatus("Failed");
			response.setMessage("User already exists");
			return ResponseEntity.badRequest().header("Content-Type", "application/json")
					.body(response);
		}
		UserCredentials userCredentials=new UserCredentials();
		userCredentials.setEmail(newFaculty.getEmail());
		userCredentials.setPassword(bCryptPasswordEncoder.encode(newFaculty.getPassword()));
		userCredentials.setName(newFaculty.getName());
		userCredentials.setUserType("Faculty");
		usercredrepo.save(userCredentials);
		newFaculty.setPassword(null);
		facultyRepository.save(newFaculty);
		response.setStatus("Success");
		response.setMessage("User registered successfully");
		return ResponseEntity.ok().header("Content-Type", "application/json")
				.body(response);
	}

}
