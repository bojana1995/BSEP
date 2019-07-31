package xml.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xml.model.User;
import xml.service.ReservationService;
import xml.web_services.Reservation;

//8099 - admin; 8098 - user
//@CrossOrigin - omogucava unakrsne zahteve (od admina i user-a)
@CrossOrigin(origins = {"http://localhost:8099","http://localhost:8098"}, maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	//BS: user application
	@GetMapping("/user")
	public ResponseEntity<List<Reservation>> getResrvationsForUser(HttpServletRequest request) {
		return new ResponseEntity<>(this.reservationService.getAllForUser((User)request.getSession().getAttribute("user")), HttpStatus.OK);
	}
	
	//BS: user application
	@DeleteMapping("/{id}")
	public ResponseEntity<Reservation> cancel(@PathVariable Long id) {
		this.reservationService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
