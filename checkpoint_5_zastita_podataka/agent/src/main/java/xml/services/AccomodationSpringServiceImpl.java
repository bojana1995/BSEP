package xml.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.repositories.AccomodationRepository;
import xml.repositories.AdressRepository;
import xml.repositories.PricePlanRepository;
import xml.repositories.ReservationRepository;
import xml.soap.AccomodationClient;
import xml.soap.ReservationClient;
import xml.web_services.Accomodation;
import xml.web_services.Adress;
import xml.web_services.Agent;
import xml.web_services.PricePlan;
import xml.web_services.Reservation;
import xml.web_services.ReservationHelper;
import xml.web_services.SendAccomodationResponse;
import xml.web_services.TryReservationResponse;

@Service
public class AccomodationSpringServiceImpl implements AccomodationSpringService{

	@Autowired
	private AccomodationRepository accomodationRepository;
	
	@Autowired
	private AdressRepository adressRepository;
	
	@Autowired
	private PricePlanRepository pricePlanRepository;
	
	@Autowired
	private AccomodationClient accomodationClient;
	
	@Autowired
	private ReservationClient reservationClient;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public Accomodation save(Accomodation accomodation) {
		Adress a = this.adressRepository.save(accomodation.getAddress());
		
		if (a == null) 
			return null;
		
		PricePlan p = this.pricePlanRepository.save(accomodation.getPricePlan());
		
		if (p == null) 
			return null;
		
		return this.accomodationRepository.save(accomodation);
	}

	@Override
	public Accomodation findById(Long id) {
		Optional<Accomodation> o = this.accomodationRepository.findById(id);
		
		if (o.isPresent()) 
			return o.get();
		
		return null;
	}
	
	@Override
	public void sendAccomodationToServer(Accomodation accomodation) {
		SendAccomodationResponse accomodationResponse = accomodationClient.sendAccomodation(accomodation);
	}
	
	@Override
	public List<Accomodation> getAccomodations() {
		return this.accomodationRepository.findAll();
	}

	@Override
	public Reservation tryToReserve(ReservationHelper helper, Agent agent) {
		Reservation reservation = new Reservation();
		reservation.setAccomodation(this.accomodationRepository.findById(helper.getAccomodationId()).get());
		reservation.setAgent(agent);
		reservation.setEndDdate(helper.getEndDate());
		reservation.setStartDdate(helper.getStartDate());
		reservation.setNumOfPersons(this.accomodationRepository.findById(helper.getAccomodationId()).get().getNumOfPersons());
		reservation.setAccepted(false);
		int days = daysBetween(helper.getStartDate(), helper.getEndDate()) + 1;
		reservation.setPrice(this.accomodationRepository.findById(helper.getAccomodationId()).get().getPricePlan().getPrice() * days);
		
		TryReservationResponse response = this.reservationClient.tryReservation(reservation);
		if (response.getReservationResponse() != null) {
			return this.reservationRepository.save(response.getReservationResponse());
		}
		
		return null;
	}
	
	public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}

}
