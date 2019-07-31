package xml.model;

import xml.web_services.Message;
import xml.web_services.Reply;

public class ChatHistory {

	private Message message;
	private Reply reply;
	
	public Message getMessage() {
		return message;
	}
	
	public void setMessage(Message message) {
		this.message = message;
	}
	
	public Reply getReply() {
		return reply;
	}
	
	public void setReply(Reply reply) {
		this.reply = reply;
	}
	
}
