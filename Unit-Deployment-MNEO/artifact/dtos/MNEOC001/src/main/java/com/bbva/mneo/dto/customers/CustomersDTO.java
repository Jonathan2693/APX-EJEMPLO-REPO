package com.bbva.mneo.dto.customers;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The CustomersDTO class...
 */
public class CustomersDTO implements Serializable  {
	private static final long serialVersionUID = 2931699728946643245L;

	/* Attributes section for the DTO */
	private int customerId;
	private String customerName;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	
	
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
		final CustomersDTO rhs = (CustomersDTO) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj))
					.append(customerId, rhs.customerId)
					.append(customerName,rhs.customerName)
					.append(address,rhs.address)
					.append(city, rhs.city)
					.append(state, rhs.state)
					.append(zipCode,rhs.zipCode)
					.isEquals();
	}

	/**
	 * Returns a hash code value for the object.
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(this.customerId)
			.append(this.customerId)
			.append(this.address)
			.append(this.city)
			.append(this.state)
			.append(this.zipCode)
			.toHashCode();
	}

	/**
	 * Returns a string representation of the object.
	 * It is important to OBFUSCATE the attributes that are sensitive to show in the representation.
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("customerId",customerId)
			.append("customerName",customerName)
			.append("address",address)
			.append("city",city)
			.append("state",state)
			.append("zipCode",zipCode)
			.toString();
	}
}
