package xml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.repository.AccomodationRepository;
import xml.repository.ReviewRepository;
import xml.web_services.Accomodation;
import xml.web_services.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private AccomodationRepository accomodationRepository;
	
	@Override
	public Review save(Review review) {
		Accomodation a = this.accomodationRepository.findById(review.getAccomodation().getId()).get();
		List<Review> reviews = this.reviewRepository.findByAccomodationAndAllowed(a, true);
		
		int sum = 0;
		int counter = 0;		
		for (Review r : reviews) {
			sum += r.getGrade();
			counter++;
		}		
		
		if (counter == 0) {
			counter = 1;
		} 
				
		a.setRate((float)sum/counter);
		this.accomodationRepository.save(a);
		
		return this.reviewRepository.save(review);
	}

	@Override
	public List<Review> findByAccomodation(Accomodation accomodation) {
		return this.reviewRepository.findByAccomodationAndAllowed(accomodation, true);
	}

	@Override
	public boolean approve(long id) {
		Review r = this.reviewRepository.findById(id).get();
		r.setAllowed(true);
		this.reviewRepository.save(r);
		
		return true;
	}

	@Override
	public boolean decline(long id) {
		Review r = this.reviewRepository.findById(id).get();
		r.setAllowed(false);
		this.reviewRepository.save(r);
		
		return true;
	}

	@Override
	public List<Review> findByAllowed(boolean allowed) {
		return this.reviewRepository.findByAllowed(allowed);
	}

	@Override
	public List<Review> fomdByAccomodationAndAllowed(long id, boolean allowed) {
		Accomodation a = this.accomodationRepository.findById(id).get();
		
		return this.reviewRepository.findByAccomodationAndAllowed(a, true);
	}

}
