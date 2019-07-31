package xml.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.model.MessageHelper;
import xml.model.User;
import xml.repository.AccomodationRepository;
import xml.repository.AgentRepository;
import xml.repository.MessageRepository;
import xml.web_services.Accomodation;
import xml.web_services.Agent;
import xml.web_services.Message;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private AccomodationRepository accomodationRepository;
	
	@Autowired
	private AgentRepository agentRepository;
	
	@Override
	public Message save(MessageHelper helper, User user) {
		Optional<Accomodation> opt = this.accomodationRepository.findById(helper.getAccomodationId());
		
		Message msg = new Message();
		msg.setAgent(opt.get().getAgent());
		msg.setMsgContent(helper.getContent());
		msg.setReadStatus(false);
		msg.setUserId(user.getId());
		
		return this.messageRepository.save(msg);
	}

	@Override
	public List<Message> findByReadStatusAndAgent(boolean readStatus, String username) {		
		Agent agent = this.agentRepository.findByUsername(username);
		List<Message> messages = this.messageRepository.findByReadStatusAndAgent(readStatus, agent);
		
		return messages;
	}

	@Override
	public Message save(Message message) {
		return this.messageRepository.save(message);
	}

	@Override
	public List<Message> findByUserId(User user) {
		return this.messageRepository.findByUserId(user.getId());
	}

}
