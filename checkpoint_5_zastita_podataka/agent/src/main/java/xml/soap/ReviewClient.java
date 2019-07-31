package xml.soap;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import xml.web_services.Agent;
import xml.web_services.GetReviewsRequest;
import xml.web_services.GetReviewsResponse;

public class ReviewClient extends WebServiceGatewaySupport {

	public GetReviewsResponse getReviews(Agent agent) {
		GetReviewsRequest request = new GetReviewsRequest();
		request.setAgentUsername(agent.getUsername());
		
		GetReviewsResponse response = (GetReviewsResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request,
						new SoapActionCallback("https://localhost:8443/ws/GetReviewsRequest"));
		
		return response;
	}
	
}
