package com.ftn.repository;

import org.springframework.data.repository.CrudRepository;

import com.ftn.model.KeyStoreModel;

public interface KeyStoreRepository extends CrudRepository<KeyStoreModel, Integer> {

	KeyStoreModel findByKsFileName(String ksFileName);
	
}
