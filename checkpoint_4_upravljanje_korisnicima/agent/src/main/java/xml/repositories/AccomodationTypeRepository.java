package xml.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xml.web_services.AccomodationType;

@Repository
public interface AccomodationTypeRepository extends JpaRepository<AccomodationType, Long>{

}
