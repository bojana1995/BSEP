package xml.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xml.service.MessageService;
import xml.web_services.GetMessagesRequest;
import xml.web_services.GetMessagesResponse;
import xml.web_services.Message;

@Endpoint
public class MessageEndpoint {

	private static final String NAMESPACE_URI = "http://xml/web-services";

	@Autowired
	private MessageService messageService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetMessagesRequest")
	@ResponsePayload
	public GetMessagesResponse getAccomodationService(@RequestPayload GetMessagesRequest request) {
		GetMessagesResponse response = new GetMessagesResponse();
		
		List<Message> messages = this.messageService.findByReadStatusAndAgent(false, request.getUsername());		
		for (Message m : messages) {
			m.setServerId(m.getId());
		}
		
		response.getMessages().addAll(messages);
		for (Message m : messages) {
			m.setReadStatus(true);
			this.messageService.save(m);
		}
		
		return response;
	}
	
}
