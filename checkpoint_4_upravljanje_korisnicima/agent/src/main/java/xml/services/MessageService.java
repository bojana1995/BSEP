package xml.services;

import java.util.List;

import xml.web_services.Message;

public interface MessageService {

	List<Message> findByReadStatus(boolean readStatus);
	
}
