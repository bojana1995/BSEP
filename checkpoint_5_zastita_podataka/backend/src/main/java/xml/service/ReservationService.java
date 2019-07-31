package xml.service;

import java.util.List;

import xml.model.User;
import xml.web_services.Accomodation;
import xml.web_services.Agent;
import xml.web_services.Reservation;

public interface ReservationService {

	List<Reservation> getAll();	
	List<Reservation> getAllForUser(User user);	
	boolean delete(long id);	
	List<Reservation> getAllForAgent(Agent agent);	
	Reservation save(Reservation reservation);	
	Reservation findById(long id);	
	List<Reservation> findByAccomodation(Accomodation accomodation);
	
}
