package com.bbva.mneo.lib.r001;

import com.bbva.mneo.dto.customers.CustomersDTO;

/**
 * The  interface MNEOR001 class...
 */
public interface MNEOR001 {

	/**
	 * The execute method...
	 */
	public CustomersDTO executeInsert(CustomersDTO customer);
	public CustomersDTO executeUpdate(CustomersDTO customer);
	public void executeDeleteById(CustomersDTO customer);
	public CustomersDTO executeGetByName(CustomersDTO customer);

}
