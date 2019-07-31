package xml.service;

import java.util.List;

import xml.model.MessageHelper;
import xml.model.User;
import xml.web_services.Message;

public interface MessageService {

	Message save(MessageHelper helper, User user);
	Message save(Message message);
	List<Message> findByReadStatusAndAgent(boolean readStatus, String username);
	List<Message> findByUserId(User user);
	
}
