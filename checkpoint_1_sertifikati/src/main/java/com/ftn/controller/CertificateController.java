package com.ftn.controller;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x509.AccessDescription;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.data.IssuerData;
import com.ftn.data.SubjectData;
import com.ftn.keystores.KeyStoreReader;
import com.ftn.model.CertificateModel;
import com.ftn.model.OtherCertificate;
import com.ftn.model.SelfSignedCertificateModel;
import com.ftn.model.StateModel;
import com.ftn.repository.CertificateRepository;

@RestController
@RequestMapping(value = "/certificate")
public class CertificateController {
	
	@Autowired
	private CertificateRepository certificateRepository;
	
	
	@RequestMapping(value = "/generisiSS", method = RequestMethod.POST, consumes ="application/json", produces="application/json")
	public ResponseEntity generisiSS(@RequestBody SelfSignedCertificateModel selfSignedCertificateModel) {
		try {			
			SubjectData subjectData = generateSubjectData(selfSignedCertificateModel);
			
			KeyPair keyPairIssuer = generateKeyPair();
			IssuerData issuerData = generateIssuerData(keyPairIssuer.getPrivate(), selfSignedCertificateModel);
			X509Certificate cert = generateCertificate(subjectData, issuerData);
			
			KeyStore keyStore = KeyStore.getInstance("JKS", "SUN");
			KeyPair keyPair = generateKeyPair();
			
			String password = selfSignedCertificateModel.getKsPassword();
			String alias = selfSignedCertificateModel.getAlias();
			String fileName = selfSignedCertificateModel.getKsName().trim();
			
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileName+".jks"));
			keyStore.load(in, password.toCharArray());
			keyStore.setCertificateEntry(alias, cert);
			keyStore.setKeyEntry(alias, keyPair.getPrivate(), password.toCharArray(), new Certificate[] {cert});
			keyStore.store(new FileOutputStream(fileName+".jks"), password.toCharArray());
			
			CertificateModel cModel = new CertificateModel();
			cModel.setSerialNum(cert.getSerialNumber().toString());
			cModel.setIssuer(cert.getIssuerDN().toString());
			cModel.setSubject(cert.getSubjectDN().toString());
			cModel.setTypeOfCertificate(cert.getType());
			cModel.setAlgorithm(cert.getSigAlgName());
			cModel.setVersion(cert.getVersion());
			cModel.setExpDate(cert.getNotAfter());
			cModel.setKsFileName(fileName);
			cModel.setStatus(false);
			cModel.setAlias(selfSignedCertificateModel.getAlias());
			cModel.setCA(true);
	
			certificateRepository.save(cModel);		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity(new StateModel("OK"), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/generisiST", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity generisiST(@RequestBody OtherCertificate otherCertificate) {
		try {			
			SubjectData subjectData = generateSubjectData2(otherCertificate);
			
			CertificateModel  certificateModel = new CertificateModel();
			List<CertificateModel> lista = (List<CertificateModel>) certificateRepository.findAll();
			for(CertificateModel cm:lista) {
				if(cm.getAlias().equals(otherCertificate.getIssuer()) && !cm.getStatus()) {
					certificateModel = cm;
				}
			}
			
			KeyStoreReader ksr = new KeyStoreReader();
			IssuerData issuerData = ksr.readIssuerFromStore(certificateModel.getKsFileName()+".jks", certificateModel.getAlias(), otherCertificate.getIssuerPassword().toCharArray(), otherCertificate.getIssuerPassword().toCharArray());
			X509Certificate certIssuer = (X509Certificate)ksr.readCertificate(certificateModel.getKsFileName() + ".jks", otherCertificate.getIssuerPassword(), otherCertificate.getIssuer());
			
			Date today = new Date();
			if(today.after(certIssuer.getNotAfter())) {
				return new ResponseEntity(new StateModel("Sertifikat sa kojim zelite da potpisete je istekao."), HttpStatus.OK);
			}
			
			X509Certificate cert = generateCertificate(subjectData, issuerData);
		
			KeyStore keyStore = KeyStore.getInstance("JKS", "SUN");
			KeyPair keyPair = generateKeyPair();
			
			String password = otherCertificate.getKsPassword();
			String alias = otherCertificate.getAlias();
			String fileName = otherCertificate.getKsName().trim();
			
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileName+".jks"));
			keyStore.load(in, password.toCharArray());
			keyStore.setCertificateEntry(alias, cert);
			keyStore.setKeyEntry(alias, keyPair.getPrivate(), password.toCharArray(), new Certificate[] {cert});
			keyStore.store(new FileOutputStream(fileName+".jks"), password.toCharArray());
			
			CertificateModel cModel = new CertificateModel();
			cModel.setSerialNum(cert.getSerialNumber().toString());
			cModel.setIssuer(cert.getIssuerDN().toString());
			cModel.setSubject(cert.getSubjectDN().toString());
			cModel.setTypeOfCertificate(cert.getType());
			cModel.setAlgorithm(cert.getSigAlgName());
			cModel.setVersion(cert.getVersion());
			cModel.setExpDate(cert.getNotAfter());
			cModel.setKsFileName(fileName);
			cModel.setStatus(false);
			cModel.setAlias(otherCertificate.getAlias());
			cModel.setCA(true); //otherCertificate.isCA()
			
			certificateRepository.save(cModel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity(new StateModel("OK"), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/generisiKK", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity generisiKK(@RequestBody OtherCertificate otherCertificate) {
		try {
			SubjectData subjectData = generateSubjectData2(otherCertificate);
			
			CertificateModel  certificateModel = new CertificateModel();
			List<CertificateModel> lista = (List<CertificateModel>) certificateRepository.findAll();
			for(CertificateModel cm:lista) {
				if(cm.getAlias().equals(otherCertificate.getIssuer()) && !cm.getStatus()) {
					certificateModel = cm;
				}
			}
			
			KeyStoreReader ksr = new KeyStoreReader();
			IssuerData issuerData = ksr.readIssuerFromStore(certificateModel.getKsFileName()+".jks", certificateModel.getAlias(), otherCertificate.getIssuerPassword().toCharArray(), otherCertificate.getIssuerPassword().toCharArray());
			X509Certificate certIssuer = (X509Certificate)ksr.readCertificate(certificateModel.getKsFileName() + ".jks", otherCertificate.getIssuerPassword(), otherCertificate.getIssuer());
			
			Date today = new Date();
			if(today.after(certIssuer.getNotAfter())) {
				return new ResponseEntity(new StateModel("Sertifikat sa kojim zelite da potpisete je istekao."), HttpStatus.OK);
			}
			
			X509Certificate cert = generateCertificate(subjectData, issuerData);
		
			KeyStore keyStore = KeyStore.getInstance("JKS", "SUN");
			KeyPair keyPair = generateKeyPair();
			
			String password = otherCertificate.getKsPassword();
			String alias = otherCertificate.getAlias();
			String fileName = otherCertificate.getKsName().trim();
			
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileName+".jks"));
			keyStore.load(in, password.toCharArray());
			keyStore.setCertificateEntry(alias, cert);
			keyStore.setKeyEntry(alias, keyPair.getPrivate(), password.toCharArray(), new Certificate[] {cert});
			keyStore.store(new FileOutputStream(fileName+".jks"), password.toCharArray());
			
			CertificateModel cModel = new CertificateModel();
			cModel.setSerialNum(cert.getSerialNumber().toString());
			cModel.setIssuer(cert.getIssuerDN().toString());
			cModel.setSubject(cert.getSubjectDN().toString());
			cModel.setTypeOfCertificate(cert.getType());
			cModel.setAlgorithm(cert.getSigAlgName());
			cModel.setVersion(cert.getVersion());
			cModel.setExpDate(cert.getNotAfter());
			cModel.setKsFileName(fileName);
			cModel.setStatus(false);
			cModel.setAlias(otherCertificate.getAlias());
			cModel.setCA(false);
			
			certificateRepository.save(cModel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity(new StateModel("OK"), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/preuzmiSertifikat", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public CertificateModel preuzmiSertifikat(@RequestBody CertificateModel cm) {
		String serialNum = "";
		CertificateModel tmp = null;
		
		try{
			serialNum = cm.getSerialNum().trim();
			if(serialNum == null || serialNum.equals(""))
				return null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {	
			tmp = certificateRepository.findBySerialNum(serialNum);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tmp;
	}
	
	
	@RequestMapping(value = "/povuciSertifikat", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public ResponseEntity povuciSertifikat(@RequestBody CertificateModel cm) {
		String serialNum = "";
		CertificateModel tmp = null;
		
		try{
			serialNum = cm.getSerialNum().trim();
			if(serialNum == null || serialNum.equals(""))
				return null;	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			CertificateModel certificateModel = certificateRepository.findBySerialNum(serialNum);
			certificateModel.setStatus(true);
			certificateRepository.save(certificateModel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity(new StateModel("Sertifikat je povucen."), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/proveraStanja", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public ResponseEntity proveraStanja(@RequestBody CertificateModel cm) {
		String serialNum = "";
		CertificateModel tmp = null;
		
		try{
			serialNum = cm.getSerialNum().trim();
			if(serialNum == null || serialNum.equals(""))
				return null;	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			CertificateModel certificateModel = certificateRepository.findBySerialNum(serialNum);
			
			if(certificateModel.getStatus() == true) {
				return new ResponseEntity(new StateModel("Sertifikat je povucen."), HttpStatus.OK);
			}else {
				return new ResponseEntity(new StateModel("Sertifikat nije povucen."), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity(new StateModel("Uspesno provereno stanje sertifikata."), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/preuzmiPotpisnike", method = RequestMethod.GET, produces="application/json")
	public ArrayList<String> preuzmiPotpisnike(HttpServletRequest request) throws KeyStoreException, NoSuchProviderException, NoSuchAlgorithmException, CertificateException, IOException {
		Date today = new Date();
		ArrayList<String> listaPotpisnika = new ArrayList<>();	
		
		//fali provera kom keystore-u pripada koji potpisnik
		List<CertificateModel> listaSvih = (List<CertificateModel>) certificateRepository.findAll();	
		for(CertificateModel cm:listaSvih) {	
			if(cm.isCA() && !cm.getStatus() && !today.after(cm.getExpDate())) 
				listaPotpisnika.add(cm.getAlias());	
		}	
		
		return listaPotpisnika;
	}
	
	
	//samopotpisani sertifikati
	private SubjectData generateSubjectData(SelfSignedCertificateModel selfSignedCertificateModel) {
		try {
			KeyPair keyPairSubject = generateKeyPair();
			
			//Datumi od kad do kad vazi sertifikat
			SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
			
			Date startDate = new Date();
			Date endDate = new Date(startDate.getTime() + 365 * 24 * 60 * 60 * 1000L);
			
			//Serijski broj sertifikata
			String sn=selfSignedCertificateModel.getSerialNum();
			//klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
			X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		    builder.addRDN(BCStyle.CN, selfSignedCertificateModel.getCommonName());
		    builder.addRDN(BCStyle.O, selfSignedCertificateModel.getOrgName());
		    builder.addRDN(BCStyle.OU, selfSignedCertificateModel.getOrgUnit());
		    builder.addRDN(BCStyle.C, selfSignedCertificateModel.getCountry());
		    builder.addRDN(BCStyle.SERIALNUMBER, selfSignedCertificateModel.getSerialNum());
		    builder.addRDN(BCStyle.E, selfSignedCertificateModel.getEmail());
		    builder.addRDN(BCStyle.PSEUDONYM, selfSignedCertificateModel.getAlias());
		    
		    //Kreiraju se podaci za sertifikat, sto ukljucuje:
		    // - javni kljuc koji se vezuje za sertifikat
		    // - podatke o vlasniku
		    // - serijski broj sertifikata
		    // - od kada do kada vazi sertifikat
		    return new SubjectData(keyPairSubject.getPublic(), builder.build(), sn, startDate, endDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	//sertifikaciona tela i sertifikati za krajnje korisnike
	private SubjectData generateSubjectData2(OtherCertificate otherCertificate) {
		try {
			KeyPair keyPairSubject = generateKeyPair();
			
			//Datumi od kad do kad vazi sertifikat
			SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
			
			Date startDate = new Date();
			Date endDate = new Date(startDate.getTime() + Integer.parseInt(otherCertificate.getValidity()) * 24 * 60 * 60 * 1000L);
			
			//Serijski broj sertifikata
			String sn=otherCertificate.getSerialNum();
			//klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
			X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		    builder.addRDN(BCStyle.CN, otherCertificate.getCommonName());
		    builder.addRDN(BCStyle.O, otherCertificate.getOrgName());
		    builder.addRDN(BCStyle.OU, otherCertificate.getOrgUnit());
		    builder.addRDN(BCStyle.C, otherCertificate.getCountry());
		    builder.addRDN(BCStyle.SERIALNUMBER, otherCertificate.getSerialNum());
		    builder.addRDN(BCStyle.E, otherCertificate.getEmail());
		    builder.addRDN(BCStyle.PSEUDONYM, otherCertificate.getAlias());
		    
		    //Kreiraju se podaci za sertifikat, sto ukljucuje:
		    // - javni kljuc koji se vezuje za sertifikat
		    // - podatke o vlasniku
		    // - serijski broj sertifikata
		    // - od kada do kada vazi sertifikat
		    return new SubjectData(keyPairSubject.getPublic(), builder.build(), sn, startDate, endDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	private IssuerData generateIssuerData(PrivateKey issuerKey, SelfSignedCertificateModel selfSignedCertificateModel) {
		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		builder.addRDN(BCStyle.CN, selfSignedCertificateModel.getCommonName());
		builder.addRDN(BCStyle.O, selfSignedCertificateModel.getOrgName());
		builder.addRDN(BCStyle.OU, selfSignedCertificateModel.getOrgUnit());
		builder.addRDN(BCStyle.C, selfSignedCertificateModel.getCountry());
		builder.addRDN(BCStyle.SERIALNUMBER, selfSignedCertificateModel.getSerialNum());
		builder.addRDN(BCStyle.E, selfSignedCertificateModel.getEmail());
		builder.addRDN(BCStyle.PSEUDONYM, selfSignedCertificateModel.getAlias());
		    
		//Kreiraju se podaci za issuer-a, sto u ovom slucaju ukljucuje:
	    // - privatni kljuc koji ce se koristiti da potpise sertifikat koji se izdaje
	    // - podatke o vlasniku sertifikata koji izdaje nov sertifikat
		return new IssuerData(issuerKey, builder.build());
	}
	
	
	public X509Certificate generateCertificate(SubjectData subjectData, IssuerData issuerData) throws CertIOException {
		try {
			Security.addProvider(new BouncyCastleProvider());
			//Posto klasa za generisanje sertifiakta ne moze da primi direktno privatni kljuc pravi se builder za objekat
			//Ovaj objekat sadrzi privatni kljuc izdavaoca sertifikata i koristiti se za potpisivanje sertifikata
			//Parametar koji se prosledjuje je algoritam koji se koristi za potpisivanje sertifiakta
			JcaContentSignerBuilder builder = new JcaContentSignerBuilder("SHA256WithRSAEncryption");
			//Takodje se navodi koji provider se koristi, u ovom slucaju Bouncy Castle
			builder = builder.setProvider("BC");

			//Formira se objekat koji ce sadrzati privatni kljuc i koji ce se koristiti za potpisivanje sertifikata
			ContentSigner contentSigner = builder.build(issuerData.getPrivateKey());

			GeneralName location = new GeneralName(GeneralName.uniformResourceIdentifier, new DERIA5String("http://www.foo.org/ca.crt"));
			//Postavljaju se podaci za generisanje sertifiakta
			X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(issuerData.getX500name(),
					new BigInteger(subjectData.getSerialNumber()),
					subjectData.getStartDate(),
					subjectData.getEndDate(),
					subjectData.getX500name(),
					subjectData.getPublicKey()).addExtension(new ASN1ObjectIdentifier("1.3.6.1.5.5.7.1.1"), false, new AuthorityInformationAccess(AccessDescription.id_ad_ocsp, location));
					//Generise se sertifikat
			X509CertificateHolder certHolder = certGen.build(contentSigner);

			//Builder generise sertifikat kao objekat klase X509CertificateHolder
			//Nakon toga je potrebno certHolder konvertovati u sertifikat, za sta se koristi certConverter
			JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
			certConverter = certConverter.setProvider("BC");

			//Konvertuje objekat u sertifikat
			return certConverter.getCertificate(certHolder);
		} catch (CertificateEncodingException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (OperatorCreationException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	private KeyPair generateKeyPair() {
        try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); 
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			keyGen.initialize(2048, random);
			return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
        
        return null;
	}
	
}
