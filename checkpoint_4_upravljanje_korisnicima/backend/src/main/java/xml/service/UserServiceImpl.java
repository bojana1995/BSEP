package xml.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import xml.model.ChatHistory;
import xml.model.User;
import xml.model.UserStatus;
import xml.model.UserType;
import xml.repository.UserRepository;
import xml.web_services.Message;
import xml.web_services.Reply;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	
	@Autowired
	public JavaMailSender emailSender;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private ReplyService replyService;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User findByUsername(User user) {
		User u = this.userRepository.findByUsername(user.getUsername());
		
		if (u == null) 
			return null;
		
		if (u.getPassword().equals(user.getPassword())) {
			return u;
		} else {
			return null;
		}
	}

	@Override
	public boolean register(User user) {
		user.setUserType(UserType.USER);
		user.setUserStatus(UserStatus.PENDING);
		User u = this.userRepository.save(user);
		
		if (u != null) {
			return true;
		}
		
		return false;
	}

	@Override
	public List<User> getNotAcctivated() {
		return this.userRepository.findByUserStatusAndUserType(UserStatus.PENDING, UserType.USER);
	}

	@Override
	public boolean setStatus(String id, UserStatus userStatus) {
		Optional<User> u = this.userRepository.findById(Long.parseLong(id));
		
		if (u.isPresent()) {
			User user = u.get();
			user.setUserStatus(userStatus);
			this.userRepository.save(user);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean activationEmail(String username) {
		User u = this.userRepository.findByUsername(username);
		
		if (u == null) 
			return false;
			
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(u.getEmail());
		message.setSubject("Password reset");
		message.setText("Please follow the link to reset password: http://localhost:8080/resetpassword.html?u=" + u.getUsername());
		emailSender.send(message);
		
		return true;
	}

	@Override
	public boolean resetPassword(User user) {
		User u = this.userRepository.findByUsername(user.getUsername());
		
		if (u == null) 
			return false;
		
		u.setPassword(user.getPassword());
		this.userRepository.save(u);
		
		return true;
	}

	@Override
	public List<ChatHistory> chat(User user) {
		List<ChatHistory> history = new ArrayList<>();
		List<Message> messages = this.messageService.findByUserId(user);
		
		for (Message m : messages) {
			Reply r = this.replyService.getReply(m);
			ChatHistory ch = new ChatHistory();
			ch.setMessage(m);
			ch.setReply(r);
			history.add(ch);
		}
		
		return history;
	}

	@Override
	public List<User> getAcctivated() {
		return this.userRepository.findByUserStatusAndUserType(UserStatus.ACTIVATED, UserType.USER);
	}

	@Override
	public boolean delete(long id) {
		Optional<User> u = this.userRepository.findById(id);
		
		if (u.isPresent()) {
			this.userRepository.delete(u.get());
			return true;
		}
		
		return false;
	}

	@Override
	public boolean block(long id) {
		Optional<User> opt = this.userRepository.findById(id);
		
		if (!opt.isPresent()) 
			return false;
		
		User user = opt.get();
		user.setUserStatus(UserStatus.DECLINED);
		this.userRepository.save(user);
		
		return true;
	}
	
}
