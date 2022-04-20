package com.bbva.mneo;

import com.bbva.elara.transaction.AbstractTransaction;
import com.bbva.mneo.dto.customers.CustomersDTO;

/**
 * In this class, the input and output data is defined automatically through the setters and getters.
 */
public abstract class AbstractMNEOT00101MXTransaction extends AbstractTransaction {

	public AbstractMNEOT00101MXTransaction(){
	}


	/**
	 * Return value for input parameter entrada
	 */
	protected String getEntrada(){
		return (String)this.getParameter("entrada");
	}

	/**
	 * Return value for input parameter customersIn
	 */
	protected CustomersDTO getCustomersin(){
		return (CustomersDTO)this.getParameter("customersIn");
	}

	/**
	 * Set value for CustomersDTO output parameter customersOut
	 */
	protected void setCustomersout(final CustomersDTO field){
		this.addParameter("customersOut", field);
	}
}
