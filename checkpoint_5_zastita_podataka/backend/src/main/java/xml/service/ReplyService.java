package xml.service;

import java.util.List;

import xml.model.User;
import xml.web_services.Message;
import xml.web_services.Reply;

public interface ReplyService {

	Reply save(Reply reply);
	List<Reply> getReplies(User user);
	Reply getReply(Message msg);
	
}
