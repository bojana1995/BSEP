package xml.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xml.service.AccomodationCategoryService;
import xml.web_services.GetAccomodationCategoryRequest;
import xml.web_services.GetAccomodationCategoryResponse;

@Endpoint
public class AccomodationCategoryEndpoint {

	private static final String NAMESPACE_URI = "http://xml/web-services";

	private AccomodationCategoryService accomodationCategoryService;

	@Autowired
	public AccomodationCategoryEndpoint(AccomodationCategoryService accomodationCategoryService) {
		this.accomodationCategoryService = accomodationCategoryService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAccomodationCategoryRequest")
	@ResponsePayload
	public GetAccomodationCategoryResponse getAccomodationService(@RequestPayload GetAccomodationCategoryRequest request) {
		GetAccomodationCategoryResponse response = new GetAccomodationCategoryResponse();
		Long id = request.getId();
		
		if (id == 0) {
			response.getAccomodationCategory().addAll(this.accomodationCategoryService.getAllAccomodationCategories());
		} else {
			response.getAccomodationCategory().add(this.accomodationCategoryService.findById(id));
		}
		
		return response;
	}
	
}
