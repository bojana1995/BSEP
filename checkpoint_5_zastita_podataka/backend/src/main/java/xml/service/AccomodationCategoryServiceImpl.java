package xml.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.repository.AccomodationCategoryRepository;
import xml.web_services.AccomodationCategory;

@Service
public class AccomodationCategoryServiceImpl implements AccomodationCategoryService{

	@Autowired
	private AccomodationCategoryRepository accomodationCategoryRepository;

	@Override
	public List<AccomodationCategory> getAllAccomodationCategories() {
		return this.accomodationCategoryRepository.findAll();
	}	
	
	@Override
	public AccomodationCategory save(AccomodationCategory as) {
		return this.accomodationCategoryRepository.save(as);
	}

	@Override
	public boolean delete(Long id) {
		Optional<AccomodationCategory> optional = this.accomodationCategoryRepository.findById(id);
		
		if (optional.isPresent()) {
			this.accomodationCategoryRepository.delete(optional.get());
			return true;
		}
		
		return false;
	}

	@Override
	public AccomodationCategory findById(Long id) {
		Optional<AccomodationCategory> o = this.accomodationCategoryRepository.findById(id);
		
		if (o.isPresent()) 
			return o.get();
		
		return null;
	}

}
