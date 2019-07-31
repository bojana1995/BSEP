package xml.service;

import java.util.List;

import xml.web_services.AccomodationType;


public interface AccomodationTypeService {

	List<AccomodationType> getAllAccomodationTypes();
	AccomodationType save(AccomodationType at);
	boolean delete(Long id);
	AccomodationType findById(Long id);
	
}
