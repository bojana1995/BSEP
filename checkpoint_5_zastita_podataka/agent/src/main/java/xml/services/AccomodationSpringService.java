package xml.services;

import java.util.List;

import xml.web_services.Accomodation;
import xml.web_services.Agent;
import xml.web_services.Reservation;
import xml.web_services.ReservationHelper;

public interface AccomodationSpringService {

	Accomodation save(Accomodation accomodation);
	void sendAccomodationToServer(Accomodation accomodation);
	Accomodation findById(Long id);
	List<Accomodation> getAccomodations();
	Reservation tryToReserve(ReservationHelper helper, Agent agent);
	
}
