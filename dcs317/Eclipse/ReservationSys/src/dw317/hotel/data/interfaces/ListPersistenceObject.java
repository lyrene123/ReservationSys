package dw317.hotel.data.interfaces;

import java.io.IOException;
import java.util.List;

import dw317.hotel.business.interfaces.*;

public interface ListPersistenceObject {
	/**
	 * Reads in the list of rooms from disk
	 * @return a List of Room objects
	 */
	List<Room> getRoomDatabase();
	
	/**
	 * Reads in a list of Customers from disk
	 * @return a List of Customers
	 */
	List<Customer> getCustomerDatabase();
	
	/**
	 * Reads in a list of Reservations from disk
	 * @return a List of Reservations
	 */
	List<Reservation> getReservationDatabase();
	
	/**
	 * Saves a List of Customers to disk
	 * @param custs List of Customers to save
	 * @throws IOException
	 */
	void saveCustomerDatabase(List <Customer> custs) throws IOException;
	
	/**
	 * Saves a List of Reservations to disk
	 * @param reservs List of Reservations to save
	 * @throws IOException
	 */
	void saveReservationDatabase(List<Reservation> reservs)
			throws IOException;

}
