package xml.service;

import java.util.List;

import xml.model.ReservationHelper;
import xml.model.SearchObject;
import xml.model.User;
import xml.web_services.Accomodation;
import xml.web_services.Reservation;

public interface AccomodationSpringService {

	Accomodation save(Accomodation accomodation);
	Accomodation findById(Long id);
	List<Accomodation> getAccomodations();
	List<Accomodation> searchAccomodations(SearchObject searchObject);
	Reservation saveReservation(ReservationHelper helper, User user);
	
}
