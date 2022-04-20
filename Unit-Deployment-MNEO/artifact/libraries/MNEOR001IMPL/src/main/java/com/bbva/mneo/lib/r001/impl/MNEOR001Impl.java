package com.bbva.mneo.lib.r001.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.apx.exception.db.DuplicateKeyException;
import com.bbva.apx.exception.db.NoResultException;
import com.bbva.apx.exception.db.TimeoutException;
import com.bbva.mneo.dto.customers.CustomersDTO;

/**
 * The MNEOR001Impl class...
 */
public class MNEOR001Impl extends MNEOR001Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(MNEOR001Impl.class);

	@Override
	public CustomersDTO executeInsert(CustomersDTO customer) {
		LOGGER.info("Entrando al metodo executeInsert");
		
		int result = 0;
		
		try {
			
			result = this.jdbcUtils.update("insert", customer.getCustomerId(),customer.getCustomerName(),customer.getAddress(),
											customer.getCity(),customer.getState(),customer.getZipCode());
		}
		catch(DuplicateKeyException e) {
			
			LOGGER.error("Ocurrio un problema, se duplico la key en la tabla customers");
			addAdvice("MNEO01317004");
		}
		
		catch(TimeoutException e) {
			
			LOGGER.error("Ocurrio un problema, se excedio el tiempo para insertar en la tabla customers");
			addAdvice("MNEO01317005");
			
		}
		LOGGER.info("El resultado del insert es {}",result);
		return customer;
	}

	@Override
	public CustomersDTO executeUpdate(CustomersDTO customer) {
		LOGGER.info("Entrando al metodo executeInsert");
		
		int result = 0;
		
		try {
			
			Map<String,Object> parametros = new HashMap<>();
			
			parametros.put("id", customer.getCustomerId());
			parametros.put("customerName", customer.getCustomerName());
			parametros.put("address", customer.getAddress());
			parametros.put("city",customer.getCity());
			parametros.put("state",customer.getState());
			parametros.put("zipCode",customer.getZipCode());
			
			result = this.jdbcUtils.update("update", parametros);
			
			/*result = this.jdbcUtils.update("update", customer.getCustomerId(),customer.getCustomerName(),customer.getAddress(),
											customer.getCity(),customer.getState(),customer.getZipCode());*/
		}
		catch(DuplicateKeyException e) {
			
			LOGGER.error("Ocurrio un problema, se duplico la key en la tabla customers");
			addAdvice("MNEO01317004");
		}
		
		catch(TimeoutException e) {
			
			LOGGER.error("Ocurrio un problema, se excedio el tiempo para insertar en la tabla customers");
			addAdvice("MNEO01317005");
			
		}
		LOGGER.info("El resultado del update es {}",result);
		return customer;
	}

	@Override
	public void executeDeleteById(CustomersDTO customer) {
		LOGGER.info("Entrando al metodo delete");
		
		int result = 0;
		
		try {
			Map<String,Object> parametros = new HashMap<>();
			parametros.put("id", customer.getCustomerId());
			result = this.jdbcUtils.update("delete", parametros);
		}
		catch(TimeoutException e) {
			
			LOGGER.error("Ocurrio un problema en la eliminación del elemento por Id");
			addAdvice("MNEO01317004");
		}

		LOGGER.info("El resultado de delete es {}",result);
	}

	@Override
	public CustomersDTO executeGetByName(CustomersDTO customer) {
		LOGGER.info("Entrando al metodo getByName");
		CustomersDTO clienteProvisional = new CustomersDTO();
		
		try {
			Map<String,Object> parametros = new HashMap<>();
			parametros.put("name", customer.getCustomerName());
			List<Map<String,Object>> lista = new ArrayList<Map<String,Object>>();
			lista = this.jdbcUtils.queryForList("getName", parametros);
			if(lista.size()>1) {
				LOGGER.info("Buscando registro en la lista");
				
				for(Map<String,Object> mapa : lista) {
					if(customer.getCustomerName().equals(mapa.get("CUSTOMER_NAME").toString())) {
						clienteProvisional.setCustomerId(Integer.parseInt(mapa.get("CUSTOMER_ID").toString()));
						clienteProvisional.setCustomerName(mapa.get("CUSTOMER_NAME").toString());
						clienteProvisional.setAddress(mapa.get("ADDRESS").toString());
						clienteProvisional.setCity(mapa.get("CITY").toString());
						clienteProvisional.setState(mapa.get("STATE").toString());
						clienteProvisional.setZipCode(mapa.get("ZIP_CODE").toString());
						break;
					}
				}
				
				LOGGER.info("Saliendo de buscar registro por nombre");
			}
			else {
				
				LOGGER.info("Entrando a unico registro de la lista");
				
				clienteProvisional.setCustomerId(Integer.parseInt(lista.get(0).get("CUSTOMER_ID").toString()));
				clienteProvisional.setCustomerName(lista.get(0).get("CUSTOMER_NAME").toString());
				clienteProvisional.setAddress(lista.get(0).get("ADDRESS").toString());
				clienteProvisional.setCity(lista.get(0).get("CITY").toString());
				clienteProvisional.setState(lista.get(0).get("STATE").toString());
				clienteProvisional.setZipCode(lista.get(0).get("ZIP_CODE").toString());
				
				LOGGER.info("Saliendo de guardar unico registro");
			}
		}
		catch(NoResultException e) {
			
			LOGGER.error("No existió el registro en la Base de datos");
			addAdvice("MNEO01317008");
		}
		
		LOGGER.info("Saliendo del metodo executeGetById");
		return clienteProvisional;
	}
}
