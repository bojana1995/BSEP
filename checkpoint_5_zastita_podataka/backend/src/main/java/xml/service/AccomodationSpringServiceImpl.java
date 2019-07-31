package xml.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.model.ReservationHelper;
import xml.model.SearchObject;
import xml.model.User;
import xml.repository.AccomodationRepository;
import xml.repository.AdressRepository;
import xml.repository.PricePlanRepository;
import xml.repository.ReservationRepository;
import xml.web_services.Accomodation;
import xml.web_services.Adress;
import xml.web_services.PricePlan;
import xml.web_services.Reservation;

@Service
public class AccomodationSpringServiceImpl implements AccomodationSpringService{

	@Autowired
	private AccomodationRepository accomodationRepository;
	
	@Autowired
	private AdressRepository adressRepository;
	
	@Autowired
	private PricePlanRepository pricePlanRepository;
	
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
	public List<Accomodation> getAccomodations() {
		return this.accomodationRepository.findAll();
	}

	@Override
	public Reservation saveReservation(ReservationHelper helper, User user) {
		Reservation reservation = new Reservation();
		Optional<Accomodation> accomodation = this.accomodationRepository.findById(helper.getAccomodationId());
		reservation.setAccepted(false);
		reservation.setAccomodation(accomodation.get());
		reservation.setAgent(accomodation.get().getAgent());
		reservation.setEndDdate(helper.getEndDate());
		reservation.setStartDdate(helper.getStartDate());
		reservation.setNumOfPersons(helper.getNumOfPersons());
		reservation.setUserId(user.getId());
		int days = daysBetween(helper.getStartDate(), helper.getEndDate());
		reservation.setPrice((days + 1) * accomodation.get().getPricePlan().getPrice());
				
		return this.reservationRepository.save(reservation);
	}
	
	public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}
	
	@Override
	public List<Accomodation> searchAccomodations(SearchObject searchObject) {		
		List<Accomodation> accomodations = this.accomodationRepository.findAll();
		List<Accomodation> possbileAccomodations = new ArrayList<>(); 
		//possbileAccomodations -> list of accommodations that met all search criteria (OK su)
		
		boolean flag = true;
		for (Accomodation acc : accomodations) {
			flag = true;
			if (searchObject.getNumOfPersons() <= acc.getNumOfPersons() && checkPricePlan(acc, searchObject)) {
				if (searchObject.getAccomodationCategoryValue() > 0) {
					if (searchObject.getAccomodationCategoryValue() != acc.getAccomodationCategory().getValue()) {
						flag = false;
					}
				}
				
				if (searchObject.getAccomodationTypeValue() != null) {
					if (!searchObject.getAccomodationTypeValue().equals(acc.getAccomodationType().getValue())) {
						flag = false;
					}
				}
				
				if (searchObject.isBreakfast() && !acc.isBreakfast()) {
					flag = false;
				}
				
				if (searchObject.isFullBoard() && !acc.isFullBoard()) {
					flag = false;
				}
				
				if (searchObject.isHalfBoard() && !acc.isHalfBoard()) {
					flag = false;
				}
				
				if (searchObject.isKitchen() && !acc.isKitchen()) {
					flag = false;
				}
				
				if (searchObject.isParking() && !acc.isParking()) {
					flag = false;
				}
				
				if (searchObject.isPrivateBathroom() && !acc.isPrivateBathroom()) {
					flag = false;
				}
				
				if (searchObject.isTv() && !acc.isTv()) {
					flag = false;
				}
				
				if (searchObject.isWifi() && !acc.isWifi()) {
					flag = false;
				}
				
				if (flag) {
					possbileAccomodations.add(acc);
				}
			}
		}
        
		
		//include date :D
		Date startDate = new Date(searchObject.getStartDate().getTime());
        Date endDate = new Date(searchObject.getEndDate().getTime());
        
        List<Accomodation> finalAccomodations = new ArrayList<>();
        boolean accomodationFlag = true;
        
        for (Accomodation a : possbileAccomodations) {
			List<Reservation> reservations = this.reservationRepository.findByAccomodation(a);
			for (Reservation r : reservations) {
				if (startDate.compareTo(r.getStartDdate()) < 0) {
					if (endDate.compareTo(r.getEndDdate()) >= 0) {
						accomodationFlag = false;
					}
				}else if(startDate.compareTo(r.getStartDdate()) > 0) {
					if (startDate.compareTo(r.getEndDdate()) < 0) {
						accomodationFlag = false;
					}
				} else {
					accomodationFlag = false;
				}
			}
			
			if (accomodationFlag) {
				finalAccomodations.add(a);
			}
			
			accomodationFlag = true;
		}
        
		return finalAccomodations;
	}

	private boolean checkPricePlan(Accomodation accomodation, SearchObject searchObject) {	
		Calendar cal = Calendar.getInstance();
		cal.setTime(searchObject.getStartDate());
		int startYear = cal.get(Calendar.YEAR);
		cal.setTime(searchObject.getEndDate());
		int endYear = cal.get(Calendar.YEAR);
		
		if (accomodation.getPricePlan().getStartDate() <= startYear && startYear <= accomodation.getPricePlan().getEndDate()) {
			if (accomodation.getPricePlan().getStartDate() <= endYear && endYear <= accomodation.getPricePlan().getEndDate()) {
				return true;
			}
		}
		
		return false;
	}
	
}
