package xml.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xml.service.AgentService;
import xml.service.ReplyService;
import xml.web_services.Reply;
import xml.web_services.SendReplyRequest;
import xml.web_services.SendReplyResponse;

@Endpoint
public class ReplyEndpoint {
	
	private static final String NAMESPACE_URI = "http://xml/web-services";

	@Autowired
	private ReplyService replyService;

	@Autowired
	private AgentService agentService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendReplyRequest")
	@ResponsePayload
	public SendReplyResponse getReply(@RequestPayload SendReplyRequest request) {		
		Reply reply = new Reply();
		reply.setAgent(agentService.findByUsername(request.getReply().getAgent().getUsername()));
		reply.setMsgId(request.getReply().getMsgId());
		reply.setReplyContent(request.getReply().getReplyContent());
		reply.setUserId(request.getReply().getUserId());		
		this.replyService.save(reply);
		
		SendReplyResponse response = new SendReplyResponse();
		response.setStatus(true);
		
		return response;
	}
	
}
