package xml.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.repositories.ReservationRepository;
import xml.soap.ReservationClient;
import xml.web_services.Agent;
import xml.web_services.Reservation;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private ReservationClient reservationClient;
	
	@Override
	public List<Reservation> getAllForAgent(Agent agent) {
		return this.getAllForAgent(agent);
	}

	@Override
	public Reservation save(Reservation reservation) {
		return this.reservationRepository.save(reservation);
	}

	@Override
	public List<Reservation> getAll() {
		return reservationRepository.findAll();
	}

	@Override
	public void removeAll() {
		this.reservationRepository.deleteAll();
	}

	@Override
	public Reservation activate(long id) {
		Optional<Reservation> opt = this.reservationRepository.findById(id);
		
		if (!opt.isPresent()) 
			return null;
		
		Reservation r = opt.get();
		r.setAccepted(true);
		reservationClient.sendReservation(r);
		
		return this.reservationRepository.save(r);
	}

}
