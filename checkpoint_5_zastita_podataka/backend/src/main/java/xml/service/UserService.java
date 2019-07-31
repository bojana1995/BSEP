package xml.service;

import java.util.List;

import xml.model.ChatHistory;
import xml.model.User;
import xml.model.UserStatus;

public interface UserService {

	public User findByUsername(User user);
	public boolean register(User user);
	public List<User> getNotAcctivated();
	public List<User> getAcctivated();
	public boolean delete(long id);
	public boolean setStatus(String id, UserStatus userStatus);
	public boolean activationEmail(String username);
	public boolean resetPassword(User user);
	public List<ChatHistory> chat(User user);
	public boolean block(long id);
	
}
