package xml.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xml.model.ChatHistory;
import xml.model.User;
import xml.model.UserStatus;
import xml.model.UserType;
import xml.service.UserService;
import xml.web_services.Agent;

//8099 - admin; 8098 - user
//@CrossOrigin - omogucava unakrsne zahteve (od admina i user-a)
@CrossOrigin(origins = {"http://localhost:8099","http://localhost:8098"}, maxAge = 3600, allowCredentials = "true")
@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//BS: user application
	@GetMapping("/chat")
	public ResponseEntity<List<ChatHistory>> chat(HttpServletRequest request) {
		return new ResponseEntity<>(this.userService.chat((User)request.getSession().getAttribute("user")), HttpStatus.OK);
	}
	
	//BS: user application
	//NV: admin application
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody User user, HttpServletRequest request,  @RequestParam String u) {
		logger.debug("REST request to login into system!");
		
		User us = this.userService.findByUsername(user);
		
		if (us != null && us.getUserStatus() == UserStatus.ACTIVATED) {
			if (us.getUserType() == UserType.USER && u.equals("user")) {
				request.getSession().setAttribute("user", us);
				logger.info("Uspesno se ulogovao obican korisnik: " + us.getUsername() + ", " + new Date());
				return new ResponseEntity<>(HttpStatus.OK);
			} else if (us.getUserType() == UserType.ADMIN && u.equals("admin")) {
				request.getSession().setAttribute("admin", us);
				logger.info("Uspesno se ulogovao admin: " + us.getUsername() + ", " + new Date());
				return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else if (us != null && us.getUserStatus() == UserStatus.PENDING){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//BS: user application
	//NV: admin application
	@GetMapping("/logout")
	public ResponseEntity logout(HttpServletRequest request, @RequestParam String u) {
		logger.debug("REST request to logout from the system!");
		
		if(u.equals("admin")) {
			User us = (User)request.getSession().getAttribute("admin");
			logger.info("Uspesno se izlogovao admin: " + us.getUsername() + ", " + new Date());
			request.getSession().removeAttribute("admin");
		}else if (u.equals("user")) {
			User us = (User)request.getSession().getAttribute("user");
			logger.info("Uspesno se izlogovao obican korisnik: " + us.getUsername() + ", " + new Date());
			request.getSession().removeAttribute("user");
		}
			
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//BS: user application
	//NV: admin application
	@GetMapping("/account")
	public ResponseEntity<User> account(HttpServletRequest request, @RequestParam String u) {
		logger.debug("REST request to get account data!");
		
		User user = (User) request.getSession().getAttribute("user");
		User admin = (User) request.getSession().getAttribute("admin");
		
		if (user != null && u.equals("user")) {
			logger.info("Trenutno je prijavljen obican korisnik: " + user.getUsername() + ", " + new Date());
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else if (admin != null && u.equals("admin")) {
			logger.info("Trenutno je prijavljen admin: " + admin.getUsername() + ", " + new Date());
			return new ResponseEntity<>(admin, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	//BS: user application
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody User user, HttpServletRequest request) {
		logger.debug("REST request to get register user!");
		
		boolean b = this.userService.register(user);
		
		if (b) {
			logger.info("Uspesna registracija obicnog korisnika: " + user.getUsername() + ", " + new Date());
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	//NV: admin application
	@GetMapping("/not-activated")
	public ResponseEntity<List<User>> getNotActivated() {
		logger.debug("REST request to get not activated users");
		
		return new ResponseEntity<>(this.userService.getNotAcctivated(), HttpStatus.OK);
	}
	
	//NV: admin application
	@GetMapping("/activated")
	public ResponseEntity<List<User>> getActivated() {
		logger.debug("REST request to get activated users");
		
		return new ResponseEntity<>(this.userService.getAcctivated(), HttpStatus.OK);
	}
	
	//NV: admin application
	@PostMapping("/approve")
	public ResponseEntity approve(@RequestBody String id) {
		logger.debug("REST request to approve registration!");
		
		boolean b = this.userService.setStatus(id.substring(0, id.length()-1), UserStatus.ACTIVATED); //id.length()-1 -> L(ong)
		
		if (b) 
		{
			logger.info("Odobrena aktivacija korisnickog naloga sa id-jem: " + id + ", " + new Date());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//NV: admin application
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable long id) {
		logger.debug("REST request to delete user!");
		
		boolean b = this.userService.delete(id);
		
		if (b) {
			
			logger.info("Obrisan korisnicki nalog sa id-jem: " + id + ", " + new Date());
			return new ResponseEntity<>(HttpStatus.OK);
	
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//NV: admin application
	@PostMapping("/decline")
	public ResponseEntity decline(@RequestBody String id) {
		logger.debug("REST request to decline registration!");
		
		boolean b = this.userService.setStatus(id.substring(0, id.length()-1), UserStatus.DECLINED); //id.length()-1 -> L(ong)
		
		if (b) {
			logger.info("Odbijena aktivacija korisnickog naloga sa id-jem: " + id + ", " + new Date());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//NV: admin application
	@GetMapping("/block/{id}")
	public ResponseEntity decline(@PathVariable long id) {
		logger.debug("REST request to block user!");
		
		boolean b = this.userService.block(id);
		
		if (b) 
		{
			logger.info("Blokiran korisnik sa id-jem: " + id + ", " + new Date());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//BS: user application
	@PostMapping("/activation-email")
	public ResponseEntity emailActivation(@RequestBody String username) {
		logger.debug("REST request to reset password!");
		
		boolean b = this.userService.activationEmail(username.substring(0, username.length()-1)); //id.length()-1 -> L(ong)
		
		if (b) {
			logger.info("Uspesno je aktiviran korisnicki nalog: " + username + ", " + new Date());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//BS: user application
	@PostMapping("/reset-password")
	public ResponseEntity resetPassword(@RequestBody User user) {
		if (this.userService.resetPassword(user)) {
			logger.info("Uspesno je promenjen password: " + user.getUsername() + ", " + new Date());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
