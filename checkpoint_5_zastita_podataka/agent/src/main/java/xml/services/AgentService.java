package xml.services;

import xml.web_services.Agent;

public interface AgentService {

	Agent findByUsername(Agent agent);
	void synchronizeData(Agent agent);
	
}
