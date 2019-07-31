package xml.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.repositories.MessageRepository;
import xml.web_services.Message;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public List<Message> findByReadStatus(boolean readStatus) {
		return this.messageRepository.findByReadStatus(readStatus);
	}
	
}
