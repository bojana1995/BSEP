package xml.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xml.service.AgentService;
import xml.web_services.Agent;

//8099 - admin; 8098 - user
//@CrossOrigin - omogucava unakrsne zahteve (od admina i user-a)
@CrossOrigin(origins = {"http://localhost:8099","http://localhost:8098"}, maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/agent")
public class AgentController {

	private static final Logger logger = LoggerFactory.getLogger(AgentController.class);
	
	private AgentService agentService;
	
	public AgentController(AgentService agentService) {
		this.agentService = agentService;;
	}
	
	//NV: admin application
	@PostMapping
	public ResponseEntity<Agent> save(@RequestBody Agent agent) {
		logger.debug("REST request save agent into system!");
		Agent a = this.agentService.save(agent);
		
		if (a != null) 
			return new ResponseEntity<>(a, HttpStatus.CREATED);
		
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
}
