package dw317.hotel.data.interfaces;   

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.DuplicateReservationException;

public interface ReservationDAO {
	/**
	 * Adds a reservation to the database. If the reservation overlaps 
        * with an existing reservation, a DuplicationReservationException
        * is thrown. Otherwise, the reservation is added based on room number
	 * and the check-in date.
	 * 
	 * @param  reserv
	 *            The reservation to add to the database.
	 * @throws DuplicateReservationException
	 */
	void add(Reservation reserv)
			throws DuplicateReservationException;

	
	/**
	 * Saves the reservations and disconnects from the database. 
	 * 
	 * @throws IOException
	 *            Problems saving or disconnecting from database.
	 */
	 void disconnect()throws IOException;
	
	 /**
	  * Returns the reservations for a given customer. 
	  * 
  	  * @param  cust  The given customer.
	  * 
	  * @return  The reservations for the customer (empty list if none).
	  */
	 List<Reservation> getReservations(Customer cust);

	/**
	 * Cancels a reservation.
	 * @param reserv The given reservation
	 * @throws NonExistingReservationException
	 *          Could not find the reservation
	 */
	void cancel(Reservation reserv) throws NonExistingReservationException;

	/**
	 * Gets a list of all rooms reserved at some point between the supplied
	 * check-in and check-out dates. Note that rooms are reserved for any 
	 * overlapping period, not necessarily the entirety of the period
	 * 
	 * @param checkin Start date of period
	 * @param checkout End date of the period
	 * @return list of all occupied rooms. Empty list is returned if
	 *         no rooms are occupied
	 */
	List<Room> getReservedRooms(LocalDate checkin, LocalDate checkout);
	
	/**
	 * Gets a list of all rooms NOT reserved at some point between the supplied
	 * check-in and check-out dates. Note that only rooms that are not reserved 
	 * for any period between the given dates are returned
	 * 
	 * @param checkin Start date of period
	 * @param checkout End date of the period
	 * @return list of all unoccupied rooms. Empty list is returned if
	 *         no rooms are unoccupied
	 */
	List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout);
	
	/**
	 * Gets a list of all rooms of given room type NOT reserved between the supplied
	 * check-in and check-out dates. Note that only rooms that are not reserved 
	 * for any period between the given dates are returned
	 * 
	 * @param checkin Start date of period
	 * @param checkout End date of the period
	 * @param roomType The type of room requested
	 * @return list of all unoccupied rooms. Empty list is returned if
	 *         no rooms are unoccupied
	 */
	List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout, RoomType roomType);
	
	/**
	 * Removes all reservations with the checkout date prior to the current date.
	 */
	void clearAllPast();

}
