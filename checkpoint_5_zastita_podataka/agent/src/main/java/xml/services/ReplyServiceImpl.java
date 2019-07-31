package xml.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.repositories.MessageRepository;
import xml.repositories.ReplyRepository;
import xml.soap.ReplyClient;
import xml.web_services.Message;
import xml.web_services.Reply;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ReplyClient replyClient;
	
	@Override
	public Reply save(Reply reply) {	
		Reply r = this.replyRepository.save(reply);
		this.replyClient.sendReply(r);
		
		Message m = this.messageRepository.findByServerId(reply.getMsgId());
		m.setReadStatus(true);
		this.messageRepository.save(m);
		
		return r;
	}

}
