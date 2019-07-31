package xml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xml.web_services.Accomodation;
import xml.web_services.Agent;
import xml.web_services.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	List<Reservation> findByAccomodation(Accomodation accomodation);
	List<Reservation> findByUserId(long userId);
	List<Reservation> findByAgent(Agent agent);
	
}
