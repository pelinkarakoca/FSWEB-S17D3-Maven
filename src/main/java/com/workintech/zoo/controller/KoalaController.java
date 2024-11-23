package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class KoalaController {

	private Map<Integer, Koala> koalas;

	public KoalaController() {
		koalas = new HashMap<>();
	}

	@GetMapping("/koalas")
	public List<Koala> findAllKoalas() {
		return koalas.values().stream().toList();
	}

	@GetMapping("/koalas/{id}")
	public Koala findKoalaById(@PathVariable Integer id) {
		if(!koalas.containsKey(id)){
			throw new ZooException("Id is not found", HttpStatus.NOT_FOUND);
		}
		return koalas.get(id);
	}

	@PostMapping("/koalas")
	public Koala saveKoala(@RequestBody Koala koala) {
		if(koalas.containsKey(koala.getId())){
			throw new ZooException("This koala is already exist", HttpStatus.BAD_REQUEST);
		}
		if(koala.getId()<= 0){
			throw new ZooException("Id must be greater than zero", HttpStatus.BAD_REQUEST);
		}
		koalas.put(koala.getId(), koala);
		return koala;
	}

	@PutMapping("/koalas/{id}")
	public ResponseEntity<Koala> updateKoala(@PathVariable("id") Integer id, @RequestBody Koala koala) {
		if(!koalas.containsKey(id)){
			throw new ZooException("This id is not exist", HttpStatus.BAD_REQUEST);
		}
		if(!koala.getId().equals(id)){
			throw new ZooException("Given ids are not matching", HttpStatus.BAD_REQUEST);
		}
		koalas.replace(koala.getId(), koala);
		return ResponseEntity.ok(koala);
	}

	@DeleteMapping("/koalas/{id}")
	public HttpStatus deleteKoala(@PathVariable Integer id) {
		if(!koalas.containsKey(id)){
			throw new ZooException("Id is not exist", HttpStatus.BAD_REQUEST);
		}
		koalas.remove(id);
		return HttpStatus.OK;
	}
}

