package xml.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.repositories.ImagesRepository;
import xml.web_services.Images;

@Service
public class ImagesServiceImpl implements ImagesService{

	@Autowired
	private ImagesRepository imagesRepository;
	
	
	@Override
	public Images save(Images im) {
		return this.imagesRepository.save(im);
	}

}
