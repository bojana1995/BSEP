package xml.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xml.web_services.Accomodation;

@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation, Long>{

	Accomodation findByName(String name);
	
}
