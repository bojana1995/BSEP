package xml.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xml.services.ReservationService;
import xml.web_services.Reservation;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@GetMapping
	public ResponseEntity<List<Reservation>> getResrvationsForUser(HttpServletRequest request) {		
		return new ResponseEntity<>(reservationService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/activate")
	public ResponseEntity<Reservation> activate(@PathVariable long id) {
		return new ResponseEntity<>(reservationService.activate(id), HttpStatus.OK);
	}
	
}
