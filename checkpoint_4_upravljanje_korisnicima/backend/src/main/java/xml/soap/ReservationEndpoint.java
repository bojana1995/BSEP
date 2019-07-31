package xml.soap;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xml.repository.AccomodationRepository;
import xml.service.AgentService;
import xml.service.ReservationService;
import xml.web_services.Accomodation;
import xml.web_services.Agent;
import xml.web_services.GetReservationsRequest;
import xml.web_services.GetReservationsResponse;
import xml.web_services.Reservation;
import xml.web_services.SendReservationsRequest;
import xml.web_services.SendReservationsResponse;
import xml.web_services.TryReservationRequest;
import xml.web_services.TryReservationResponse;

@Endpoint
public class ReservationEndpoint {

	private static final String NAMESPACE_URI = "http://xml/web-services";

	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private AccomodationRepository accomodationRepository;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetReservationsRequest")
	@ResponsePayload
	public GetReservationsResponse getReservations(@RequestPayload GetReservationsRequest request) {
		Agent a = this.agentService.findByUsername(request.getUsername());
		List<Reservation> reservations = this.reservationService.getAllForAgent(a);
		GetReservationsResponse response = new GetReservationsResponse();
		
		for(Reservation r: reservations) {
			r.setServerId(r.getId());
		}
		response.getReservations().addAll(reservations);
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendReservationsRequest")
	@ResponsePayload
	public SendReservationsResponse sendReservation(@RequestPayload SendReservationsRequest request) {
		Reservation r = request.getReservation();
		Reservation reservation = this.reservationService.findById(r.getServerId());
		reservation.setAccepted(r.isAccepted());
		this.reservationService.save(reservation);
		
		SendReservationsResponse response = new SendReservationsResponse();
		response.setStatus(true);
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "TryReservationRequest")
	@ResponsePayload
	public TryReservationResponse tryReservation(@RequestPayload TryReservationRequest request) {
		TryReservationResponse response = new TryReservationResponse();
		
		Date startDate = request.getReservation().getStartDdate();
		Date endDate = request.getReservation().getEndDdate();
		boolean possibleReservation = true;
		List<Reservation> reservations = this.reservationService.findByAccomodation(this.accomodationRepository.findByName(request.getReservation().getAccomodation().getName()));
		this.accomodationRepository.findByName(request.getReservation().getAccomodation().getName());
		
		for (Reservation r : reservations) {		
			if (startDate.compareTo(r.getStartDdate()) < 0) {
				if (endDate.compareTo(r.getEndDdate()) >= 0) {
					possibleReservation = false;
					break;
				}
			}else if(startDate.compareTo(r.getStartDdate()) > 0) {
				if (startDate.compareTo(r.getEndDdate()) < 0) {
					possibleReservation = false;
					break;
				}
			} else {
				possibleReservation = false;
				break;
			}
		}
		
		if(!possibleReservation) {
			response.setReservationResponse(null);
		}else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			int startYear = cal.get(Calendar.YEAR);
			cal.setTime(endDate);
			int endYear = cal.get(Calendar.YEAR);
			Accomodation accomodation = this.accomodationRepository.findByName(request.getReservation().getAccomodation().getName());
			
			if (accomodation.getPricePlan().getStartDate() <= startYear && startYear <= accomodation.getPricePlan().getEndDate()) {
				if (accomodation.getPricePlan().getStartDate() <= endYear && endYear <= accomodation.getPricePlan().getEndDate()) {
					Reservation r = new Reservation();
					r.setAccepted(request.getReservation().isAccepted());
					r.setAccomodation(accomodation);
					r.setAgent(this.agentService.findByUsername(request.getReservation().getAgent().getUsername()));
					r.setEndDdate(request.getReservation().getEndDdate());
					r.setNumOfPersons(request.getReservation().getNumOfPersons());
					r.setPrice(request.getReservation().getPrice());
					r.setServerId(request.getReservation().getId());
					r.setStartDdate(request.getReservation().getStartDdate());
					this.reservationService.save(r);
					response.setReservationResponse(request.getReservation());
					return response;
				}
			}
			response.setReservationResponse(null);
		}
		
		return response;
	}
	
}
