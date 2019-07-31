package xml.soap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xml.repository.AccomodationCategoryRepository;
import xml.repository.AccomodationTypeRepository;
import xml.repository.AdressRepository;
import xml.repository.AgentRepository;
import xml.repository.ImagesRepository;
import xml.repository.PricePlanRepository;
import xml.service.AccomodationSpringService;
import xml.web_services.Accomodation;
import xml.web_services.Adress;
import xml.web_services.Agent;
import xml.web_services.Images;
import xml.web_services.PricePlan;
import xml.web_services.SendAccomodationRequest;
import xml.web_services.SendAccomodationResponse;

@Endpoint
public class AccomodationEndpoint {

	private static final String NAMESPACE_URI = "http://xml/web-services";

	private AccomodationSpringService accomodationSpringService;
	
	@Autowired
	private AdressRepository adressRepository;
	
	@Autowired
	private PricePlanRepository pricePlanRepository;

	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private ImagesRepository imagesRepository;
	
	@Autowired
	private AccomodationTypeRepository accomodationTypeRepository;
	
	@Autowired
	private AccomodationCategoryRepository accomodationCategoryRepository;	
	
	@Autowired
	public AccomodationEndpoint(AccomodationSpringService accomodationSpringService) {
		this.accomodationSpringService = accomodationSpringService;
	}

	//writing object into backend database and return response (to client)
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendAccomodationRequest")
	@ResponsePayload
	public SendAccomodationResponse sendAccomodation(@RequestPayload SendAccomodationRequest request) {
		SendAccomodationResponse response = new SendAccomodationResponse();
		Accomodation accomodation = request.getAccomodation();
		
		// recreate objects because of ids
		Adress adress = new Adress();
		adress.setCity(accomodation.getAddress().getCity());
		adress.setCountry(accomodation.getAddress().getCountry());
		adress.setStreet(accomodation.getAddress().getStreet());
		adress.setStreetNumber(accomodation.getAddress().getStreetNumber());
		this.adressRepository.save(adress);
		
		PricePlan p = new PricePlan();
		p.setEndDate(accomodation.getPricePlan().getEndDate());
		p.setStartDate(accomodation.getPricePlan().getStartDate());
		p.setPrice(accomodation.getPricePlan().getPrice());
		this.pricePlanRepository.save(p);
		
		Agent agent = this.agentRepository.findByUsername(accomodation.getAgent().getUsername());
		/*agent.setBusinessID(accomodation.getAgent().getBusinessID());
		agent.setUsername(accomodation.getAgent().getUsername());
		agent.setPassword(accomodation.getAgent().getPassword());
		agent.setFirstName(accomodation.getAgent().getFirstName());
		agent.setLastName(accomodation.getAgent().getLastName());*/
		this.agentRepository.save(agent);
		
		Accomodation a = new Accomodation();
		a.setAccomodationCategory(accomodation.getAccomodationCategory());
		a.setAccomodationType(accomodation.getAccomodationType());
		a.setAddress(adress);
		a.setAgent(accomodation.getAgent());
		a.setAgent(agent);
		a.setDescription(accomodation.getDescription());
		a.setName(accomodation.getName());
		a.setNumOfPersons(accomodation.getNumOfPersons());
		a.setPricePlan(p);
		a.setBreakfast(accomodation.isBreakfast());
		a.setFullBoard(accomodation.isFullBoard());
		a.setHalfBoard(accomodation.isHalfBoard());
		a.setKitchen(accomodation.isKitchen());
		a.setParking(accomodation.isParking());
		a.setPrivateBathroom(accomodation.isPrivateBathroom());
		a.setTv(accomodation.isTv());
		a.setWifi(accomodation.isWifi());
		a.setAccomodationCategory(this.accomodationCategoryRepository.findByValue(accomodation.getAccomodationCategory().getValue()).get(0));
		a.setAccomodationType(this.accomodationTypeRepository.findByValue(accomodation.getAccomodationType().getValue()).get(0));
		
		List<Images> listImages = new ArrayList<>();		
		for (Images image : accomodation.getImages()) {
			Images i = new Images();
			i.setImageUrl(image.getImageUrl());
			listImages.add(this.imagesRepository.save(i));
		}
		
		a.getImages().addAll(listImages);
		Accomodation acc = this.accomodationSpringService.save(a);
		
		if (acc == null) {
			response.setStatus(false);
		} else {
			response.setStatus(true);
		}
		
		return response;
	}
	
}
