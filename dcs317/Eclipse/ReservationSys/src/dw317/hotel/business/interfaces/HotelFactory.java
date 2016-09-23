/**
 * 
 */
package dw317.hotel.business.interfaces;
import java.io.Serializable;
import dw317.lib.creditcard.CreditCard;

/**
 * The HotelFactory class is an interface class containing abstract methods that define
 * the behavior of classes related to the Hotel Business. When a class implements this interface,
 * it has to provide all the interface's abstract methods. This class extends to  
 * Serializable interface
 * @author provided by teacher
 * @version September 2016
 */
public interface HotelFactory extends Serializable 
{
	//abstract methods
	Customer getCustomerInstance(String firstName, String lastName, String email);
	CreditCard getCard(String cardtype, String number);
	Room getRoomInstance(int roomNumber, String roomtype);
	Reservation getReservationInstance(Customer aCustomer,
			Room aRoom, int inYear, int inMonth, int inDay,
			int outYear, int outMonth, int outDay);
}


