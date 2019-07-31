package xml.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xml.services.MessageService;
import xml.web_services.Message;

@RestController
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@GetMapping
	public ResponseEntity<List<Message>> getNotReadMessages() {
		return new ResponseEntity<>(this.messageService.findByReadStatus(false), HttpStatus.OK);
	}
	
}
