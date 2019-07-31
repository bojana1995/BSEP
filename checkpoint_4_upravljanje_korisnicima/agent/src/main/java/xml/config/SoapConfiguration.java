package xml.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import xml.soap.AccomodationCategoryClient;
import xml.soap.AccomodationClient;
import xml.soap.AccomodationTypeClient;
import xml.soap.MessageClient;
import xml.soap.ReplyClient;
import xml.soap.ReservationClient;
import xml.soap.ReviewClient;

@Configuration
public class SoapConfiguration {
	
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("xml.web_services");
		return marshaller;
	}

	@Bean
	public AccomodationTypeClient accomodationTypeClient(Jaxb2Marshaller marshaller) {
		AccomodationTypeClient client = new AccomodationTypeClient();
		client.setDefaultUri("http://localhost:8090/ws/accomodation-type.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
	@Bean
	public AccomodationCategoryClient accomodationCategoryClient(Jaxb2Marshaller marshaller) {
		AccomodationCategoryClient client = new AccomodationCategoryClient();
		client.setDefaultUri("http://localhost:8090/ws/accomodation-category.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
	@Bean
	public AccomodationClient accomodationClient(Jaxb2Marshaller marshaller) {
		AccomodationClient client = new AccomodationClient();
		client.setDefaultUri("http://localhost:8090/ws/accomodation.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
	@Bean
	public MessageClient messageClient(Jaxb2Marshaller marshaller) {
		MessageClient client = new MessageClient();
		client.setDefaultUri("http://localhost:8090/ws/message.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
	@Bean
	public ReplyClient replyClient(Jaxb2Marshaller marshaller) {
		ReplyClient client = new ReplyClient();
		client.setDefaultUri("http://localhost:8090/ws/reply.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
	@Bean
	public ReservationClient reservationClient(Jaxb2Marshaller marshaller) {
		ReservationClient client = new ReservationClient();
		client.setDefaultUri("http://localhost:8090/ws/reservation.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
	@Bean
	public ReviewClient reviewClient(Jaxb2Marshaller marshaller) {
		ReviewClient client = new ReviewClient();
		client.setDefaultUri("http://localhost:8090/ws/review.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}
