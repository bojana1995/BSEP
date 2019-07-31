package xml.soap;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import xml.web_services.Accomodation;
import xml.web_services.SendAccomodationRequest;
import xml.web_services.SendAccomodationResponse;

public class AccomodationClient extends WebServiceGatewaySupport{

	public SendAccomodationResponse sendAccomodation(Accomodation accomodation) {
		SendAccomodationRequest request = new SendAccomodationRequest();
		request.setAccomodation(accomodation);
		SendAccomodationResponse response = (SendAccomodationResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request,
						new SoapActionCallback("https://localhost:8443/ws/SendAccomodationRequest"));
		
		return response;
	}
	
}
