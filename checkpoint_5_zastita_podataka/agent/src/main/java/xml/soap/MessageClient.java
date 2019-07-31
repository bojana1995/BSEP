package xml.soap;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import xml.web_services.GetMessagesRequest;
import xml.web_services.GetMessagesResponse;

public class MessageClient extends WebServiceGatewaySupport {

	public GetMessagesResponse getMessages(String username) {
		GetMessagesRequest request = new GetMessagesRequest();
		request.setUsername(username);
		
		GetMessagesResponse response = (GetMessagesResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request,
						new SoapActionCallback("https://localhost:8443/ws/GetMessagesRequest"));
		
		return response;
	}
	
}
