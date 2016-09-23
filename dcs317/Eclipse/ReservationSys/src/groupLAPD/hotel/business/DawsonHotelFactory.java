/**
 * 
 */
package groupLAPD.hotel.business;
import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.lib.creditcard.CreditCard;


/**
 * The DawsonHotelFactory enum is a mechanism to ensure that only one DawsonHotelFactory 
 * object is ever created (i.e., the Singleton design pattern). 
 * DawsonHotelFactory is used when you need instances of DawsonCustomers, 
 * Credit Cards, DawsonRooms, or DawsonReservations.
 * @author provided by the teacher
 * @version September 2016
 */
public enum DawsonHotelFactory implements HotelFactory {
	DAWSON;
	@Override
	public Customer getCustomerInstance(String firstName, String lastName, String email) {
		return new DawsonCustomer(firstName, lastName, email);
	}
	@Override
	public CreditCard getCard(String cardtype, String number) {
		return CreditCard.getInstance(
				CreditCard.CardType.valueOf(cardtype.toUpperCase()), number);
	}
	@Override
	public Room getRoomInstance(int roomNumber, String roomtype) {
		return new DawsonRoom(roomNumber,
				RoomType.valueOf(roomtype.toUpperCase()));
	}
	@Override
	public Reservation getReservationInstance(Customer aCustomer, Room aRoom, int inYear, int inMonth, int inDay,
			int outYear, int outMonth, int outDay) {
		return new DawsonReservation(aCustomer, aRoom, inYear, inMonth, inDay, outYear, outMonth, outDay);
	}
}

