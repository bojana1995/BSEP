package xml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xml.web_services.Accomodation;
import xml.web_services.Agent;

@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation, Long>{

	Accomodation findByName(String name);
	List<Accomodation> findByAgent(Agent agent);
	
}
