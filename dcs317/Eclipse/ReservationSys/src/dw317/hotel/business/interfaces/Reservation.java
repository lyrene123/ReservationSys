package dw317.hotel.business.interfaces;
import java.io.Serializable;

public interface Reservation extends Comparable<Reservation>, Serializable {

	public Customer getCustomer();
	public Room getRoom();
	public LocalDate getCheckInDate();
	public LocalDate getCheckOutDate();
	public int getNumberDays();
}
