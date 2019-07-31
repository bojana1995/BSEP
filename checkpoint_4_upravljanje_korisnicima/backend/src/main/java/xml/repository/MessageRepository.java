package xml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xml.web_services.Agent;
import xml.web_services.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

	List<Message> findByReadStatusAndAgent(boolean readStatus, Agent agent);
	List<Message> findByUserId(long userId);
	
}
