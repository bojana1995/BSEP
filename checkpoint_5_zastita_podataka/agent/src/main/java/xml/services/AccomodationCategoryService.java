package xml.services;

import java.util.List;

import xml.web_services.AccomodationCategory;


public interface AccomodationCategoryService {
	
	List<AccomodationCategory> getAllAccomodationCategories();
	AccomodationCategory save(AccomodationCategory as);
	boolean delete(Long id);
	AccomodationCategory findById(Long id);

}
