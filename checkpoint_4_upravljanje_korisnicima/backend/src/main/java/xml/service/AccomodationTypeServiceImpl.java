package xml.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.repository.AccomodationTypeRepository;
import xml.web_services.AccomodationType;


@Service
public class AccomodationTypeServiceImpl implements AccomodationTypeService{
	
	@Autowired
	private AccomodationTypeRepository accomodationTypeRepository;
	
	@Override
	public AccomodationType save(AccomodationType at) {
		return this.accomodationTypeRepository.save(at);
	}

	@Override
	public boolean delete(Long id) {
		Optional<AccomodationType> optional = this.accomodationTypeRepository.findById(id);
		
		if (optional.isPresent()) {
			this.accomodationTypeRepository.delete(optional.get());
			return true;
		}
		
		return false;
	}

	@Override
	public List<AccomodationType> getAllAccomodationTypes() {
		return this.accomodationTypeRepository.findAll();
	}

	@Override
	public AccomodationType findById(Long id) {
		Optional<AccomodationType> o = this.accomodationTypeRepository.findById(id);
		
		if (o.isPresent()) 
			return o.get();
		
		return null;
	}

}
