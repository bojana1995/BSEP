package com.ftn.repository;

import org.springframework.data.repository.CrudRepository;

import com.ftn.model.CertificateModel;

public interface CertificateRepository extends CrudRepository<CertificateModel, Integer> {

	CertificateModel findBySerialNum(String serialNum);
	
}