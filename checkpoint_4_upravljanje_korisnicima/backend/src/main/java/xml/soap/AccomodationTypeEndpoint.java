package xml.soap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xml.service.AccomodationTypeService;
import xml.web_services.GetAccomodationTypeRequest;
import xml.web_services.GetAccomodationTypeResponse;

import org.springframework.ws.server.endpoint.annotation.Endpoint;


@Endpoint
public class AccomodationTypeEndpoint {
	
	private static final String NAMESPACE_URI = "http://xml/web-services";

	private AccomodationTypeService accomodationTypeService;

	@Autowired
	public AccomodationTypeEndpoint(AccomodationTypeService accomodationTypeService) {
		this.accomodationTypeService = accomodationTypeService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAccomodationTypeRequest")
	@ResponsePayload
	public GetAccomodationTypeResponse getAccomodationType(@RequestPayload GetAccomodationTypeRequest request) {
		GetAccomodationTypeResponse response = new GetAccomodationTypeResponse();
		Long id = request.getId();
		
		if (id == 0) {
			response.getAccomodationType().addAll(this.accomodationTypeService.getAllAccomodationTypes());
		} else {
			response.getAccomodationType().add(this.accomodationTypeService.findById(id));
		}
		
		return response;
	}
	
}
