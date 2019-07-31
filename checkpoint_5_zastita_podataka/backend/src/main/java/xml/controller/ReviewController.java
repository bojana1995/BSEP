package xml.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xml.service.ReviewService;
import xml.web_services.Review;

//8099 - admin; 8098 - user
//@CrossOrigin - omogucava unakrsne zahteve (od admina i user-a)
@CrossOrigin(origins = {"http://localhost:8099","http://localhost:8098"}, maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	//NV: admin application
	@GetMapping("/pending")
	public ResponseEntity<List<Review>> reviews(){
		return new ResponseEntity<>(this.reviewService.findByAllowed(false), HttpStatus.OK);
	}
	
	//BS: user application
	@GetMapping("/{id}")
	public ResponseEntity<List<Review>> getReviewsByAccomodation(@PathVariable long id){
		return new ResponseEntity<>(this.reviewService.fomdByAccomodationAndAllowed(id, true), HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<Review>> getReviews(){
		return new ResponseEntity<>(this.reviewService.findByAllowed(true), HttpStatus.OK);
	}
	
	//NV: admin application
	@GetMapping("/approve/{id}")
	public ResponseEntity<Review> approve(@PathVariable long id) {
		this.reviewService.approve(id);
		
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	//NV: admin application
	@GetMapping("/decline/{id}")
	public ResponseEntity<Review> decline(@PathVariable long id) {
		this.reviewService.decline(id);
		
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
}
