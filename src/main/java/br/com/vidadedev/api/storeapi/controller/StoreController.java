package br.com.vidadedev.api.storeapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.vidadedev.api.storeapi.dto.StoreDto;
import br.com.vidadedev.api.storeapi.model.Store;
import br.com.vidadedev.api.storeapi.repository.StoreRepository;
import br.com.vidadedev.api.storeapi.service.IStoreService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("stores")
public class StoreController {
	
	@Autowired
	private IStoreService storeService;
	
	/**
	 * Busca todos os stores cadastrados no banco de dados
	 */
	@GetMapping
	@ApiOperation(value = "Busca todos os locais cadastrados no banco de dados")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Busca realizada com sucesso", response= Store.class, responseContainer = "List")
	})
	public ResponseEntity getAllStores() {
		return new ResponseEntity(storeService.getAllStores(), HttpStatus.OK);
	}
	
	/**
	 * Busca um local especifico pelo ID
	 */
	@GetMapping("/{id}")
	public ResponseEntity getStoreById(@PathVariable("id") Long id) {
		Store store = storeService.getStoreById(id);
		
		if(store == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity(store, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity createStore(@RequestBody StoreDto dto) {
		Store store = new Store(null, dto.getName(), dto.getEmail(), dto.getPhone());
		Store newStore = storeService.saveStore(store);
		
		if(newStore == null) {
			return new ResponseEntity(HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity(newStore, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza os dados de um store especifico a partir de um id")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Registro atualizado com sucesso", response= Store.class),
		@ApiResponse(code = 409, message = "Endereço de email já cadastrado"),
		@ApiResponse(code = 422, message = "ID do registro não encontrado")
	})
	public ResponseEntity updateStore(@PathVariable("id") Long id, @RequestBody StoreDto dto) {
		if(storeService.getStoreById(id) == null) {
			return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		Store store = new Store(id, dto.getName(), dto.getEmail(), dto.getPhone());
		Store newStore = storeService.updateStore(store);
		
		if(newStore == null) {
			return new ResponseEntity(HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity(newStore, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteStore(@PathVariable("id") Long id) {
		if(storeService.getStoreById(id) == null) {
			return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		storeService.deleteStore(id);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
}
