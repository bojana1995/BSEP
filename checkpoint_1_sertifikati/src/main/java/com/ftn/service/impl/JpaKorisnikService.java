package com.ftn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.model.Korisnik;
import com.ftn.repository.KorisnikRepository;
import com.ftn.service.KorisnikService;

@Transactional
@Service
public class JpaKorisnikService implements KorisnikService {
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Override
	public Korisnik findOne(Long id) {
		return korisnikRepository.findOne(id);
	}

	@Override
	public Korisnik findByEmail(String email) {
		return korisnikRepository.findByEmailIgnoreCaseContaining(email);
	}

	@Override
	public List<Korisnik> findAll() {
		return korisnikRepository.findAll();
	}

	@Override
	public Korisnik save(Korisnik korisnik) {
		return korisnikRepository.save(korisnik);
	}

	@Override
	public List<Korisnik> save(List<Korisnik> korisnici) {
		return korisnikRepository.save(korisnici);
	}

	@Override
	public Korisnik delete(Long id) {
		Korisnik korisnik = korisnikRepository.findOne(id);
		if(korisnik == null){
			throw new IllegalArgumentException("Pokusaj brisanja nepostojeceg korisnika.");
		}
		korisnikRepository.delete(korisnik);
		return korisnik;
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
	}

	@Override
	public List<Korisnik> search(String ime, String prezime) {
		return korisnikRepository.findByImeIgnoreCaseContainingAndPrezimeIgnoreCaseContaining(ime, prezime);
	}

}
