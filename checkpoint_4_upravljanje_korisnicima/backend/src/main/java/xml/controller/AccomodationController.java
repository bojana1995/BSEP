package xml.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xml.model.ReservationHelper;
import xml.model.SearchObject;
import xml.model.User;
import xml.service.AccomodationSpringService;
import xml.service.ReviewService;
import xml.web_services.Accomodation;
import xml.web_services.Reservation;
import xml.web_services.Review;

//8099 - admin; 8098 - user
//@CrossOrigin - omogucava unakrsne zahteve (od admina i user-a)
@CrossOrigin(origins = {"http://localhost:8099","http://localhost:8098"}, maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/accomodation")
public class AccomodationController {

	@Autowired
	private AccomodationSpringService accomodationSpringService;
	
	@Autowired
	private ReviewService reviewService;
	
	//BS: user application
	@GetMapping
	public ResponseEntity<List<Accomodation>> getAccomodations() {
		return new ResponseEntity<>(this.accomodationSpringService.getAccomodations(), HttpStatus.OK);
	}
	
	//BS: user application
	@PostMapping("/{id}/review")
	public ResponseEntity<Review> create(@RequestBody Review review, @PathVariable long id, HttpServletRequest request) {
		review.setAccomodation(this.accomodationSpringService.findById(id));
		review.setAllowed(false);
		review.setUserId(((User)request.getSession().getAttribute("user")).getId());
		
		return new ResponseEntity<>(this.reviewService.save(review), HttpStatus.OK);
	}
	
	//BS: user application
	@GetMapping("/{id}")
	public ResponseEntity<Accomodation> getAccomodation(@PathVariable long id) {
		return new ResponseEntity<>(this.accomodationSpringService.findById(id), HttpStatus.OK);
	}
	
	//BS: user application
	@PostMapping("/search")
	public ResponseEntity<List<Accomodation>> searchAccomodations(@RequestBody SearchObject searchObject) {
		return new ResponseEntity<>(this.accomodationSpringService.searchAccomodations(searchObject), HttpStatus.OK);
	}
	
	//BS: user application
	@PostMapping("/reserve")
	public ResponseEntity<Reservation> reserveAccomodation(@RequestBody ReservationHelper reservationHelper, HttpServletRequest request) {
		Reservation reservation = this.accomodationSpringService.saveReservation(reservationHelper, (User) request.getSession().getAttribute("user"));
		
		return new ResponseEntity<>(reservation, HttpStatus.OK);
	}
	
	//BS: user application
	@GetMapping("/sort/{sortBy}")
	public ResponseEntity<Accomodation> sort(@PathVariable String sortBy){
		System.out.println("\n\n\t\t\tSORT (not implemented) by "+sortBy);
		
		return null;
	}
	
}
