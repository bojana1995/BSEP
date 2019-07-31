package xml.service;

import xml.web_services.Agent;

public interface AgentService {
	
	Agent save(Agent agent);
	Agent findByUsername(String username);
	
}
