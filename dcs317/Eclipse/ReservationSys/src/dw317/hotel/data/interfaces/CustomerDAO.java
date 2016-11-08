package dw317.hotel.data.interfaces;

import java.io.IOException;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.NonExistingCustomerException;
import dw317.lib.Email;
import dw317.lib.creditcard.CreditCard;

public interface CustomerDAO {

	/**
	 * Adds a customer to the database. The customer is added in
	 * Email order as to keep the database in sorted order.
	 * Only one customer can be inserted for an email address.
	 * 
	 * @param  cust
	 *            The customer to add to the database.
	 * @throws DuplicateCustomerException;
	 *            The requested email already exists. 
	 */
	void add(Customer cust) 
			throws DuplicateCustomerException;

	/**
	 * Saves the list of customers and disconnects from the database. 
	 * 
	 * @throws IOException
	 *            Problems saving or disconnecting from database.
	 */
	void disconnect()throws IOException;

	/**			
	 * Returns the customer with the specified email address. 
	 * 
	 * @param  email The email of the requested customer.
	 *
	 * @return 	The customer with the specified email.
	 *
	 * @throws  NonExistingCustomerException If there is no customer
	 *			with the specified email. 
	 */
	Customer getCustomer(Email email) 
			throws NonExistingCustomerException;

	/**
	 * Modifies a customer's credit card. 
	 * 
	 * @param email
	 *            The email of the customer to be updated
	 * @param card
	 *            The new credit card
	 * @throws NonExistingCustomerException
	 *            The customer is not in database.
	 */
	void update (Email email, CreditCard card)
			throws NonExistingCustomerException;
}

