package com.springproject.curso.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.curso.entities.User;


@RestController //recurso web implementada por um controlador rest
@RequestMapping(value="/users") // setando caminho para localhost:8080/users
public class UserResource {

	@GetMapping // método que responde a requisição get do http
	public ResponseEntity<User> findAll(){
		User u = new User(1L, "aaaa", "aaa@gmail.com", "9999", "12345");
		return ResponseEntity.ok().body(u); // retorna User feita a requisição
	}
}
