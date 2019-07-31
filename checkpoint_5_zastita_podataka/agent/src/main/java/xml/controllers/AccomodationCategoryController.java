package xml.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xml.services.AccomodationCategoryService;
import xml.web_services.AccomodationCategory;

@RestController
@RequestMapping("/accomodation-category")
public class AccomodationCategoryController {

	@Autowired
	private AccomodationCategoryService accomodationCategoryService;
	
	@GetMapping
	public ResponseEntity<List<AccomodationCategory>> getAllAccomodationCategories() {
		return new ResponseEntity<>(this.accomodationCategoryService.getAllAccomodationCategories(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<AccomodationCategory> create(@RequestBody AccomodationCategory as) {
		AccomodationCategory accomodationCategory = this.accomodationCategoryService.save(as);
		
		if (accomodationCategory != null) 
			return new ResponseEntity<>(accomodationCategory, HttpStatus.CREATED);
		
		return new ResponseEntity<>(accomodationCategory, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping
	public ResponseEntity<AccomodationCategory> updateAccomodationCategory(@RequestBody AccomodationCategory as) {
		AccomodationCategory accomodationCategory = this.accomodationCategoryService.save(as);
		
		if (accomodationCategory != null) 
			return new ResponseEntity<>(accomodationCategory, HttpStatus.OK);
		
		return new ResponseEntity<>(accomodationCategory, HttpStatus.BAD_REQUEST);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity deleteAccomodationCategory(@PathVariable Long id) {
		boolean deleted = this.accomodationCategoryService.delete(id);
		
		if (deleted) 
			return new ResponseEntity(HttpStatus.OK);
		
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
}
