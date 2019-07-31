package xml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xml.web_services.Accomodation;
import xml.web_services.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{

	List<Review> findByAccomodationAndAllowed(Accomodation accomodation, boolean allowed);
	List<Review> findByAllowed(boolean allowed);
	List<Review> findByAccomodation(Accomodation accomodation);
	
}
