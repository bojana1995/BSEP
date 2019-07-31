package com.ftn.service;

import java.util.List;
import com.ftn.model.Korisnik;

public interface KorisnikService {
		
	Korisnik findOne(Long id);
	Korisnik findByEmail(String email);
	List<Korisnik> findAll();
	
	Korisnik save(Korisnik korisnik);
	List<Korisnik> save(List<Korisnik> korisnici);
	
	Korisnik delete(Long id);
	void delete(List<Long> ids);
	
	List<Korisnik> search(String ime, String prezime);

	//void setActivated(Boolean aktiviranNalog, String email);
	
}
