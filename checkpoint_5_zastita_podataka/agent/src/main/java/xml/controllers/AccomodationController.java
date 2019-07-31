package xml.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import xml.services.AccomodationSpringService;
import xml.services.ImagesService;
import xml.services.ReviewService;
import xml.web_services.Accomodation;
import xml.web_services.Agent;
import xml.web_services.Images;
import xml.web_services.Reservation;
import xml.web_services.ReservationHelper;
import xml.web_services.Review;

@RestController
@RequestMapping("/accomodation")
public class AccomodationController {

	@Autowired
	private AccomodationSpringService accomodationSpringService;
	
	@Autowired
	private ImagesService imagesService;
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping
	public ResponseEntity<List<Accomodation>> getAccomodations() {
		return new ResponseEntity<>(this.accomodationSpringService.getAccomodations(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Accomodation> create(@RequestBody Accomodation accomodation, HttpServletRequest request) {
		accomodation.setAgent((Agent)request.getSession().getAttribute("agent"));
		Accomodation a = this.accomodationSpringService.save(accomodation);
		
		if (a != null) 
			return new ResponseEntity<>(a, HttpStatus.CREATED);
		
		return new ResponseEntity<>(a, HttpStatus.BAD_REQUEST);
	}
	
	
	@PostMapping("/upload-multiple-files")
    public ResponseEntity<Accomodation> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, @RequestParam("accomodation") Long id) throws IOException {
		Accomodation accomodation = this.accomodationSpringService.findById(id);
		
		if (accomodation == null) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		List<Images> imagesList = new ArrayList<>();
		for(MultipartFile mf : files) {
			FileOutputStream out = new FileOutputStream("C:\\Users\\Bojana\\Desktop\\" + mf.getOriginalFilename());
			out.write(mf.getInputStream().read());
			out.close();
			Images im = new Images();
			
			im.setImageUrl("C:\\\\Users\\\\Bojana\\\\Desktop\\\\" + mf.getOriginalFilename());
			imagesList.add(this.imagesService.save(im));
		}
		accomodation.getImages().addAll(imagesList);
		this.accomodationSpringService.save(accomodation);
		this.accomodationSpringService.sendAccomodationToServer(accomodation);
		
		return new ResponseEntity<>(null, HttpStatus.OK);
    }
	
	
	@PostMapping("/reserve")
	public ResponseEntity<Reservation> reserveAccomodation(@RequestBody ReservationHelper reservationHelper, HttpServletRequest request) {		
		Reservation r = this.accomodationSpringService.tryToReserve(reservationHelper, (Agent)request.getSession().getAttribute("agent"));
		
		if (r != null) {
			return new ResponseEntity<>(r, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}	
	
}
