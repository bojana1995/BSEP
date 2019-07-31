package xml.service;

import org.springframework.stereotype.Service;

import xml.repository.AgentRepository;
import xml.web_services.Agent;

@Service
public class AgentServiceImpl implements AgentService {

	private AgentRepository agentRepository;
	
	public AgentServiceImpl(AgentRepository agentRepository) {
		this.agentRepository = agentRepository;
	}

	@Override
	public Agent save(Agent agent) {
		return this.agentRepository.save(agent);
	}

	@Override
	public Agent findByUsername(String username) {	
		return this.agentRepository.findByUsername(username);
	}
	
}
