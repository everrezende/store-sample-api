package br.com.vidadedev.api.storeapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vidadedev.api.storeapi.model.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{

	/**
	 * Busca registros a partir de um email
	 */
	List<Store> findByEmail(String email);
	
}
