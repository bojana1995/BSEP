package xml.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xml.web_services.Agent;


@Repository
public interface AgentRepository extends JpaRepository<Agent, Long>{

	Agent findByUsername(String username);
	
}
