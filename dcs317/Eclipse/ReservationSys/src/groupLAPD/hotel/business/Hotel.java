/**
 * 
 */
package groupLAPD.hotel.business;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.HotelManager;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.NonExistingCustomerException;
import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ReservationDAO;
import dw317.hotel.data.interfaces.RoomDAO;
import dw317.lib.Email;
import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
import dw317.lib.creditcard.MasterCard;
import dw317.lib.creditcard.Visa;
import groupLAPD.hotel.data.ReservationListDB;

/**
 * 
 * @author Lyrene Labor
 * @version November 2016
 *
 */
public class Hotel extends java.util.Observable 
							implements HotelManager {
	
	private final HotelFactory factory; 
	private final CustomerDAO customers; 
	private final ReservationDAO reservations; 
	private static final long serialVersionUID = 42031768871L; 
	
	
	/**
	 * 
	 * @param factory
	 * @param customers
	 * @param reservations
	 */
	public Hotel(HotelFactory factory, 
			CustomerDAO customers,ReservationDAO reservations){
		this.factory = factory;
		this.customers = customers;
		this.reservations = reservations;
	}
	
	
	/**
	 * The cancelReservation method will cancel a given reservation
	 * from the reservation database of the Hotel
	 * @param reservation - A provided reservation object to cancel
	 * @throws NonExistingReservationException
	 * 			If the given reservation does not exist in the 
	 * 			reservation database of the hotel
	 */
	@Override
	public void cancelReservation(Reservation reservation) 
			throws NonExistingReservationException {
		this.reservations.cancel(reservation);		
	}

	
	/**
	 * The closeHotel method will save all data and 
	 * closes the hotel.
	 * @throws IOException
	 *         If there is a problem closing the hotel files.
	 */
	@Override
	public void closeHotel() throws IOException {
		try{
			this.reservations.disconnect();
			this.customers.disconnect();
		}catch(IOException e){
			throw new IOException("There is a problem closing"
					+ " the hotel files adn saving the data.");
		}
	}

	
	/**
	 * The createReservation method will create and add
	 * a reservation for a customer.
	 * @param customer The given customer
	 * @param checkin	The requested check in date
	 * @param checkout  The requested check out date
	 * @param roomType  The requested room type
	 * @return The Reservation if possible
	 */
	@Override
	public Optional<Reservation> createReservation
	(Customer customer, LocalDate checkin, LocalDate checkout,
			RoomType roomType) {
		
		
		return null;
	}

	
	/**
	 * The findCustomer method will find and return a customer record.
	 * @param email  The customer's e-mail address
	 * @return Customer object
	 * @throws NonExistingCustomerException
	 *         if the customer with the given e-mail cannot be found
	 */
	@Override
	public Customer findCustomer(String email) 
			throws NonExistingCustomerException {
		Email custEmail = new Email(email);
		Customer cust;
		try {
			cust = this.customers.getCustomer(custEmail);
		}catch(NonExistingCustomerException e){
			throw new NonExistingCustomerException();
		}		
		return cust;	
	}

	
	/**
	 * The findReservations method will find all reservations 
	 * made by a customer
	 * @param customer - the customer we want to find reservations from
	 * @return List of Reservations. Returns empty list if no 
	 * reservations can be found.
	 */
	@Override
	public List<Reservation> findReservations(Customer customer) {
		return this.reservations.getReservations(customer);
	}

	
	/**
	 * The registerCustomer method will register or add
	 * a new Customer to the customer database from the hotel
	 * @param firstName - String containing first name of customer
	 * @param lastName - String containing last name of customer
	 * @param email - String containing customer email
	 * @return The Customer object created
	 * @throws DuplicateCustomerException is a customer with same 
	 * e-mail address exists
	 */
	@Override
	public Customer registerCustomer(String firstName, String lastName,
			String email)throws DuplicateCustomerException {
		Customer customer = this.factory.getCustomerInstance
								(firstName, lastName, email);
		try{
			this.customers.add(customer);
		}catch(DuplicateCustomerException e){
			throw new DuplicateCustomerException();
		}
		return customer;
	}

	
	/**
	 * The updateCreditCard method will add or update
	 * the credit card associate with a customer.
	 * @param email The email address of the customer
	 * @param cardType The card type of the customer credit card
	 * @param cardnumber The credit card number of the customer
	 * @return the updated Customer
	 * @throws NonExistingCustomerException
	 *         if the customer with the given e-mail cannot be found
	 */
	@Override
	public Customer updateCreditCard(String email, String cardType,
			String cardnumber)throws NonExistingCustomerException {
		
		Email custEmail = new Email(email);
		Customer customer = this.customers.getCustomer(custEmail);
		//if number is invalid, an IllegalArgumentException will be thrown 
		CreditCard custCard = this.factory.getCard(cardType, cardnumber);
		
		//if the the cardType doesn't match mastercard, visa or amex 
		//then the credit card will be set to null
		customer.setCreditCard(Optional.of(custCard));
		return customer;
	}

}

