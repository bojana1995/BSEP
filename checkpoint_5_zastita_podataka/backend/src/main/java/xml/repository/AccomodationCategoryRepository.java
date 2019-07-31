package xml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xml.web_services.AccomodationCategory;

@Repository
public interface AccomodationCategoryRepository extends JpaRepository<AccomodationCategory, Long>{
	
	List<AccomodationCategory> findByValue(int value);
	
}
