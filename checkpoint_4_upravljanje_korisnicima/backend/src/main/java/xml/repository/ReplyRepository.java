package xml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xml.web_services.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long>{

	List<Reply> findByUserId(long userId);
	Reply findByMsgId(long msgId);
	
}
