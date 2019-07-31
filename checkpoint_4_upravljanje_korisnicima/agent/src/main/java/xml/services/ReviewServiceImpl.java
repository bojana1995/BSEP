package xml.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.repositories.ReviewRepository;
import xml.web_services.Accomodation;
import xml.web_services.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Override
	public Review save(Review review) {
		return this.reviewRepository.save(review);
	}

	@Override
	public List<Review> findByAccomodation(Accomodation accomodation) {
		return this.reviewRepository.findByAccomodationAndAllowed(accomodation, true);
	}

	@Override
	public boolean approve(Review review) {
		Review r = this.reviewRepository.findById(review.getId()).get();
		r.setAllowed(true);
		this.reviewRepository.save(r);
		
		return true;
	}

	@Override
	public boolean decline(Review review) {
		Review r = this.reviewRepository.findById(review.getId()).get();
		r.setAllowed(false);
		this.reviewRepository.save(r);
		
		return true;
	}

	@Override
	public List<Review> findAll() {
		return this.reviewRepository.findAll();
	}

}
