package dw317.hotel.business.interfaces;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * The Reservation interface contains all the behaviors
 * that any Reservation class should Implement.
 * @author Ali Dali
 **/

public interface Reservation extends Comparable<Reservation>, Serializable {

	public Customer getCustomer();//gets customer
	public Room getRoom();//gets room
	public LocalDate getCheckInDate();//gets checkIn Date
	public LocalDate getCheckOutDate();//gets CheckOut Date
	public int getNumberOfDays();//get the number of days till checkOut Date
	public boolean overlap(Reservation other);//Checks if 2 Reservation dates
											  //overlap
}
