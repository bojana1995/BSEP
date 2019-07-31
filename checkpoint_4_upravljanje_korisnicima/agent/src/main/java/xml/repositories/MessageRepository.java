package xml.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xml.web_services.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

	List<Message> findByReadStatus(boolean readStatus);
	Message findByServerId(long serverId);
	
}
