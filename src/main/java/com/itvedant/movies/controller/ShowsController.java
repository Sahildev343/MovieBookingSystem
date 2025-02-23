package com.itvedant.movies.controller;

import java.net.ResponseCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itvedant.movies.dao.AddShowDAO;
import com.itvedant.movies.service.ShowsService;

@Controller
@RequestMapping("/showses")
public class ShowsController {

	
	@Autowired
	private ShowsService showsService;
	
	
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody AddShowDAO addShowDAO) {
		
		return ResponseEntity.ok(this.showsService.createShow(addShowDAO));
	}
}
