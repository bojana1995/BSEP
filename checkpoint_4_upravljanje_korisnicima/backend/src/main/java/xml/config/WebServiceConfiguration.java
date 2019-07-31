package xml.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfiguration extends WsConfigurerAdapter{
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}

	@Bean(name = "accomodation-type")
	public DefaultWsdl11Definition accomodationTypeWsdl11Definition(XsdSchema accomodationTypeSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("AccomodationTypePort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://xml/web-services");
		wsdl11Definition.setSchema(accomodationTypeSchema);
		return wsdl11Definition;
	}
	
	@Bean(name = "accomodation-category")
	public DefaultWsdl11Definition accomodationServicel11Definition(XsdSchema accomodationCategorySchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("AccomodationCategoryPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://xml/web-services");
		wsdl11Definition.setSchema(accomodationCategorySchema);
		return wsdl11Definition;
	}
	
	@Bean(name = "accomodation")
	public DefaultWsdl11Definition accomodationDefinition(XsdSchema accomodationSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("AccomodationPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://xml/web-services");
		wsdl11Definition.setSchema(accomodationSchema);
		return wsdl11Definition;
	}
	
	@Bean(name = "message")
	public DefaultWsdl11Definition messageDefinition(XsdSchema messageSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("MessagePort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://xml/web-services");
		wsdl11Definition.setSchema(messageSchema);
		return wsdl11Definition;
	}
	
	@Bean(name = "reply")
	public DefaultWsdl11Definition replyDefinition(XsdSchema replySchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("ReplyPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://xml/web-services");
		wsdl11Definition.setSchema(replySchema);
		return wsdl11Definition;
	}
	
	@Bean(name = "reservation")
	public DefaultWsdl11Definition reservationDefinition(XsdSchema reservationSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("ReservationPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://xml/web-services");
		wsdl11Definition.setSchema(reservationSchema);
		return wsdl11Definition;
	}
	
	@Bean(name = "review")
	public DefaultWsdl11Definition reviewDefinition(XsdSchema reviewSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("ReviewPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://xml/web-services");
		wsdl11Definition.setSchema(reviewSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema accomodationTypeSchema() {
		return new SimpleXsdSchema(new ClassPathResource("accomodationtypes.xsd"));
	}
	
	@Bean
	public XsdSchema accomodationCategorySchema() {
		return new SimpleXsdSchema(new ClassPathResource("accomodationcategories.xsd"));
	}
	
	@Bean
	public XsdSchema accomodationSchema() {
		return new SimpleXsdSchema(new ClassPathResource("accomodation.xsd"));
	}
	
	@Bean
	public XsdSchema messageSchema() {
		return new SimpleXsdSchema(new ClassPathResource("message.xsd"));
	}
	
	@Bean
	public XsdSchema replySchema() {
		return new SimpleXsdSchema(new ClassPathResource("reply.xsd"));
	}
	
	@Bean
	public XsdSchema reservationSchema() {
		return new SimpleXsdSchema(new ClassPathResource("reservation.xsd"));
	}
	
	@Bean
	public XsdSchema reviewSchema() {
		return new SimpleXsdSchema(new ClassPathResource("review.xsd"));
	}
}
