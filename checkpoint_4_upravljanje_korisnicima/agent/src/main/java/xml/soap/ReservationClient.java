package xml.soap;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import xml.web_services.Agent;
import xml.web_services.GetReservationsRequest;
import xml.web_services.GetReservationsResponse;
import xml.web_services.Reservation;
import xml.web_services.SendReservationsRequest;
import xml.web_services.SendReservationsResponse;
import xml.web_services.TryReservationRequest;
import xml.web_services.TryReservationResponse;

public class ReservationClient extends WebServiceGatewaySupport {

	public GetReservationsResponse getReservations(Agent agent) {
		GetReservationsRequest request = new GetReservationsRequest();
		request.setUsername(agent.getUsername());;
		
		GetReservationsResponse response = (GetReservationsResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request,
						new SoapActionCallback("http://localhost:8090/ws/GetReservationsRequest"));
		
		return response;
	}
	
	public SendReservationsResponse sendReservation(Reservation reservation) {
		SendReservationsRequest request = new SendReservationsRequest();
		request.setReservation(reservation);
		SendReservationsResponse response = (SendReservationsResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request,
						new SoapActionCallback("http://localhost:8090/ws/SendReservationsRequest"));
		
		return response;
	}
	
	public TryReservationResponse tryReservation(Reservation reservation) {
		TryReservationRequest request = new TryReservationRequest();
		request.setReservation(reservation);
		TryReservationResponse response = (TryReservationResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request,
						new SoapActionCallback("http://localhost:8090/ws/TryReservationRequest"));
		return response;
	}
	
}