package com.ftn.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.model.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	
	Korisnik findById(Long id);
	Korisnik findByEmailIgnoreCaseContaining(String email);
	List<Korisnik> findAll();
	List<Korisnik> findByImeIgnoreCaseContainingAndPrezimeIgnoreCaseContaining(String ime, String prezime);
		
}
