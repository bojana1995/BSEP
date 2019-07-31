package xml.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xml.model.MessageHelper;
import xml.model.User;
import xml.service.MessageService;
import xml.web_services.Message;

//8099 - admin; 8098 - user
//@CrossOrigin - omogucava unakrsne zahteve (od admina i user-a)
@CrossOrigin(origins = {"http://localhost:8099","http://localhost:8098"}, maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	//BS: user application
	@PostMapping
	public ResponseEntity<Message> save(@RequestBody MessageHelper helper, HttpServletRequest request) {
		return new ResponseEntity<>(this.messageService.save(helper, (User)request.getSession().getAttribute("user")), HttpStatus.OK);
	}
	
}
