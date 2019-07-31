package xml.service;

import java.util.List;

import xml.web_services.Accomodation;
import xml.web_services.Review;

public interface ReviewService {

	Review save(Review review);
	List<Review> findByAccomodation(Accomodation accomodation);
	boolean approve(long id);
	boolean decline(long id);
	List<Review> findByAllowed(boolean allowed);
	List<Review> fomdByAccomodationAndAllowed(long id, boolean allowed);
	
}
