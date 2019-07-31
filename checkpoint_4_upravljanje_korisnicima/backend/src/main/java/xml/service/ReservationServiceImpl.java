package xml.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.model.User;
import xml.repository.ReservationRepository;
import xml.web_services.Accomodation;
import xml.web_services.Agent;
import xml.web_services.Reservation;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public List<Reservation> getAll() {
		return this.reservationRepository.findAll();
	}

	@Override
	public List<Reservation> getAllForUser(User user) {
		return this.reservationRepository.findByUserId(user.getId());
	}

	@Override
	public boolean delete(long id) {
		this.reservationRepository.deleteById(id);
		
		return true;
	}

	@Override
	public List<Reservation> getAllForAgent(Agent agent) {
		return this.reservationRepository.findByAgent(agent);
	}

	@Override
	public Reservation save(Reservation reservation) {
		return this.reservationRepository.save(reservation);
	}

	@Override
	public Reservation findById(long id) {
		Optional<Reservation> opt = this.reservationRepository.findById(id);
		
		if (opt.isPresent()) {
			return opt.get();
		}
		
		return null;
	}

	@Override
	public List<Reservation> findByAccomodation(Accomodation accomodation) {
		return this.reservationRepository.findByAccomodation(accomodation);
	}

}
