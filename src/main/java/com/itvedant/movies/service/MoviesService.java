package com.itvedant.movies.service;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.itvedant.movies.FileStorageProperties;
import com.itvedant.movies.entity.Movie;

import com.itvedant.movies.exception.StorageException;
import com.itvedant.movies.repository.MoviesRepository;


@Service
public class MoviesService {

	@Autowired
	private MoviesRepository moviesRepository;
	
	
	private final Path rootLocation;
	
	public MoviesService(FileStorageProperties properties) {
		this.rootLocation= Paths.get(properties.getUploadDir());
		
		try {
			Files.createDirectories(rootLocation);
		} catch (Exception e) {
			throw new StorageException("cannot initialized directories");
		}
	}
	
	
	public String storeFile(Integer id, MultipartFile file) {
		
		try {
		if(file.isEmpty()) {
			throw new StorageException("Could not store Empty File");
			
			
		}
		
		
		Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename())); 
		
		try(InputStream inputStream = file.getInputStream()) {
			Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			
		}
		
		Movie movies = this.moviesRepository.findById(id).orElse(null);
		
		String fileUploadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				            .path("/movieses/download/")
				            .path(file.getOriginalFilename())
				            .toUriString();
		
		movies.setMoviesPoster(fileUploadUrl);
		
		this.moviesRepository.save(movies);
		 
		} catch (Exception e) {
			throw new StorageException("Could Not Store File");
		}
		
		return "File Stored";
		            
		
	}
	
	public Resource loadFile(String fileName) {
		
		Path file = rootLocation.resolve(fileName);
		
		try {
			Resource resource = new UrlResource(file.toUri());
			
			if(resource.exists() || resource.isReadable()) {
				return resource;
			}else {
				throw new StorageException("Could Not Load File");
			}
		} catch (MalformedURLException e) {
			throw new StorageException("Could Not load resource");
		}
	}
	
	
}
