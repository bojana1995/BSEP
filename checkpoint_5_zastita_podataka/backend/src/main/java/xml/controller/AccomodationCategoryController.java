package xml.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xml.service.AccomodationCategoryService;
import xml.web_services.AccomodationCategory;

//8099 - admin; 8098 - user
//@CrossOrigin - omogucava unakrsne zahteve (od admina i user-a)
@CrossOrigin(origins = {"http://localhost:8099","http://localhost:8098"}, maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/accomodation-category")
public class AccomodationCategoryController {

	@Autowired
	private AccomodationCategoryService accomodationCategoryService;
	
	//BS: user application
	//NV: admin application
	@GetMapping
	public ResponseEntity<List<AccomodationCategory>> getAllAccomodationServices() {
		return new ResponseEntity<>(this.accomodationCategoryService.getAllAccomodationCategories(), HttpStatus.OK);
	}
	
	//NV: admin application
	@PostMapping
	public ResponseEntity<AccomodationCategory> create(@RequestBody AccomodationCategory as) {
		AccomodationCategory accomodationService = this.accomodationCategoryService.save(as);
		
		if (accomodationService != null) 
			return new ResponseEntity<>(accomodationService, HttpStatus.CREATED);
		
		return new ResponseEntity<>(accomodationService, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping
	public ResponseEntity<AccomodationCategory> updateAccomodationService(@RequestBody AccomodationCategory as) {
		AccomodationCategory accomodationCategory = this.accomodationCategoryService.save(as);
		
		if (accomodationCategory != null) 
			return new ResponseEntity<>(accomodationCategory, HttpStatus.OK);
		
		return new ResponseEntity<>(accomodationCategory, HttpStatus.BAD_REQUEST);
	}
	
	//NV: admin application
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity deleteAccomodationService(@PathVariable Long id) {
		boolean deleted = this.accomodationCategoryService.delete(id);
		
		if (deleted) 
			return new ResponseEntity(HttpStatus.OK);
		
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
}
