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

import xml.services.AccomodationTypeService;
import xml.web_services.AccomodationType;


@RestController
@RequestMapping("/accomodation-type")
public class AccomodationTypeController {

	@Autowired
	private AccomodationTypeService accomodationTypeService;
	
	@GetMapping
	public ResponseEntity<List<AccomodationType>> getAllAccomodationTypes() {
		return new ResponseEntity<>(this.accomodationTypeService.getAllAccomodationTypes(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<AccomodationType> createAccomodationType(@RequestBody AccomodationType at) {
		AccomodationType accomodationType = this.accomodationTypeService.save(at);
		
		if (accomodationType != null) 
			return new ResponseEntity<>(accomodationType, HttpStatus.CREATED);
		
		return new ResponseEntity<>(accomodationType, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping
	public ResponseEntity<AccomodationType> updateAccomodationType(@RequestBody AccomodationType at) {
		AccomodationType accomodationType = this.accomodationTypeService.save(at);
		
		if (accomodationType != null) 
			return new ResponseEntity<>(accomodationType, HttpStatus.OK);
		
		return new ResponseEntity<>(accomodationType, HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<AccomodationType> deleteAccomodationType(@PathVariable Long id) {
		boolean deleted = this.accomodationTypeService.delete(id);
		
		if (deleted) 
			return new ResponseEntity(HttpStatus.OK);
		
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
}
