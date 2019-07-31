package xml.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xml.web_services.Adress;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Long>{

}
