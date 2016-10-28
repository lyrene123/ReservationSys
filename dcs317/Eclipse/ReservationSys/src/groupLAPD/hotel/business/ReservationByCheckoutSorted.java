package groupLAPD.hotel.business;

import java.util.Comparator;
import dw317.hotel.business.interfaces.Reservation;

/**
 * ReservationByCheckoutSorted implements the Comparator<Reservation> interface to
 * enable sorting of Reservations on checkout date. The Comparator
 * interface defines the compare method.
 * 
 * @author Pengkim Sy
 * @version 28/10/2016
 */
public class ReservationByCheckoutSorted implements Comparator<Reservation>{

	/**
	 * This method is to compare the first reservation with the second reservation
	 * base on their check out date. If two reservations have the same checkout date,
	 *  order them by the default order (i.e., compareTo).
	 * 
	 * @param the first Reservation
	 * @param the second Reservation
	 */
	@Override
	public int compare(Reservation r1, Reservation r2) {
		
		if(r1.equals(r2))
			return 0;
		
		if(!r1.getCheckOutDate().equals(r2.getCheckOutDate()))
			return r1.getCheckOutDate().compareTo(r2.getCheckOutDate());
		
		return r1.compareTo(r2);
	}

}
