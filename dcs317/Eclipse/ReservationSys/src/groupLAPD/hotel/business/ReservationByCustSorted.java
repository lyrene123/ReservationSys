package groupLAPD.hotel.business;

import java.util.Comparator;
import dw317.hotel.business.interfaces.Reservation;

/**
 * ReservationByCustSorted implements the Comparator<Reservation> interface to 
 * enable sorting of Reservations on customer and checkin date. The Comparator
 * interface defines the compare method.
 * 
 * @author Pengkim Sy
 * @version 28/10/2016
 */
public class ReservationByCustSorted implements Comparator<Reservation>{
	
	/**
	 * This method is to compare the first reservation with the second reservation
	 * base on customer. If same customer, order by check in (i.e., default).
	 * 
	 * @param the first Reservation
	 * @param the second Reservation
	 */
	@Override
	public int compare(Reservation r1, Reservation r2) {
		
		if(r1.equals(r2))
			return 0;
		
		if(!r1.getCustomer().equals(r2.getCustomer()))
			return r1.getCustomer().compareTo(r2.getCustomer());
		
		return r1.compareTo(r2);
	}
}
