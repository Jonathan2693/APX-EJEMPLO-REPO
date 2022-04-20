package com.bbva.mneo;

import com.bbva.elara.domain.transaction.Advice;
import com.bbva.elara.domain.transaction.Severity;
import com.bbva.mneo.dto.customers.CustomersDTO;
import com.bbva.mneo.lib.r001.MNEOR001;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TRX para CRUD de customers
 *
 */
public class MNEOT00101MXTransaction extends AbstractMNEOT00101MXTransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(MNEOT00101MXTransaction.class);

	/**
	 * The execute method...
	 */
	@Override
	public void execute() {
		MNEOR001 mneoR001 = this.getServiceLibrary(MNEOR001.class);
		// TODO - Implementation of business logic
		String entrada = this.getEntrada();
		if("0".equals(entrada)) {
			LOGGER.info("Entrando a la operacion insertar");
			
			CustomersDTO customers = mneoR001.executeInsert(getCustomersin());
			setCustomersout(customers);
			
			LOGGER.info("Saliendo de operacion insertar");
		}
		else if ("1".equals(entrada)) {
			LOGGER.info("Entrando a la operacion actualizar");
			
			CustomersDTO customers = mneoR001.executeUpdate(getCustomersin());
			setCustomersout(customers);
			
			LOGGER.info("Saliendo de operacion actualizar");
		}
		else if("2".equals(entrada)) {
			LOGGER.info("Entrando a la operacion delete");
			
			mneoR001.executeDeleteById(getCustomersin());
			
			LOGGER.info("Saliendo de operacion delete");
		}
		else if("3".equals(entrada)) {
			LOGGER.info("Entrando a la operacion getByName");
			
			CustomersDTO customer = mneoR001.executeGetByName(getCustomersin());
			setCustomersout(customer);
			
			LOGGER.info("Saliendo de operacion getByName");
		}
		
		
		Advice advice = getAdvice();
		if(advice != null && "MNEO01317007".equals(advice.getCode())) {
			setSeverity(Severity.EWR);
		} else {
			setSeverity(Severity.OK);
			LOGGER.info("La operacion termino de manera exitosa");
		}
	}

}
