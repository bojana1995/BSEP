package xml.soap;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import xml.web_services.GetAccomodationCategoryRequest;
import xml.web_services.GetAccomodationCategoryResponse;

public class AccomodationCategoryClient extends WebServiceGatewaySupport {
	
	public GetAccomodationCategoryResponse getAccomodationType(Long id) {
		GetAccomodationCategoryRequest request = new GetAccomodationCategoryRequest();
		request.setId(id);
		
		GetAccomodationCategoryResponse response = (GetAccomodationCategoryResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request,
						new SoapActionCallback("https://localhost:8443/ws/GetAccomodationCategoryRequest"));
		
		return response;
	}
	
}
