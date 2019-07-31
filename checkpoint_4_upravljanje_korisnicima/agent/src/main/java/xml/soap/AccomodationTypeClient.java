package xml.soap;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import xml.web_services.GetAccomodationTypeRequest;
import xml.web_services.GetAccomodationTypeResponse;

public class AccomodationTypeClient extends WebServiceGatewaySupport {

	public GetAccomodationTypeResponse getAccomodationType(Long id) {
		GetAccomodationTypeRequest request = new GetAccomodationTypeRequest();
		request.setId(id);
		
		GetAccomodationTypeResponse response = (GetAccomodationTypeResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request,
						new SoapActionCallback("http://localhost:8090/ws/GetAccomodationTypeRequest"));
		
		return response;
	}
	
}
