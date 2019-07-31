package xml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xml.web_services.Images;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Long>{

}
