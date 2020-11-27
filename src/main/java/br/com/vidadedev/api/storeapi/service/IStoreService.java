package br.com.vidadedev.api.storeapi.service;

import java.util.List;

import br.com.vidadedev.api.storeapi.model.Store;

public interface IStoreService {

	/**
	 * Busca um Store a partir de um ID
	 */
	public Store getStoreById(Long id);
	
	/**
	 * Busca todos os registros no banco de dados
	 * @return
	 */
	public List<Store> getAllStores();
	
	/**
	 * Cria um registro no banco de dados
	 */
	public Store saveStore(Store s);
	
	/**
	 * Atualiza um registro no banco de dados
	 */
	public Store updateStore(Store s);
	
	/**
	 * Deleta um registro do banco de dados
	 */
	public void deleteStore(Long id);
	
}
