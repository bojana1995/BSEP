package xml.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.repositories.AccomodationCategoryRepository;
import xml.repositories.AccomodationRepository;
import xml.repositories.AccomodationTypeRepository;
import xml.repositories.AgentRepository;
import xml.repositories.MessageRepository;
import xml.repositories.ReviewRepository;
import xml.soap.AccomodationCategoryClient;
import xml.soap.AccomodationTypeClient;
import xml.soap.MessageClient;
import xml.soap.ReservationClient;
import xml.soap.ReviewClient;
import xml.web_services.AccomodationCategory;
import xml.web_services.Agent;
import xml.web_services.GetAccomodationCategoryResponse;
import xml.web_services.GetAccomodationTypeResponse;
import xml.web_services.GetMessagesResponse;
import xml.web_services.GetReservationsResponse;
import xml.web_services.GetReviewsResponse;
import xml.web_services.Message;
import xml.web_services.Reservation;
import xml.web_services.Review;

@Service
public class AgentServiceImpl implements AgentService {

	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private AccomodationTypeRepository accomodationTypeRepository;
	
	@Autowired
	private AccomodationCategoryRepository accomodationCategoryRepository;
	
	@Autowired
	private AccomodationTypeClient accomodationTypeClient;

	@Autowired
	private AccomodationCategoryClient accomodationCategoryClient;
	
	@Autowired
	private AccomodationRepository accomodationRepository;
	
	@Autowired
	private MessageClient messageClient;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ReservationClient reservationClient;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private ReviewClient reviewClient;
	
	@Override
	public Agent findByUsername(Agent agent) {
		System.out.println("Trazim agenta: " + agent.getUsername());
		Agent a = this.agentRepository.findByUsername(agent.getUsername());
		
		if (a != null) 
			return a;
		
		return null;
	}

	@Override
	public void synchronizeData(Agent agent) {

		GetAccomodationTypeResponse accomodationTypeResponse = accomodationTypeClient.getAccomodationType(0L); //0L -> returns all types
		this.accomodationTypeRepository.saveAll(accomodationTypeResponse.getAccomodationType());
		
		//recreating because of id-s
		GetAccomodationCategoryResponse accomodationCategoryResponse = accomodationCategoryClient.getAccomodationType(0L);
		for (AccomodationCategory ac: accomodationCategoryResponse.getAccomodationCategory()) {
			AccomodationCategory category = this.accomodationCategoryRepository.findByValue(ac.getValue());
			if (category == null) { 
				category = new AccomodationCategory();
				category.setValue(ac.getValue());
				this.accomodationCategoryRepository.save(category);
			}
		}
		
		//recreating because of id-s
		GetMessagesResponse messagesResponse = this.messageClient.getMessages(agent.getUsername());
		for (Message m : messagesResponse.getMessages()) {
			Message message = new Message();
			message.setAgent(this.agentRepository.findByUsername(m.getAgent().getUsername()));
			message.setMsgContent(m.getMsgContent());
			message.setReadStatus(false);
			message.setServerId(m.getServerId());
			message.setUserId(m.getUserId());
			this.messageRepository.save(message);
		}
		
		GetReservationsResponse reservationResponse = this.reservationClient.getReservations(agent);
		//seljacki
		this.reservationService.removeAll();
		for (Reservation r : reservationResponse.getReservations()) {
			Reservation reservation = new Reservation();
			reservation.setAccepted(r.isAccepted());
			reservation.setAccomodation(this.accomodationRepository.findByName(r.getAccomodation().getName()));
			reservation.setAgent(this.agentRepository.findByUsername(r.getAgent().getUsername()));
			reservation.setEndDdate(r.getEndDdate());
			reservation.setStartDdate(r.getStartDdate());
			reservation.setNumOfPersons(r.getNumOfPersons());
			reservation.setPrice(r.getPrice());
			reservation.setUserId(r.getUserId());
			reservation.setServerId(r.getServerId());
			this.reservationService.save(reservation);
		}
		
		this.reviewRepository.deleteAll();
		GetReviewsResponse reviewResponse = this.reviewClient.getReviews(agent);
		//if(reviewResponse != null) {
			for (Review r: reviewResponse.getReviews()) {
				Review review = new Review();
				review.setAccomodation(this.accomodationRepository.findByName(r.getAccomodation().getName()));
				review.setAllowed(r.isAllowed());
				review.setComment(r.getComment());
				review.setGrade(r.getGrade());
				review.setUserId(r.getUserId());
				review.setServerId(r.getServerId());
				this.reviewRepository.save(review);
			}
		//}
	}
	
}
