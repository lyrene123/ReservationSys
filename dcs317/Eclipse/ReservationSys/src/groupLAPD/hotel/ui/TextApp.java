package groupLAPD.hotel.ui;

import dw317.hotel.business.interfaces.*;
import dw317.hotel.data.interfaces.*;
import groupLAPD.hotel.business.*;
import groupLAPD.hotel.data.*;

/**
 * TextApp class is the application of the whole Dawson
 * Reservation System project which implements a model, a view
 * and a controller in order to display all required information
 * to a user and to respond to any type of queries of the user
 * @author provided by the teacher
 *
 */
public class TextApp {
	public static void main(String[] args) {
		HotelFactory factory = 
				DawsonHotelFactory.DAWSON;
		CustomerDAO customerDb =
				new CustomerListDB(new ObjectSerializedList
						("datafiles/database/rooms.ser" ,
							"datafiles/database/customers.ser",
							"datafiles/database/reservations.ser"));
		ReservationDAO reservationDb = 
				new ReservationListDB(new ObjectSerializedList
						("datafiles/database/rooms.ser" ,
							"datafiles/database/customers.ser",
							"datafiles/database/reservations.ser"));

		Hotel model = new Hotel(factory, customerDb, reservationDb);
		TextView view = new TextView(model);
		TextController controller = new TextController(model);
		controller.run();
	}
}
