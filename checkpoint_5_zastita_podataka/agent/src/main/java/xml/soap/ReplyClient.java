package xml.soap;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import xml.web_services.Reply;
import xml.web_services.SendReplyRequest;
import xml.web_services.SendReplyResponse;

public class ReplyClient extends WebServiceGatewaySupport {

	public SendReplyResponse sendReply(Reply reply) {
		SendReplyRequest request = new SendReplyRequest();
		request.setReply(reply);
		
		SendReplyResponse response = (SendReplyResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request,
						new SoapActionCallback("https://localhost:8443/ws/SendReplyRequest"));
		
		return response;
	}
	
}