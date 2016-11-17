package dw317.hotel.business.interfaces;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.hotel.data.*;

public interface HotelManager extends Serializable {
	
	/**
	 * Cancels a given reservation
	 * @param reservation
	 * @throws NonExistingReservationException
	 */
	void cancelReservation(Reservation reservation)
			throws NonExistingReservationException;

	/**
	 * Saves all data and closes the hotel.
	 * 
	 * @throws IOException
	 *             If there is a problem closing the hotel files.
	 */
	void closeHotel() throws IOException;

	/**
	 * Creates and adds a reservation for a customer.
	 * @param customer The given customer
	 * @param checkin	The requested check in date
	 * @param checkout  The requested check out date
	 * @param roomType  The requested room type
	 * @return The Reservation if possible
	 */
	Optional<Reservation> createReservation(Customer customer, 
			LocalDate checkin, LocalDate checkout, RoomType roomType);

	/**
	 * Finds and returns a customer record.
	 * @param email  The customer's e-mail address
	 * @return Customer object
	 * @throws NonExistingCustomerException
	 *         if the customer with the given e-mail cannot be found
	 */
	Customer findCustomer(String email)
			throws NonExistingCustomerException;
	
	/**
	 * Finds all reservations made by a customer
	 * @param customer
	 * @return List of Reservations. Returns empty list if no 
	 * reservations can be found.
	 */
	List<Reservation> findReservations(Customer customer);
	
	/**
	 * Registers a new Customer
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @return The Customer object
	 * @throws DuplicateCustomerException is a customer with same 
	 * e-mail address exists
	 */
	Customer registerCustomer(String firstName, String lastName, String email)
			throws DuplicateCustomerException;

	/**
	 * Adds or updates the credit card associate with a customer.
	 * @param email The email address of the customer
	 * @param cardType
	 * @param cardnumber
	 * @return the updated Customer
	 * @throws NonExistingCustomerException
	 *         if the customer with the given e-mail cannot be found
	 */
	Customer updateCreditCard(String email, String cardType, String cardnumber)
			throws NonExistingCustomerException;
	
}
