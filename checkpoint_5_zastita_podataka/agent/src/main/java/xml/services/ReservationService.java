package xml.services;

import java.util.List;

import xml.web_services.Agent;
import xml.web_services.Reservation;

public interface ReservationService {

	List<Reservation> getAllForAgent(Agent agent);	
	Reservation save(Reservation reservation);	
	List<Reservation> getAll();	
	void removeAll();	
	Reservation activate(long id);
	
}
