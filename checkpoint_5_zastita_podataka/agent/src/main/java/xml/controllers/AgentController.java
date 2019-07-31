package xml.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import test.Hashing;
import xml.services.AgentService;
import xml.web_services.Agent;

@RestController
@RequestMapping("/agent")
public class AgentController {

	private static final Logger logger = LoggerFactory.getLogger(AgentController.class);
	
	@Autowired
	private AgentService agentService;
	
	
	@PostMapping("/login")
	public ResponseEntity<Agent> login(@RequestBody Agent agent, HttpServletRequest request) {
		logger.debug("REST request to login into system!");
		
		Agent a = this.agentService.findByUsername(agent);
		System.out.println("Request na login: " + request);
		if (a != null && a.getPassword().equals(Hashing.hash(agent.getPassword()))) {
			System.out.println("Bice vracen status OKE za agenta!");
			request.getSession().setAttribute("agent", a);
			System.out.println("Agent iz sessije na accout na login: " + request.getSession().getAttribute("agent"));
			//this.agentService.synchronizeData(a);
			logger.info("Uspesno se ulogovao agent: " + a.getUsername() + ", " + new Date());
			return new ResponseEntity<>(a, HttpStatus.OK);	
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/logout")
	public ResponseEntity logout(HttpServletRequest request) {
		logger.debug("REST request to logout from the system!");
		System.out.println("##############################LOGOUT");
		Agent a = (Agent)request.getSession().getAttribute("agent");
		logger.info("Uspesno se izlogovao agent: " + a.getUsername() + ", " + new Date());
		request.getSession().removeAttribute("agent");
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/account")
	public ResponseEntity account(HttpServletRequest request) {
		logger.debug("REST request to logout from the system!");
		System.out.println("Agent iz sessije na accout: " + request.getSession().getAttribute("agent"));
		Agent agent = (Agent)request.getSession().getAttribute("agent");
		System.out.println("Request na agent: " + request);

		if (agent != null) {
			logger.info("Trenutno je prijavljen agent: " + agent.getUsername() + ", " + new Date());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		System.out.println("Agent nije nadjen u sesiji!");
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
