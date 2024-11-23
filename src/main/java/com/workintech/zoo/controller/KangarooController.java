package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;

import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class KangarooController {

	private Map<Integer, Kangaroo> kangaroos;

	public KangarooController() {
		kangaroos = new HashMap<>();
	}

	@GetMapping("/kangaroos")
	public List<Kangaroo> findAllKangaroos() {
		return kangaroos.values().stream().toList();
	}

	@GetMapping("/kangaroos/{id}")
	public Kangaroo findKangarooById(@PathVariable Integer id) {
	if(!kangaroos.containsKey(id)){
			throw new ZooException("Id is not found", HttpStatus.NOT_FOUND);
		}
		return kangaroos.get(id);
	}

	@PostMapping("/kangaroos")
	public Kangaroo saveKangaroo(@RequestBody Kangaroo kangaroo) {
		if(kangaroos.containsKey(kangaroo.getId())){
			throw new ZooException("This kangaroo is already exist", HttpStatus.BAD_REQUEST);
		}
		if(kangaroo.getId()<= 0){
			throw new ZooException("Id must be greater than zero", HttpStatus.BAD_REQUEST);
		}
		kangaroos.put(kangaroo.getId(), kangaroo);
		return kangaroo;
	}

	@PutMapping("/kangaroos/{id}")
	public ResponseEntity<Kangaroo> updateKangaroo(@PathVariable Integer id, @RequestBody Kangaroo kangaroo) {
		if(!kangaroos.containsKey(id)){
			throw new ZooException("This id is not exist", HttpStatus.BAD_REQUEST);
		}
		if(!kangaroo.getId().equals(id)){
			throw new ZooException("Id and kangaroo's id are not matching", HttpStatus.BAD_REQUEST);
		}
		kangaroos.replace(id, kangaroo);
		return ResponseEntity.ok(kangaroo);
	}

	@DeleteMapping("/kangaroos/{id}")
	public Kangaroo deleteKangaroo(@PathVariable("id") Integer id) {
		if(!kangaroos.containsKey(id)){
			throw new ZooException("Id is not exist", HttpStatus.BAD_REQUEST);
		}
		return kangaroos.remove(id);
	}
}
