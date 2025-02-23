package com.itvedant.movies.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.itvedant.movies.service.MoviesService;

@Controller
@RequestMapping("/movies")
public class MoviesController {

	@Autowired
	private MoviesService moviesService;
	
	
	@PostMapping("/{id}")
	public ResponseEntity<?> upload(@PathVariable Integer id, @RequestParam("file") MultipartFile file) {
		
		return ResponseEntity.ok(this.moviesService.storeFile(id, file));
	}
	
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity<?> download(@PathVariable String fileName) {
		
		Resource resource = this.moviesService.loadFile(fileName);
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
				                  .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\"" + fileName + "\"").body(resource);
	}
}
