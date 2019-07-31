package xml.soap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xml.repository.AccomodationRepository;
import xml.repository.AgentRepository;
import xml.repository.ReviewRepository;
import xml.web_services.Accomodation;
import xml.web_services.Agent;
import xml.web_services.GetReviewsRequest;
import xml.web_services.GetReviewsResponse;
import xml.web_services.Review;

@Endpoint
public class ReviewEndpoint {

	private static final String NAMESPACE_URI = "http://xml/web-services";

	@Autowired
	private AccomodationRepository accomodationRepository;
	
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetReviewsRequest")
	@ResponsePayload
	public GetReviewsResponse getReservations(@RequestPayload GetReviewsRequest request) {
		GetReviewsResponse response = new GetReviewsResponse();
		List<Review> reviews = new ArrayList<>();
		Agent agent = this.agentRepository.findByUsername(request.getAgentUsername());
		
		if (agent == null) {
			response = null;
		}else {
			List<Accomodation> agentAccomodations = this.accomodationRepository.findByAgent(agent);
			for (Accomodation a: agentAccomodations) {
				reviews.addAll(this.reviewRepository.findByAccomodation(a));
			}
			for (Review r: reviews) {
				r.setServerId(r.getId());
			}
			response.getReviews().addAll(reviews);
		}
		
		return response;
	}
	
}
