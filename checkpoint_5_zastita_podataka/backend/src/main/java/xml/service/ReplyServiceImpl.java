package xml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.model.User;
import xml.repository.ReplyRepository;
import xml.web_services.Message;
import xml.web_services.Reply;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyRepository replyRepository;
	
	@Override
	public Reply save(Reply reply) {
		return this.replyRepository.save(reply);
	}

	@Override
	public List<Reply> getReplies(User user) {
		return this.replyRepository.findByUserId(user.getId());
	}

	@Override
	public Reply getReply(Message msg) {
		return this.replyRepository.findByMsgId(msg.getServerId());
	}

}
