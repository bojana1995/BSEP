package xml.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xml.services.ReplyService;
import xml.web_services.Agent;
import xml.web_services.Reply;

@RestController
@RequestMapping("/reply")
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	
	@PostMapping
	public ResponseEntity<Reply> save(@RequestBody Reply reply, HttpServletRequest request) {
		reply.setAgent((Agent) request.getSession().getAttribute("agent"));
		
		return new ResponseEntity<>(this.replyService.save(reply), HttpStatus.OK);
	}
	
}
