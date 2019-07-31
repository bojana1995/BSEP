package com.ftn.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Korisnik implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true) //i baza TREBA da prijavi exception ako se dva puta unese ista e-mail adresa
	private String email;
	
	@Column(nullable = false)
	private String lozinka;
	
	@Column(nullable = false)
	private String ime;
	
	@Column(nullable = false)
	private String prezime;
	
	@Column(nullable = false)
	private String adresa;
	
	@Column(nullable = false)
	private String brojTelefona;
	
	@Column(nullable = false)
	private String uloga;
	
	@Column(nullable = false)
	private boolean aktiviranNalogPrekoMejla;
	
	@Column(nullable = false)
	private boolean prviPutSeUlogovao;
		
	@Column(nullable = false)
	private String odgovorNaSigurnosnoPitanje;
	

	public Korisnik() {
		
	}

	public Korisnik(String email, String lozinka, String ime, String prezime, String adresa, String brojTelefona, 
			String uloga, boolean aktiviranNalogPrekoMejla, boolean prviPutSeUlogovao, String odgovorNaSigurnosnoPitanje) {
		super();
		this.email = email;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.uloga = uloga;
		this.aktiviranNalogPrekoMejla = aktiviranNalogPrekoMejla;
		this.prviPutSeUlogovao = prviPutSeUlogovao;
		this.odgovorNaSigurnosnoPitanje = odgovorNaSigurnosnoPitanje;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public boolean isAktiviranNalogPrekoMejla() {
		return aktiviranNalogPrekoMejla;
	}

	public void setAktiviranNalogPrekoMejla(boolean aktiviranNalogPrekoMejla) {
		this.aktiviranNalogPrekoMejla = aktiviranNalogPrekoMejla;
	}

	public boolean isPrviPutSeUlogovao() {
		return prviPutSeUlogovao;
	}

	public void setPrviPutSeUlogovao(boolean prviPutSeUlogovao) {
		this.prviPutSeUlogovao = prviPutSeUlogovao;
	}
	
	public String getOdgovorNaSigurnosnoPitanje() {
		return odgovorNaSigurnosnoPitanje;
	}

	public void setOdgovorNaSigurnosnoPitanje(String odgovorNaSigurnosnoPitanje) {
		this.odgovorNaSigurnosnoPitanje = odgovorNaSigurnosnoPitanje;
	}
	
}

