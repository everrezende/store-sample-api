package br.com.vidadedev.api.storeapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vidadedev.api.storeapi.model.Store;
import br.com.vidadedev.api.storeapi.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StoreService implements IStoreService{

	@Autowired
	private StoreRepository storeRepository;
	
	@Override
	public Store saveStore(Store s) {
		List<Store> existsStore = storeRepository.findByEmail(s.getEmail());
		
		if(!existsStore.isEmpty()) {
			log.info("Email {} já cadastrado", s.getEmail());
			return null;
		}
		
		return storeRepository.save(s);
	}

	@Override
	public List<Store> getAllStores() {
		return storeRepository.findAll();
	}

	@Override
	public Store getStoreById(Long id) {
		return storeRepository.findById(id).orElse(null);
	}

	@Override
	public Store updateStore(Store s) {
		return storeRepository.save(s);
	}

	@Override
	public void deleteStore(Long id) {
		storeRepository.deleteById(id);
	}

}
