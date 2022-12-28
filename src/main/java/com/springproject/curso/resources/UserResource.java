package com.springproject.curso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.curso.entities.User;
import com.springproject.curso.services.UserService;


@RestController //recurso web implementada por um controlador rest
@RequestMapping(value="/users") // setando caminho para localhost:8080/users
public class UserResource {

	@Autowired
	private UserService userService;
	
	@GetMapping // método que responde a requisição get do http
	public ResponseEntity<List<User>> findAll(){
		List<User> list = userService.findAll();
		return ResponseEntity.ok().body(list); // retorna User feita a requisição
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User user = userService.findById(id);
		return ResponseEntity.ok().body(user);
	}
}
