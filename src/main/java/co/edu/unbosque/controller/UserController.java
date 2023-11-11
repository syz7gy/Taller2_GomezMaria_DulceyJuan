package co.edu.unbosque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.User;
import co.edu.unbosque.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/User")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:8081", "*"})
@Transactional
public class UserController {
	
	@Autowired
	private UserService userServ;
	
	public UserController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping(path="/greet")
	public ResponseEntity<String> greet(){
		return new ResponseEntity<String>("Good morning, world", HttpStatus.OK);
	}
	
	@PostMapping(path="/createuserjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewUserWithJson(@RequestBody User newUser){
		int status = userServ.create(newUser);
		if(status == 0) {
			return new ResponseEntity<>("User succesfuly created", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error creating the user.", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping(path= "/getall")
	public ResponseEntity<List<User>> getAll(){
		List<User> users = userServ.getAll();
		if(users.isEmpty()) {
			return new ResponseEntity<List<User>>(users, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<User>>(users, HttpStatus.ACCEPTED);
		}
	}
	
	

}
