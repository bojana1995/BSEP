package xml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xml.web_services.PricePlan;

@Repository
public interface PricePlanRepository extends JpaRepository<PricePlan, Long>{

}
