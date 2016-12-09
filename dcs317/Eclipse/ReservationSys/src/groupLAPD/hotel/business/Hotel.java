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
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.DuplicateReservationException;
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
 * The Hotel class is the concrete class that implements the Hotel
 * Manager interface and it also extends the Observable class.
 * All Hotel objects allow a hotel frontdesk to query the system, 
 * about customers and about reservations. The Hotel class will ensure
 * that all business rules are respected for the particular hotel.
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
	 * The constructor will take as input a HotelFactory Object,
	 * a ReservationDAO Object and a CustomerDAO Object and will
	 * use those input to fill up the class' fields
	 * @param factory - a HotelFactory Object
	 * @param customers -  a CustomerDAO Object
	 * @param reservations - a ReservationDAO Object
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
		//the exception will be thrown from the ReservationListDB class
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
					+ " the hotel files and saving the data.");
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
		DawsonHotelAllocationPolicy policy = 
				new DawsonHotelAllocationPolicy(this.reservations);
		//generate a room available based on checkin checkout and roomtype 
		Optional<Room> randomRoom = policy.getAvailableRoom(checkin,
											checkout, roomType);	
		Reservation custReserv = null;
		//if there is a room available do the following:
		if(randomRoom.isPresent()){
			Room allocatedRoom = randomRoom.get();
			//create reservation
			custReserv = this.factory.getReservationInstance(customer, 
					allocatedRoom, checkin.getYear(), checkin.getMonthValue(), 
					checkin.getDayOfMonth(), checkout.getYear(), 
					checkout.getMonthValue(), checkout.getDayOfMonth());		
			try{
				//add reservation
				this.reservations.add(custReserv);
			}
			catch(DuplicateReservationException e){
				System.out.println(e.getMessage());
			}
			
			setChanged();
			notifyObservers(Optional.of(custReserv));
			
			//return created reservation
			return Optional.of(custReserv);
		}
		//if no room available, return an empty reservation
		return Optional.empty();
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
		
		setChanged();
		notifyObservers(cust);
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
		setChanged();
		notifyObservers(this.reservations.getReservations(customer));
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
		
		setChanged();
		notifyObservers(customer);
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
		
		//if email invalid, an IllegalArgumentException will be thrown with
		//this statement
		Email custEmail = new Email(email);
		//if customer doesn't exist, the following statement will throw
		//NonExistingCustomerException
		Customer customer = this.customers.getCustomer(custEmail);
		
		//if number is invalid, an IllegalArgumentException will be thrown 
		CreditCard custCard = this.factory.getCard(cardType, cardnumber);
		
		//if the the cardType doesn't match mastercard, visa or amex 
		//then the credit card will be set to null
		customer.setCreditCard(Optional.of(custCard));
		
		setChanged();
		notifyObservers(customer);
		return customer;
	}

}

